package com.example.bis;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import static android.preference.PreferenceManager.getDefaultSharedPreferences;
import static com.example.bis.NarucivanjePretragaDataStorage.listViewData;


public class NarucivanjePretragaMyAdapter extends BaseAdapter {


    private Context myContext;
    PunjenjeBazeAdminDatabaseHelper myDb;

    public NarucivanjePretragaMyAdapter(Context myContext) {
        this.myContext = myContext;
        myDb = new PunjenjeBazeAdminDatabaseHelper(myContext);
    }

    @Override
    public int getCount() {
        return listViewData.size();
    }

    @Override
    public Object getItem(int i) {
        return listViewData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {




        LayoutInflater myInflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View itemView = myInflater.inflate(R.layout.narucivanje2, viewGroup, false);


        TextView textView = itemView.findViewById(R.id.textView);
        String currentDataItem = listViewData.get(i);
        textView.setText(String.valueOf(currentDataItem));


        final SharedPreferences prefs = getDefaultSharedPreferences(myContext);
        final String oib = prefs.getString("oib", "");
        final String osobna = prefs.getString("osobna", "");
        final Button naruci = itemView.findViewById(R.id.naruci);
        naruci.setVisibility(View.GONE);


        // naruciiiii
        naruci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ime_odjela = listViewData.get(i).toString();
                Toast.makeText(myContext, ime_odjela, Toast.LENGTH_LONG).show();

                //Toast.makeText(myContext, "Naručili ste pretragu! Narucena je " + name, Toast.LENGTH_LONG).show();
                myDb.update_Data_Pacijenti(oib, osobna, ime_odjela);
            }

        });



        // printanjeee
        final Button print = itemView.findViewById(R.id.print);
        print.setVisibility(View.GONE);
        print.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(myContext, "Uputnica se printa!", Toast.LENGTH_LONG).show();
                print.setBackgroundResource(R.drawable.buttons_checked);
            }
        });



        // Kontakt
        final Button kontakt = itemView.findViewById(R.id.contact);
        kontakt.setVisibility(View.GONE);
        kontakt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(myContext, "Preusmjeravamo vas na Kontakt informacije...", Toast.LENGTH_LONG).show();
                Intent kontaktIntent = new Intent(itemView.getContext(), Kontakt.class);
                itemView.getContext().startActivity(kontaktIntent);
            }
        });



        // otvaranje izbornika
        final Button botun = itemView.findViewById(R.id.button_listview);
        botun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                naruci.setVisibility((naruci.getVisibility() == View.VISIBLE)
                        ? View.GONE
                        : View.VISIBLE);

                print.setVisibility((print.getVisibility() == View.VISIBLE)
                        ? View.GONE
                        : View.VISIBLE);

                kontakt.setVisibility((kontakt.getVisibility() == View.VISIBLE)
                        ? View.GONE
                        : View.VISIBLE);
            }
        });
        return itemView;
    }
}