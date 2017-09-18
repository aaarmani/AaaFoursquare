package andersonarmani.aaafoursquare.ui.activity;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

import andersonarmani.aaafoursquare.api.model.Item;

/**
 * Created by Armani andersonaramni@gmail.com on 17/09/2017.
 */

public class ClusterItemPoi implements ClusterItem {
    private final LatLng mPosition;
    private final String mTitle;
    private final String mSnippet;

    public ClusterItemPoi(Item item) {
        mPosition = new LatLng(item.getVenue().getLocation().getLat(), item.getVenue().getLocation().getLng());
        mTitle = item.getVenue().getName();
        mSnippet = item.getVenue().getName();
    }

    @Override
    public LatLng getPosition() {
        return mPosition;
    }

    @Override
    public String getTitle() {
        return mTitle;
    }

    @Override
    public String getSnippet() {
        return mSnippet;
    }
}
