package com.example.bis;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class PunjenjeBazeAdmin extends AppCompatActivity {

    PunjenjeBazeAdminDatabaseHelper myDb;
    EditText ID, OIB, OSOBNA, IME, PREZIME, NARUCENO, OBRADA, GOTOVO;
    Button botun, viewall, update, delete, povratak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.punjenje_baze);


        myDb = new PunjenjeBazeAdminDatabaseHelper(this);

        ID = (EditText) findViewById(R.id.ID2);
        OIB = (EditText) findViewById(R.id.OIB2);
        OSOBNA = (EditText) findViewById(R.id.OSOBNA2);
        IME = (EditText) findViewById(R.id.IME2);
        PREZIME = (EditText) findViewById(R.id.PREZIME2);
        NARUCENO = (EditText) findViewById(R.id.NARUCENO2);
        OBRADA = (EditText) findViewById(R.id.OBRADA2);
        GOTOVO = (EditText) findViewById(R.id.GOTOVI2);
        update = (Button) findViewById(R.id.update);
        botun = (Button) findViewById(R.id.button);
        viewall = (Button) findViewById(R.id.viewall);
        delete = (Button) findViewById(R.id.delete);
        TextView tekst = (TextView) findViewById(R.id.textView);
        povratak = (Button)findViewById(R.id.povratak);

        addData();
        viewAll();
        UpdateData();
        deleteData();

        final String oib = getIntent().getStringExtra("OIB");
        final String osobna = getIntent().getStringExtra("OSOBNA");

        povratak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent povratak = new Intent(PunjenjeBazeAdmin.this, StartIzbornik.class);
                povratak.putExtra("OIB", oib);
                povratak.putExtra("OSOBNA", osobna);
                startActivity(povratak);
            }
        });

    }


    public void viewAll() {
        viewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDb.getAllData();
                if (res.getCount() == 0) {
                    showMessage("Error", "Nothing found");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("ID :" + res.getString(0) + "\n");
                    buffer.append("OIB :" + res.getString(1) + "\n");
                    buffer.append("OSOBNA :" + res.getString(2) + "\n");
                    buffer.append("IME :" + res.getString(3) + "\n");
                    buffer.append("PREZIME :" + res.getString(4) + "\n");
                    buffer.append("NARUCENO :" + res.getString(5) + "\n");
                    buffer.append("OBRADA :" + res.getString(6) + "\n");
                    buffer.append("GOTOVO :" + res.getString(7) + "\n\n");
                }
                showMessage("Data", buffer.toString());
            }
        });
    }

    public void showMessage(String title, String message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }


    public void deleteData() {
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deletedRows = myDb.deleteData(ID.getText().toString());
                if (deletedRows > 0)
                    Toast.makeText(PunjenjeBazeAdmin.this, "ok", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(PunjenjeBazeAdmin.this, "not ok", Toast.LENGTH_LONG).show();
            }
        });
    }


    public void UpdateData() {
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdated = myDb.updateData(ID.getText().toString(), OIB.getText().toString(), OSOBNA.getText().toString(), IME.getText().toString(), PREZIME.getText().toString(), NARUCENO.getText().toString(), OBRADA.getText().toString(), GOTOVO.getText().toString());
                if (isUpdated == true)
                    Toast.makeText(PunjenjeBazeAdmin.this, "ok", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(PunjenjeBazeAdmin.this, "not ok", Toast.LENGTH_LONG).show();
            }
        });
    }


    public void addData() {

        botun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = myDb.insertData(ID.getText().toString(), OIB.getText().toString(), OSOBNA.getText().toString(), IME.getText().toString(), PREZIME.getText().toString(), NARUCENO.getText().toString(), OBRADA.getText().toString(), GOTOVO.getText().toString());

                if (isInserted = true)
                    Toast.makeText(PunjenjeBazeAdmin.this, "ok", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(PunjenjeBazeAdmin.this, "not ok", Toast.LENGTH_LONG).show();
            }
        });
    }
}