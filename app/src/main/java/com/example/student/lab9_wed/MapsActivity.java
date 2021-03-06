package com.example.student.lab9_wed;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.plus.model.people.Person;

public class MapsActivity extends FragmentActivity
        implements GoogleMap.OnMapLongClickListener, LocationListener {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    LocationManager lMgr;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();


        //Set Map to accept long click
        mMap.setOnMapLongClickListener(this);

        //LocationListener
        lMgr = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        lMgr.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        LatLng BKK = new LatLng(13.736717,100.523186);
       // LatLng BKK2 = new LatLng(15.736717,102.523186);

      //  mMap.addMarker(new MarkerOptions().position(BKK).title("Bangkok"));
      //  mMap.addMarker(new MarkerOptions().position(BKK2).title("HongKong"));

        //Move Map Center Point
        CameraUpdate start_map = CameraUpdateFactory.newLatLng(BKK);
        mMap.moveCamera(start_map);

        //Change Map Zoom Level
        CameraUpdate start_zoom = CameraUpdateFactory.zoomTo(5);
        mMap.animateCamera(start_zoom);
/*
        //Draw Line
        mMap.addPolyline(new PolylineOptions().add(BKK).add(BKK2));

  */  }

    @Override
    public void onMapLongClick(LatLng latLng) {

        LatLng OTHERPLACE = latLng;

        mMap.addMarker(new MarkerOptions().position(OTHERPLACE).title("OTHERPLACE"));


    }

    int Place = 0 ;

    LatLng PREV;
    //From LocationListener
    @Override
    public void onLocationChanged(Location location) {

        LatLng OTHERPLACE = new LatLng(location.getLatitude(),location.getLongitude());

        mMap.addMarker(new MarkerOptions().position(OTHERPLACE).title("Place " + ++Place));




        //Move Map Center Point
        CameraUpdate start_map = CameraUpdateFactory.newLatLng(OTHERPLACE);
        mMap.moveCamera(start_map);

        //Change Map Zoom Level
        CameraUpdate start_zoom = CameraUpdateFactory.zoomTo(5);
        mMap.animateCamera(start_zoom);

        //Draw Line
       if(PREV != null) {
           mMap.addPolyline(new PolylineOptions().add(PREV).add(OTHERPLACE));
       }
        PREV = OTHERPLACE;





    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
