package andersonarmani.aaafoursquare.ui.activity;


import com.google.android.gms.maps.model.LatLng;

import andersonarmani.aaafoursquare.api.model.Item;
import andersonarmani.aaafoursquare.ui.fragment.pois.PoisView;

/**
 * Created by Armani andersonaramni@gmail.com on 17/09/2017.
 */

public interface MapPresenter {
    void initializePresenter(MapView mapView, PoisView poisView);
    void mapReady();
    void selectMapItem(Item item);
    void mapCentreChange(LatLng latLngCentre);
    void setRadius(int radius);
}
