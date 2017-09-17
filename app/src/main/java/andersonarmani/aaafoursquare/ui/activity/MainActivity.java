package andersonarmani.aaafoursquare.ui.activity;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import javax.inject.Inject;

import andersonarmani.aaafoursquare.FoursquareApplication;
import andersonarmani.aaafoursquare.R;
import andersonarmani.aaafoursquare.api.FoursquareService;
import andersonarmani.aaafoursquare.api.model.Explore;
import andersonarmani.aaafoursquare.api.model.Item;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback {
    private static final String TAG = MainActivity.class.getSimpleName();
    private GoogleMap mMap;
    @Inject
    FoursquareService foursquareService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //Dependency injection
        FoursquareApplication.getsAppComponent().inject(this);


        //Just for Test
        //TODO change it
        foursquareService.getExploreVenues("KKAKBLWJJAVNQU1NHGDAS2NJE2GRMPYWRT03NIT1FHEARWR4",
                "GDGHPFJIEEVBGU2GNOJ5B1KLFCYLZRRCNFKRH52NZ3GBB45I", "20170917", "40.7463956,-73.9852992", 100)
                .map(new Function<Explore, List<Item>>() {
                    @Override
                    public List<Item> apply(@NonNull Explore explore) throws Exception {
                        return explore.getResponse().getGroups().get(0).getItems();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Item>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.d(TAG, "onSubscribe");
                    }

                    @Override
                    public void onSuccess(@NonNull List<Item> items) {
                        Log.d(TAG, "onSuccess");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "onError = " + e.getMessage());
                    }
                });

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
