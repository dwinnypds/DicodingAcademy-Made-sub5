package com.example.asus.catalogmov;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class PosterView extends AppCompatActivity {

    ImageView poster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poster_view);

        poster = findViewById(R.id.img_poster);
        Glide
                .with(PosterView.this)
                .load("http://image.tmdb.org/t/p/original/"+getIntent().getStringExtra("dtlPoster"))
                .into(poster);
    }
}
