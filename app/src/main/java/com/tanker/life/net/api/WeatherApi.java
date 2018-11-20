package com.tanker.life.net.api;

import com.tanker.life.common.NetWorkConfig;
import com.tanker.life.net.retrofit.RetrofitUtils;

import io.reactivex.Observable;

/**
 * @author : Tanker
 * @email :zhoukai@zto.cn
 * @date : 2018/11/08
 * @describe : 天气接口类
 */
public class WeatherApi {

    private static final WeatherApi weatherApi = new WeatherApi();

    public static WeatherApi api() {
        return weatherApi;
    }

    /**
     * 获取天气
     *
     * @param cityName
     */
    public Observable getWeather(String cityName) {
        return RetrofitUtils.getApiService(NetWorkConfig.getWeatherUrl()).getWeatherAsAddress(cityName);
    }

}