package com.tanker.life.app;

import android.app.Application;

import com.tanker.base.util.AppUtils;
import com.tanker.base.util.LogUtils;

/**
 * @author : Tanker
 * @email :zhoukai@zto.cn
 * @date : 2018/11/02
 * @describe : TODO
 */
public class LifeApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (!AppUtils.isAppProcess(getApplicationContext())) {
            return;
        }
        long startTime = System.currentTimeMillis();
        LogUtils.openPrintLog();
        ApplicationContext.getInstance().initContext(this);
        CrashHandler.getInstance().open();
        long endTime = System.currentTimeMillis();
        LogUtils.i("" + (endTime - startTime));
    }

}