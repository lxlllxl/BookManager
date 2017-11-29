package com.example.administrator.bookmanager.admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.bookmanager.R;
import com.example.administrator.bookmanager.admin.houtai_admin.admin_content;
import com.example.administrator.bookmanager.admin.qiantai_admin.BaseActivity;

public class AdminActivity extends BaseActivity {
    private EditText user_ed, pwd_ed;
    private Button back_bt, login_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        init();//界面初始化
    }

    private void init() {

        //返回按钮的事件监听
        back_bt = (Button) findViewById(R.id.a_back);
        back_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminActivity.this, MainActivity.class);
                startActivity(intent);
                ActivityCollector.finishAll();
            }
        });
        //登录按钮的事件监听
        login_bt = (Button) findViewById(R.id.a_register);
        login_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user_ed = (EditText) findViewById(R.id.a_name);
                pwd_ed = (EditText) findViewById(R.id.a_password);
                String struser = user_ed.getText().toString();
                String strpwd = pwd_ed.getText().toString();
                String admin="admin";
                String pwd="123456";
                //如果管理员输入的用户名与密码正确就登录，用户名：admin；密码：123456
                if (admin.equals(struser) && pwd.equals(strpwd)) {
                    Intent intent = new Intent(AdminActivity.this, admin_content.class);
                    startActivity(intent);



                } else {

                    Toast.makeText(AdminActivity.this, "用户名或密码不正确", Toast.LENGTH_LONG).show();
                    ((EditText) findViewById(R.id.a_name)).setText("");
                    ((EditText) findViewById(R.id.a_password)).setText("");
                    ((EditText) findViewById(R.id.a_name)).requestFocus();
                }

            }
        });
    }
}
