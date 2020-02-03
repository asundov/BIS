package com.example.bis;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import static android.view.View.GONE;


public class StartIzbornik extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);


        Button mojiNalazi = (Button) findViewById(R.id.mojiNalazi);
        Button opcenitobotun = (Button) findViewById(R.id.Opcenito);
        Button narucivanjebotun = (Button) findViewById(R.id.narucivanjePacijenata);
        Button kontaktbotun = (Button) findViewById(R.id.Kontakt);
        Button povratakbotun = (Button) findViewById(R.id.povratak);
        final Button glupopunjene = (Button) findViewById(R.id.glupibotunzapunjenje);


        //povukli smo podatke s proslog intenta i usporedili ih s vrijednostima admin-a
        final String oib = getIntent().getStringExtra("OIB");
        final String osobna = getIntent().getStringExtra("OSOBNA");


        if (oib.equals("0") && osobna.equals("0"))
            glupopunjene.setVisibility(View.VISIBLE);
        else
            glupopunjene.setVisibility(GONE);


        mojiNalazi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nalazi = new Intent(StartIzbornik.this, MojiNalazi.class);
                nalazi.putExtra("OIB", oib);
                nalazi.putExtra("OSOBNA", osobna);
                startActivity(nalazi);
            }
        });


        opcenitobotun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent opcenito = new Intent(StartIzbornik.this, OpcenitooMikrobiologiji.class);
                opcenito.putExtra("OIB", oib);
                opcenito.putExtra("OSOBNA", osobna);
                startActivity(opcenito);
            }
        });


        narucivanjebotun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent narucivanje_intent = new Intent(StartIzbornik.this, NarucivanjePretraga.class);
                narucivanje_intent.putExtra("OIB", oib);
                narucivanje_intent.putExtra("OSOBNA", osobna);
                startActivity(narucivanje_intent);
            }
        });


        kontaktbotun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kontakt2 = new Intent(StartIzbornik.this, Kontakt.class);
                kontakt2.putExtra("OIB", oib);
                kontakt2.putExtra("OSOBNA", osobna);
                startActivity(kontakt2);
            }
        });


        povratakbotun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent povratak2 = new Intent(StartIzbornik.this, Prijava.class);
                povratak2.putExtra("OIB", oib);
                povratak2.putExtra("OSOBNA", osobna);
                startActivity(povratak2);
            }
        });


        glupopunjene.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent noi = new Intent(StartIzbornik.this, PunjenjeBazeAdmin.class);
                noi.putExtra("OIB", oib);
                noi.putExtra("OSOBNA", osobna);
                startActivity(noi);
            }
        });
    }
}
