package com.crystallake.basic.http.interceptor;

import android.text.TextUtils;

import com.crystallake.basic.http.HttpConstant;
import com.crystallake.basic.utils.SPUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptor implements Interceptor {

    private HashMap<String, Object> headers;

    public HeaderInterceptor() {
    }

    public HeaderInterceptor(HashMap<String, Object> headers) {
        this.headers = headers;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder requestBuilder = request.newBuilder();
        String domain = request.url().host();

        requestBuilder.addHeader("Content-type", "application/json; charset=utf-8");

        // 将 Cookie 设置在 header 中
        String cookie = SPUtils.getInstance().getString(domain);
        if (!TextUtils.isEmpty(domain) && !TextUtils.isEmpty(cookie)) {
            requestBuilder.addHeader(HttpConstant.COOKIE_NAME, cookie);
        }

        // 如果公共请求头不为空,则构建新的请求
        if (headers != null) {
            for (Map.Entry<String, Object> entry : headers.entrySet()) {
                requestBuilder.addHeader(entry.getKey(), entry.getValue().toString());
            }
        }

        Request requestB = requestBuilder.build();
        Response.Builder responseBuilder = chain.proceed(requestB).newBuilder();
        if (!TextUtils.isEmpty(requestB.cacheControl().toString())) {
            responseBuilder
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", "public, max-age=" + requestB.cacheControl().maxAgeSeconds());
        }
        return responseBuilder.build();
    }
}
