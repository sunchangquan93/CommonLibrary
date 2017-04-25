package com.quanquan.qgodlibrary.okhttp.callback;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by qGod on 2017/4/21
 * Thank you for watching my code
 */

public abstract class Callback<T>
{
    /**
     * UI Thread
     */
    public void onBefore(Request request)
    {
    }

    /**
     * UI Thread
     */
    public void onAfter()
    {
    }

    /**
     * 进度显示
     */
    public void inProgress(float progress, long total)
    {

    }

    /**
     * if you parse reponse code in parseNetworkResponse, you should make this method return true.
     */
    public boolean validateReponse(Response response)
    {
        return response.isSuccessful();
    }

    /**
     * Thread Pool Thread
     */
    public abstract T parseNetworkResponse(Response response) throws Exception;

    public abstract void onError(Call call, Exception e);

    public abstract void onResponse(T response);

    public static Callback CALLBACK_DEFAULT = new Callback()
    {
        @Override
        public Object parseNetworkResponse(Response response) throws Exception
        {
            return null;
        }

        @Override
        public void onError(Call call, Exception e)
        {

        }

        @Override
        public void onResponse(Object response)
        {

        }
    };

}