package com.tanker.life.util;

import android.text.TextUtils;

import com.tanker.life.BuildConfig;

/**
 * @author : Tanker
 * @email :zhoukai@zto.cn
 * @date : 2018/07/18
 * @describe : 判断构建类型工具类
 */
public class BuildTypeUtils {

    /**
     * 是否release模式
     *
     * @return
     */
    public static boolean isRelease() {
        return TextUtils.equals(BuildConfig.BUILD_TYPE, BuildTypeCode.RELEASE);
    }

    /**
     * 是否debug模式
     *
     * @return
     */
    public static boolean isDebug() {
        return TextUtils.equals(BuildConfig.BUILD_TYPE, BuildTypeCode.DEBUG);
    }

    public class BuildTypeCode {
        /**
         * 生产模式
         */
        private static final String RELEASE = "release";
        /**
         * 开发模式
         */
        private static final String DEBUG = "debug";
    }

}