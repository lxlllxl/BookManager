package com.example.administrator.bookmanager.admin.houtai_admin;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.administrator.bookmanager.R;
import com.example.administrator.bookmanager.admin.ActivityCollector;
import com.example.administrator.bookmanager.admin.databaseHelp;
import com.example.administrator.bookmanager.admin.qiantai_admin.BaseActivity;

/**
 * 修改读者信息的页面
 */

public class admin_update_reader extends BaseActivity {
    private EditText user_ed, pwd_ed, email_ed;
    private ImageButton back_bt;
    private Button update_bt;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_update_reader);
        inut();//初始化界面
    }

    private void inut() {
        //将id返回的记录查询在edittext
        user_ed = (EditText) findViewById(R.id.update_name);
        pwd_ed = (EditText) findViewById(R.id.update_password);
        email_ed = (EditText) findViewById(R.id.update_email);
        Bundle bundle = getIntent().getExtras();
        id = bundle.getInt("id");
        final databaseHelp help = new databaseHelp(getApplicationContext());
        Cursor cursor = help.queryid(id);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            user_ed.setText(cursor.getString(cursor.getColumnIndex("user")));
            pwd_ed.setText(cursor.getString(cursor.getColumnIndex("password")));
            email_ed.setText(cursor.getString(cursor.getColumnIndex("email")));

        }
        //返回按钮的事件监听
        back_bt = (ImageButton) findViewById(R.id.updater_back);
        back_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(admin_update_reader.this, admin_editer_reader.class);
                startActivity(intent);
            }
        });
        //修改按钮的事件监听
        update_bt = (Button) findViewById(R.id.update_register);
        update_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String struser = user_ed.getText().toString();
                String strpwd = pwd_ed.getText().toString();
                String stremail = email_ed.getText().toString();
                SQLiteDatabase db = help.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("user", struser);
                values.put("password", strpwd);
                values.put("email", stremail);
                db.update("admin", values, "_id=?", new String[]{String.valueOf(id)});
                Intent intent = new Intent(admin_update_reader.this, admin_editer_reader.class);
                startActivity(intent);
                ActivityCollector.finishAll();
            }
        });

    }
}
