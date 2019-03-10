package com.example.favoritemoviemodul;

import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.favoritemoviemodul.model.FavModel;

import static com.example.favoritemoviemodul.API.ADB.POSTER_IMAGE;

public class DetailFav extends AppCompatActivity {

    ImageView poster;
    TextView judul, pop, rilis, sinopsis;

    private FavModel favoriteModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_fav);

        poster = findViewById(R.id.img_poster);

        judul = findViewById(R.id.txt_judul);
        pop = findViewById(R.id.txt_popz);
        rilis = findViewById(R.id.txt_releas_date);
        sinopsis = findViewById(R.id.txt_sinopsis);

        loadData();
        storeData();

    }
    private void storeData() {
        if (favoriteModel== null) return;

        judul.setText(favoriteModel.getJudul());
        pop.setText(String.valueOf(favoriteModel.getPopularity()));
        rilis.setText(favoriteModel.getReleasDate());
        sinopsis.setText(favoriteModel.getSinopsis());
        Glide.with(this)
                .load(POSTER_IMAGE + favoriteModel.getPoster())
                .into(poster);
    }

    private void loadData() {
        Uri uri = getIntent().getData();
        if (uri == null) return;
        Cursor cursor = getContentResolver().query(
                uri,
                null,
                null,
                null,
                null
        );

        if (cursor != null) {
            if (cursor.moveToFirst()) favoriteModel = new FavModel(cursor);
            cursor.close();
        }
    }

}
