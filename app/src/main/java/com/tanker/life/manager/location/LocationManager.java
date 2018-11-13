package com.tanker.life.manager.location;

import android.content.Context;
import android.text.TextUtils;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

/**
 * @author : Tanker
 * @email :zhoukai@zto.cn
 * @date : 2018/11/05
 * @describe : TODO
 */
public class LocationManager {

    private static LocationManager manager;

    private LocationManager() {
    }

    public static LocationManager getInstance() {
        if (manager == null) {
            manager = new LocationManager();
        }
        return manager;
    }

    /**
     * 获取定位城市
     *
     * @param context
     * @param getAddressCallBack
     */
    public void getLocationCity(Context context, final LocationCallBack.GetAddressCallBack getAddressCallBack) {
        final LocationClient locationClient = new LocationClient(context);
        locationClient.setLocOption(getAddressAsLCO());
        locationClient.registerLocationListener(new BDAbstractLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation bdLocation) {
                locationClient.stop();
                if (bdLocation == null) {
                    return;
                }
                if (TextUtils.isEmpty(bdLocation.getCityCode())) {
                    return;
                }
                if (TextUtils.isEmpty(bdLocation.getCity())) {
                    return;
                }
                getAddressCallBack.onCallaBack(bdLocation.getCityCode(), bdLocation.getCity());
            }
        });
        locationClient.start();
    }

    /**
     * 设置获取地址
     *
     * @return
     */
    private LocationClientOption getAddressAsLCO() {
        LocationClientOption clientOption = new LocationClientOption();
        clientOption.setIsNeedAddress(true);
        clientOption.setOpenGps(true);
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        clientOption.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认gcj02，设置返回的定位结果坐标系
        clientOption.setCoorType("gcj02");
        return clientOption;
    }

}