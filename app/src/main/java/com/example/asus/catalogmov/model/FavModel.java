package com.example.asus.catalogmov.model;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import static com.example.asus.catalogmov.DB.DBContract.PaporitKolom.RILIS_KOLOM;
import static com.example.asus.catalogmov.DB.DBContract.PaporitKolom.SINOPSIS_KOLOM;
import static com.example.asus.catalogmov.DB.DBContract.PaporitKolom.ID_MOVIE;
import static com.example.asus.catalogmov.DB.DBContract.PaporitKolom.JUDUL_KOLOM;
import static com.example.asus.catalogmov.DB.DBContract.PaporitKolom.POPULER_KOLOM;
import static com.example.asus.catalogmov.DB.DBContract.PaporitKolom.POPULER_KOLOM;
import static com.example.asus.catalogmov.DB.DBContract.PaporitKolom.POSTER_KOLOM;
import static com.example.asus.catalogmov.DB.DBContract.PaporitKolom.RILIS_KOLOM;
import static com.example.asus.catalogmov.DB.DBContract.PaporitKolom.SINOPSIS_KOLOM;
import static com.example.asus.catalogmov.DB.DBContract.getColumnInt;
import static com.example.asus.catalogmov.DB.DBContract.getColumnString;

public class FavModel implements Parcelable{
    private int id;
    private String popularity;
    private String judul;
    private String poster;
    private String sinopsis;
    private  String releasDate;

    public String getReleasDate() {
        return releasDate;
    }

    public void setReleasDate(String releasDate) {
        this.releasDate = releasDate;
    }

    public static final Creator<FavModel> CREATOR = new Creator<FavModel>() {
        @Override
        public FavModel createFromParcel(Parcel in) {
            return new FavModel(in);
        }

        @Override
        public FavModel[] newArray(int size) {
            return new FavModel[size];
        }
    };

    public FavModel() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public static Creator<FavModel> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(this.judul);
        dest.writeString(this.poster);
        dest.writeString(this.sinopsis);
        dest.writeString(this.releasDate);
        dest.writeInt(this.id);
        dest.writeString(this.popularity);


    }

    protected FavModel(Parcel in){

        this.judul= in.readString();
        this.poster= in.readString();
        this.sinopsis= in.readString();
        this.releasDate= in.readString();
        this.id= in.readInt();
        this.popularity= in.readString();

    }

    public FavModel(Cursor cursor){

        this.judul=getColumnString(cursor, JUDUL_KOLOM);
        Log.e("judul", judul);
        this.poster= getColumnString(cursor, POSTER_KOLOM);
        this.sinopsis= getColumnString(cursor, SINOPSIS_KOLOM);
        this.releasDate= getColumnString(cursor, RILIS_KOLOM);
        this.id= getColumnInt(cursor, ID_MOVIE);
        this.popularity= getColumnString(cursor, POPULER_KOLOM);
    }
}
