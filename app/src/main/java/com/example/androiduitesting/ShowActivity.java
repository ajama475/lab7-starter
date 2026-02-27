package com.example.androiduitesting;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ShowActivity extends AppCompatActivity {

    public static final String EXTRA_CITY_NAME = "city_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_activity);

        String cityName = getIntent().getStringExtra(EXTRA_CITY_NAME);

        TextView tv = findViewById(R.id.textView2);
        if (cityName != null) {
            tv.setText(cityName);
        }

        Button backButton = findViewById(R.id.button);
        backButton.setOnClickListener(view -> finish());
    }
}