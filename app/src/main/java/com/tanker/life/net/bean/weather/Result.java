package com.tanker.life.net.bean.weather;

import android.text.TextUtils;

/**
 * @author : Tanker
 * @email :zhoukai@zto.cn
 * @date : 2018/11/06
 * @describe : TODO
 */
public class Result<T> {
    private String resultcode;
    private String  reason;
    private String error_code;
    private T result;

    public boolean isSuccess(){
        return TextUtils.equals(resultcode,"200");
    }

    public String getReason() {
        return reason;
    }

    public T getResult() {
        return result;
    }

    public String getErrorCode() {
        return error_code;
    }

}