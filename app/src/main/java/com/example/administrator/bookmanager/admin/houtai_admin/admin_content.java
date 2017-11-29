package com.example.administrator.bookmanager.admin.houtai_admin;


import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;


import com.example.administrator.bookmanager.R;
import com.example.administrator.bookmanager.admin.databaseHelp;

import java.util.Date;

public class admin_content extends AppCompatActivity {
    private long mExitTime;
    private ImageButton selct_bt, manReader_bt, manBook_bt;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_content);
        init();//界面初始化

    }

    private void init() {
        //查询信息
        selct_bt=(ImageButton)findViewById(R.id.ad_select);
        selct_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(admin_content.this,admin_select_message.class);
                startActivity(intent);
            }
        });
        //管理读者
        manReader_bt=(ImageButton)findViewById(R.id.ad_manager_reader);
        manReader_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(admin_content.this,admin_manager_reader.class);
                startActivity(intent);
            }
        });
        //管理图书
        manBook_bt=(ImageButton)findViewById(R.id.ad_manager_book);
        manBook_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(admin_content.this,admin_manager_book.class);
                startActivity(intent);
            }
        });


    }
    public void exit() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            Toast.makeText(admin_content.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {

            finish();
            System.exit(0);
        }
    }


}
