package com.example.administrator.bookmanager.admin.houtai_admin;

import android.content.Intent;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.administrator.bookmanager.R;
import com.example.administrator.bookmanager.admin.databaseHelp;

/**
 * 管理员查询图书信息
 */

public class admin_select_bookinfo extends AppCompatActivity {
    private ImageButton back_bt;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_select_bookinfo);
        init();//界面初始化

    }

    private void init() {
        back_bt = (ImageButton) findViewById(R.id.sel_book_back);
        back_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(admin_select_bookinfo.this, admin_select_message.class);
                startActivity(intent);
            }
        });
        listView = (ListView) findViewById(R.id.select_book_list);
        final databaseHelp help = new databaseHelp(getApplicationContext());
        Cursor cursor = help.querybookinfo();
        String from[] = {"_id", "bookname", "author", "page", "price", "publish", "intime"};
        int to[] = {R.id.book_id, R.id.book_name, R.id.book_author, R.id.book_page, R.id.book_price, R.id.book_publish, R.id.book_intime};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.admin_book_item, cursor, from, to);
        listView.setAdapter(adapter);
    }
}
