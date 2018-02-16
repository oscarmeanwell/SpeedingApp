package com.example.oscarmeanwell.sensorslab;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setTitle("Drive Safety");
        setContentView(R.layout.splash);

        ImageView img= (ImageView) findViewById(R.id.image);
        img.setImageResource(R.drawable.logo);
        int secondsDelayed = 1;

        new Handler().postDelayed(new Runnable() {
            public void run() {
                startActivity(new Intent(Splash.this, MainActivity.class));
                finish();
            }
        }, secondsDelayed * 1000);
    }
}
