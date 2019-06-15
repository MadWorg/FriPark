package com.example.marti.fripark;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;


import org.w3c.dom.Text;

import java.io.Serializable;

public class MarkerForm extends AppCompatActivity {

    boolean chk = false;
    protected final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    protected boolean mLocationPermissionGranted = false;
    protected LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marker_form);

    }

    public void finishForm(View view) {
        EditText exp = findViewById(R.id.editText);

        Intent markData = new Intent();
        if(exp.getText().toString().equals("")) {
        } else {
            markData.putExtra("time",exp.getText().toString());
        }

        setResult(1, markData);
        //---close the activity---
        finish();
    }

    @Override
    public void onBackPressed() {
        setResult(0);
        finish();
    }

    public void getLocationPermission(View view) {

        boolean checked =(((CheckBox) view).isChecked());
        //TextView tx = findViewById(R.id.textView2);
        //tx.setText("Banana");

        if(checked) {
            //tx.setText("Jabuk");
            if(ContextCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                chk = true;
            } else {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
            }
        }


    }

    public void setClock(int hour, int minute) {
        EditText exp = findViewById(R.id.editText);
        exp.setText(String.format("%d:%d", hour, minute));
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    /*
    //region GET GPS
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("Lat", "Status");
    }

    @Override
    public void onLocationChanged(Location location) {
        TextView tx = findViewById(R.id.textView2);
        tx.setText("Blalalalala");
}

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("Lat","Enabled");
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d("Lat","Disabled");
    }
    //endregion
    */

    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true;
                    chk = true;
                }
            }
        }
    }




}


