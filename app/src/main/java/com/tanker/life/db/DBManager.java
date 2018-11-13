package com.tanker.life.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.tanker.life.util.BuildTypeUtils;

import org.greenrobot.greendao.database.Database;

/**
 * @author : Tanker
 * @email :zhoukai@zto.cn
 * @date : 2018/11/12
 * @describe : 数据库操作类
 */
public class DBManager {

    private final String dbName = "LIFE_APP_DB";
    private final String dbPassWard = "life";
    private DaoMaster daoMaster;
    private DaoSession daoSession;

    private static DBManager dbManager;

    public static DBManager getInstance() {
        if (dbManager == null) {
            synchronized (DBManager.class) {
                if (dbManager == null) {
                    dbManager = new DBManager();
                }
            }
        }
        return dbManager;
    }

    public void initDB(Context context) {
        DaoBossHelper helper = new DaoBossHelper(context, dbName);
        if (BuildTypeUtils.isDebug()) {
            SQLiteDatabase db = helper.getWritableDatabase();
            daoMaster = new DaoMaster(db);
        } else {
            Database db = helper.getEncryptedWritableDb(dbPassWard);
            daoMaster = new DaoMaster(db);
        }
        daoSession = daoMaster.newSession();
    }

    public DaoMaster getDaoMaster() {
        return daoMaster;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

}