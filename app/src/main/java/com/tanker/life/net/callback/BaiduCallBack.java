package com.tanker.life.net.callback;

import com.tanker.life.net.bean.baidu.BaiduResult;

import retrofit2.Call;
import retrofit2.Response;

/**
 * @author : Tanker
 * @email :zhoukai@zto.cn
 * @date : 2018/11/09
 * @describe : TODO
 */
public abstract class BaiduCallBack<T> extends BaseCallBack<T> {

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if(!response.isSuccessful()){
            onCompleteStart();
            onFailure(String.valueOf(response.code()), response.message());
            onCompleteEnd();
            return;
        }
        BaiduResult result = (BaiduResult) response.body();
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
