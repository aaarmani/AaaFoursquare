package andersonarmani.aaafoursquare.ui.fragment.pois;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import andersonarmani.aaafoursquare.R;
import andersonarmani.aaafoursquare.api.model.Item;
import andersonarmani.aaafoursquare.ui.activity.ListPoiPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class PoisFragment extends Fragment implements PoisView, SeekBar.OnSeekBarChangeListener {
    private static final String TAG = PoisFragment.class.getSimpleName();
    private static final int DEFAULT_RADIUS = 200;
    private OnListFragmentInteractionListener mListener;
    private List<Item> mItemList;
    private RecyclerView mRecyclerView;
    private SeekBar mSeekBar;
    private ListPoiPresenter mListPoiPresenter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PoisFragment() {
    }

    /*public static PoisFragment newInstance(int columnCount) {
        PoisFragment fragment = new PoisFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mItemList = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pois_list, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.list);
        mSeekBar = (SeekBar) view.findViewById(R.id.seekBar);
        mSeekBar.setOnSeekBarChangeListener(this);
        mSeekBar.setProgress(DEFAULT_RADIUS);

        // Set the adapter
        if (mRecyclerView != null) {
            Context context = view.getContext();

            mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
            mRecyclerView.setAdapter(new MyPoisRecyclerViewAdapter(mItemList, mListener));
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            /*throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");*/
            //TODO: change it, removing this comments
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void initializeFragment(ListPoiPresenter listPoiPresenter) {
        mListPoiPresenter = listPoiPresenter;
    }

    @Override
    public void setListItems(List<Item> itemList) {
        Log.d(TAG, "setListItems");
        mItemList.clear();
        mItemList.addAll(itemList);
        mRecyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void clear() {
        mItemList.clear();
        mRecyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        if(mListPoiPresenter != null) {
            mListPoiPresenter.radiusChanged(seekBar.getProgress());
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Item item);
    }
}
