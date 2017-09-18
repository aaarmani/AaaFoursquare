package andersonarmani.aaafoursquare.ui.activity;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import javax.inject.Inject;

import andersonarmani.aaafoursquare.FoursquareApplication;
import andersonarmani.aaafoursquare.api.model.Item;
import andersonarmani.aaafoursquare.repository.FoursquareRepository;
import andersonarmani.aaafoursquare.ui.fragment.pois.PoisView;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Armani andersonaramni@gmail.com on 17/09/2017.
 */

public class MapPresenterImpl implements MapPresenter {
    private static final String TAG = MapPresenterImpl.class.getSimpleName();
    private static final int MIN_RADIUS = 100;
    private static final int MAX_RADIUS = 1000;
    private MapView mMapView;
    private PoisView mPoisView;
    private LatLng mCurrentLatLng;
    private int mRadius = 200;
    @Inject
    FoursquareRepository foursquareRepository;

    @Override
    public void initializePresenter(MapView mapView, PoisView poisView) {
        mMapView = mapView;
        mPoisView = poisView;

        //Adyen Amsterdam
        mCurrentLatLng = new LatLng(52.376481, 4.905917);

        FoursquareApplication.getsAppComponent().inject(this);
    }

    @Override
    public void mapReady() {
        mMapView.centreMap(mCurrentLatLng);
        loadFoursquareItens(mCurrentLatLng);
    }

    @Override
    public void selectMapItem(Item item) {

    }

    @Override
    public void mapCentreChange(LatLng latLngCentre) {
        loadFoursquareItens(latLngCentre);
    }

    @Override
    public void setRadius(int radius) {
        if(radius > MIN_RADIUS && radius < MAX_RADIUS) {
            mRadius = radius;
            loadFoursquareItens(mCurrentLatLng);
        }
    }

    private void loadFoursquareItens(final LatLng latLng) {
        //mMapView.centreMap(latLng);
        mCurrentLatLng = latLng;

        foursquareRepository.getVenuesExplore(latLng, mRadius)
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
                        mMapView.addMapItems(items);
                        mPoisView.setListItems(items);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "onError = " + e.getMessage());
                        mMapView.showError(e.getMessage());
                        mPoisView.showError(e.getMessage());
                    }
                });
    }
}
