package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;
import com.example.myapplication.entry.ImageItemAdapter;
import com.example.myapplication.entry.MoviesAdapter;
import com.example.myapplication.entry.RecieveData;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoadImageTestActivity extends BaseActivity {

    List<RecieveData.Image> imageList = new ArrayList<>();
    ImageItemAdapter imageItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_image_test);

        RecyclerView recyclerView = findViewById(R.id.recycler_view_img);

//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(linearLayoutManager);

        StaggeredGridLayoutManager sglm = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(sglm);

        imageItemAdapter = new ImageItemAdapter(this, imageList);
        recyclerView.setAdapter(imageItemAdapter);

        getImageData();
    }

    public void getImageData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                Request request = null;

                request = new Request.Builder()
                        .url("https://api.apiopen.top/getImages")
                        .build();

                try {
                    Response response = client.newCall(request).execute();
                    String responseData = null;
                    if (response.body() != null) {
                        responseData = response.body().string();
                    }
                    // 把json字符串转换成相应的对象
                    RecieveData recieveData = new Gson().fromJson(responseData, RecieveData.class);
                    // 取出其中关于图片 id time url 部分
                    List<RecieveData.Image> images = recieveData.getResult();
                    imageList.addAll(images);

                    for (RecieveData.Image image :
                            imageList) {

                        System.out.println(image.getImg());

                        // 替换协议 Glide好像不支持http ？
                        image.setImg(image.getImg().replace("http:", "https:"));
                    }

                    imageItemAdapter.updateList(imageList);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            imageItemAdapter.notifyDataSetChanged();
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
