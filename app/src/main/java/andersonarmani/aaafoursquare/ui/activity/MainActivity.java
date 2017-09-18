package andersonarmani.aaafoursquare.ui.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.maps.android.clustering.ClusterManager;

import java.util.List;

import andersonarmani.aaafoursquare.R;
import andersonarmani.aaafoursquare.api.model.Item;
import andersonarmani.aaafoursquare.ui.fragment.pois.PoisFragment;

public class MainActivity extends FragmentActivity
        implements OnMapReadyCallback, GoogleMap.OnCameraIdleListener, GoogleMap.OnCameraChangeListener,
        MapView, ListPoiPresenter {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int REQUEST_LOCATION = 1;

    private GoogleMap mMap;
    private ClusterManager<ClusterItemPoi> mClusterManager;
    private MapPresenter mMapPresenter = new MapPresenterImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        PoisFragment poisFragment = (PoisFragment) getSupportFragmentManager()
                .findFragmentById(R.id.bottom_sheet_fragment);
        poisFragment.initializeFragment(this);

        mapFragment.getMapAsync(this);
        mMapPresenter.initializePresenter(this, poisFragment);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnCameraIdleListener(this);
        mMap.setOnCameraChangeListener(this);
        mMapPresenter.mapReady();

        //Set settings
        googleMap.setMinZoomPreference(7);
        googleMap.setIndoorEnabled(false);
        googleMap.getUiSettings().setMapToolbarEnabled(false);
        googleMap.moveCamera(CameraUpdateFactory.zoomTo(16F));

        //Location
        if (ActivityCompat.checkSelfPermission(getBaseContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(getBaseContext(), Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            googleMap.setMyLocationEnabled(true);
            getCurrentLocation();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_LOCATION) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (mMap != null
                        && (ActivityCompat.checkSelfPermission(getBaseContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(getBaseContext(), Manifest.permission.ACCESS_COARSE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED)) {
                    //Enable location
                    mMap.setMyLocationEnabled(true);
                    getCurrentLocation();
                }
            }
        }
    }

    private void getCurrentLocation() {

        LocationManager locationManager = (LocationManager) getSystemService(getBaseContext().LOCATION_SERVICE);
        Criteria criteria = new Criteria();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
        if (location != null)
        {
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            centreMap(latLng);
        }
    }

    @Override
    public void addMapItem(Marker marker) {

    }

    @Override
    public void addMapItems(List<Item> itemList) {
        if(mMap == null) {
            Log.d(TAG, "addMarkers mGoogleMap == null");
            return;
        }

        mClusterManager = new ClusterManager<>(getBaseContext(), mMap);

        // Point the map's listeners at the listeners implemented by the cluster
        // manager.
        mMap.setOnCameraIdleListener(mClusterManager);
        mMap.setOnMarkerClickListener(mClusterManager);

        for(Item item: itemList) {
            ClusterItemPoi offsetItem = new ClusterItemPoi(item);
            mClusterManager.addItem(offsetItem);
        }
        mClusterManager.cluster();
    }

    @Override
    public void centreMap(LatLng latLng) {
        if(mMap != null) {
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        }
    }

    @Override
    public void clear() {
        if(mMap != null) {
            mMap.clear();
        }
    }

    @Override
    public void showError(String message) {
        Log.d(TAG, message);
    }

    @Override
    public void onCameraIdle() {
        //mMapPresenter.mapCentreChange(mMap.getCameraPosition().target);
    }

    @Override
    public void onCameraChange(CameraPosition cameraPosition) {
        //TODO: Remove this callback and use OnCameraIdle. But in the first moment OnCameraIdle didn't work
        mMapPresenter.mapCentreChange(cameraPosition.target);
    }

    @Override
    public void radiusChanged(int value) {
        mMap.clear();
        mMapPresenter.setRadius(value);
    }
}
