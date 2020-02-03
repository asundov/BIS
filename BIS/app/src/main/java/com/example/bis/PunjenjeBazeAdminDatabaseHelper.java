package com.example.bis;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;


public class PunjenjeBazeAdminDatabaseHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "BOLNICKIPACIJENTI.db";
    public static final String TABLE_NAME = "BOLNICKIPACIJENTI_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "OIB";
    public static final String COL_3 = "OSOBNA";
    public static final String COL_4 = "IME";
    public static final String COL_5 = "PREZIME";
    public static final String COL_6 = "NARUCENO";
    public static final String COL_7 = "OBRADA";
    public static final String COL_8 = "GOTOVO";


    public PunjenjeBazeAdminDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, OIB INTEGER, OSOBNA INTEGER, IME STRING, PREZIME STRING, NARUCENO STRING, OBRADA STRING, GOTOVO STRING)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }




    //dobavljanje svih podataka
    public Cursor getAllData() {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }




    //dodavanje novih podataka
    public boolean insertData(String ID, String OIB, String OSOBNA, String IME, String PREZIME, String NARUCENO, String OBRADA, String GOTOVO) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentVales = new ContentValues();
        contentVales.put(COL_1, ID);
        contentVales.put(COL_2, OIB);
        contentVales.put(COL_3, OSOBNA);
        contentVales.put(COL_4, IME);
        contentVales.put(COL_5, PREZIME);
        contentVales.put(COL_6, NARUCENO);
        contentVales.put(COL_7, OBRADA);
        contentVales.put(COL_8, GOTOVO);
        long result = db.insert(TABLE_NAME, null, contentVales);
        if (result == -1)
            return false;
        else
            return true;
    }




    //update podataka
    public boolean updateData(String ID, String OIB, String OSOBNA, String IME, String PREZIME, String NARUCENO, String OBRADA, String GOTOVO) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentVales = new ContentValues();
        contentVales.put(COL_1, ID);
        contentVales.put(COL_2, OIB);
        contentVales.put(COL_3, OSOBNA);
        contentVales.put(COL_4, IME);
        contentVales.put(COL_5, PREZIME);
        contentVales.put(COL_6, NARUCENO);
        contentVales.put(COL_7, OBRADA);
        contentVales.put(COL_8, GOTOVO);

        db.update(TABLE_NAME, contentVales, "id = ?", new String[]{ID});
        return true;
    }




    //brisanje podataka
    public Integer deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[]{id});
    }




    // provjera upisa oiba i osobne
    public boolean provjera(String oib, String osobna) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from BOLNICKIPACIJENTI_TABLE where OIB=? and OSOBNA=?", new String[]{oib, osobna});
        if (cursor.getCount() > 0) return true;
        else return false;
    }




    // pacijent dodaje novu narudzbu
    public boolean update_Data_Pacijenti(String OIB, String OSOBNA, String NARUCENO) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from BOLNICKIPACIJENTI_TABLE where OIB=? and OSOBNA=?", new String[]{OIB, OSOBNA});
        ContentValues contentVales = new ContentValues();
        contentVales.put(COL_6, NARUCENO);

        db.update(TABLE_NAME, contentVales, "oib = ?", new String[]{OIB});
        return true;
    }
}