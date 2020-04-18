package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;

public class ServiceTestActivity extends AppCompatActivity implements View.OnClickListener {

    private MyService.DownloadBinder downloadBinder;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (MyService.DownloadBinder) service;
            downloadBinder.startDownload();
            downloadBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_test);

        Button start_service = findViewById(R.id.start_service);
        Button stop_service = findViewById(R.id.stop_service);
        Button btn_bind_service = findViewById(R.id.btn_bind_service);
        Button btn_unbind_service = findViewById(R.id.btn_unbind_service);

        start_service.setOnClickListener(this);
        stop_service.setOnClickListener(this);
        btn_bind_service.setOnClickListener(this);
        btn_unbind_service.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_service:
                Intent startIntent = new Intent(this, MyService.class);
                startService(startIntent); // 启动服务
                break;
            case R.id.stop_service:
                Intent stopIntent = new Intent(this, MyService.class);
                stopService(stopIntent); // 停止服务
                break;
            case R.id.btn_bind_service:
                Intent bindIntent = new Intent(this, MyService.class);
                bindService(bindIntent, connection, BIND_AUTO_CREATE); // 绑定服务
                break;
            case R.id.btn_unbind_service:
                unbindService(connection); // 解绑服务
                break;
            default:
                break;
        }
    }
}
