package com.mdrahman.userlocationonmap;

import android.*;
import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    LocationManager locationManager;
    LocationListener locationListener;
    private GoogleMap mMap;

    // after user click permission accept or denied
    // this will call only until user give permission. once user give permission this method will no longer will be called
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            // we again need to check if we have permission
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            }
        }
    }

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

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        //when location changed
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                // Add a marker in user location and move the camera
                LatLng userLocation = new LatLng(location.getLatitude(), location.getLongitude());

                mMap.clear(); // to clear existing location

                mMap.addMarker(new MarkerOptions().position(userLocation).title("Your Current Location"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(userLocation));
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        // we are running Marshmellow SDK 23, if we run app device earlier than Marshmall
        if(Build.VERSION.SDK_INT < 23){
            // for earlier version, will work here
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        } else{// if version is not earlier

            // if we don't have permission
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                // since don't have permission we need to ask for permission
                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 1);

            } else{// we have permission, then
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

                // user's current location when app begin
                Location lastKnowLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                LatLng userLocation = new LatLng(lastKnowLocation.getLatitude(), lastKnowLocation.getLongitude());
                mMap.addMarker(new MarkerOptions().position(userLocation).title("Your Current Location"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(userLocation));
            }

        }


    }
}
