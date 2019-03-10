package com.example.asus.catalogmov;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.asus.catalogmov.adapter.FilmAdapter;
import com.example.asus.catalogmov.model.Film;

import java.util.ArrayList;

import static com.example.asus.catalogmov.MainActivity.INTENT_SEARCH;

public class ResSearch extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Film>>{

    private RecyclerView rview;
    private FilmAdapter filmAdapter;
    private String cari ;

    int columns = 0;

    static final String EXTRAS_MOVIE = "EXTRAS_MOVIE";

    public ResSearch() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res_search);

        rview = findViewById(R.id.rview);
        filmAdapter = new FilmAdapter(this);
        filmAdapter.notifyDataSetChanged();

        rview.setAdapter(filmAdapter);
        rview.setLayoutManager(new LinearLayoutManager(this));

        Bundle bundle = new Bundle();
        cari = getIntent().getStringExtra(INTENT_SEARCH);
        bundle.putString(EXTRAS_MOVIE, getIntent().getStringExtra(INTENT_SEARCH));
        getSupportLoaderManager().initLoader(2, bundle, this);
    }

    @NonNull
    @Override
    public Loader<ArrayList<Film>> onCreateLoader(int id, @Nullable Bundle args) {
        return new MyAsyntask(getApplicationContext(), 3, cari);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<Film>> loader, ArrayList<Film> data) {
        filmAdapter.setData(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<Film>> loader) {
        filmAdapter.setData(null);
    }
}
