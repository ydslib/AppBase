/**
 * Created by : yds
 * Time: 2020-11-23 9:51 PM
 */
package com.crystallake.basic.http.cookie.store;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.HttpUrl;

public interface CookieStore {

    /**保存url对应的所有cookie*/
    void saveCookie(HttpUrl url, List<Cookie> cookies);

    /**报错URL对应的cookie*/
    void saveCookie(HttpUrl url,Cookie cookie);

    /**加载URL对应的所有cookies*/
    List<Cookie> loadCookie(HttpUrl url);

    /**获取当前URL对应的所有cookie*/
    List<Cookie> getAllCookies();

    List<Cookie> getCookie(HttpUrl url);

    boolean removeCookie(HttpUrl url,Cookie cookie);

    /** 根据url移除所有的cookie */
    boolean removeCookie(HttpUrl url);

    /** 移除所有的cookie */
    boolean removeAllCookie();



}
