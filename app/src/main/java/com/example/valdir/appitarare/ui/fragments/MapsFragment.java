package com.example.valdir.appitarare.ui.fragments;

import android.os.Bundle;

import com.example.valdir.appitarare.R;
import com.example.valdir.appitarare.util.Constants;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsFragment extends SupportMapFragment implements OnMapReadyCallback {

    private Double mLatitude;
    private Double mLongitude;
    private String mTitle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();

        mLatitude = bundle.getDouble(getString(R.string.KEY_LATITUDE));
        mLongitude = bundle.getDouble(getString(R.string.KEY_LONGITUDE));
        mTitle = bundle.getString(getString(R.string.key_title_Adv));

        getMapAsync(this);
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

        googleMap.getUiSettings().setZoomControlsEnabled(true);

        // Add a marker in Sydney and move the camera
        LatLng positionLocalAdv = new LatLng(mLatitude, mLongitude);

        googleMap.setMaxZoomPreference(Constants.MAX_ZOOM_PREF_MAPS);
        googleMap.setMinZoomPreference(Constants.MIN_ZOOM_PREF_MAPS);

        googleMap.addMarker(new MarkerOptions().position(positionLocalAdv).title(mTitle));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(positionLocalAdv));

        googleMap.animateCamera(CameraUpdateFactory.zoomTo(Constants.DEFAULT_ZOMM_MAPS));
    }
}
