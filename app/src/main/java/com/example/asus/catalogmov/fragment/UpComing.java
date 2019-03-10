package com.example.asus.catalogmov.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus.catalogmov.MyAsyntask;
import com.example.asus.catalogmov.R;
import com.example.asus.catalogmov.adapter.FilmAdapter;
import com.example.asus.catalogmov.model.Film;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpComing extends Fragment implements LoaderManager.LoaderCallbacks<ArrayList<Film>>{

    private RecyclerView rview;
    private FilmAdapter filmAdapter;
    private ArrayList<Film> data;

    static final String EXTRAS_MOVIE = "EXTRAS_MOVIE";

    public UpComing() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_up_coming, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rview = view.findViewById(R.id.rviewUp);
        filmAdapter = new FilmAdapter(getContext());
        filmAdapter.notifyDataSetChanged();

        rview.setAdapter(filmAdapter);
        rview.setLayoutManager(new LinearLayoutManager(getContext()));

        Log.e("dataBekup",data.toString());

        if (data.isEmpty()){

            Bundle bundle = new Bundle();
            getLoaderManager().initLoader(1, bundle, this);
        }else{

            filmAdapter.setData(data);

        }

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        data  = new ArrayList<>();
    }

    @NonNull
    @Override
    public Loader<ArrayList<Film>> onCreateLoader(int id, @NonNull Bundle args) {
        return new MyAsyntask(getActivity().getApplicationContext(), 2,"");
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<Film>> loader, ArrayList<Film> data2) {
      data.addAll(data2);
        filmAdapter.setData(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<Film>> loader) {
        filmAdapter.setData(null);
    }
}
