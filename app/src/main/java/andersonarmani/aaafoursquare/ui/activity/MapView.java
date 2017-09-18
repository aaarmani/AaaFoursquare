package andersonarmani.aaafoursquare.ui.activity;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import java.util.List;

import andersonarmani.aaafoursquare.api.model.Item;

/**
 * Created by Armani andersonaramni@gmail.com on 17/09/2017.
 */

public interface MapView {
    void addMapItem(Marker marker);
    void addMapItems(List<Item> itemList);
    void centreMap(LatLng latLng);
    void clear();
    void showError(String message);
}
