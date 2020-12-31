package com.zjl.bookkeeping;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.Toast;

import com.zjl.bookkeeping.Adapter.ItemAdapter;
import com.zjl.bookkeeping.db.Item;
import com.zjl.bookkeeping.db.DBManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    CalendarView calendarView;
    ListView todaylist;
    int year,month,day;
    List<Item> mDatas;
    ItemAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTime();
        initView();
        mDatas = new ArrayList<>();
        adapter = new ItemAdapter(this, mDatas);
        todaylist.setAdapter(adapter);

    }

    private void initView() {
        todaylist=findViewById(R.id.today_list);
        calendarView=findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                MainActivity.this.year = year;
                MainActivity.this.month = month +1;
                MainActivity.this.day = dayOfMonth;
                onResume();
            }
        });
        setLVLongClickListener();
    }

    private void setLVLongClickListener() {
        todaylist.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                Item clickBean = mDatas.get(position);  //获取正在被点击的这条信息

                //弹出提示用户是否删除的对话框
                showDeleteItemDialog(clickBean);
                return false;
            }
        });
    }

    private void showDeleteItemDialog(final Item clickBean) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示信息").setMessage("您确定要删除这条记录么？")
                .setNegativeButton("取消",null)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int click_id = clickBean.getId();
                        //执行删除的操作
                        DBManager.deleteItemFromAccounttbById(click_id);
                        mDatas.remove(clickBean);   //实时刷新，移除集合当中的对象
                        adapter.notifyDataSetChanged();   //提示适配器更新数据
                    }
                });
        builder.create().show();   //显示对话框
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_in:
                Intent it1 = new Intent(MainActivity.this, InActivity.class);  //跳转界面
                startActivity(it1);
                break;
            case R.id.button_home:
                Toast.makeText(MainActivity.this, "当前页面已经是主页", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_out:
                Intent it2 = new Intent(MainActivity.this, OutActivity.class);  //跳转界面
                startActivity(it2);
                break;
            case R.id.FAB:
                Intent it3 = new Intent(this, RecordActivity.class);  //跳转界面
                startActivity(it3);
                break;
        }
    }



    // 当activity获取焦点时，会调用的方法
    @Override
    protected void onResume() {
        super.onResume();
        loadDBData();
    }

    private void loadDBData() {
        List<Item> list = DBManager.getAccountListOneDayFromAccounttb(year, month, day);
        mDatas.clear();
        mDatas.addAll(list);
        adapter.notifyDataSetChanged();
    }

    /* 获取今日的具体时间*/
    private void initTime() {
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH)+1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
    }

}