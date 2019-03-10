package com.example.asus.catalogmov;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.example.asus.catalogmov.model.Film;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MyAsyntask extends AsyncTaskLoader<ArrayList<Film>>{


    private ArrayList<Film> mData = new ArrayList<>();
    private boolean mHasResult = false;

    private String mKumpulan_movie;
    public String API = "https://api.themoviedb.org/3/movie/popular?api_key=94fe5b9f899ce456efdc69788f7897ef&language=en-US&page=1";
    public String url = "hmm";
    String searchApi = "\n" +
            "https://api.themoviedb.org/3/search/movie?api_key=94fe5b9f899ce456efdc69788f7897ef&language=en-US&query=";
    String nowApi = "https://api.themoviedb.org/3/movie/now_playing?api_key=94fe5b9f899ce456efdc69788f7897ef&language=en-US";
    public  static final String upcomingApi = "https://api.themoviedb.org/3/movie/upcoming?api_key=94fe5b9f899ce456efdc69788f7897ef&language=en-US";
    String end = "&page=1&include_adult=false";


    public MyAsyntask(final Context context, int index, String cari) {
        super(context);

        onContentChanged();

        Log.e("cari", "no");
        switch (index){
            case 1 : url = nowApi;
                break;
            case 2 : url = upcomingApi;
                break;
            case 3 : url = searchApi + cari + end;
                break;
        }

    }

    @Override
    public  void deliverResult(ArrayList<Film> data){
        mData = data;
        mHasResult = true;
        super.deliverResult(data);
    }


    @Override
    protected void onStartLoading() {
        if (takeContentChanged())
            forceLoad();
        else if (mHasResult)
            deliverResult(mData);
    }

    @Override
    protected void onReset() {
        super.onReset();
        onStopLoading();
        if (mHasResult) {
            onReleasResources(mData);
            mData = null;
            mHasResult = false;
        }
    }
    private static final String API_KEY = "94fe5b9f899ce456efdc69788f7897ef";

    @Override
    public ArrayList<Film> loadInBackground() {
        SyncHttpClient client = new SyncHttpClient();
        //final ArrayList<Film> movieItemes = new ArrayList<>();

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                setUseSynchronousMode(true);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responObject = new JSONObject(result);
                    JSONArray list = responObject.getJSONArray("results");

                    //Res_Search
                    if (url.equals(upcomingApi)){
                        Log.e("getDataUpc", list.toString());
                    } else if (url.equals(nowApi)){
                        Log.e("getDataNow", list.toString());
                    }else {
                        Log.e("getDataSearch", list.toString());
                    }
                    //Log.e("getData", list.toString());

                    for (int i = 0; i < list.length(); i++) {
                        JSONObject movie = list.getJSONObject(i);
                        Film movieItems = new Film(movie);
                        mData.add(movieItems);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
Log.e("onFailure", error.getMessage());
            }
        });
        return mData;
    }

    private void onReleasResources(ArrayList<Film> mData) {
    }

}
