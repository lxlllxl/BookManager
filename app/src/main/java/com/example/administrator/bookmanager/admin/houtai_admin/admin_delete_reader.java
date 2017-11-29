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
 * 管理员删除读者
 */
public class admin_delete_reader extends AppCompatActivity {
    private ListView listView;
    private ImageButton back_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_delete_reader);
        init();//初始化界面

    }

    private void init() {
        back_bt = (ImageButton) findViewById(R.id.editreader_back);
        back_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(admin_delete_reader.this, admin_manager_reader.class);
                startActivity(intent);
            }
        });
        listView = (ListView) findViewById(R.id.edit_reader_list);
        final databaseHelp help = new databaseHelp(getApplicationContext());
        Cursor cursor = help.query();
        String from[] = {"_id", "user", "password", "email"};
        int to[] = {R.id.read_id, R.id.read_user, R.id.read_pwd, R.id.read_email};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.select_reader_item, cursor, from, to);
        listView.setAdapter(adapter);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //listview的单击事件监听
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final long temp = l;
                builder.setMessage("确定要删除吗？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        help.del((int) temp);
                        //删除后重新显示
                        Cursor cursor = help.query();
                        String from[] = {"_id", "user", "password", "email"};
                        int to[] = {R.id.read_id, R.id.read_user, R.id.read_pwd, R.id.read_email};
                        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getApplicationContext(), R.layout.select_reader_item, cursor, from, to);
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
