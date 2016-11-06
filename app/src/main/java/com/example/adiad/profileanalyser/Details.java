package com.example.adiad.profileanalyser;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * Created by code_eagle on 11/6/2016.
 */
public class Details extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);
        ImageView i=(ImageView)findViewById(R.id.img);
        i.setImageResource(R.drawable.health_chart);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
