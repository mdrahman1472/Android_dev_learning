package com.mdrahman.googlemapsdemo;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
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

        // plain map to containing satelitte image as well
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        // Add a marker in City college
        LatLng ccny = new LatLng(40.820287, -73.9514287);

        //mMap.addMarker(new MarkerOptions().position(ccny).title("Marker in City College"));
        // the following added extra code let change the icon color. we can even use icon picture
        mMap.addMarker(new MarkerOptions().position(ccny).title("Marker in City College").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        //mMap.moveCamera(CameraUpdateFactory.newLatLng(ccny));
        // following code for zoom level of view of map
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ccny, 15)); // zoom from 1 to 20 where 20 is highest zoom level

    }
}
