package com.example.favoritemoviemodul;

import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.favoritemoviemodul.Adapter.FavAdapter;

import static com.example.favoritemoviemodul.DB.DBContract.CONTENT_URI;

public class FavModulActivity extends AppCompatActivity {

    private RecyclerView rvPap;
    private FavAdapter favAdapter;
    private Cursor list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_modul);

        favAdapter = new FavAdapter(list);

        rvPap = findViewById(R.id.rvPapo);

        rvPap.setLayoutManager(new LinearLayoutManager(this));
        rvPap.addItemDecoration(new DividerItemDecoration(rvPap.getContext(), DividerItemDecoration.VERTICAL));
        rvPap.setAdapter(favAdapter);
        new load().execute();
    }

    private class load extends AsyncTask<Void, Void, Cursor> {
        @Override
        protected Cursor doInBackground(Void... voids) {
            return getContentResolver().query(
                    CONTENT_URI,
                    null,
                    null,
                    null,
                    null
            );
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);

            list = cursor;
            Log.e("favCursor", String.valueOf(cursor.getCount()));
            favAdapter.replaceAll(list);
        }
    }
}
