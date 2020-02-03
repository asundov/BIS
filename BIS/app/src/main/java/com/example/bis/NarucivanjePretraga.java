package com.example.bis;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;


public class NarucivanjePretraga extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.narucivanje);


        final ListView myListView = (ListView) findViewById(R.id.listView);
        final String oib = getIntent().getStringExtra("OIB");
        final String osobna = getIntent().getStringExtra("OSOBNA");


        //povratak
        Button povratak = (Button) findViewById(R.id.povratak4);
        povratak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent start_intent = new Intent(NarucivanjePretraga.this, StartIzbornik.class);
                start_intent.putExtra("OIB", oib);
                start_intent.putExtra("OSOBNA", osobna);
                startActivity(start_intent);
            }
        });


        //dohvacanje upisanih oib i osobne
        final SharedPreferences prefs = getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("oib", oib);
        editor.commit();
        editor.putString("osobna", osobna);
        editor.commit();


        //popunjavanje imena item-a
        NarucivanjePretragaDataStorage.fillData();
        myListView.setAdapter(new NarucivanjePretragaMyAdapter(getApplicationContext()));


        //klikanje na item listViewa
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String name = (String) parent.getItemAtPosition(position);
                Toast.makeText(NarucivanjePretraga.this, "Za detaljnije informacije kliknite na strelicu" + name, Toast.LENGTH_LONG).show();
            }

        });
    }
}
