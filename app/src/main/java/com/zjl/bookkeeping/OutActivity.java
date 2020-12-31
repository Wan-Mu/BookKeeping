package com.zjl.bookkeeping;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.zjl.bookkeeping.Adapter.ItemAdapter;
import com.zjl.bookkeeping.db.Item;
import com.zjl.bookkeeping.db.DBManager;

import java.util.ArrayList;
import java.util.List;

public class OutActivity extends AppCompatActivity {

    private List<Item> OutList = new ArrayList<>();
    ListView OutLv;
    List<Item> mDatas;
    ItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_out);
        OutLv = this.findViewById(R.id.out_list);
        mDatas = new ArrayList<>();
        adapter = new ItemAdapter(this,mDatas);
        OutLv.setAdapter(adapter);
        loadData();
        setLVClickListener();

        Button button_in = (Button) findViewById(R.id.button_in);
        button_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_in = new Intent(OutActivity.this, InActivity.class);
                startActivity(intent_in);
            }
        });

        Button button_home = (Button) findViewById(R.id.button_home);
        button_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_home = new Intent(OutActivity.this, MainActivity.class);
                startActivity(intent_home);
            }
        });
    }

    private void setLVClickListener() {
        OutLv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Item item = mDatas.get(position);
                deleteItem(item);
                return false;
            }
        });
    }

    private void deleteItem(final Item item) {
        final int delId = item.getId();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示信息").setMessage("您确定要删除这条记录么？")
                .setNegativeButton("取消",null)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DBManager.deleteItemFromAccounttbById(delId);
                        mDatas.remove(item);   //实时刷新，从数据源删除
                        adapter.notifyDataSetChanged();
                    }
                });
        builder.create().show();
    }

    private void loadData() {
        List<Item> list = DBManager.getOneAccountListFromAccounttb(0);
        mDatas.clear();
        mDatas.addAll(list);
        adapter.notifyDataSetChanged();
    }

}