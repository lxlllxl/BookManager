package com.example.administrator.bookmanager.admin.qiantai_admin;
/*
实现扫码功能，zxing包是 new file import moudle ，然后在app build grade的文件中田家庵    compile project(path: ':zxing')
 */
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.bookmanager.R;
import com.google.zxing.client.android.CaptureActivity;

public class ScanActivity extends AppCompatActivity {
private TextView result_tx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
    }
    public void scanner(View view){
        Intent intent = new Intent(this, CaptureActivity.class);
        startActivityForResult(intent, 0);
        result_tx=(TextView)findViewById(R.id.show_result);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode==RESULT_OK){
            String result = data.getStringExtra("result");
            result_tx.setText(result);
        }

    }
}
