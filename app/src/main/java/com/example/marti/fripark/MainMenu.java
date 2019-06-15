package com.example.marti.fripark;

import android.content.Intent;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }


    //start map with no markers
    public void givePark(View view) {
        Intent openMap = new Intent(this, OpenMap.class);
        openMap.putExtra("mode",true);
        startActivity(openMap);
    }

    //start map with all available markers
    public void findPark(View view) {
        Intent openMap = new Intent(this, OpenMap.class);
        startActivity(openMap);
    }

    public void profile(View view) {
        Intent profile = new Intent(this, Profile.class);
        startActivity(profile);
    }


}

