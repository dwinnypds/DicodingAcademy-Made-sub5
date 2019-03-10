package com.example.asus.catalogmov;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.asus.catalogmov.DB.FavoritHelpeeer;
import com.example.asus.catalogmov.model.FavModel;
import com.example.asus.catalogmov.model.Film;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.example.asus.catalogmov.DB.DBContract.CONTENT_URI;
import static com.example.asus.catalogmov.DB.DBContract.PaporitKolom.RILIS_KOLOM;
import static com.example.asus.catalogmov.DB.DBContract.PaporitKolom.SINOPSIS_KOLOM;
import static com.example.asus.catalogmov.DB.DBContract.PaporitKolom.ID_MOVIE;
import static com.example.asus.catalogmov.DB.DBContract.PaporitKolom.JUDUL_KOLOM;
import static com.example.asus.catalogmov.DB.DBContract.PaporitKolom.POPULER_KOLOM;
import static com.example.asus.catalogmov.DB.DBContract.PaporitKolom.POPULER_KOLOM;
import static com.example.asus.catalogmov.DB.DBContract.PaporitKolom.POSTER_KOLOM;
import static com.example.asus.catalogmov.DB.DBContract.PaporitKolom.RILIS_KOLOM;
import static com.example.asus.catalogmov.DB.DBContract.PaporitKolom.SINOPSIS_KOLOM;

public class DetailMov extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<Film> mData = new ArrayList<>();
    ImageView poster;
    TextView judul, pop, rilis, sinopsis;

    public static final String POSTER_IMAGE = "http://image.tmdb.org/t/p/w154";

    //paporit
    Button btn_paporittt;
    boolean isFavorit;
    private FavoritHelpeeer favoritHelpeeer;

    int idKunci;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_mov);

        poster = findViewById(R.id.img_poster);
        poster.setOnClickListener(this);

        judul = findViewById(R.id.txt_judul);
        pop = findViewById(R.id.txt_popz);
        rilis = findViewById(R.id.txt_releas_date);
        sinopsis = findViewById(R.id.txt_sinopsis);

        //favoriteeee
        btn_paporittt = findViewById(R.id.btn_paporit);
        btn_paporittt.setOnClickListener(this);


        judul.setText(getIntent().getStringExtra("judul"));
        pop.setText(getIntent().getStringExtra("populer"));
        rilis.setText(getIntent().getStringExtra("rilis"));
        sinopsis.setText(getIntent().getStringExtra("sinopsis"));
        Glide
                .with(DetailMov.this)
                .load("http://image.tmdb.org/t/p/w185/" + getIntent().getStringExtra("poster"))
                .into(poster);

        load();
        btn_paporittt.setOnClickListener(new tapPaporit());
    }

    //
    private void load() {
        favoritHelpeeer = new FavoritHelpeeer(getApplicationContext());
        favoritHelpeeer.open();
        Cursor cursor = favoritHelpeeer.queryByIdProvider(String.valueOf(getIntent().getIntExtra("id", 0)));
        Log.e("data", String.valueOf(cursor.getCount()));
        if (cursor.getCount() == 1) {
            cursor.moveToFirst();
            idKunci = cursor.getInt(cursor.getColumnIndex(_ID));
            Log.e("_ID ", String.valueOf(idKunci));

            isFavorit = true;
            btn_paporittt.setText("Hapus");
        } else {
            isFavorit = false;
            btn_paporittt.setText("Favorite");
        }
        favoritHelpeeer.close();
    }

    //
    private class tapPaporit implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            if (isFavorit) {
                Snackbar.make(v, R.string.untap_Msg, BaseTransientBottomBar.LENGTH_SHORT).show();
                isFavorit = false;
                delFav();
            } else {

                Snackbar.make(v, R.string.tap_Msg, BaseTransientBottomBar.LENGTH_SHORT).show();
                isFavorit = true;
                save();
                load();
            }
        }
    }

    private void save() {
        ContentValues values = new ContentValues();
        values.put(ID_MOVIE, getIntent().getIntExtra("id", 0));
        values.put(JUDUL_KOLOM, getIntent().getStringExtra("judul"));
        values.put(SINOPSIS_KOLOM, getIntent().getStringExtra("sinopsis"));
        values.put(POPULER_KOLOM, getIntent().getStringExtra("populer"));
        values.put(RILIS_KOLOM, getIntent().getStringExtra("rilis"));
        values.put(POSTER_KOLOM, getIntent().getStringExtra("poster"));

        getContentResolver().insert(CONTENT_URI, values);

        finish();
    }

    private void delFav() {
        Uri uri = Uri.parse(CONTENT_URI + "/" + idKunci);
        String string[] = {String.valueOf(getIntent().getIntExtra("id", 0))};
        getContentResolver().delete(uri, null, null);

        load();
        // finish();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_poster:
                Intent intent = new Intent(DetailMov.this, PosterView.class);
                intent.putExtra("dtlPoster", getIntent().getStringExtra("poster"));
                v.getContext().startActivity(intent);
                break;
        }
    }
}
