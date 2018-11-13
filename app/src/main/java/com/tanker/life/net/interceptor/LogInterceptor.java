package com.tanker.life.net.interceptor;

import com.tanker.base.util.LogUtils;
import com.tanker.life.common.LookLog;

import java.io.IOException;
import java.util.Locale;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author : Tanker
 * @email :zhoukai@zto.cn
 * @date : 2018/11/07
 * @describe : Log拦截器代码
 */
public class LogInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        LogUtils.i(LookLog.NET_WORK_INFO, "request:" + request.toString());
        long t1 = System.nanoTime();
        okhttp3.Response response = chain.proceed(chain.request());
        long t2 = System.nanoTime();
        LogUtils.i(LookLog.NET_WORK_INFO, String.format(Locale.getDefault(),
                "Received response for %s in %.1fms%n%s",
                response.request().url(), (t2 - t1) / 1e6d, response.headers()));
        okhttp3.MediaType mediaType = response.body().contentType();
        String content = response.body().string();
        LogUtils.i(LookLog.NET_WORK_INFO, "response body:" + content);
        return response.newBuilder().body(okhttp3.ResponseBody.create(mediaType, content)).build();
    }

}