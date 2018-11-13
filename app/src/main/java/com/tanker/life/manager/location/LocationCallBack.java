package com.tanker.life.manager.location;

/**
 * @author : Tanker
 * @email :zhoukai@zto.cn
 * @date : 2018/11/05
 * @describe : 定位相关回调
 */
public interface LocationCallBack {

    public interface GetAddressCallBack{
        /**
         * @param cityCode
         * @param cityName
         */
        void onCallaBack(String cityCode,String cityName);
    }

}