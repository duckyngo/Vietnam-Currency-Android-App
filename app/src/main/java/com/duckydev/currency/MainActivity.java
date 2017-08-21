package com.duckydev.currency;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;

import com.duckydev.currency.fragment.CurrencyFragment;
import com.duckydev.currency.fragment.GoldPriceFragment;
import com.duckydev.currency.object.Currency;
import com.duckydev.currency.object.GoldPrice;
import com.duckydev.currency.utils.SharedPreferenceUtils;
import com.duckydev.currency.utils.WebServiceUtils;
import com.duckydev.currency.view.ExitDialog;
import com.duckydev.currency.view.NoInternetAccessDialog;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements CurrencyFragment.OnCurrencyFragmentInteractionListener, GoldPriceFragment.OnGoldPriceFragmentInteractionListener {

    ArrayList<Currency> currencyArrayList;

    ArrayList<GoldPrice> goldPriceArrayList;

    MyFragmentPagerAdapter myFragmentPagerAdapter;


    public CurrencyFragment currencyFragment;
    public GoldPriceFragment goldPriceFragment;

    @Override
    public void onBackPressed() {
        if (SharedPreferenceUtils.getRateClickCount(getApplicationContext()) <= 5) {
            if (android.os.Build.VERSION.SDK_INT >= 11) {
                new ExitDialog(this, android.R.style.Theme_Holo_Dialog_NoActionBar);
                return;
            } else {
                new ExitDialog(this, 0);
                return;
            }
        }else {
            finish();
        }
    }

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

        currencyFragment = CurrencyFragment.newInstance(1, 1);
        currencyFragment.onAttach(this);
        goldPriceFragment = GoldPriceFragment.newInstance(1, 1);
        goldPriceFragment.onAttach(this);

        int[] imageResId = {
                R.drawable.currency_tab_icon,
                R.drawable.gold_tab_icon
        };

        for (int i = 0; i < imageResId.length; i++) {
            tabLayout.getTabAt(i).setIcon(imageResId[i]);
        }

        if (WebServiceUtils.hasInternetConnection(this)) {
            updateCurrency();
            updateGoldPrice();
        }else {
            if (android.os.Build.VERSION.SDK_INT >= 11) {
                new NoInternetAccessDialog(this,  android.R.style.Theme_Holo_Dialog_NoActionBar);

                return;
            } else {
                new NoInternetAccessDialog(this, 0);

                return;
            }
        }

    }

    public void updateGoldPrice() {
        new UpdateGoldPriceAsyn().execute();
    }

    public void updateCurrency() {
        new UpdateCurrencyAsyn().execute();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (WebServiceUtils.hasInternetConnection(getApplicationContext())) {
            updateCurrency();
            updateGoldPrice();
        }
    }

    @Override
    public void onCurrencyFragmentRequestRefresh() {
        updateCurrency();
    }

    @Override
    public void onGoldPriceFragmentRequestUpdate() {
        updateGoldPrice();
    }

    private class UpdateGoldPriceAsyn extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            if (WebServiceUtils.hasInternetConnection(getApplicationContext())) {
                try {
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
            if (goldPriceArrayList != null) {
                goldPriceFragment.updateListview(goldPriceArrayList);
            }
            goldPriceFragment.setRefreshing(false);
        }
    }

    private class UpdateCurrencyAsyn extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            if (WebServiceUtils.hasInternetConnection(getApplicationContext())) {
                try {
                    currencyArrayList = WebServiceUtils.getCurrencyList(Constains.CURRENCY_URL);

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
            if (currencyArrayList != null) {
                currencyFragment.initListview(currencyArrayList);
            }
            currencyFragment.setRefreshing(false);
        }
    }


    private class MyFragmentPagerAdapter extends FragmentPagerAdapter {
        final int PAGE_COUNT = 2;
        private String tabTitles[] = new String[]{getResources().getString(R.string.currency)
                                , getResources().getString(R.string.gold_price)};
        private Context context;


        int[] imageResId = {
                R.drawable.currency_tab_icon,
                R.drawable.gold_tab_icon
        };

        public MyFragmentPagerAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.context = context;
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return currencyFragment;
                default:
                    return goldPriceFragment;
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {

            Drawable image = context.getResources().getDrawable(imageResId[position]);
            image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());

            SpannableString sb = new SpannableString(" " + tabTitles[position]);
            ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
            sb.setSpan(imageSpan, 0, 3, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            return sb;
//        return tabTitles[position];
        }
    }
}
