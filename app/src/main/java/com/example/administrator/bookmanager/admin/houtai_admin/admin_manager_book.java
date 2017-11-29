package com.example.administrator.bookmanager.admin.houtai_admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.administrator.bookmanager.R;
import com.example.administrator.bookmanager.admin.ActivityCollector;
import com.example.administrator.bookmanager.admin.qiantai_admin.BaseActivity;

public class admin_manager_book extends BaseActivity {
    private ImageButton back_bt, addbook, editbook, deletebook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_manager_book);
        init();//界面出售化

    }

    private void init() {
        back_bt = (ImageButton) findViewById(R.id.manbook_back);
        back_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(admin_manager_book.this, admin_content.class);
                startActivity(intent);
            }
        });
        addbook = (ImageButton) findViewById(R.id.ad_add);
        addbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(admin_manager_book.this, admin_add_book.class);
                startActivity(intent);
            }
        });
        editbook = (ImageButton) findViewById(R.id.ad_edit);
        editbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(admin_manager_book.this, admin_edit_book.class);
                startActivity(intent);
            }
        });
        deletebook = (ImageButton) findViewById(R.id.ad_delete);
        deletebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(admin_manager_book.this, admin_delete_book.class);
                startActivity(intent);
            }
        });
    }
}
