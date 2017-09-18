package andersonarmani.aaafoursquare.ui.fragment.pois;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.Toast;

import andersonarmani.aaafoursquare.R;
import andersonarmani.aaafoursquare.api.model.Item;
import andersonarmani.aaafoursquare.ui.activity.ListPoiPresenter;
import andersonarmani.aaafoursquare.ui.activity.itemDetail.ItemDetailActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListPoisClickItem}
 * interface.
 */
public class PoisFragment extends Fragment implements PoisView, SeekBar.OnSeekBarChangeListener, OnListPoisClickItem {
    private static final String TAG = PoisFragment.class.getSimpleName();
    private static final int DEFAULT_RADIUS = 200;
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
            mRecyclerView.setAdapter(new MyPoisRecyclerViewAdapter(mItemList, this));
        }
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
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

    //On ItemList clicked
    @Override
    public void onClick(Item item) {
        Log.d(TAG, "OnItemClicked");

        Intent intent = new Intent(getActivity(), ItemDetailActivity.class);
        intent.putExtra(ItemDetailActivity.ITEM_ID, item.getVenue().getId());
        startActivity(intent);
        //Toast.makeText(getContext(), "Item Detail not implemented", Toast.LENGTH_SHORT).show();
    }
}
