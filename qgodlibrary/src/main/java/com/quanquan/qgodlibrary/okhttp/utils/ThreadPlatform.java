package com.quanquan.qgodlibrary.okhttp.utils;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by qGod on 2017/4/21
 * Thank you for watching my code
 * 线程类
 */

public class ThreadPlatform {

    private static final ThreadPlatform PLATFORM = findPlatform();

    public static ThreadPlatform get()
    {
        L.e(PLATFORM.getClass().toString());
        return PLATFORM;
    }
    private static ThreadPlatform findPlatform()
    {
        try
        {
            Class.forName("android.os.Build");
            if (Build.VERSION.SDK_INT != 0)
            {
                return new Android();
            }
        } catch (ClassNotFoundException ignored)
        {
        }
        return new ThreadPlatform();
    }

    public Executor defaultCallbackExecutor()
    {
        return Executors.newCachedThreadPool();
    }

    public void execute(Runnable runnable)
    {
        defaultCallbackExecutor().execute(runnable);
    }

    static class Android extends ThreadPlatform
    {
        @Override
        public Executor defaultCallbackExecutor()
        {
            return new MainThreadExecutor();
        }

        static class MainThreadExecutor implements Executor
        {
            private final Handler handler = new Handler(Looper.getMainLooper());

            @Override
            public void execute(Runnable r)
            {
                handler.post(r);
            }
        }
    }


}
