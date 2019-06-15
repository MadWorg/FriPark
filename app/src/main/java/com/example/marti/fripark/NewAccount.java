package com.example.marti.fripark;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

public class NewAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);

        TextView textView = findViewById(R.id.textView5);
        String text = textView.getText().toString();

        SpannableString ss = new SpannableString(text);
        ForegroundColorSpan fcs = new ForegroundColorSpan(Color.RED);
        ss.setSpan(fcs,11, 14, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView.setText(ss);

    }
}
