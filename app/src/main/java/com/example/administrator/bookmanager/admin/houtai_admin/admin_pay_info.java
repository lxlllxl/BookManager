package com.example.administrator.bookmanager.admin.houtai_admin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.administrator.bookmanager.R;
import com.example.administrator.bookmanager.admin.databaseHelp;

import java.util.List;
import java.util.Map;

public class admin_pay_info extends AppCompatActivity {
private ListView ad_pay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_pay_info);
        ad_pay=(ListView)findViewById(R.id.ad_show_pay);
        databaseHelp help=new databaseHelp(getApplicationContext());
        List<Map<String, Object>> data = help.querypay();
        SimpleAdapter adapter = new SimpleAdapter(
                admin_pay_info.this, data, R.layout.ad_pay_item,
                new String[] {  "_Rid","Borname","Bookid", "bookname", "nowtime" },
                new int[] { R.id.ad_pid ,R.id.ad_puname,
                        R.id.ad_ppid, R.id.ad_pnbame,
                        R.id.ad_ptime });
        ad_pay.setAdapter(adapter);

    }
}
