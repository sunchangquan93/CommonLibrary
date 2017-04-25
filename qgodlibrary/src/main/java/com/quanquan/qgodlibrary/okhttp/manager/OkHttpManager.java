package com.quanquan.qgodlibrary.okhttp.manager;

import com.quanquan.qgodlibrary.okhttp.builder.GetBuilder;
import com.quanquan.qgodlibrary.okhttp.builder.PostFormBuilder;
import com.quanquan.qgodlibrary.okhttp.builder.PostStringBuilder;
import com.quanquan.qgodlibrary.okhttp.callback.Callback;
import com.quanquan.qgodlibrary.okhttp.request.RequestCall;
import com.quanquan.qgodlibrary.okhttp.utils.ThreadPlatform;

import java.io.IOException;
import java.util.concurrent.Executor;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * Created by qGod on 2017/4/21
 * Thank you for watching my code
 */
public class OkHttpManager
{
    public static final long DEFAULT_MILLISECONDS = 10_000L;
    private volatile static OkHttpManager mInstance;
    private OkHttpClient mOkHttpClient;
    private ThreadPlatform mPlatform;

    public OkHttpManager(OkHttpClient okHttpClient)
    {
        if (okHttpClient == null)
        {
            mOkHttpClient = new OkHttpClient();
        } else
        {
            mOkHttpClient = okHttpClient;
        }

        mPlatform = ThreadPlatform.get();
    }


    public static OkHttpManager initClient(OkHttpClient okHttpClient)
    {
        if (mInstance == null)
        {
            synchronized (OkHttpManager.class)
            {
                if (mInstance == null)
                {
                    mInstance = new OkHttpManager(okHttpClient);
                }
            }
        }
        return mInstance;
    }

    public static OkHttpManager getInstance()
    {
        return initClient(null);
    }

    public Executor getDelivery()
    {
        return mPlatform.defaultCallbackExecutor();
    }

    public OkHttpClient getOkHttpClient()
    {
        return mOkHttpClient;
    }

    public static PostFormBuilder post()
    {
        return new PostFormBuilder();
    }

    public static GetBuilder get()
    {
        return new GetBuilder();
    }

    public static PostStringBuilder postString()
    {
        return new PostStringBuilder();
    }

    public void execute(final RequestCall requestCall, Callback callback)
    {
        if (callback == null)
            callback = Callback.CALLBACK_DEFAULT;
        final Callback finalCallback = callback;

        requestCall.getCall().enqueue(new okhttp3.Callback()
        {
            @Override
            public void onFailure(Call call, final IOException e)
            {
                sendFailResultCallback(call, e, finalCallback);
            }

            @Override
            public void onResponse(final Call call, final Response response)
            {
                try
                {
                    if (call.isCanceled())
                    {
                        sendFailResultCallback(call, new IOException("Canceled!"), finalCallback);
                        return;
                    }

                    if (!finalCallback.validateReponse(response))
                    {
                        sendFailResultCallback(call, new IOException("request failed , reponse's code is : " + response.code()), finalCallback);
                        return;
                    }

                    Object o = finalCallback.parseNetworkResponse(response);
                    sendSuccessResultCallback(o, finalCallback);
                } catch (Exception e)
                {
                    sendFailResultCallback(call, e, finalCallback);
                } finally
                {
                    if (response.body() != null)
                        response.body().close();
                }

            }
        });
    }


    public void sendFailResultCallback(final Call call, final Exception e, final Callback callback)
    {
        if (callback == null) return;

        mPlatform.execute(new Runnable()
        {
            @Override
            public void run()
            {
                callback.onError(call, e);
                callback.onAfter();
            }
        });
    }

    public void sendSuccessResultCallback(final Object object, final Callback callback)
    {
        if (callback == null) return;
        mPlatform.execute(new Runnable()
        {
            @Override
            public void run()
            {
                callback.onResponse(object);
                callback.onAfter();
            }
        });
    }

    public void cancelTag(Object tag)
    {
        for (Call call : mOkHttpClient.dispatcher().queuedCalls())
        {
            if (tag.equals(call.request().tag()))
            {
                call.cancel();
            }
        }
        for (Call call : mOkHttpClient.dispatcher().runningCalls())
        {
            if (tag.equals(call.request().tag()))
            {
                call.cancel();
            }
        }
    }

    public static class METHOD
    {
        public static final String HEAD = "HEAD";
        public static final String DELETE = "DELETE";
        public static final String PUT = "PUT";
        public static final String PATCH = "PATCH";
    }
}

