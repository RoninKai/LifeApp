package com.tanker.life.action.login;

import android.view.View;

import com.tanker.life.R;
import com.tanker.life.action.base.BaseActivity;
import com.tanker.life.action.main.MainActivity;
import com.tanker.life.util.BuildTypeUtils;

/**
 * 登录界面
 * @author Tanker
 */
public class LoginActivity extends BaseActivity {

    @Override
    protected int getContentViewId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        if(BuildTypeUtils.isDebug()){
            toActivity(MainActivity.class);
            finish();
        }
    }

    public void toLogin(View view){
        toActivity(MainActivity.class);
        finish();
    }

}