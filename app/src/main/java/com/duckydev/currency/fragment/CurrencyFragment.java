package com.duckydev.currency.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import de.halfbit.pinnedsection.PinnedSectionListView;
import com.duckydev.currency.Constains;
import com.duckydev.currency.R;
import com.duckydev.currency.object.Currency;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnCurrencyFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CurrencyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CurrencyFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private int mParam1;
    private int mParam2;

    private ListView listView;
    private TextView updateTextview;
    private SwipeRefreshLayout refreshLayout;
    private CurrencyPinnedSectionListAdapter currencyPinnedSectionListAdapter;

    private OnCurrencyFragmentInteractionListener mListener;

    public CurrencyFragment() {
    }

    // TODO: Rename and change types and number of parameters
    public static CurrencyFragment newInstance(int param1, int param2) {
        CurrencyFragment fragment = new CurrencyFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        args.putInt(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
            mParam2 = getArguments().getInt(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_currency, container, false);
        listView = (ListView) view.findViewById(R.id.listview);

        TextView sourceView = (TextView) view.findViewById(R.id.sourceTv);
        sourceView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Constains.CURRENCY_SRC));
                startActivity(browserIntent);
            }
        });

        updateTextview = (TextView) view.findViewById(R.id.updateTv);
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);
        refreshLayout.setOnRefreshListener(this);

        return view;
    }


    public void onRefreshPressed() {
        if (mListener != null) {
            mListener.onCurrencyFragmentRequestRefresh();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnCurrencyFragmentInteractionListener) {
            mListener = (OnCurrencyFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnCurrencyFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onRefresh() {
        onRefreshPressed();
    }

    public void setRefreshing(boolean Isrefreshing) {
        refreshLayout.setRefreshing(Isrefreshing);
    }

    public interface OnCurrencyFragmentInteractionListener {
        // TODO: Update argument type and name
        void onCurrencyFragmentRequestRefresh();
    }

    public void initListview(ArrayList<Currency> currencies) {
        if (currencyPinnedSectionListAdapter == null && currencies != null) {
            currencyPinnedSectionListAdapter = new CurrencyPinnedSectionListAdapter(getActivity(), currencies);
            listView.setAdapter(currencyPinnedSectionListAdapter);
        }
        currencyPinnedSectionListAdapter.notifyDataSetChanged();
        updateTextview.setText("Cập nhật lúc: " + currencies.get(2).getUpdated());
    }


    public class CurrencyPinnedSectionListAdapter extends ArrayAdapter<Currency> implements PinnedSectionListView.PinnedSectionListAdapter {


        Context mContext;
        ArrayList<Currency> dataSet = new ArrayList<>();

        public CurrencyPinnedSectionListAdapter(@NonNull Context context, ArrayList<Currency> objects) {
            super(context, 0, objects);
            this.mContext = context;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View view;
            LayoutInflater inflater = LayoutInflater.from(getContext());
            Currency currency = getItem(position);
            if (convertView == null) {
                view = inflater.inflate(R.layout.currency_item_layout, null);
            } else {
                view = convertView;
            }


            ImageView imageFlag = (ImageView) view.findViewById(R.id.flagIv);
            TextView countryCode = (TextView) view.findViewById((R.id.countryCodeTv));
            TextView countryName = (TextView) view.findViewById((R.id.countryNameTv));
            TextView buy = (TextView) view.findViewById((R.id.buyTv));
            TextView transfer = (TextView) view.findViewById((R.id.transferTv));
            TextView sell = (TextView) view.findViewById((R.id.sellTv));

            if (currency != null && currency.getType() == Currency.SECTION) {
                imageFlag.setVisibility(View.GONE);
                countryName.setVisibility(View.GONE);
                view.setBackgroundColor(Color.DKGRAY);
                countryCode.setText(currency.getCurrencyCode());
                buy.setText(currency.getBuy());
                buy.setTypeface(null, Typeface.BOLD);
                transfer.setText(currency.getTransfer());
                transfer.setTypeface(null, Typeface.BOLD);
                sell.setText(currency.getSell());
                sell.setTypeface(null, Typeface.BOLD);
            } else {
                imageFlag.setImageResource(getResourceId(currency.getCurrencyCode().toLowerCase(), "drawable", getActivity().getPackageName()));
                countryCode.setText(currency.getCurrencyCode());
                countryName.setText(currency.getCurrencyName());
                if (currency.getBuy().equalsIgnoreCase("0")) {
                    buy.setText("--      ");
                } else {
                    buy.setText(NumberFormat.getNumberInstance(Locale.US).format(Double.parseDouble(currency.getBuy())));
                }
                transfer.setText(NumberFormat.getNumberInstance(Locale.US).format(Double.parseDouble(currency.getTransfer())));
                sell.setText(NumberFormat.getNumberInstance(Locale.US).format(Double.parseDouble(currency.getSell())));
            }

            return view;
        }

        @Override
        public int getItemViewType(int position) {
            return getItem(position).getType();
        }

        @Override
        public boolean isItemViewTypePinned(int viewType) {
            return viewType == Currency.SECTION;
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }
    }

    public int getResourceId(String pVariableName, String pResourcename, String pPackageName) {
        try {
            return getResources().getIdentifier(pVariableName, pResourcename, pPackageName);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }




}
