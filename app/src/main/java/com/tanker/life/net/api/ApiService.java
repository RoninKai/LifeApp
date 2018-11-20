package com.tanker.life.net.api;


import com.tanker.life.net.bean.baidu.BaiduResult;
import com.tanker.life.net.bean.weather.Result;
import com.tanker.life.net.bean.weather.WeatherResult;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author : Tanker
 * @email :zhoukai@zto.cn
 * @date : 2018/11/07
 * @describe : retrofit2 Api接口
 */
public interface ApiService {

    /**
     * 获取天气
     *
     * @param city 城市
     * @return
     */
    @GET("index?key=30878a0ea2df204acf307208298a1d92")
    Observable<Result<WeatherResult>> getWeatherAsAddress(@Query("cityname") String city);

    @GET("listjson?pn=0&rn=30&ie=utf8")
    Observable<BaiduResult> getBaiduGirl(@Query("tag1") String name, @Query("tag2") String type);

}