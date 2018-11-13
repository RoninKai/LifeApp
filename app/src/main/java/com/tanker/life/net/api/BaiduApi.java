package com.tanker.life.net.api;

import com.tanker.life.common.NetWorkConfig;
import com.tanker.life.net.bean.baidu.BaiduResult;
import com.tanker.life.net.callback.BaiduCallBack;
import com.tanker.life.net.retrofit.RetrofitUtils;

/**
 * @author : Tanker
 * @email :zhoukai@zto.cn
 * @date : 2018/11/08
 * @describe : TODO
 */
public class BaiduApi {
    private static  BaiduApi api = new BaiduApi();

    public static BaiduApi api(){
        return api;
    }

    public void getBaiduGirl(String name, String type,BaiduCallBack<BaiduResult> callback){
        RetrofitUtils.getApiService(NetWorkConfig.getBaiduGirl()).getBaiduGirl(name,type).enqueue(callback);
    }

}
