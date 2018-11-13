package com.tanker.life.net.callback;

import com.tanker.life.net.bean.weather.Result;

import retrofit2.Call;
import retrofit2.Response;

/**
 * @author : Tanker
 * @email :zhoukai@zto.cn
 * @date : 2018/11/07
 * @describe : 数据返回统一处理
 */
public abstract class WeatherCallBack<T> extends BaseCallBack<T> {

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if(!response.isSuccessful()){
            onCompleteStart();
            onFailure(String.valueOf(response.code()), response.message());
            onCompleteEnd();
            return;
        }
        Result result = (Result) response.body();
        if (result == null) {
            onCompleteStart();
            onError(new Throwable("result is null"));
            onCompleteEnd();
            return;
        }
        onCompleteStart();
        onSuccess((T) result);
        onCompleteEnd();
    }

}