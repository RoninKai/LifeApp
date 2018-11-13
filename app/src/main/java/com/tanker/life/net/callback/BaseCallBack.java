package com.tanker.life.net.callback;

import com.tanker.life.net.HttpExceptionUtil;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * @author : Tanker
 * @email :zhoukai@zto.cn
 * @date : 2018/11/08
 * @describe : 网络回调Base
 */
public abstract class BaseCallBack <T> implements Callback<T> {

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        onCompleteStart();
        onFailure(HttpExceptionUtil.NET_EXCEPTION_CODE, HttpExceptionUtil.exceptionHandler(t));
        onCompleteEnd();
    }

    public abstract void onSuccess(T result);

    public abstract void onFailure(String errCode, String errorMsg);

    public void onError(Throwable t) {
    }

    public void onCompleteStart() {
    }

    public void onCompleteEnd() {
    }

}