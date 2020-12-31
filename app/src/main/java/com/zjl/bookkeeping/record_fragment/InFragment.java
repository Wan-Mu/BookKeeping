package com.zjl.bookkeeping.record_fragment;

import com.zjl.bookkeeping.R;
import com.zjl.bookkeeping.db.DBManager;

public class InFragment extends BaseFragment {

    @Override
    public void saveAccountToDB() {
        item.setKind(1);
        item.setImageId(R.drawable.in_red);
        DBManager.insertItemToAccounttb(item);
    }
}