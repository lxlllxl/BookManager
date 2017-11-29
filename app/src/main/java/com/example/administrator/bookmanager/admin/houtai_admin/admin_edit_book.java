package com.example.administrator.bookmanager.admin.houtai_admin;

import android.content.Intent;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.administrator.bookmanager.R;
import com.example.administrator.bookmanager.admin.ActivityCollector;
import com.example.administrator.bookmanager.admin.databaseHelp;
import com.example.administrator.bookmanager.admin.qiantai_admin.BaseActivity;

/**
 * 管理员编辑图书的界面
 */

public class admin_edit_book extends BaseActivity {
private ImageButton back_bt;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_book);
        init();//界面初始化
    }

    private void init() {
        back_bt=(ImageButton)findViewById(R.id.editbook_back);
        back_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(admin_edit_book.this,admin_manager_book.class);
                startActivity(intent);
            }
        });
        listView=(ListView)findViewById(R.id.edit_book_list);
        final databaseHelp help = new databaseHelp(getApplicationContext());
        Cursor cursor = help.querybookinfo();
        String from[] = {"_id", "bookname", "author", "page","price","publish","intime"};
        int to[] = {R.id.book_id, R.id.book_name, R.id.book_author,R.id.book_page,R.id.book_price,R.id.book_publish,R.id.book_intime};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.admin_book_item, cursor, from, to);
        listView.setAdapter(adapter);
        //listview的单击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //传值到修改界面
                int i = position;
                Intent intent = new Intent(admin_edit_book.this, admin_upadte_book.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id", i);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
    }
}
