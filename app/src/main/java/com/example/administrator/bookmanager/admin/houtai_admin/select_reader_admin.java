package com.example.administrator.bookmanager.admin.houtai_admin;

import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.administrator.bookmanager.R;
import com.example.administrator.bookmanager.admin.databaseHelp;

/**
 * 查找读者的界面
 *
 */
public class select_reader_admin extends AppCompatActivity {
private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_reader_admin);
        listView=(ListView)findViewById(R.id.sel_reader_list);
        databaseHelp help=new databaseHelp(getApplicationContext());
        Cursor cursor=help.query();
        String from[]={"_id","user","password","email"};
        int to[]={R.id.read_id,R.id.read_user,R.id.read_pwd,R.id.read_email};
        SimpleCursorAdapter adapter=new SimpleCursorAdapter(this,R.layout.select_reader_item,cursor,from,to);
        listView.setAdapter(adapter);
    }
}
