package com.duckydev.currency.view;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.duckydev.currency.MainActivity;
import com.duckydev.currency.utils.SharedPreferenceUtils;

import com.duckydev.currency.R;


public class ExitDialog extends Dialog {

//    public static InterstitialAd interstitial;
    private MainActivity mContext;

    public ExitDialog(MainActivity mainactivity, int i) {
        super(mainactivity, i);
        mContext = mainactivity;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.rate_dialog);

//        AdRequest adRequest = new AdRequest.Builder().build();
//
//        interstitial = new InterstitialAd(mContext);
//        interstitial.setAdUnitId(mContext.getResources().getString(R.string.admob_full));
//        interstitial.loadAd(adRequest);


        setCanceledOnTouchOutside(false);
        TextView text = (TextView) findViewById(R.id.btCancle);
        TextView textview = (TextView) findViewById(R.id.btExit);
        TextView textview1 = (TextView) findViewById(R.id.btRate);
//        text.setTypeface(MainActivity.type_Roboto_Medium);
//        textview.setTypeface(MainActivity.type_Roboto_Medium);
//        textview1.setTypeface(MainActivity.type_Roboto_Medium);
//        ((TextView) findViewById(R.id.tvRateTitle))
//                .setTypeface(MainActivity.type_Roboto_Medium);
//        ((TextView) findViewById(R.id.tvRateContent))
//                .setTypeface(MainActivity.type_Roboto_Regular);
        text.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view) {
//                interstitial.show();
                dismiss();
            }

        });
        textview.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view) {
//                interstitial.show();
                mContext.finish();

            }

        });
        textview1.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view) {

                String packageName = mContext.getPackageName();
                Intent intent = new Intent(
                        "android.intent.action.VIEW",
                        Uri.parse((new StringBuilder(
                                "https://play.google.com/store/apps/details?id=com.duckydev.currency"))
                                .append(packageName).toString()));
                mContext.startActivity(intent);
                ((MainActivity)mContext).overridePendingTransition(R.anim.slide_in, R.anim.notrans_activity);
                Toast.makeText(mContext,
                        "Hãy đánh giá 5 sao và bình luân. Cảm ơn!", Toast.LENGTH_LONG).show();

                SharedPreferenceUtils.updateRateClickCount(mContext, SharedPreferenceUtils.getRateClickCount(mContext) + 1);
            }
        });
        show();
    }

}
