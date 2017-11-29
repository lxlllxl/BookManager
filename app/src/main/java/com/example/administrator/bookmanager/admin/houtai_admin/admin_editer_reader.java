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
import com.example.administrator.bookmanager.admin.databaseHelp;

/**
 * 编辑读者页面
 */

public class admin_editer_reader extends AppCompatActivity {
    private ListView listView;
    private ImageButton back_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_editer_reader);
        init();//初始化界面

    }

    private void init() {
        back_bt = (ImageButton) findViewById(R.id.deletereader_back);
        back_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(admin_editer_reader.this, admin_manager_reader.class);
                startActivity(intent);
            }
        });
        listView = (ListView) findViewById(R.id.delete_reader_list);
        final databaseHelp help = new databaseHelp(getApplicationContext());
        Cursor cursor = help.query();
        String from[] = {"_id", "user", "password", "email"};
        int to[] = {R.id.read_id, R.id.read_user, R.id.read_pwd, R.id.read_email};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.select_reader_item, cursor, from, to);
        listView.setAdapter(adapter);
        //listview的单击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //传值到修改界面
                int i = position + 1;
                Intent intent = new Intent(admin_editer_reader.this, admin_update_reader.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id", i);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
