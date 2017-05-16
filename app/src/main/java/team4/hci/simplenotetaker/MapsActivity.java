package team4.hci.simplenotetaker;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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

        // Add a marker in Vienna and move the camera
        LatLng vienna = new LatLng(48.220200, 16.356114);
        mMap.addMarker(new MarkerOptions().position(vienna).title("Eintrag in Wien"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(vienna));

        LatLng berlin = new LatLng(52.524370, 13.410530);
        mMap.addMarker(new MarkerOptions().position(berlin).title("Eintrag in Berlin"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(berlin));

        LatLng munich = new LatLng(48.137154, 11.576124);
        mMap.addMarker(new MarkerOptions().position(munich).title("Eintrag in München"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(munich));
    }
}
