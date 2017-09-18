package andersonarmani.aaafoursquare.ui.fragment.pois;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import andersonarmani.aaafoursquare.R;
import andersonarmani.aaafoursquare.api.model.Item;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a List of Foursquare items
 * specified {@link OnListPoisClickItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyPoisRecyclerViewAdapter extends RecyclerView.Adapter<MyPoisRecyclerViewAdapter.ViewHolder> {

    private final List<Item> mValues;
    private final OnListPoisClickItem mListener;

    public MyPoisRecyclerViewAdapter(List<Item> items, OnListPoisClickItem listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_poi, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        String strRating;
        Double rating = mValues.get(position).getVenue().getRating();
        if(rating != null) {
            strRating = String.valueOf(rating);
        }
        else {
            strRating = "-";
        }

        holder.mItem = mValues.get(position);
        holder.mIdView.setText(strRating);
        holder.mContentView.setText(mValues.get(position).getVenue().getName());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onClick(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public Item mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
