package com.example.asus.catalogmov.fragment;


import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.asus.catalogmov.R;
import com.example.asus.catalogmov.adapter.FavoriteAdapter;

import junit.framework.Assert;

import static com.example.asus.catalogmov.DB.DBContract.CONTENT_URI;

/**
 * A simple {@link Fragment} subclass.
 */
public class Paporit extends Fragment {

    private RecyclerView rview;
    static final String EXTRAS_MOVIE = "EXTRAS_MOVIES";
    private Cursor listPap;
    private FavoriteAdapter favoriteAdapter;
    //private ProgressBar progressBar;

    public Paporit() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = getLayoutInflater().inflate(R.layout.fragment_paporit, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rview = view.findViewById(R.id.rviewPap);
        favoriteAdapter = new FavoriteAdapter(getContext());

        //progressBar = view.findViewById(R.id.progresbaaaar);
        rview.setLayoutManager(new LinearLayoutManager(getActivity()));
        rview.setAdapter(favoriteAdapter);
        new LoadPaporitAsyntask().execute();
    }

    private  class LoadPaporitAsyntask extends AsyncTask<Void, Void, Cursor>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Cursor doInBackground (Void... voids){
            return  getActivity().getContentResolver().
                    query(CONTENT_URI, null, null, null,null);
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);

            listPap = cursor;
            favoriteAdapter.setListNotes(listPap);
            Log.e("cursor", String.valueOf(cursor.getCount()));
            favoriteAdapter.notifyDataSetChanged();
        }
    }


}
