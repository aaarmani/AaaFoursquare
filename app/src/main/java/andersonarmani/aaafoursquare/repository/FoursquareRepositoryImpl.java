package andersonarmani.aaafoursquare.repository;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import javax.inject.Inject;

import andersonarmani.aaafoursquare.FoursquareApplication;
import andersonarmani.aaafoursquare.api.FoursquareService;
import andersonarmani.aaafoursquare.api.model.Explore;
import andersonarmani.aaafoursquare.api.model.Item;
import andersonarmani.aaafoursquare.api.model.VenueRequest;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by Armani andersonaramni@gmail.com on 17/09/2017.
 */

public class FoursquareRepositoryImpl implements FoursquareRepository {
    private static final String CLIENT_ID = "KKAKBLWJJAVNQU1NHGDAS2NJE2GRMPYWRT03NIT1FHEARWR4";
    private static final String CLIENT_SECRET = "GDGHPFJIEEVBGU2GNOJ5B1KLFCYLZRRCNFKRH52NZ3GBB45I";
    private static final String API_DATE = "20170917";

    @Inject
    FoursquareService foursquareService;

    public FoursquareRepositoryImpl() {
        FoursquareApplication.getsAppComponent().inject(this);
    }

    @Override
    public Single getVenuesExplore(LatLng latLng, int radius) {
        String strLatLng = latLng.latitude + "," + latLng.longitude;
        return foursquareService.getVenuesExplore(CLIENT_ID, CLIENT_SECRET, API_DATE, strLatLng, radius)
                .map(new Function<Explore, List<Item>>() {
                    @Override
                    public List<Item> apply(@NonNull Explore explore) throws Exception {
                        return explore.getResponse().getGroups().get(0).getItems();
                    }
                });
    }

    @Override
    public Single<VenueRequest> getVenueRequest(String venueId) {
        return foursquareService.getVenuesRequest(venueId, CLIENT_ID, CLIENT_SECRET, API_DATE);
    }
}
