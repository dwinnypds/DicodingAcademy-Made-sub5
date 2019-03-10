package com.example.asus.catalogmov.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelpeeeer extends SQLiteOpenHelper {
    public static int DB_VERSION = 1;

    public static String NAMA_DATABASE = "DB_Movieee";

    public DBHelpeeeer(Context context) {
        super(context, NAMA_DATABASE, null, DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "create table " + DBContract.TABLE_PAPORIT + " (" +
                DBContract.PaporitKolom._ID+ " integer primary key autoincrement, " +
                DBContract.PaporitKolom.ID_MOVIE+ " integer, " +
                DBContract.PaporitKolom.JUDUL_KOLOM + " text not null, " +
                DBContract.PaporitKolom.SINOPSIS_KOLOM + " text not null, " +
                DBContract.PaporitKolom.POPULER_KOLOM + " text not null, " +
                DBContract.PaporitKolom.RILIS_KOLOM + " text not null, " +
                DBContract.PaporitKolom.POSTER_KOLOM + " text not null );";

        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ DBContract.TABLE_PAPORIT);
        onCreate(db);
    }
}
