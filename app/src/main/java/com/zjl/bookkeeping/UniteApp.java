package com.zjl.bookkeeping;

import android.app.Application;

import com.zjl.bookkeeping.db.DBManager;

public class UniteApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化数据库
        DBManager.initDB(getApplicationContext());
    }
}
