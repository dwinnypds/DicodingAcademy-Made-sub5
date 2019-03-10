package com.example.favoritemoviemodul;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.example.favoritemoviemodul.model.FavModel;

import java.util.concurrent.ExecutionException;

import static com.example.favoritemoviemodul.API.ADB.POSTER_IMAGE;
import static com.example.favoritemoviemodul.DB.DBContract.CONTENT_URI;

public class Widget_Remote_View implements
        RemoteViewsService.RemoteViewsFactory {

    private int mAppWidgetId;
    private Context context;
    private Cursor list;
    private TextView textView;

    public Widget_Remote_View(Context context, Intent intent) {
        this.context = context;
        mAppWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);


    }

    public void onCreate() {
        list = context.getContentResolver().query(
                CONTENT_URI,
                null,
                null,
                null,
                null
        );
    }

    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        Log.e("widgetCount",String.valueOf(list.getCount()));
        return list.getCount();
    }

    @Override
    public RemoteViews getViewAt(int position) {

        FavModel item = getItem(position);
        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.item_widget);

        Bitmap bitmap = null;
        try {

            bitmap = Glide.with(context)
                    .load(POSTER_IMAGE + item.getPoster())
                    .asBitmap()
                    .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        rv.setImageViewBitmap(R.id.imageView, bitmap);

        Bundle extras = new Bundle();
        extras.putInt(Widget_fav.EXTRA_ITEM, position);
        Intent fillInIntent = new Intent();
        fillInIntent.putExtras(extras);

        rv.setOnClickFillInIntent(R.id.imageView, fillInIntent);
        return rv;

    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    private FavModel getItem(int position) {
        if (!list.moveToPosition(position)) {
            throw new IllegalStateException("Position invalid!");
        }

        return new FavModel(list);
    }

}
