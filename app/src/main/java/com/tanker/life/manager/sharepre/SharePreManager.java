package com.tanker.life.manager.sharepre;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @author Tanker。
 * e-mail : zhoukai@zto.cn
 * time   : 2018/06/21
 * desc   : SharedPreferences 操作类
 */
public class SharePreManager {

    private SharePreManager() {
    }

    private static class LazyHolder {
        private static final SharePreManager INSTANCE = new SharePreManager();
    }

    public static final SharePreManager getInstance() {
        return LazyHolder.INSTANCE;
    }

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    /**
     * 初始化 SharedPreferences
     *
     * @param context
     * @param fileName  生成的xml文件名
     * @param shareType 存储的格式
     */
    public void init(Context context, String fileName, int shareType) {
        sharedPreferences = context.getSharedPreferences(fileName, shareType);
        editor = sharedPreferences.edit();
    }

    /**
     * 清空保存的所有数据
     */
    public void clear() {
        editor.clear().apply();
    }

    /**
     * 移除数据
     *
     * @param key
     */
    public void remove(String key) {
        editor.remove(key).apply();
    }

    /**************************** String ***************************/

    public void putString(String key, String value) {
        editor.putString(key, value).apply();
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
        editor.putInt(key, value).apply();
    }

    public int getInt(String key) {
        return getInt(key, 0);
    }

    public int getInt(String key, int defaultValue) {
        return sharedPreferences.getInt(key, defaultValue);
    }

    /**************************** boolean ***************************/

    public void putBoolean(String key, boolean value) {
        editor.putBoolean(key, value).apply();
    }

    public boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    /**************************** float ***************************/

    public void putFloat(String key, int value) {
        editor.putFloat(key, value).apply();
    }

    public float getFloat(String key) {
        return getFloat(key, 0);
    }

    public float getFloat(String key, float defaultValue) {
        return sharedPreferences.getFloat(key, defaultValue);
    }

    /**************************** float ***************************/

    public void putLong(String key, long value) {
        editor.putLong(key, value).apply();
    }

    public long getLong(String key) {
        return getLong(key, 0);
    }

    public long getLong(String key, long defaultValue) {
        return sharedPreferences.getLong(key, defaultValue);
    }

    /**************************** Bean ***************************/

    public void putBean(String key, Object value) {
        if (value instanceof Serializable) {// obj必须实现Serializable接口，否则会出问题
            ByteArrayOutputStream byteArrayOutputStream = null;
            ObjectOutputStream objectOutputStream = null;
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                objectOutputStream.writeObject(value);
                putString(key, new String(Base64.encode(byteArrayOutputStream.toByteArray(), 0)));
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (objectOutputStream != null) {
                    try {
                        objectOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public Object getBean(String key) {
        return getBean(key, null);
    }

    public Object getBean(String key, Object defaultValue) {
        Object bean = null;
        String base64 = getString(key);
        if (TextUtils.isEmpty(base64)) {
            return defaultValue;
        }
        ByteArrayInputStream byteArrayInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            byte[] base64Bytes = Base64.decode(base64.getBytes(), 1);
            byteArrayInputStream = new ByteArrayInputStream(base64Bytes);
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            bean = objectInputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (byteArrayInputStream != null) {
                try {
                    byteArrayInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (objectInputStream != null) {
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bean;
    }

}