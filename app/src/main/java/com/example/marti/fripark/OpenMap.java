package com.example.marti.fripark;

import android.app.Dialog;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


public class OpenMap extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnMapClickListener, Serializable {

    private GoogleMap mMap;
    private Dialog pop;

    private Marker own;
    private boolean test;
    private Map<String, Object> markLib;
    private String ourKey = "vsabsd5mg";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_map);
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
        mMap.setOnMapClickListener(this);
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        CameraUpdateFactory.zoomTo(15);
        markLib = new HashMap();


        //testData
        Marker marker1= mMap.addMarker(new MarkerOptions().position(new LatLng(46.047200, 14.502798)).title("Prosto ob: 13:45").snippet("User:Test1").visible(false));
        Marker marker2= mMap.addMarker(new MarkerOptions().position(new LatLng(46.046872, 14.499565)).title("Prosto ob: 12:00").snippet("User:Test2").visible(false));
        Marker marker3= mMap.addMarker(new MarkerOptions().position(new LatLng(46.046500, 14.498879)).title("Prosto ob: 17:23").snippet("User:Test3").visible(false));
        Marker marker4= mMap.addMarker(new MarkerOptions().position(new LatLng(46.047594, 14.495856)).title("Prosto ob: 20:00").snippet("User:Test4").visible(false));
        Marker marker5= mMap.addMarker(new MarkerOptions().position(new LatLng(46.047691, 14.496317)).title("Prosto ob: 08:54").snippet("User:Test5").visible(false));
        markLib.put("vsi3a05mg", marker1);
        markLib.put("vsabsd5mg", marker2); // recimo da je to naš marker
        markLib.put("vbsi6456g", marker3);
        markLib.put("525basd65", marker4);
        markLib.put("634b24014", marker5);


        Intent data = getIntent();
        test = data.getBooleanExtra("mode", false); //true if giving away parking space



        // testne koordinate
        LatLng ljubljana = new LatLng(46.056946, 14.505752);

        // use test value to check which markers you draw



        if(test) {
            mMap.moveCamera(CameraUpdateFactory.newLatLng(ljubljana));
        } else {
            mMap.moveCamera(CameraUpdateFactory.newLatLng(ljubljana));

            for (Map.Entry<String, Object> entry : markLib.entrySet()) {
                String key = entry.getKey();
                Marker toDraw = (Marker) entry.getValue();
                if(!key.equals("vsabsd5mg")) {
                    toDraw.setVisible(true);
                }
            }

        }

        mMap.setOnMarkerClickListener(this);
    }



    public boolean onMarkerClick(final Marker marker) {

        if(!test) { // false if searching

            String time = marker.getTitle();
            String user = marker.getSnippet();

            Intent pick = new Intent(this, PickSpot.class);
            pick.putExtra("info", time+"//"+user);
            startActivityForResult(pick, 2);

        } else {
            Intent form = new Intent(this, MarkerForm.class);
            startActivityForResult(form, 1);

        }

        return false;
    }

    // sets marker at location if test=true
    public void onMapClick(LatLng coord) {
        if(!test){ //false is searching for parking spot
            return;
        } else {
            if(own != null) {
                own.setPosition(coord);
            } else {
                own = mMap.addMarker(new MarkerOptions().position(coord).title("Your parking space"));
            }
        }

    }

    @Override  // get result from MarkerForm
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == 1) {
            String time = data.getStringExtra("time");
            own.setSnippet(time);
        } else if (resultCode == 0) {
            return;
        }
    }


    //TODO fix this
    public void showPopup(View view) {
        TextView close;
        Button reserve;
        pop.setContentView(R.layout.select_marker);
        close = (TextView) pop.findViewById(R.id.closepopup);
        reserve = (Button) pop.findViewById(R.id.reserve);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pop.dismiss();
            }
        });
        reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop.dismiss();
            }
        });
        pop.show();
    }


}


