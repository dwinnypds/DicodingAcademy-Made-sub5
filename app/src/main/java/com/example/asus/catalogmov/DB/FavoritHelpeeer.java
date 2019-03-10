package com.example.asus.catalogmov.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import static android.provider.BaseColumns._ID;
import static com.example.asus.catalogmov.DB.DBContract.PaporitKolom.ID_MOVIE;
import static com.example.asus.catalogmov.DB.DBContract.TABLE_PAPORIT;

public class FavoritHelpeeer {
    private static String DB_TABLE = TABLE_PAPORIT;
    private Context context;
    private DBHelpeeeer dbHelpeeeer;
    private SQLiteDatabase database;

    public FavoritHelpeeer(Context context){
        this.context =context;
    }

    public FavoritHelpeeer open() throws SQLException {
        dbHelpeeeer = new DBHelpeeeer(context);
        database = dbHelpeeeer.getWritableDatabase();
        return this;
    }
    public void close() {
        dbHelpeeeer.close();
    }

    public Cursor queryByIdProvider(String id) {
        return database.query(DB_TABLE, null
                , ID_MOVIE + " = ?"
                , new String[]{id}
                , null
                , null
                , null
                , null);
    }

    public Cursor queryProvider() {
        return database.query(DB_TABLE
                , null
                , null
                , null
                , null
                , null
                , _ID + " DESC");
    }

    public long insertProvider(ContentValues values) {
        return database.insert(DB_TABLE, null, values);
    }

    public  int updateProvider(String id, ContentValues values) {
        return database.update(DB_TABLE, values, _ID + " = ?", new String[]{id});
    }

    public int deleteProvider(String id) {
        return database.delete(DB_TABLE, _ID + " = ?", new String[]{id});
    }
}
