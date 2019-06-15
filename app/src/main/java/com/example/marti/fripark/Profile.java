package com.example.marti.fripark;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class Profile extends AppCompatActivity {

    EditText newName;
    EditText newPass;
    TextView curName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }

    public void newName(View view) {
        try {

            String name =  newName.getText().toString();

            if(!name.equals("")) {
                curName.setText(name);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
