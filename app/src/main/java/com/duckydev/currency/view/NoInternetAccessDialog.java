package com.duckydev.currency.view;

import android.app.Dialog;
import android.content.Intent;
import android.provider.Settings;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.duckydev.currency.MainActivity;

import com.duckydev.currency.R;


public class NoInternetAccessDialog extends Dialog {

    private MainActivity mContext;

    public NoInternetAccessDialog(MainActivity mainactivity, int i) {
        super(mainactivity, i);
        mContext = mainactivity;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.no_internet_dialog);
        TextView textView1 = (TextView) findViewById(R.id.btCancle);
        TextView textview = (TextView) findViewById(R.id.btSetting);
        textView1.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view) {
                dismiss();
            }

        });
        textview.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view) {
                dismiss();
                mContext.startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
            }
        });
        show();
    }

}
