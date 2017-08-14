package duckydev.android.com.currency.fragment;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import duckydev.android.com.currency.Constains;
import duckydev.android.com.currency.R;
import duckydev.android.com.currency.object.Currency;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CurrencyFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CurrencyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CurrencyFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private int mParam1;
    private int mParam2;

    private ListView listView;

    private CurrencyPinnedSectionListAdapter currencyPinnedSectionListAdapter;

    private OnFragmentInteractionListener mListener;

    public CurrencyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CurrencyFragment.
     */
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
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void initListview(ArrayList<Currency> list){
        currencyPinnedSectionListAdapter = new CurrencyPinnedSectionListAdapter(getActivity(), list);
        listView.setAdapter(currencyPinnedSectionListAdapter);
    }


    public class CurrencyPinnedSectionListAdapter extends ArrayAdapter<Currency> implements PinnedSectionListView.PinnedSectionListAdapter {


        Context mContext;
        ArrayList<Currency> dataSet = new ArrayList<>();

        public CurrencyPinnedSectionListAdapter(@NonNull Context context, ArrayList<Currency> objects) {
            super(context,0, objects);
            this.mContext = context;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View view;
            LayoutInflater inflater = LayoutInflater.from(getContext());
            Currency currency = (Currency) getItem(position);
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
            }else {
                imageFlag.setImageResource(getResourceId(currency.getCurrencyCode().toLowerCase(), "drawable", getActivity().getPackageName()));
                countryCode.setText(currency.getCurrencyCode());
                countryName.setText(currency.getCurrencyName());
                if (currency.getBuy().equalsIgnoreCase("0")) {
                    buy.setText("--      ");
                }else {
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

    public int getResourceId(String pVariableName, String pResourcename, String pPackageName)
    {
        try {
            return getResources().getIdentifier(pVariableName, pResourcename, pPackageName);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
