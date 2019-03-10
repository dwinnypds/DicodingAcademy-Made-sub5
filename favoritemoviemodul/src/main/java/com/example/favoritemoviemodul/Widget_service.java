package com.example.favoritemoviemodul;

import android.content.Intent;
import android.widget.RemoteViewsService;

public class Widget_service extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new Widget_Remote_View(this.getApplicationContext(), intent);
    }

}
