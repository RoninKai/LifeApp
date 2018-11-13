package com.tanker.life.app;

import android.app.Application;
import android.content.Context;

import com.tanker.base.util.LogUtils;
import com.tanker.life.common.CommonValues;
import com.tanker.life.db.DBManager;
import com.tanker.life.manager.sharepre.SharePreManager;

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
        LogUtils.openPrintLog();
        SharePreManager.getInstance().init(getApplicationContext(), CommonValues.SHAREPRE_FILE_NAME,Context.MODE_PRIVATE);
        DBManager.getInstance().initDB(getApplicationContext());
    }

}