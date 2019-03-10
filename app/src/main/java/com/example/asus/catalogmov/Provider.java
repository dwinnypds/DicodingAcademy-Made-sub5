package com.example.asus.catalogmov;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.asus.catalogmov.DB.FavoritHelpeeer;

import static com.example.asus.catalogmov.DB.DBContract.AUTHORITY;
import static com.example.asus.catalogmov.DB.DBContract.CONTENT_URI;
import static com.example.asus.catalogmov.DB.DBContract.TABLE_PAPORIT;

public class Provider extends ContentProvider{

    private static final int FAV = 1;
    private static final int FAV_ID = 2;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {

        sUriMatcher.addURI(AUTHORITY,TABLE_PAPORIT+ "/#",FAV_ID);
        sUriMatcher.addURI(AUTHORITY,TABLE_PAPORIT+ "",FAV);
    }

    private FavoritHelpeeer favoritHelpeeer;


    @Override
    public boolean onCreate() {
        favoritHelpeeer = new FavoritHelpeeer(getContext());
        favoritHelpeeer.open();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor;
        switch(sUriMatcher.match(uri)){
            case FAV:
                cursor = favoritHelpeeer.queryProvider();
                break;
            case FAV_ID:
                cursor = favoritHelpeeer.queryByIdProvider(uri.getLastPathSegment());
                break;
            default:
                cursor = null;
                break;
        }

        if (cursor != null){
            cursor.setNotificationUri(getContext().getContentResolver(),uri);
        }

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        long added ;

        switch (sUriMatcher.match(uri)){
            case FAV:
                added = favoritHelpeeer.insertProvider(values);
                break;
            default:
                added = 0;
                break;
        }

        if (added > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return Uri.parse(CONTENT_URI + "/" + added);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int deleted;
        switch (sUriMatcher.match(uri)) {
            case FAV_ID:
                deleted =  favoritHelpeeer.deleteProvider(uri.getLastPathSegment());
                break;
            default:
                deleted = 0;
                break;
        }

        if (deleted > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return deleted;
    }

    @Override
    public int update(@NonNull Uri uri,  ContentValues values,
                      String selection, String[] selectionArgs) {
        int updated ;
        switch (sUriMatcher.match(uri)) {
            case FAV_ID:
                updated =  favoritHelpeeer.updateProvider(uri.getLastPathSegment(),values);
                //  updated = FavHelper.updateP
                break;
            default:
                updated = 0;
                break;
        }

        if (updated > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return updated;
    }

}
