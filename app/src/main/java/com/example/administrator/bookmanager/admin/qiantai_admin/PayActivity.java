package com.example.administrator.bookmanager.admin.qiantai_admin;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.bookmanager.R;
import com.example.administrator.bookmanager.admin.ActivityCollector;
import com.example.administrator.bookmanager.admin.databaseHelp;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PayActivity extends BaseActivity {
private TextView pid,pnname,pauthor,ptime;
    private Button paybook_bt;
    int id;
    private String str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        pid=(TextView)findViewById(R.id.paybid);
        pnname=(TextView)findViewById(R.id.payname);
        pauthor=(TextView)findViewById(R.id.payauthor);
        ptime=(TextView)findViewById(R.id.paytime);

        Bundle bundle=this.getIntent().getExtras();
        id=bundle.getInt("id");
        pid.setText(bundle.getString("bookid"));
        pnname.setText(bundle.getString("bookname"));
        pauthor.setText(bundle.getString("bookauthor"));
        ptime.setText(bundle.getString("booktime"));
        //还书按钮的事件监听
        paybook_bt=(Button)findViewById(R.id.paybo_bt);
        paybook_bt.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                /*
                将信息插入还书表
                 */
                //还书日期
                SimpleDateFormat formatter    =   new    SimpleDateFormat    ("yyyy年MM月dd日    HH:mm:ss     ");
                Date curDate    =   new    Date(System.currentTimeMillis());//获取当前时间
                str    =    formatter.format(curDate);
                SharedPreferences perf=getSharedPreferences("data",MODE_PRIVATE);
               // String datetime=perf.getString("time","");//获得当前系统时间
                String username=perf.getString("users","");//获得当前用户名称

                String strbid=pid.getText().toString();//书籍编号
                String strbname=pnname.getText().toString();//书籍名称
                String strbauthor=pauthor.getText().toString();//书籍作者
                databaseHelp help=new databaseHelp(getApplicationContext());
                ContentValues values=new ContentValues();
                values.put("Borname",username);
                values.put("Bookid",strbid);
                values.put("bookname",strbname);
                values.put("bookauthor",strbauthor);
                values.put("nowtime",str);
                help.insertpay(values);
                Toast.makeText(PayActivity.this,"还书成功",Toast.LENGTH_LONG).show();
                /*
                删除相应的借书信息
*/

                help.delborrow(id);
                Intent intent=new Intent(PayActivity.this,person_borrow.class);
                startActivity(intent);
                ActivityCollector.finishAll();
            }

        });
    }
}
