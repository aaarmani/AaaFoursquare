package andersonarmani.aaafoursquare.api;

import andersonarmani.aaafoursquare.api.model.Explore;
import andersonarmani.aaafoursquare.api.model.VenueRequest;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Armani andersonaramni@gmail.com on 17/09/2017.
 */

public interface FoursquareService {
    @GET("venues/explore/")
    Single<Explore> getVenuesExplore(
            @Query("client_id") String clientId,
            @Query("client_secret") String clientSecret,
            @Query("v") String apiDate,
            @Query("ll") String latLongSring,
            @Query("radius") int radius
    );

    @GET("venues/{id}/")
    Single<VenueRequest> getVenuesRequest(
            @Path("id") String venueId,
            @Query("client_id") String clientId,
            @Query("client_secret") String clientSecret,
            @Query("v") String apiDate
    );
}
