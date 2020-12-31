package com.zjl.bookkeeping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

public class LoadActivity extends AppCompatActivity {

    private final int time = 5000;
    private boolean lag = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {//延时time秒后，将运行如下代码
                if(lag){
                    finish();
                    //Toast.makeText(LoadActivity.this , "等待了5s后进入主页" , Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoadActivity.this , MainActivity.class);
                    startActivity(intent);
                }
            }
        } , time);

        Button button = (Button) findViewById(R.id.button);
//给按钮添加监听事件，当点击时，直接进入主页面
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                //Toast.makeText(LoadActivity.this , "跳过等待进入主页" , Toast.LENGTH_LONG).show();
                Intent intent = new Intent(LoadActivity.this , MainActivity.class);
                startActivity(intent);
                lag = false;
            }
        });
    }
}