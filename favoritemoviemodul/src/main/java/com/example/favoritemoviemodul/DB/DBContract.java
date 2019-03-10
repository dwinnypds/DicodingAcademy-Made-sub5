package com.example.favoritemoviemodul.DB;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

public class DBContract {
    public static String TABLE_PAPORIT = "favorit";
    public static final String AUTHORITY = "com.example.asus.catalogmov";

    public static final class PaporitKolom implements BaseColumns {
        public static String ID_MOVIE= "id_movies";
        public static String POPULER_KOLOM = "pop";
        public static String JUDUL_KOLOM = "judul";
        public static String SINOPSIS_KOLOM = "descr";
        public static String RILIS_KOLOM = "releaseDate";
        public static String POSTER_KOLOM = "poster";
    }

    public static final Uri CONTENT_URI = new Uri.Builder().scheme("content")
            .authority(AUTHORITY)
            .appendPath(TABLE_PAPORIT)
            .build();

    public static String getColumnString(Cursor cursor, String columnName) {
        return cursor.getString( cursor.getColumnIndex(columnName) );
    }

    public static int getColumnInt(Cursor cursor, String columnName) {
        return cursor.getInt( cursor.getColumnIndex(columnName) );
    }

}
