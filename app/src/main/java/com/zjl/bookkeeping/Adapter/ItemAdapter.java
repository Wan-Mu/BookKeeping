package com.zjl.bookkeeping.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zjl.bookkeeping.R;
import com.zjl.bookkeeping.db.Item;

import java.util.Calendar;
import java.util.List;

public class ItemAdapter extends BaseAdapter {
    Context context;
    List<Item> mDatas;
    LayoutInflater inflater;
    int year,month,day;
    public ItemAdapter(Context context, List<Item> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
        inflater = LayoutInflater.from(context);
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH)+1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_main,parent,false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        Item bean = mDatas.get(position);
        holder.reasonTv.setText(bean.getReason());
        holder.nameTv.setText(bean.getName());
        holder.moneyTv.setText("￥ "+bean.getMoney());
        holder.timeTv.setText(bean.getTime());
        holder.typeIv.setImageResource(bean.getImageId());

        return convertView;
    }

    class ViewHolder{
        ImageView typeIv;
        TextView timeTv,moneyTv,reasonTv,nameTv;
        public ViewHolder(View view){
            typeIv = view.findViewById(R.id.item_icon);
            nameTv = view.findViewById(R.id.item_name);
            timeTv = view.findViewById(R.id.item_time);
            moneyTv = view.findViewById(R.id.item_money);
            reasonTv = view.findViewById(R.id.item_reason);
        }
    }
}
