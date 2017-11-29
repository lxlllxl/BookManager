package com.example.administrator.bookmanager.admin.qiantai_admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.administrator.bookmanager.R;
import com.example.administrator.bookmanager.admin.ActivityCollector;

public class jieyueInfo extends BaseActivity {
private ImageButton borrow,pay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jieyue_info);
        borrow=(ImageButton)findViewById(R.id.imageborrow);
        borrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                跳转到我的借书信息页面
                 */
                Intent intent=new Intent(jieyueInfo.this,person_borrow.class);
                startActivity(intent);
                ActivityCollector.finishAll();
            }
        });
        //还书信息
        pay=(ImageButton)findViewById(R.id.imagepay);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 /*
                跳转到我的还书信息页面
                 */
                Intent intent=new Intent(jieyueInfo.this,person_pay.class);
                startActivity(intent);
                ActivityCollector.finishAll();
            }
        });
    }
}
