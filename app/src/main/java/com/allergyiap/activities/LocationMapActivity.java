package com.allergyiap.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.allergyiap.R;
import com.allergyiap.beans.Station;
import com.allergyiap.utils.C;
import com.allergyiap.utils.CommonServices;
import com.allergyiap.utils.Util;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class LocationMapActivity extends BaseActivity implements OnMapReadyCallback {

    private GoogleMap googleMap;
    private Station station;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_map);

        station = (Station) getIntent().getSerializableExtra(C.IntentExtra.Sender.VAR_STATION);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        getSupportActionBar().setTitle(station.getName_station());

        findViewById(R.id.btn_active).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.setStation(station);
                setResult(CommonServices.RESULT_RESTART, new Intent());
                finish();
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
        this.googleMap = googleMap;

        // Add a marker in Sydney and move the camera
        /*LatLng sydney = new LatLng(-34, 151);
        this.googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        this.googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/
        LatLng pos = new LatLng(station.getLatitude(), station.getLongitude());

        createMarker(pos, station.getName_station());
        drawCircle(pos);
    }

    private Marker createMarker(LatLng pos, String title) {
        // Add a marker in Sydney and move the camera
        Marker marker = googleMap.addMarker(new MarkerOptions()
                .position(pos)
                .title(title)
                .draggable(false)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(pos));

        return marker;

        //marker.showInfoWindow();
    }

    private void drawCircle(LatLng point) {

        // Instantiating CircleOptions to draw a circle around the marker
        CircleOptions circleOptions = new CircleOptions();

        // Specifying the center of the circle
        circleOptions.center(point);

        // Radius of the circle
        circleOptions.radius(3000);

        // Border color of the circle
        circleOptions.strokeColor(Color.BLACK);

        // Fill color of the circle
        circleOptions.fillColor(0x30ff0000);

        // Border width of the circle
        circleOptions.strokeWidth(3);

        // Adding the circle to the GoogleMap
        googleMap.addCircle(circleOptions);

    }
}
