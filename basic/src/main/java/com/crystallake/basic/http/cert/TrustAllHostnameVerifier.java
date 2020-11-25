package com.crystallake.basic.http.cert;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

public class TrustAllHostnameVerifier implements HostnameVerifier {

    /**
     * 返回true，信任所有服务器
     */
    @Override
    public boolean verify(String hostname, SSLSession session) {
        return true;
    }
}