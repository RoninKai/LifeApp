package com.tanker.life.manager.sharepre;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.tanker.life.app.ApplicationContext;
import com.tanker.life.common.CommonValues;

/**
 * @author Tanker。
 * e-mail : zhoukai@zto.cn
 * time   : 2018/06/21
 * desc   : SharedPreferences 操作类
 */
public class SharePreManager {

    private SharePreManager() {
        init(ApplicationContext.getInstance().getApplicationContext(), CommonValues.SHAREPRE_FILE_NAME, Context.MODE_PRIVATE);
    }

    private static class LazyHolder {
        private static final SharePreManager INSTANCE = new SharePreManager();
    }

    public static SharePreManager getInstance() {
        return LazyHolder.INSTANCE;
    }

    private SharedPreferences sharedPreferences;

    /**
     * 初始化 SharedPreferences
     *
     * @param context
     * @param fileName  生成的xml文件名
     * @param shareType 存储的格式
     */
    public void init(Context context, String fileName, int shareType) {
        sharedPreferences = context.getSharedPreferences(fileName, shareType);
    }

    private SharedPreferences.Editor getEditor() {
        return sharedPreferences.edit();
    }

    /**
     * 清空保存的所有数据
     */
    public void clear() {
        getEditor().clear().apply();
    }

    /**
     * 移除数据
     *
     * @param key
     */
    public void remove(String key) {
        getEditor().remove(key).apply();
    }

    /**************************** String ***************************/

    public void putString(String key, String value) {
        getEditor().putString(key, value).apply();
    }

    public String getString(String key) {
        return getString(key, "");
    }

    public String getString(String key, String defaultValue) {
        String value = sharedPreferences.getString(key, defaultValue);
        if (TextUtils.isEmpty(value)) {
            return defaultValue;
        }
        return value;
    }

    /**************************** int ***************************/

    public void putInt(String key, int value) {
        getEditor().putInt(key, value).apply();
    }

    public int getInt(String key) {
        return getInt(key, 0);
    }

    public int getInt(String key, int defaultValue) {
        return sharedPreferences.getInt(key, defaultValue);
    }

    /**************************** boolean ***************************/

    public void putBoolean(String key, boolean value) {
        getEditor().putBoolean(key, value).apply();
    }

    public boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    /**************************** float ***************************/

    public void putFloat(String key, int value) {
        getEditor().putFloat(key, value).apply();
    }

    public float getFloat(String key) {
        return getFloat(key, 0);
    }

    public float getFloat(String key, float defaultValue) {
        return sharedPreferences.getFloat(key, defaultValue);
    }

    /**************************** float ***************************/

    public void putLong(String key, long value) {
        getEditor().putLong(key, value).apply();
    }

    public long getLong(String key) {
        return getLong(key, 0);
    }

    public long getLong(String key, long defaultValue) {
        return sharedPreferences.getLong(key, defaultValue);
    }

}