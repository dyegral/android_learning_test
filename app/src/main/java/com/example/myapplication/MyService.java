package com.example.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    private DownloadBinder mBinder = new DownloadBinder();

    // 该服务要做的任务 ？
    class DownloadBinder extends Binder {

        void startDownload() {
            Log.d("MyService", "startDownload executed");
        }

        int getProgress() {
            Log.d("MyService", "getProgress executed");
            return 0;
        }

    }

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        // throw new UnsupportedOperationException("Not yet implemented");
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MyService", "MyService created");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("MyService", "MyService started");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d("MyService", "MyService destroy");
        super.onDestroy();
    }
}
