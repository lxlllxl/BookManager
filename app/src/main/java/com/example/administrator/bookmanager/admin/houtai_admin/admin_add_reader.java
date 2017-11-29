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
import android.widget.Toast;

import com.example.administrator.bookmanager.R;
import com.example.administrator.bookmanager.admin.ActivityCollector;
import com.example.administrator.bookmanager.admin.databaseHelp;
import com.example.administrator.bookmanager.admin.qiantai_admin.BaseActivity;
import com.example.administrator.bookmanager.admin.registerActivity;

/**
 * 管理员添加读者
 */
public class admin_add_reader extends BaseActivity {
    private EditText user_eed, pwd_ed, email_ed;
    private ImageButton back_bt;
    private Button addreader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_reader);
        init();//出实话界面
    }

    private void init() {
        user_eed = (EditText) findViewById(R.id.reader_name);
        pwd_ed = (EditText) findViewById(R.id.reader_password);
        email_ed = (EditText) findViewById(R.id.reader_email);
        back_bt = (ImageButton) findViewById(R.id.reader_back);
        //返回按钮的事件监听
        back_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(admin_add_reader.this, admin_manager_reader.class);
                startActivity(intent);
            }
        });
        addreader = (Button) findViewById(R.id.reader_register);
        //添加读者按钮的事件监听
        addreader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String struser = user_eed.getText().toString();
                String strpwd = pwd_ed.getText().toString();
                String stremail = email_ed.getText().toString();
                //验证用户名是否存在
                databaseHelp help = new databaseHelp(getApplicationContext());
                SQLiteDatabase db = help.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("user", struser);
                values.put("password", strpwd);
                values.put("email", stremail);
                Cursor cursor = db.query("admin", null, null, null, null, null, null);
                if (cursor.moveToFirst()) {
                    do {
                        String username = cursor.getString(cursor.getColumnIndex("user"));
                        if (username.equals(user_eed.getText().toString())) {
                            Toast.makeText(admin_add_reader.this, "用户名已存在", Toast.LENGTH_LONG).show();
                            ((EditText) findViewById(R.id.reader_name)).setText("");
                            return;
                        }


                    } while (cursor.moveToNext());

                }
                cursor.close();
                help.insert(values);
                Toast.makeText(admin_add_reader.this, "用户添加成功", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(admin_add_reader.this, admin_manager_reader.class);
                startActivity(intent);
                ActivityCollector.finishAll();
            }
        });
    }
}
