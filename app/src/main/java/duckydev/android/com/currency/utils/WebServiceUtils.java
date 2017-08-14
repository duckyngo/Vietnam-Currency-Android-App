package duckydev.android.com.currency.utils;

import android.content.Context;
import android.net.ConnectivityManager;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParserException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import duckydev.android.com.currency.Constains;
import duckydev.android.com.currency.object.Currency;
import duckydev.android.com.currency.object.GoldPrice;

/**
 * Created by DK on 8/11/2017.
 */

public class WebServiceUtils {
    public static final String TAG = WebServiceUtils.class.getName();

    public static ArrayList<Currency> getCurrencyList(String urlString) throws IOException, XmlPullParserException {

        InputStream inputStream = downloadUrl(urlString);
        try {

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(inputStream);
            ArrayList<Currency> currencyList = new ArrayList<>();
            Element element = document.getDocumentElement();
            element.normalize();

            String s = document.toString();
            NodeList nList = document.getElementsByTagName(Constains.XML_EXRATE);

            currencyList.add(new Currency(Currency.SECTION, "Mã tiền tệ", "", "Mua", "Chuyển khoản", "Bán"));
            for (int i = 0; i < nList.getLength(); i++) {
                Currency currency = new Currency(Currency.ITEM);
                Node node = nList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element2 = (Element) node;
                    currency.setCurrencyCode(element2.getAttribute(Constains.XML_CURRENCY_CODE));
                    currency.setCurrencyName(element2.getAttribute(Constains.XML_CURRENCY_NAME));
                    currency.setBuy(element2.getAttribute(Constains.XML_BUY));
                    currency.setTransfer(element2.getAttribute(Constains.XML_TRANSFER));
                    currency.setSell(element2.getAttribute(Constains.XML_SELL));
                }
                currencyList.add(currency);
            }

            return currencyList;
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<GoldPrice> getGoldPriceList(String urlString) throws IOException, XmlPullParserException {


        InputStream inputStream = downloadUrl(urlString);
        try {

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(inputStream);

            String s = document.toString();
            ArrayList<GoldPrice> goldPricesList = new ArrayList<>();
            Element element = document.getDocumentElement();
            element.normalize();

//            NodeList rateList = document.getElementsByTagName("ratelist");
            NodeList nCityList = document.getElementsByTagName(Constains.XML_GOLD_CITY);

            for (int i = 0; i < nCityList.getLength(); i++) {
                Node node = nCityList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element2 = (Element) node;
                    NodeList itemList = element2.getElementsByTagName("item");
                    GoldPrice sectionGoldPrice = new GoldPrice(GoldPrice.SECTION,"",element2.getAttribute("name"),"", "");
                    goldPricesList.add(sectionGoldPrice);
                    for (int j = 0; j < itemList.getLength(); j++) {
                        GoldPrice goldPrice = new GoldPrice(GoldPrice.ITEM);
                        goldPrice.setCityName(element2.getAttribute("name"));
                        if (node.getNodeType() == Node.ELEMENT_NODE) {
                            Element element3 = (Element) itemList.item(j);
                            goldPrice.setGoldType(element3.getAttribute(Constains.XML_GOLD_TYPE));
                            goldPrice.setBuy(element3.getAttribute(Constains.XML_GOLD_BUY));
                            goldPrice.setSell(element3.getAttribute(Constains.XML_GOLD_SELL));
                        }
                        goldPricesList.add(goldPrice);
                    }

                }
            }

            return goldPricesList;
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return null;
    }





    private static InputStream downloadUrl(String urlString) {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(Constains.READ_TIMEOUT);
            connection.setConnectTimeout(Constains.CONNECTION_TIMEOUT);
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            // Starts the query
            connection.connect();
            return connection.getInputStream();
        } catch (ProtocolException e) {
            LogUtils.log(TAG, e.getMessage());
        } catch (MalformedURLException e) {
            LogUtils.log(TAG, e.getMessage());
        } catch (IOException e) {
            LogUtils.log(TAG, e.getMessage());
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return null;
    }

//    public static String requestStringBody(String serviceURL) {
//        HttpURLConnection urlConnection = null;
//
//        try {
//            URL urlToRequest = new URL(serviceURL);
//            urlConnection = (HttpURLConnection) urlToRequest.openConnection();
//            urlConnection.setConnectTimeout(Constains.CONNECTION_TIMEOUT);
//            urlConnection.setReadTimeout(Constains.READ_TIMEOUT);
//
//            int statusCode = urlConnection.getResponseCode();
//            if (statusCode == HttpURLConnection.HTTP_UNAUTHORIZED) {
//                LogUtils.log(TAG, "Unauthorized access!");
//            } else if (statusCode == HttpURLConnection.HTTP_NOT_FOUND) {
//                LogUtils.log(TAG, "404 page not found");
//            } else if (statusCode == HttpURLConnection.HTTP_OK) {
//                LogUtils.log(TAG, "URL Response error");
//            }
//
//            InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
//            return convertInputStreamToString(inputStream);
//        } catch (MalformedURLException e) {
//            LogUtils.log(TAG, e.getMessage());
//        } catch (IOException e) {
//            LogUtils.log(TAG, e.getMessage());
//        }finally {
//            if (urlConnection != null) {
//                urlConnection.disconnect();
//            }
//        }
//
//        return null;
//    }
//
//    private static String convertInputStreamToString(InputStream inputStream) {
//        StringBuilder stringBuilder = new StringBuilder();
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//        String respnseText;
//        try {
//            while ((respnseText = bufferedReader.readLine()) != null) {
//                stringBuilder.append(respnseText);
//            }
//        } catch (IOException e) {
//            LogUtils.log(TAG, e.getMessage());
//        }
//        String s = stringBuilder.toString();
//        return stringBuilder.toString();
//    }

    public static boolean hasInternetConnection(Context context) {
        ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));

        return connectivityManager != null &&
                connectivityManager.getActiveNetworkInfo() != null &&
                connectivityManager.getActiveNetworkInfo().isConnected();
    }


}
