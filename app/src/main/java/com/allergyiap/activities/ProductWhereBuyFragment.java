package com.allergyiap.activities;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.allergyiap.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductWhereBuyFragment extends Fragment implements OnMapReadyCallback, GoogleMap.InfoWindowAdapter {

    static final String TAG = "ProductWhereBuyFrag";

    ProductCatalogMapActivity activity;
    Context context;

    private MapView mapView;
    private GoogleMap googleMap;

    public ProductWhereBuyFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (ProductCatalogMapActivity) getActivity();
        context = getActivity().getApplicationContext();

        //activity.updateLocale();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_where_buy, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.v(TAG, ".onViewCreated");
        super.onViewCreated(view, savedInstanceState);

        mapView = (MapView) view.findViewById(R.id.map_fragment);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);
    }

    public void onDestroyView() {
        super.onDestroyView();
        /*if (this.googleMap != null) {
            getFragmentManager()
                    .beginTransaction()
                    .remove(getFragmentManager().findFragmentById(R.id.map_fragment))
                    .commit();
        }*/
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        this.googleMap.setInfoWindowAdapter(this);

        loadData();
    }

    private void loadData() {
        //TODO: Load All pharmacies location
    }

    private Marker createMarker(double latitude, double longitude, String title) {
        // Add a marker in Sydney and move the camera
        LatLng pos = new LatLng(latitude, longitude);
        Marker marker = googleMap.addMarker(new MarkerOptions()
                .position(pos)
                .title(title)
                .draggable(false)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(pos));

        return marker;

        //marker.showInfoWindow();
    }

    /**
     * La interfaz contiene dos métodos que puedes implementar: getInfoWindow(Marker) y getInfoContents(Marker).
     * La API primero llamará a getInfoWindow(Marker).
     * Si el valor devuelto es null, llamará a getInfoContents(Marker).
     * Si el valor devuelto también es null, se usará la ventana de información predeterminada.
     *
     * @param marker
     * @return
     */
    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }
}
