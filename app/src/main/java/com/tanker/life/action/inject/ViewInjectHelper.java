package com.tanker.life.action.inject;

import android.app.Activity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author : Tanker
 * @email :zhoukai@zto.cn
 * @date : 2018/10/08
 * @describe : activity布局反射
 */
public class ViewInjectHelper {

    /**
     * Activity布局反射
     *
     * @param activity
     */
    public static void initInjectContentView(Activity activity) {
        Class a = activity.getClass();
        if (a.isAnnotationPresent(ContentView.class)) {
            ContentView contentView = (ContentView) a.getAnnotation(ContentView.class);
            int layoutId = contentView.value();
            try {
                Method method = a.getMethod("setContentView", int.class);
                method.setAccessible(true);
                method.invoke(activity, layoutId);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

}