package com.zjl.bookkeeping.record_fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.zjl.bookkeeping.R;
import com.zjl.bookkeeping.db.Item;
import com.zjl.bookkeeping.SelectTimeDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public abstract class BaseFragment extends Fragment implements View.OnClickListener{

    TextView timeTv;
    EditText moneyEt,nameEt,reasonEt;
    Item item;
    Button okButton,noButton;

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        item = new Item();   //创建对象
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_record, container, false);
        initView(view);
        setInitTime();
        return view;
    }

    /* 获取当前时间，显示在timeTv上*/
    private void setInitTime() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        String time = sdf.format(date);
        timeTv.setText(time);
        item.setTime(time);

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        item.setYear(year);
        item.setMonth(month);
        item.setDay(day);
    }

    private void initView(View view) {
        moneyEt = view.findViewById(R.id.edit_record_money);
        reasonEt = view.findViewById(R.id.edit_record_reason);
        timeTv = view.findViewById(R.id.record_time);
        nameEt = view.findViewById(R.id.edit_record_name);
        okButton = view.findViewById(R.id.record_ok);
        noButton = view.findViewById(R.id.record_no);
        timeTv.setOnClickListener(this);
        okButton.setOnClickListener(this);
        noButton.setOnClickListener(this);

        //设置金额文本框焦点，光标文字
        moneyEt.setFocusable(true);
        moneyEt.requestFocus();
        moneyEt.setFocusableInTouchMode(true);
        //设置金额框监听事件
        moneyEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //文本改变前
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //文本改变时
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //文本改变后，一般使用此方法
                //获取输入钱数
                String moneyStr = moneyEt.getText().toString();
                if (TextUtils.isEmpty(moneyStr)||moneyStr.equals("0")) {
                    getActivity().finish();
                    return;
                }
                float money = Float.parseFloat(moneyStr);
                item.setMoney(money);
            }
        });

        //设置姓名文本框焦点，光标文字
        nameEt.setFocusable(true);
        nameEt.requestFocus();
        nameEt.setFocusableInTouchMode(true);
        //设置姓名框监听事件
        nameEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //文本改变前
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //文本改变时
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //文本改变后，一般使用此方法
                //获取输入姓名
                String nameStr = nameEt.getText().toString();
                item.setName(nameStr);
            }
        });

        //设置原因文本框焦点，光标文字
        reasonEt.setFocusable(true);
        reasonEt.requestFocus();
        reasonEt.setFocusableInTouchMode(true);
        //设置原因框监听事件
        reasonEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //文本改变前
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //文本改变时
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //文本改变后，一般使用此方法
                //获取输入原因
                String reasonStr = reasonEt.getText().toString();
                item.setReason(reasonStr);
            }
        });
    }

    public abstract void saveAccountToDB();

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.record_time:
                showTimeDialog();
                break;
            case R.id.record_ok:
                if(item.getMoney() == 0){
                    Toast.makeText(getActivity(),"金额不可为0",Toast.LENGTH_SHORT).show();
                    // 返回上一级页面
                    getActivity().finish();
                }
                //获取记录的信息，保存在数据库当中
                saveAccountToDB();
                // 返回上一级页面
                getActivity().finish();
                break;
            case R.id.record_no:
                getActivity().finish();
                break;
        }
    }

    public void showTimeDialog() {
        SelectTimeDialog dialog = new SelectTimeDialog(getContext());
        dialog.show();
        //设定确定按钮被点击了的监听器
        dialog.setOnEnsureListener(new SelectTimeDialog.OnEnsureListener() {
            @Override
            public void onEnsure(String time, int year, int month, int day) {
                timeTv.setText(time);
                item.setTime(time);
                item.setYear(year);
                item.setMonth(month);
                item.setDay(day);
            }
        });
    }

}