package duckydev.android.com.currency.fragment;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import de.halfbit.pinnedsection.PinnedSectionListView;
import duckydev.android.com.currency.R;
import duckydev.android.com.currency.object.Currency;
import duckydev.android.com.currency.object.GoldPrice;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GoldPriceFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GoldPriceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GoldPriceFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    TextView textView;

    // TODO: Rename and change types of parameters
    private int mParam1;
    private int mParam2;

    private OnFragmentInteractionListener mListener;

    private ListView listView;
    private GoldPinnedSectionListAdapter goldPinnedSectionListAdapter;

    public GoldPriceFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GoldPriceFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GoldPriceFragment newInstance(int param1, int param2) {
        GoldPriceFragment fragment = new GoldPriceFragment();
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
        View view = inflater.inflate(R.layout.fragment_gold_price, container, false);
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

    public void updateListview(ArrayList<GoldPrice> goldPrices) {
        goldPinnedSectionListAdapter = new GoldPinnedSectionListAdapter(getActivity(), goldPrices);
        listView.setAdapter(goldPinnedSectionListAdapter);

    }

    public class GoldPinnedSectionListAdapter extends ArrayAdapter<GoldPrice> implements PinnedSectionListView.PinnedSectionListAdapter {


        Context mContext;
        ArrayList<GoldPrice> dataSet = new ArrayList<>();

        public GoldPinnedSectionListAdapter(@NonNull Context context, ArrayList<GoldPrice> objects) {
            super(context,0, objects);
            this.mContext = context;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View view;
            LayoutInflater inflater = LayoutInflater.from(getContext());
            GoldPrice gold = (GoldPrice) getItem(position);
            if (convertView == null) {
                view = inflater.inflate(R.layout.gold_item_layout, null);
            } else {
                view = convertView;
            }

            TextView type = (TextView) view.findViewById((R.id.typeTv));
            TextView buy = (TextView) view.findViewById((R.id.buyTv));
            TextView sell = (TextView) view.findViewById((R.id.sellTv));

            if (gold != null) {
                type.setText(String.valueOf(gold.getGoldType()));
                buy.setText(String.valueOf(gold.getBuy()));
                sell.setText(String.valueOf(gold.getSell()));
            }

            if (gold.getType() == Currency.SECTION) {
                view.setBackgroundColor(Color.DKGRAY);
                type.setTypeface(null, Typeface.BOLD);
                buy.setVisibility(View.GONE);
                sell.setVisibility(View.GONE);
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
}
