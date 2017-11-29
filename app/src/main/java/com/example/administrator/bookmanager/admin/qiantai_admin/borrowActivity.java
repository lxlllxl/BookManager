package com.example.administrator.bookmanager.admin.qiantai_admin;


import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.bookmanager.R;
import com.example.administrator.bookmanager.admin.databaseHelp;

import java.text.SimpleDateFormat;
import java.util.Date;


public class borrowActivity extends AppCompatActivity {
private TextView bid,bname,bauthor,bpage,bprice;
    private Button borrow_bt;
    private   String    str;
    int id;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow);
        /*
        获取当前系统时间
        用作借书日期time
         */
        SimpleDateFormat formatter    =   new    SimpleDateFormat    ("yyyy年MM月dd日    HH:mm:ss     ");
        Date curDate    =   new    Date(System.currentTimeMillis());//获取当前时间
           str    =    formatter.format(curDate);
       /* SharedPreferences.Editor editor=getSharedPreferences("data",MODE_PRIVATE).edit();
        editor.putString("time",str);
        editor.apply();*/

        bid=(TextView)findViewById(R.id.bookbid);
        bname=(TextView)findViewById(R.id.bookbname);
        bauthor=(TextView)findViewById(R.id.bookbauthor);
        bpage=(TextView)findViewById(R.id.bookbpage);
        bprice=(TextView)findViewById(R.id.bookbprice);
        Bundle bundle=this.getIntent().getExtras();
        id=bundle.getInt("id")+1;
        final databaseHelp help=new databaseHelp(getApplicationContext());
        Cursor cursor=help.querybookinfoid(id);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
           bid.setText(cursor.getString(cursor.getColumnIndex("_id")));
            bname.setText(cursor.getString(cursor.getColumnIndex("bookname")));
          bauthor.setText(cursor.getString(cursor.getColumnIndex("author")));
            bpage.setText(cursor.getString(cursor.getColumnIndex("page")));
            bprice.setText(cursor.getString(cursor.getColumnIndex("price")));


        }
borrow_bt=(Button)findViewById(R.id.borroe_bt);
        borrow_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//将书籍信息插入借阅表中
                //借书日期
                SharedPreferences perf=getSharedPreferences("data",MODE_PRIVATE);
               // String datetime=perf.getString("time","");//获得当前系统时间
                String username=perf.getString("users","");//获得当前用户名称

               String strbid=bid.getText().toString();
                String strbname=bname.getText().toString();
                String strbauthor=bauthor.getText().toString();
                int intbid=Integer.parseInt(strbid);
                ContentValues values=new ContentValues();
                values.put("Bookid",intbid);
                values.put("bookname",strbname);
                values.put("bookauthor",strbauthor);
                values.put("Borname",username);
                values.put("nowtime",str);
                help.insertorrowo(values);
              //获取当前用户

                Toast.makeText(borrowActivity.this,"借书成功",Toast.LENGTH_LONG).show();
               
            }
        });
    }


}
