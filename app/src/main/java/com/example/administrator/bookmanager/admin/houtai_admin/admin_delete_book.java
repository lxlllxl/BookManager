package com.example.administrator.bookmanager.admin.houtai_admin;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.example.administrator.bookmanager.admin.databaseHelp;

/**
 * 管理员删除图书的节目
 */

public class admin_delete_book extends AppCompatActivity {
private ListView listView;
    private ImageButton back_bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_delete_book);
        init();//界面初始化
    }

    private void init() {
        back_bt=(ImageButton)findViewById(R.id.deletebook_back);
        back_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(admin_delete_book.this,admin_manager_book.class);
                startActivity(intent);
            }
        });
        //listView的事件监听
        listView=(ListView)findViewById(R.id.delete_book_list);
        final databaseHelp help = new databaseHelp(getApplicationContext());
        Cursor cursor = help.querybookinfo();
        String from[] = {"_id", "bookname", "author", "page","price","publish","intime"};
        int to[] = {R.id.book_id, R.id.book_name, R.id.book_author,R.id.book_page,R.id.book_price,R.id.book_publish,R.id.book_intime};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.admin_book_item, cursor, from, to);
        listView.setAdapter(adapter);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //删除listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                final long temp = l;
                builder.setMessage("确定要删除吗？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        help.delbookinfo((int) temp);
                        //删除后重新显示
                        Cursor cursor = help.querybookinfo();
                        String from[] = {"_id", "bookname", "author", "page","price","publish","intime"};
                        int to[] = {R.id.book_id, R.id.book_name, R.id.book_author,R.id.book_page,R.id.book_price,R.id.book_publish,R.id.book_intime};
                        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getApplicationContext(), R.layout.admin_book_item, cursor, from, to);
                        listView.setAdapter(adapter);

                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        help.close();

    }
}
