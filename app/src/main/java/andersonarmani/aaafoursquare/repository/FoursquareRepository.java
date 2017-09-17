package andersonarmani.aaafoursquare.repository;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import andersonarmani.aaafoursquare.api.model.Item;
import io.reactivex.Single;

/**
 * Created by Armani andersonaramni@gmail.com on 17/09/2017.
 */

public interface FoursquareRepository {
    Single<List<Item>> getVenuesExplore(LatLng latLng, int radius);
}
