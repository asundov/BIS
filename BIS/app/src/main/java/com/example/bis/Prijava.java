package com.example.bis;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


public class Prijava extends AppCompatActivity {

    PunjenjeBazeAdminDatabaseHelper myDb;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prijava);


        final EditText oib_unos_edittext = (EditText) findViewById(R.id.unesiOIB);
        final EditText osobna_unos_edittext = (EditText) findViewById(R.id.unesiOSOBNU);
        Button prijava = (Button) findViewById(R.id.prijava);
        myDb = new PunjenjeBazeAdminDatabaseHelper(this);


        // da se tipkovnica ne pali cim udjemo na ekran
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);




        //na dodir plaintekstovi postaju blank
        oib_unos_edittext.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                oib_unos_edittext.setText("");

                return false;
            }
        });
        osobna_unos_edittext.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                osobna_unos_edittext.setText("");

                return false;
            }
        });




        prijava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oib = oib_unos_edittext.getText().toString();
                String osobna = osobna_unos_edittext.getText().toString();

                if (oib.equals("") || osobna.equals("")) {
                    oib_unos_edittext.setText("");
                    osobna_unos_edittext.setText("");
                    AlertDialog.Builder builder = new AlertDialog.Builder(Prijava.this);
                    builder.setCancelable(true);
                    builder.setTitle("Error");
                    builder.setMessage("Nepotpuni podaci! Unesite ponovno!!");
                    builder.show();
                }


                Boolean provjerica = myDb.provjera(oib, osobna);



               if (provjerica == true || (oib.equals("0") && osobna.equals("0"))) {
                    Intent mojintent = new Intent(Prijava.this, StartIzbornik.class);
                    mojintent.putExtra("OIB", oib);
                    mojintent.putExtra("OSOBNA", osobna);
                    startActivity(mojintent);
                }
                else {
                    oib_unos_edittext.setText("");
                    osobna_unos_edittext.setText("");
                    AlertDialog.Builder builder = new AlertDialog.Builder(Prijava.this);
                    builder.setCancelable(true);
                    builder.setTitle("Error");
                    builder.setMessage("Pogrešni podaci! Molimo unesite ponovno!");
                    builder.show();
                }
            }
        });
    }
}










