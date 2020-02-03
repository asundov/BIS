package com.example.bis;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class OpcenitooMikrobiologiji extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opcenito);

        ImageView myImageView = (ImageView) findViewById(R.id.imageView2);
        myImageView.setImageResource(R.drawable.microbiology);



        final String oib = getIntent().getStringExtra("OIB");
        final String osobna = getIntent().getStringExtra("OSOBNA");
        final Button povratak = (Button) findViewById(R.id.povratak2);


        povratak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent povratak3 = new Intent(OpcenitooMikrobiologiji.this, StartIzbornik.class);
                povratak3.putExtra("OIB", oib);
                povratak3.putExtra("OSOBNA", osobna);
                startActivity(povratak3);
            }
        });

    }
}


