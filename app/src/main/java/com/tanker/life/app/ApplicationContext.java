package com.tanker.life.app;

import android.app.Application;
import android.content.Context;

/**
 * @author : Tanker
 * @email :zhoukai@zto.cn
 * @date : 2018/11/14
 * @describe : 保存获取ApplicationContext
 */
public class ApplicationContext {

    private static ApplicationContext applicationContext;

    private ApplicationContext() {
    }

    public static ApplicationContext getInstance() {
        if (applicationContext == null) {
            applicationContext = new ApplicationContext();
        }
        return applicationContext;
    }

    private Application application;

    void initContext(Application application) {
        this.application = application;
    }

    public Context getApplicationContext() {
        return application.getApplicationContext();
    }

}