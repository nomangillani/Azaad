package com.example.azaad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.TextView;

public class Subsripton extends AppCompatActivity {
    Toolbar toolbar;
    TextView tittle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subsripton);
        toolbar=findViewById(R.id.navbar);
        setSupportActionBar(toolbar);
        tittle=toolbar.findViewById(R.id.navtittle);
        tittle.setText("Subscriptioin");
        tittle.setTextSize(18);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Drawable backarrow=getApplicationContext().getResources().getDrawable(R.drawable.backarrow);
        getSupportActionBar().setHomeAsUpIndicator(backarrow);

    }
}
