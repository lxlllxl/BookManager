package com.example.administrator.bookmanager.admin.qiantai_admin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.bookmanager.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.example.administrator.bookmanager.admin.databaseHelp;
import com.example.administrator.bookmanager.admin.houtai_admin.admin_content;

public class contentActivity extends AppCompatActivity implements View.OnClickListener {
    private DrawerLayout drawerLayout;
    private ListView listView;
    private long mExitTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.ic_menu);
        }
        //侧滑菜单栏的选项
        navigationView.setCheckedItem(R.id.shoucang);//设置菜单项的默认选项
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.exit:
                        finish();
                        break;
                    case R.id.jieyue:
                        //跳转到个人借书的页面
                        Intent intent = new Intent(contentActivity.this, jieyueInfo.class);
                        startActivity(intent);
                        break;
                    case R.id.saoyisao:
                        //跳转到扫码界面
                        Intent intent1 = new Intent(contentActivity.this, ScanActivity.class);
                        startActivity(intent1);
                        break;
                    default:

                }
                drawerLayout.closeDrawers();//将滑动菜单关闭
                return true;
            }
        });
        insert();//插入数据往借书表中
        listView = (ListView) findViewById(R.id.list_view);
        databaseHelp help = new databaseHelp(getApplicationContext());

        Cursor cursor = help.querybookinfo();
        String from[] = {"_id", "bookname", "author"};
        int to[] = {R.id.book_bid, R.id.book_name, R.id.book_author};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.book_item, cursor, from, to);
        listView.setAdapter(adapter);
    }

    private void insert() {

        listView = (ListView) findViewById(R.id.list_view);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {
                int i = position + 1;
                setTitle("点击" + i + "的item");
                Intent intent = new Intent(contentActivity.this, borrowActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id", position);
                System.out.print(position);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    //toolbar的菜单栏的选项
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.search:
                Toast.makeText(contentActivity.this, "search", Toast.LENGTH_LONG).show();
                break;
            default:
        }
        return true;
    }

    @Override
    public void onClick(View v) {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    public void exit() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            Toast.makeText(contentActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }
}
