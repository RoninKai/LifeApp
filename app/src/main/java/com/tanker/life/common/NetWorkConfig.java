package com.tanker.life.common;

/**
 * @author : Tanker
 * @email :zhoukai@zto.cn
 * @date : 2018/11/08
 * @describe : 网络相关配置信息  结合BuildType做到多种配置
 */
public class NetWorkConfig {

    private static final int DEFAULT_TIME_OUT_AS_SECONDS = 10;

    /**
     * 获取网络超时时长
     *
     * @return
     */
    public static int getNetWorkTimeOut() {
        return DEFAULT_TIME_OUT_AS_SECONDS;
    }

    /**
     * 聚合获取天气URL
     */
    private static final String GET_WEATHER_URL = "http://v.juhe.cn/weather/";

    /**
     * 获取天气的请求地址
     *
     * @return
     */
    public static String getWeatherUrl() {
//        if (BuildTypeUtils.isDebug()) {
//            //天气接口有次数限制，因此debug模式不获取天气信息
//            return "http://x.xxxx.xx/xxxxxx/";
//        }
        return GET_WEATHER_URL;
    }

    private static final String GET_BAIDU_GIRL = "http://image.baidu.com/channel/";

    public static String getBaiduGirl() {
        return GET_BAIDU_GIRL;
    }

}