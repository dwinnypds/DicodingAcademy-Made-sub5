package com.example.asus.catalogmov.model;

import android.os.Parcel;
import android.util.Log;

import org.json.JSONObject;

public class Film {

    String Poster, Judul,  Populer, Rilis, Sinopsis;
    private int id;

    public Film(JSONObject movie) {

        try{
            int id = movie.getInt("id");
            String judul = movie.getString("title");
            String poster = movie.getString("poster_path");
            String sinopsis = movie.getString("overview");
            String releaseDate = movie.getString("release_date");
            String populer = movie.getString("popularity");


            this.id = id;
            this.Populer = populer;
            this.Judul = judul;
            this.Sinopsis = sinopsis;
            this.Rilis = releaseDate;
            this.Poster = poster;

        }
        catch (Exception e){
            Log.e("errorfilm", e.getMessage());
        }

    }

    protected Film (Parcel intejer){
        id = intejer.readInt();
        Populer = intejer.readString();
        Judul = intejer.readString();
        Poster = intejer.readString();
        Sinopsis = intejer.readString();
        Rilis = intejer.readString();
    }

    public int getId(){return id;}
    public void setId(int id) {this.id = id; }

    public String getPoster() {
        return Poster;
    }
    public void setPoster(String poster) {
        Poster = poster;
    }

    public String getJudul() {
        return Judul;
    }
    public void setJudul(String judul) { Judul = judul; }

    public String getRilis() {
        return Rilis;
    }
    public void setRilis(String rilis) {
        Rilis = rilis;
    }

    public String getPopuler() {
        return Populer;
    }
    public void setPopuler(String populer) { Populer = populer; }

    public String getSinopsis() { return Sinopsis; }
    public void setSinopsis(String sinopsis) { Sinopsis = sinopsis; }


}
