package com.zjl.bookkeeping;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.zjl.bookkeeping.Adapter.RecordAdapter;
import com.zjl.bookkeeping.record_fragment.InFragment;
import com.zjl.bookkeeping.record_fragment.OutFragment;

import java.util.ArrayList;
import java.util.List;

public class RecordActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        //1.查找控件
        tabLayout = findViewById(R.id.record_tabs);
        viewPager = findViewById(R.id.record_vp);
        //2.设置ViewPager加载页面
        initPager();
    }

    private void initPager() {
        //        初始化ViewPager页面的集合
        List<Fragment> fragmentList = new ArrayList<>();
//        创建收入和支出页面，放置在Fragment当中
        OutFragment outFrag = new OutFragment();//支出
        InFragment inFrag = new InFragment(); //收入
        fragmentList.add(outFrag);
        fragmentList.add(inFrag);

//        创建适配器
        RecordAdapter pagerAdapter = new RecordAdapter(getSupportFragmentManager(), fragmentList);
//        设置适配器
        viewPager.setAdapter(pagerAdapter);
        //将TabLayout和ViwePager进行关联
        tabLayout.setupWithViewPager(viewPager);
    }
}
