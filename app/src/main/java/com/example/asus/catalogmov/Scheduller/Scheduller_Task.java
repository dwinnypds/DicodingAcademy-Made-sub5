package com.example.asus.catalogmov.Scheduller;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.gcm.GcmNetworkManager;
import com.google.android.gms.gcm.PeriodicTask;
import com.google.android.gms.gcm.Task;

public class Scheduller_Task {
    private GcmNetworkManager mGcmNetworkManager;

    public Scheduller_Task(Context context) {
        mGcmNetworkManager = GcmNetworkManager.getInstance(context);
    }

    public void createPeriodicTask() {
        Task periodicTask = new PeriodicTask.Builder()
                .setService(Scheduller_Service.class)
                .setPeriod(3 * 60)
                .setFlex(10)
                .setTag(Scheduller_Service.TAG_TASK_UPCOMING)
                .setPersisted(true)
                .build();


        mGcmNetworkManager.schedule(periodicTask);
        Log.e("STask", "true");
    }

    public void cancelPeriodicTask() {
        if (mGcmNetworkManager != null) {
            mGcmNetworkManager.cancelTask(Scheduller_Service.TAG_TASK_UPCOMING, Scheduller_Service.class);
        }
    }
}
