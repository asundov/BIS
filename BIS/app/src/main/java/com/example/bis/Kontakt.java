package com.example.bis;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Kontakt extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kontakt);

        //inicijalizacija i pozivanje imageVIew
        ImageView myImageView = (ImageView) findViewById(R.id.kontakt2); //iz nekog razloga ove dvi linije nisu bile potribne
        myImageView.setImageResource(R.drawable.kontaktbackground); //da bi se prikazala slika u novom layoutu. zasto??


        Button povratak = (Button) findViewById(R.id.povratak3);


        final String oib = getIntent().getStringExtra("OIB");
        final String osobna = getIntent().getStringExtra("OSOBNA");


        //onClick povratak na prethodni activity
        povratak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent povratak4 = new Intent(Kontakt.this, StartIzbornik.class);
                povratak4.putExtra("OIB", oib);
                povratak4.putExtra("OSOBNA", osobna);
                startActivity(povratak4);
            }
        });
    }
}
