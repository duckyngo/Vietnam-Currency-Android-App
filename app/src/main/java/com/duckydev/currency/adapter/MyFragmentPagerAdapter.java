package com.duckydev.currency.adapter;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;

import com.duckydev.currency.fragment.CurrencyFragment;

import com.duckydev.currency.R;

import com.duckydev.currency.fragment.GoldPriceFragment;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 2;
    private String tabTitles[] = new String[]{"Tỉ Giá", "Giá Vàng"};
    private Context context;

    public CurrencyFragment currencyFragment;
    public GoldPriceFragment goldPriceFragment;


    int[] imageResId = {
            R.drawable.currency_tab_icon,
            R.drawable.gold_tab_icon
    };

    public MyFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
        currencyFragment = CurrencyFragment.newInstance(1,1);
        goldPriceFragment = GoldPriceFragment.newInstance(1, 1);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
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

        SpannableString sb = new SpannableString(" " +tabTitles[position]);
        ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BASELINE);
        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sb;
    }
}
