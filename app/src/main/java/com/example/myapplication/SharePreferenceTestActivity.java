package com.example.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class SharePreferenceTestActivity extends BaseActivity {

    private static final String FILE_NAME = "sp_test";
    private static final String sp_key = "sp_key";
    private SharedPreferences mSharedPreferences;
    private EditText input_text;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_share_preference);

        input_text = findViewById(R.id.input_text);
        mSharedPreferences = getSharedPreferences(FILE_NAME, MODE_PRIVATE);
    }



    public void saveClick(View view) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        String str = input_text.getText().toString().trim();
        if(!str.equals("")) {
            editor.putString(sp_key, str);
            editor.apply();
            // toast前面会有应用名
            // Toast.makeText(SharePreferenceTestActivity.this, "存储成功", Toast.LENGTH_SHORT).show();
            Toast toast = Toast.makeText(SharePreferenceTestActivity.this, null, Toast.LENGTH_LONG);
            toast.setText("存储成功");
            toast.show();
        }
    }

    public void showClick(View view) {
        String str = mSharedPreferences.getString(sp_key, null);
        if(str != null) {
            Toast toast = Toast.makeText(SharePreferenceTestActivity.this, null, Toast.LENGTH_LONG);
            toast.setText(str);
            toast.show();
        }
    }
}
