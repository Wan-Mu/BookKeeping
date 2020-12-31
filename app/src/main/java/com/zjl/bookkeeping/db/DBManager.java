package com.zjl.bookkeeping.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DBManager {
    private static SQLiteDatabase db;
    /* 初始化数据库对象*/
    public static void initDB(Context context) {
        DBOpenHelper helper = new DBOpenHelper(context);  //得到帮助类对象
        db = helper.getWritableDatabase();      //得到数据库对象
    }

    /*
     * 向记账表当中插入一条元素
     * */
    public static void insertItemToAccounttb(Item item){
        ContentValues values = new ContentValues();
        values.put("reason",item.getReason());
        values.put("name",item.getName());
        values.put("money",item.getMoney());
        values.put("time",item.getTime());
        values.put("year",item.getYear());
        values.put("month",item.getMonth());
        values.put("day",item.getDay());
        values.put("kind",item.getKind());
        values.put("imageId",item.getImageId());
        db.insert("accounttb",null,values);
    }

    /*
     * 获取记账表当中某一天的所有支出或者收入情况
     * */
    public static List<Item> getAccountListOneDayFromAccounttb(int year, int month, int day){
        List<Item>list = new ArrayList<>();
        String sql = "select * from accounttb where year=? and month=? and day=? order by id desc";
        Cursor cursor = db.rawQuery(sql, new String[]{year + "", month + "", day + ""});
        //遍历符合要求的每一行数据
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String reason = cursor.getString(cursor.getColumnIndex("reason"));
            String time = cursor.getString(cursor.getColumnIndex("time"));
            int kind = cursor.getInt(cursor.getColumnIndex("kind"));
            float money = cursor.getFloat(cursor.getColumnIndex("money"));
            int imageId = cursor.getInt(cursor.getColumnIndex("imageId"));
            Item item = new Item(id,  name, reason,  money, time, year, month, day, kind,imageId);
            list.add(item);
        }
        return list;
    }

    public static int deleteItemFromAccounttbById(int id) {
        int i = db.delete("accounttb", "id=?", new String[]{id + ""});
        return i;
    }

    public static List<Item>getOneAccountListFromAccounttb(int kind){
        List<Item>list = new ArrayList<>();
        String sql = "select * from accounttb where kind =? order by id desc";
        Cursor cursor = db.rawQuery(sql, new String[]{ kind + ""});
        //遍历符合要求的每一行数据
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String reason = cursor.getString(cursor.getColumnIndex("reason"));
            String time = cursor.getString(cursor.getColumnIndex("time"));
            float money = cursor.getFloat(cursor.getColumnIndex("money"));
            int year = cursor.getInt(cursor.getColumnIndex("year"));
            int month = cursor.getInt(cursor.getColumnIndex("month"));
            int day = cursor.getInt(cursor.getColumnIndex("day"));
            int imageId = cursor.getInt(cursor.getColumnIndex("imageId"));
            Item item = new Item(id, name, reason,  money, time, year, month, day, kind,imageId);
            list.add(item);
        }
        return list;
    }
}
