package com.zjl.bookkeeping.record_fragment;

import com.zjl.bookkeeping.R;
import com.zjl.bookkeeping.db.DBManager;

public class OutFragment extends BaseFragment {

    @Override
    public void saveAccountToDB() {
        item.setKind(0);
        item.setImageId(R.drawable.out_blue);
        DBManager.insertItemToAccounttb(item);
    }
}
