package com.example.bis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //fja za prebacivanje i fadeanje activitya nakon 3s
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mojintent = new Intent(MainActivity.this, Prijava.class);
                startActivity(mojintent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
            }
        }, 2000);
    }

}







