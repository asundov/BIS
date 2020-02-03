package com.example.bis;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;


public class MojiNalaziTab1 extends Fragment {


    public MojiNalaziTab1() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View tabview = inflater.inflate(R.layout.mojinalazi_fragment_tab1, container, false);
        TextView tekst1 = (TextView) tabview.findViewById(R.id.tabtext1);

        SharedPreferences preferences = this.getActivity().getSharedPreferences("tekst", Context.MODE_PRIVATE);
        String storedPreference = preferences.getString("tekst", "Nema");

        tekst1.setText(storedPreference);

        return tabview;
    }
}
