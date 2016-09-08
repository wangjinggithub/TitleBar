package com.trywang.titlebar.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.trywang.titlebar.R;

public class MainActivity extends AppCompatActivity {
    private long mBackTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickLeft(View v) {
        back();
    }
    public void onClickCenter(View v) {
        Toast.makeText(MainActivity.this, "点击了中间", Toast.LENGTH_SHORT).show();
    }
    public void onClickRight(View v) {
        Log.d("TAG","onClickRight");
        Toast.makeText(MainActivity.this, "点击了右边", Toast.LENGTH_SHORT).show();
    }
    
    public void back(){
        if (System.currentTimeMillis() - mBackTime < 500) {
            finish();
        }
        mBackTime = System.currentTimeMillis();
        Toast.makeText(MainActivity.this, "再按一次返回键退出程序", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        back();
    }
}
