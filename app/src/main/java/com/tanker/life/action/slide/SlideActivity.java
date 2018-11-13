package com.tanker.life.action.slide;

import com.tanker.life.R;
import com.tanker.life.action.base.BaseActivity;
import com.tanker.life.action.guide.GuideActivity;
import com.tanker.life.manager.thread.ExecuteCallBack;
import com.tanker.life.manager.thread.ThreadPoolManager;
import com.tanker.life.manager.thread.ThreadRunnable;
import com.tanker.life.util.BuildTypeUtils;

/**
 * 启动页
 * @author Tanker
 */
public class SlideActivity extends BaseActivity {

    @Override
    protected int getContentViewId() {
        return R.layout.activity_slide;
    }

    @Override
    protected void initView() {
        if(BuildTypeUtils.isDebug()){
            toActivity(GuideActivity.class);
            finish();
            return;
        }
        ThreadPoolManager.getInstance().execute(new ThreadRunnable.ThreadSleep(this, new ExecuteCallBack() {
            @Override
            public void callBack() {
                if (!thisActivity.isFinishing()) {
                    toActivity(GuideActivity.class);
                    finish();
                }
            }
        }, 3000));
    }

}