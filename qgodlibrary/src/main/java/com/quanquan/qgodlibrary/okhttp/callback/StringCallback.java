package com.quanquan.qgodlibrary.okhttp.callback;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by qGod on 2017/4/21
 * Thank you for watching my code
 */
public abstract class StringCallback extends Callback<String>
{
    @Override
    public String parseNetworkResponse(Response response) throws IOException
    {
        return response.body().string();
    }
}
