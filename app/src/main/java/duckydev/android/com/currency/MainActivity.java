package duckydev.android.com.currency;

import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.ButterKnife;
import duckydev.android.com.currency.adapter.MyFragmentPagerAdapter;
import duckydev.android.com.currency.object.Currency;
import duckydev.android.com.currency.object.GoldPrice;
import duckydev.android.com.currency.utils.WebServiceUtils;

public class MainActivity extends AppCompatActivity {

    ArrayList<Currency> currencyArrayList;

    ArrayList<GoldPrice> goldPriceArrayList;

    MyFragmentPagerAdapter myFragmentPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), MainActivity.this);
        viewPager.setAdapter(myFragmentPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

        int[] imageResId = {
                R.drawable.currency_tab_icon,
                R.drawable.gold_tab_icon
        };

        for (int i = 0; i < imageResId.length; i++) {
            tabLayout.getTabAt(i).setIcon(imageResId[i]);
        }


        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                if (WebServiceUtils.hasInternetConnection(getApplicationContext())) {
                    try {
                        currencyArrayList = WebServiceUtils.getCurrencyList(Constains.CURRENCY_URL);

                        goldPriceArrayList = WebServiceUtils.getGoldPriceList(Constains.GOLD_PRICE_URL);

                    } catch (XmlPullParserException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                String txt = "";

                if (goldPriceArrayList == null) {
                    return;
                }

                for(int i = 0; i < currencyArrayList.size(); i++) {
                    txt += currencyArrayList.get(i).getCurrencyName() + "    " + currencyArrayList.get(i).getCurrencyCode() + "    " + currencyArrayList.get(i).getBuy() + " - " + currencyArrayList.get(i).getTransfer() + " - " + currencyArrayList.get(i).getSell();
                    txt += "\n\n";
                }

                for(int i = 0; i < goldPriceArrayList.size(); i++) {
                    txt += goldPriceArrayList.get(i).getCityName() + "    " + goldPriceArrayList.get(i).getGoldType() + "    " + goldPriceArrayList.get(i).getBuy() + " - " + goldPriceArrayList.get(i).getSell();
                    txt += "\n\n";
                }

                myFragmentPagerAdapter.goldPriceFragment.updateListview(goldPriceArrayList);
                myFragmentPagerAdapter.currencyFragment.initListview(currencyArrayList);

            }
        }.execute();
    }
}
