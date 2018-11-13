package com.tanker.life.db;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

/**
 * @author : Tanker
 * @email :zhoukai@zto.cn
 * @date : 2018/11/12
 * @describe : TODO
 */
public class DaoBossHelper extends DaoMaster.OpenHelper {

    public DaoBossHelper(Context context, String name) {
        super(context, name);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
        // TODO: 2018/11/12   升级操作
    }
}