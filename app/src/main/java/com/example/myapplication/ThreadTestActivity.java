package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ThreadTestActivity extends BaseActivity implements View.OnClickListener {
    public static int UPDATE_TEXT = 1;
    private TextView textView;
    private ProgressBar progressBar;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {

        @SuppressLint("SetTextI18n")
        public void handleMessage(Message msg) {
            if (msg.what == UPDATE_TEXT) {
                if(textView.getText() == "hello world!") {
                    textView.setText("nice to miss you!");
                } else {
                    textView.setText("hello world!");
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_test);

        textView = findViewById(R.id.textView2);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        Button btn_handler = findViewById(R.id.btn_handler);
        Button btn_async_task = findViewById(R.id.btn_async_task);

        btn_handler.setOnClickListener(this);
        btn_async_task.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_handler:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message = new Message();
                        message.what = UPDATE_TEXT;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            case R.id.btn_async_task:
                new MyTask().execute();
                break;
            default:
                break;
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class MyTask extends AsyncTask<Void, Integer, Boolean> {

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            progressBar.setVisibility(View.GONE);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(values[0]);
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(30);
                    publishProgress(i + 1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return true;
        }
    }
}
