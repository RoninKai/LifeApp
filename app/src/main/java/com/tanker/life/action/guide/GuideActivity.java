package com.tanker.life.action.guide;

import com.tanker.life.R;
import com.tanker.life.action.base.BaseActivity;
import com.tanker.life.action.login.LoginActivity;

/**
 * 新功能引导页
 * @author Tanker
 */
public class GuideActivity extends BaseActivity {

    @Override
    protected int getContentViewId() {
        return R.layout.activity_guide;
    }

    @Override
    protected void initView() {
        toActivity(LoginActivity.class);
        finish();
    }

}