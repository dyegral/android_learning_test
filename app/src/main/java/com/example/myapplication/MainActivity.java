package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        Button btn_share_preference = findViewById(R.id.btn_share_preference);
        Button btn_http = findViewById(R.id.btn_http);
        Button btn_rv_horizontal = findViewById(R.id.btn_recycler_view);
        Button btn_rv_vertical = findViewById(R.id.btn_recycler_view2);
        Button btn_load_img = findViewById(R.id.btn_load_img);

        btn_share_preference.setOnClickListener(this);
        btn_http.setOnClickListener(this);
        btn_rv_horizontal.setOnClickListener(this);
        btn_rv_vertical.setOnClickListener(this);
        btn_load_img.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_share_preference:
            {
                Intent intent = new Intent(MainActivity.this, SharePreferenceTestActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.btn_http:
            {
                // 添加代码块 可以让变量名 intent 重复使用
                Intent intent = new Intent(MainActivity.this, HttpRequestTestActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.btn_recycler_view:
            {
                Intent intent = new Intent(MainActivity.this, RecyclerViewTestActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.btn_recycler_view2:
            {
                Intent intent = new Intent(MainActivity.this, RecyclerView2TestActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.btn_load_img:
            {
                Intent intent = new Intent(MainActivity.this, LoadImageTestActivity.class);
                startActivity(intent);
            }
            break;
        }
    }
}
