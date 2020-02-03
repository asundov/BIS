package com.example.bis;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;


public class MojiNalazi extends AppCompatActivity {
    private TabLayout tablayout;
    private ViewPager viewpager;
    private TabItem tab1, tab2, tab3;
    public MojiNalaziPageAdapter pagerAdapter;

    MojiNalaziDatabaseHelper myDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mojinalazi);

        //dohvacanje upisanog oiba i osobne
        final String oib = getIntent().getStringExtra("OIB");
        final String osobna = getIntent().getStringExtra("OSOBNA");


        //inicijalizacija tabova
        tablayout = (TabLayout) findViewById(R.id.tablayout);
        tab1 = (TabItem) findViewById(R.id.tab1);
        tab2 = (TabItem) findViewById(R.id.tab2);
        tab3 = (TabItem) findViewById(R.id.tab3);
        viewpager = (ViewPager) findViewById(R.id.viewpager);


        //deklariranje baze
        myDb = new MojiNalaziDatabaseHelper(this);


        //cemu sluzi viewpager?
        pagerAdapter = new MojiNalaziPageAdapter(getSupportFragmentManager(), tablayout.getTabCount());
        viewpager.setAdapter(pagerAdapter);


        //dohvacanje podataka iz baze, spremanje u sharedpreferences kako bi se pojavili odmah pri otvaranju taba
        pagerAdapter.notifyDataSetChanged();
        Cursor res = myDb.getAllData(oib, osobna);
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append("Ime            : " + res.getString(3) + "\n");
            buffer.append("Prezime    : " + res.getString(4) + "\n");
            buffer.append("Naruceno : " + res.getString(5) + "\n\n");
        }
        String tekst = buffer.toString();

        final SharedPreferences prefs = getSharedPreferences("tekst", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("tekst", tekst);
        editor.commit();


        //botun za povratak
        Button povratak = (Button) findViewById(R.id.povratak4);
        povratak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent start_intent = new Intent(MojiNalazi.this, StartIzbornik.class);
                start_intent.putExtra("OIB", oib);
                start_intent.putExtra("OSOBNA", osobna);
                startActivity(start_intent);
            }
        });


        //klikanje na jednu od linija listViewa
        tablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewpager.setCurrentItem((tab.getPosition()));

                //dobavlja trenutnu poziciju otvorena tab-a
                if (tab.getPosition() == 0) {
                    pagerAdapter.notifyDataSetChanged();
                    Cursor res = myDb.getAllData(oib, osobna);


                    //ukoliko je prvi tab ispisi narucene pretrage
                    if (res.getCount() == 0) {
                        showMessage_naruceno("Error", "Nothing found");
                        return;
                    }
                    StringBuffer buffer = new StringBuffer();
                    while (res.moveToNext()) {
                        buffer.append("Ime            : " + res.getString(3) + "\n");
                        buffer.append("Prezime    : " + res.getString(4) + "\n");
                        buffer.append("Naruceno : " + res.getString(5) + "\n\n");
                    }
                    showMessage_naruceno("Data", buffer.toString());
                }


                //drugi tab ispisuje nalaze u obradi
                else if (tab.getPosition() == 1) {
                    pagerAdapter.notifyDataSetChanged();
                    Cursor res = myDb.getAllData(oib, osobna);
                    if (res.getCount() == 0) {
                        showMessage_obrada("Error", "Nemate nalaze u obradi");
                        return;
                    }
                    StringBuffer buffer = new StringBuffer();
                    while (res.moveToNext()) {
                        buffer.append("Ime            : " + res.getString(3) + "\n");
                        buffer.append("Prezime    : " + res.getString(4) + "\n");
                        buffer.append("Obrada      : " + res.getString(6) + "\n\n");
                    }
                    showMessage_obrada("Data", buffer.toString());
                }


                //treci tab ispisuje gotove nalaze
                else if (tab.getPosition() == 2) {
                    pagerAdapter.notifyDataSetChanged();
                    Cursor res = myDb.getAllData(oib, osobna);
                    if (res.getCount() == 0) {
                        showMessage_gotovo("Error", "Nothing found");
                        return;
                    }
                    StringBuffer buffer = new StringBuffer();
                    while (res.moveToNext()) {
                        buffer.append("Ime            : " + res.getString(3) + "\n");
                        buffer.append("Prezime    : " + res.getString(4) + "\n");
                        buffer.append("Gotovo      : " + res.getString(7) + "\n\n");
                    }
                    showMessage_gotovo("Data", buffer.toString());
                }
            }


            public void showMessage_naruceno(String title, String message) {

                TextView tekst1 = (TextView) findViewById(R.id.tabtext1);
                tekst1.setText(message);
            }

            public void showMessage_obrada(String title, String message) {

                TextView tekst2 = (TextView) findViewById(R.id.tabtext2);
                tekst2.setText(message);
            }

            public void showMessage_gotovo(String title, String message) {

                TextView tekst3 = (TextView) findViewById(R.id.tabtext3);
                tekst3.setText(message);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));
    }
}
