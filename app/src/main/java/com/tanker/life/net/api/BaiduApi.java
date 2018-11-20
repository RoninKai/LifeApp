package com.tanker.life.net.api;

import com.tanker.life.common.NetWorkConfig;
import com.tanker.life.net.retrofit.RetrofitUtils;

import io.reactivex.Observable;

/**
 * @author : Tanker
 * @email :zhoukai@zto.cn
 * @date : 2018/11/08
 * @describe : TODO
 */
public class BaiduApi {
    private static BaiduApi api = new BaiduApi();

    public static BaiduApi api() {
        return api;
    }

    public Observable getBaiduGirl(String name, String type) {
        return RetrofitUtils.getApiService(NetWorkConfig.getBaiduGirl()).getBaiduGirl(name, type);
    }

}