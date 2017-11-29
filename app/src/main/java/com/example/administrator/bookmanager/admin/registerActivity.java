package com.example.administrator.bookmanager.admin;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.bookmanager.R;
import com.example.administrator.bookmanager.admin.qiantai_admin.BaseActivity;

public class registerActivity extends BaseActivity {
    private EditText user_ed, pwd_ed, repwd_ed, email_ed;
    private Button back_bt, register_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();//界面初始化
    }

    private void init() {
        user_ed = (EditText) findViewById(R.id.r_name);
        pwd_ed = (EditText) findViewById(R.id.r_password);
        repwd_ed = (EditText) findViewById(R.id.r_repwd);
        email_ed = (EditText) findViewById(R.id.r_email);
        //返回按钮的事件监听
        back_bt = (Button) findViewById(R.id.r_back);
        back_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(registerActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        //注册按钮de事件监听
        register_bt = (Button) findViewById(R.id.r_register);
        register_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String struser = user_ed.getText().toString();
                String strpwd = pwd_ed.getText().toString();
                String strrepwd = repwd_ed.getText().toString();
                String stremail = email_ed.getText().toString();
                //验证两次密码是否输入一致
                if (!strpwd.equals(strrepwd)) {
                    Toast.makeText(registerActivity.this, "两次密码输入不一致", Toast.LENGTH_LONG).show();
                    ((EditText) findViewById(R.id.r_password)).setText("");
                    ((EditText) findViewById(R.id.r_repwd)).setText("");
                    ((EditText) findViewById(R.id.r_password)).requestFocus();
                    return;

                }
                databaseHelp help = new databaseHelp(getApplicationContext());
                ContentValues values = new ContentValues();
                values.put("user", struser);
                values.put("password", strpwd);
                values.put("email", stremail);
                SQLiteDatabase db = help.getWritableDatabase();
                //查询用户是否已经存在
                Cursor cusror = db.query("admin", null, null, null, null, null, null);
                if (cusror.moveToFirst()) {
                    do {
                        String username = cusror.getString(cusror.getColumnIndex("user"));
                        if (username.equals(user_ed.getText().toString())) {
                            Toast.makeText(registerActivity.this, "用户名已存在", Toast.LENGTH_LONG).show();
                            ((EditText) findViewById(R.id.r_name)).setText("");
                            return;
                        }


                    } while (cusror.moveToNext());

                }
                cusror.close();
                help.insert(values);
                Toast.makeText(registerActivity.this, "用户注册成功", Toast.LENGTH_LONG).show();
                //用户注册成功，就跳转到登录页面
                Intent intent = new Intent(registerActivity.this, MainActivity.class);
                startActivity(intent);
                ActivityCollector.finishAll();
            }
        });

    }
}
