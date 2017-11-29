package com.example.administrator.bookmanager.admin.qiantai_admin;
/*
个人借书表
 */

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.administrator.bookmanager.R;
import com.example.administrator.bookmanager.admin.databaseHelp;

import java.util.List;
import java.util.Map;

public class person_borrow extends AppCompatActivity {
    private ListView listView;
    private String bookid, bookname, bookauthor, booktime,Rorname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_borrow);
        listView = (ListView) findViewById(R.id.show_borrow);
        final databaseHelp help = new databaseHelp(getApplicationContext());
        SharedPreferences perf = getSharedPreferences("data", MODE_PRIVATE);

        String username = perf.getString("users", "");//获得当前用户名称
        //根据用户查询自己的借阅信息
        List<Map<String, Object>> data = help.queryuser(username);
        SimpleAdapter adapter = new SimpleAdapter(
                person_borrow.this, data, R.layout.borrow_item,
                new String[]{"Borname", "Bookid", "bookname",
                        "bookauthor", "nowtime"},
                new int[]{R.id.Borname, R.id.Bbookid,
                        R.id.Bbookname, R.id.Bbookauthor,
                        R.id.Bnowtimae});
        listView.setAdapter(adapter);
        //通过id查询图书表里的所有信息，用bundle进行数据交互

        //进行还书,跳转到信息界面
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                int i = position +1;
                setTitle("点击" + i + "的item");

                Cursor cursor = help.queryborrowinfoid(i);
                if (cursor.moveToFirst()) {
                    do {
                        bookid = cursor.getString(cursor.getColumnIndex("Bookid"));
                        bookname = cursor.getString(cursor.getColumnIndex("bookname"));
                        bookauthor = cursor.getString(cursor.getColumnIndex("bookauthor"));
                        booktime = cursor.getString(cursor.getColumnIndex("nowtime"));
                        Rorname = cursor.getString(cursor.getColumnIndex("Borname"));//当前用户

                    } while (cursor.moveToNext());

                }

                Intent intent = new Intent(person_borrow.this, PayActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id", position);
                bundle.putString("bookid", bookid);
                bundle.putString("bookname", bookname);
                bundle.putString("bookauthor", bookauthor);
                bundle.putString("booktime", booktime);
                bundle.putString("Rorname",Rorname);
                intent.putExtras(bundle);
                System.out.print(position);

                startActivity(intent);

            }
        });


    }
}
