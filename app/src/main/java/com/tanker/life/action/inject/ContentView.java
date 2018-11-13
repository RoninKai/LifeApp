package com.tanker.life.action.inject;

import android.support.annotation.LayoutRes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author : Tanker
 * @email :zhoukai@zto.cn
 * @date : 2018/10/08
 * @describe : 获取Activity布局Id
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ContentView {
    @LayoutRes int value();
}