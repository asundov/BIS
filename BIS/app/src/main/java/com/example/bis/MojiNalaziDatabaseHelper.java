package com.example.bis;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MojiNalaziDatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="BOLNICKIPACIJENTI.db";
    public static final String TABLE_NAME="BOLNICKIPACIJENTI_table";
    public static final String COL_1 = "OIB";
    public static final String COL_2 = "BROJ OSOBNE ISKAZNICE";
    public static final String COL_3 = "IME";
    public static final String COL_4 = "PREZIME";
    public static final String COL_5 = "NARUCENO";
    public static final String COL_6= "OBRADA";
    public static final String COL_7 = "GOTOVO";


    public MojiNalaziDatabaseHelper(Context myContext) {
        super(myContext, DATABASE_NAME, null, 1);
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


    public Cursor getAllData(String oib, String osobna) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from BOLNICKIPACIJENTI_TABLE where OIB=? and OSOBNA=?", new String[]{oib, osobna});
        return res;
    }
}
