package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.entry.Book;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class LitePalTestActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lite_pal_test);

        Button btn_create_db = findViewById(R.id.btn_create_db);
        Button btn_add_db = findViewById(R.id.btn_add_db);
        Button btn_query_db = findViewById(R.id.btn_query_db);
        Button btn_delete_db = findViewById(R.id.btn_delete_db);
        Button btn_update_db = findViewById(R.id.btn_update_db);

        btn_create_db.setOnClickListener(this);
        btn_add_db.setOnClickListener(this);
        btn_query_db.setOnClickListener(this);
        btn_delete_db.setOnClickListener(this);
        btn_update_db.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_create_db:
                Connector.getDatabase();
                break;
            case R.id.btn_add_db:
            {
                Book book = new Book();
                book.setName("The Lost Symbol");
                book.setAuthor("Dan Brown");
                book.setPages(510);
                book.setPrice(19.95);
                book.setPress(null);
                book.save();

                Book book1 = new Book();
                book1.setName("The Dan Vinci Code");
                book1.setAuthor("Dan Brown");
                book1.setPages(454);
                book1.setPrice(16.96);
                book1.setPress("Unknow");
                book1.save();

                Book book2 = new Book();
                book2.setName("The Test Name");
                book2.setAuthor("Test");
                book2.setPages(666);
                book2.setPrice(66.66);
                book2.setPress("test");
                book2.save();
            }
                break;
            case R.id.btn_query_db:
            {
                List<Book> books = LitePal.findAll(Book.class);
                Log.d("LitePalTestActivity", "++++++++ start +++++++++");
                for (Book book:
                        books) {
                    Log.d("LitePalTestActivity", "name -- " + book.getName());
                    Log.d("LitePalTestActivity", "author -- " + book.getAuthor());
                    Log.d("LitePalTestActivity", "pages -- " + book.getPages());
                    Log.d("LitePalTestActivity", "price -- " + book.getPrice());
                    Log.d("LitePalTestActivity", "press -- " + book.getPress());
                    Log.d("LitePalTestActivity", "----------分割线-----------");
                }
                Log.d("LitePalTestActivity", "++++++++ end +++++++++");
            }
                break;
            case R.id.btn_delete_db:
                LitePal.deleteAll(Book.class);
                break;
            case R.id.btn_update_db:
            {
                Book book = new Book();
                book.setPrice(200.0);
                book.setName("update name");
                book.updateAll("name = ? and author = ?", "The Test Name", "Test");
            }
                break;
        }
    }
}
