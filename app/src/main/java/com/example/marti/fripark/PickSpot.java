package com.example.marti.fripark;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PickSpot extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_spot);

        String [] data = getIntent().getStringExtra("info").split("//");
        System.out.println(data[1]);

        String user = data[1].split(":")[1];
        String time = data[0].split(" ")[2];

        TextView ownerUser = findViewById(R.id.owner);
        ownerUser.setText(user);

        TextView timer = findViewById(R.id.timeUnlock);
        timer.setText(time);

    }

    public void claim(View view) {
        setResult(2);
        finish();
    }

    @Override
    public void onBackPressed() {
        setResult(0);
        finish();
    }
}
