package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpRequestTestActivity extends BaseActivity {
    TextView textView_response;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_http);

        Button button1 = findViewById(R.id.button_http_url_connection);
        Button button2 = findViewById(R.id.button_okhttp);
        textView_response = findViewById(R.id.textView_response);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        HttpsURLConnection connection = null;
                        BufferedReader reader = null;
                        try {
                            URL url = new URL("https://api.apiopen.top/getImages?page=0&count=5");
                            connection = (HttpsURLConnection) url.openConnection();
                            connection.setRequestMethod("POST");
                            connection.setConnectTimeout(3000);
                            connection.setReadTimeout(3000);

                            InputStream inputStream = connection.getInputStream();

                            // 对输入流读取
                            reader = new BufferedReader(new InputStreamReader(inputStream));
                            StringBuilder respose = new StringBuilder();
                            String line;
                            while ((line = reader.readLine()) != null) {
                                respose.append(line);
                            }
                            showResponse(respose.toString());
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            if(reader != null) {
                                try {
                                    reader.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            if(connection != null) {
                                connection.disconnect();
                            }
                        }
                    }
                }).start();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        OkHttpClient client = new OkHttpClient();
                        Request request = null;
                        // get
//                        request = new Request.Builder()
//                                .url("https://api.apiopen.top/getImages?page=0&count=6")
//                                .build();

                        // post
                        RequestBody requestBody = new FormBody.Builder()
                                .add("page", "0")
                                .add("count", "6")
                                .build();

                        request = new Request.Builder()
                                .url("https://api.apiopen.top/getImages")
                                .post(requestBody)
                                .build();

                        try {
                            Response response = client.newCall(request).execute();
                            String responseData = null;
                            if (response.body() != null) {
                                responseData = response.body().string();
                            }
                            showResponse(responseData);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }

    private void showResponse(final String response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView_response.setText(response);
            }
        });
    }
}
