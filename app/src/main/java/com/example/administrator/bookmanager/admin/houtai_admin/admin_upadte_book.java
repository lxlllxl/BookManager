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

/***
 * 管理员修改图书信息
 */
public class admin_upadte_book extends BaseActivity {
    private ImageButton back_bt;
    private EditText name_ed, author_Ed, page_ed, price_ed, publish_ed, intime_rd;
    private Button update_book_bt;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_upadte_book);
        init();//初始化界面
    }

    private void init() {
        back_bt = (ImageButton) findViewById(R.id.updatebook_back);
        back_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(admin_upadte_book.this, admin_edit_book.class);
                startActivity(intent);
            }
        });
        //将返回点击id的记录
        name_ed = (EditText) findViewById(R.id.update_bokname);
        author_Ed = (EditText) findViewById(R.id.update_bokauthor);
        page_ed = (EditText) findViewById(R.id.update_bokpage);
        price_ed = (EditText) findViewById(R.id.update_bokprice);
        publish_ed = (EditText) findViewById(R.id.update_bokpublish);
        intime_rd = (EditText) findViewById(R.id.update_bokintime);
        Bundle bundle = getIntent().getExtras();
        id = bundle.getInt("id")+1 ;
        final databaseHelp help = new databaseHelp(getApplicationContext());
        Cursor cursor = help.querybookinfoid(id);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            name_ed.setText(cursor.getString(cursor.getColumnIndex("bookname")));
            author_Ed.setText(cursor.getString(cursor.getColumnIndex("author")));
            page_ed.setText(cursor.getString(cursor.getColumnIndex("page")));
            price_ed.setText(cursor.getString(cursor.getColumnIndex("price")));
            publish_ed.setText(cursor.getString(cursor.getColumnIndex("publish")));
            intime_rd.setText(cursor.getString(cursor.getColumnIndex("intime")));

        }
        //修改按钮的事件监听
        update_book_bt = (Button) findViewById(R.id.update_book);
        update_book_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strname = name_ed.getText().toString();
                String strauthor = author_Ed.getText().toString();
                String strpage = page_ed.getText().toString();
                String strprice = price_ed.getText().toString();
                String strpublish = publish_ed.getText().toString();
                String strintime = intime_rd.getText().toString();
                SQLiteDatabase db = help.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("bookname", strname);
                values.put("author", strauthor);
                values.put("page", strpage);
                values.put("price", strprice);
                values.put("publish", strpublish);
                values.put("intime", strintime);
                db.update("bookinfo", values, "_id=?", new String[]{String.valueOf(id)});
                Intent intent = new Intent(admin_upadte_book.this, admin_edit_book.class);
                startActivity(intent);
                ActivityCollector.finishAll();
            }
        });
    }
}
