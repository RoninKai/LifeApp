package com.tanker.life.action.memo;

import com.tanker.life.R;
import com.tanker.life.action.base.BaseActivity;

public class AddMemoActivity extends BaseActivity {

    @Override
    protected int getContentViewId() {
        return R.layout.pop_add_memo_layout;
    }

    @Override
    protected String getTitleText() {
        return getString(R.string.add_memo);
    }

    @Override
    protected void initView() {

    }

}