package com.quanquan.qgodlibrary.okhttp.builder;

import java.util.Map;

/**
 * Created by qGod on 2017/4/21
 * Thank you for watching my code
 */
public interface HasParamsable
{
    OkHttpRequestBuilder params(Map<String, String> params);
    OkHttpRequestBuilder addParams(String key, String val);
}
