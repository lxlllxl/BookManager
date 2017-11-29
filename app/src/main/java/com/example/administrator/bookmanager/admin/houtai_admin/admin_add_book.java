package com.example.administrator.bookmanager.admin.houtai_admin;

import android.content.ContentValues;
import android.content.Intent;
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

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 管理员添加图书的界面
 */

public class admin_add_book extends BaseActivity {
    private ImageButton back_bt;
    private EditText name_ed, author_Ed, page_ed, price_ed, publish_ed, intime_rd;
    private Button add_book_bt;
    private String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_book);
        init();//界面初始化
    }

    private void init() {
        //返回按钮的事件监听
        back_bt = (ImageButton) findViewById(R.id.addbook_back);
        back_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(admin_add_book.this, admin_manager_book.class);
                startActivity(intent);
            }
        });
        name_ed = (EditText) findViewById(R.id.add_bokname);
        author_Ed = (EditText) findViewById(R.id.add_bokauthor);
        page_ed = (EditText) findViewById(R.id.add_bokpage);
        price_ed = (EditText) findViewById(R.id.add_bokprice);
        publish_ed = (EditText) findViewById(R.id.add_bokpublish);

        SimpleDateFormat formatter    =   new    SimpleDateFormat    ("yyyy年MM月dd日    ");
        Date curDate    =   new    Date(System.currentTimeMillis());//获取当前时间
        str    =    formatter.format(curDate);
        //添加按钮的事件监听
        add_book_bt = (Button) findViewById(R.id.add_book);
        add_book_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strname = name_ed.getText().toString();
                String strauthor = author_Ed.getText().toString();
                String strpage = page_ed.getText().toString();
                String strprice = price_ed.getText().toString();
                String strpublish = publish_ed.getText().toString();

                //将字符串型转换成double类型
                Double dprice = Double.parseDouble(strprice);
                if (strname.equals("")) {
                    Toast.makeText(admin_add_book.this, "名称不能为空，请重新输入",
                            Toast.LENGTH_LONG).show();

                } else if (strauthor.equals("")) {
                    Toast.makeText(admin_add_book.this, "作者不能为空，请重新输入",
                            Toast.LENGTH_LONG).show();

                } else if ("".equals(dprice)) {
                    Toast.makeText(admin_add_book.this, "价格不能为空，请重新输入",
                            Toast.LENGTH_LONG).show();

                } else if (strpage.equals("")) {
                    Toast.makeText(admin_add_book.this, "页数不能为空，请重新输入",
                            Toast.LENGTH_LONG).show();

                } else if (strpublish.equals("")) {
                    Toast.makeText(admin_add_book.this, "出版社不能为空，请重新输入",
                            Toast.LENGTH_LONG).show();

                } else {
                    ContentValues values = new ContentValues();
                    values.put("bookname", strname);
                    values.put("author", strauthor);
                    values.put("page", strpage);
                    values.put("price", strprice);
                    values.put("publish", strpublish);
                    values.put("intime", str);
                    databaseHelp helper = new databaseHelp(
                            getApplicationContext());
                    helper.insertbookinfo(values);
                    Toast.makeText(admin_add_book.this, "图书添加成功",
                            Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(admin_add_book.this,
                            admin_manager_book.class);
                    startActivity(intent);
                    ActivityCollector.finishAll();
                }
            }
        });
    }
}
