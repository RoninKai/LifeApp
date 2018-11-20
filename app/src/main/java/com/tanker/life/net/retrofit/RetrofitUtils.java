package com.tanker.life.net.retrofit;

import com.tanker.life.common.NetWorkConfig;
import com.tanker.life.net.api.ApiService;
import com.tanker.life.net.interceptor.LogInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author : Tanker
 * @email :zhoukai@zto.cn
 * @date : 2018/11/07
 * @describe : Retrofit封装
 */
public class RetrofitUtils {

    private static RetrofitUtils mInstance;

    public static RetrofitUtils getInstance() {
        if (mInstance == null) {
            synchronized (RetrofitUtils.class) {
                if (mInstance == null) {
                    mInstance = new RetrofitUtils();
                }
            }
        }
        return mInstance;
    }

    /**
     * 初始化必要对象和参数
     *
     * @param url 基础baseUrl
     * @return
     */
    public Retrofit getRetrofit(String url) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .readTimeout(NetWorkConfig.getNetWorkTimeOut(), TimeUnit.SECONDS)
                .connectTimeout(NetWorkConfig.getNetWorkTimeOut(), TimeUnit.SECONDS)
                .writeTimeout(NetWorkConfig.getNetWorkTimeOut(), TimeUnit.SECONDS)
                .addInterceptor(new LogInterceptor())
                .retryOnConnectionFailure(true)
                .build();
        return new Retrofit.Builder()
                .client(client)
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static <T> T getService(String baseUrl, Class<T> service) {
        return getInstance().getRetrofit(baseUrl).create(service);
    }

    public static ApiService getApiService(String baseUrl) {
        return getInstance().getRetrofit(baseUrl).create(ApiService.class);
    }

}