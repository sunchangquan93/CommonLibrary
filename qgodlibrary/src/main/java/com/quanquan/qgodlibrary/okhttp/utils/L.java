package com.quanquan.qgodlibrary.okhttp.utils;

import android.util.Log;

/**
 * Created by qGod on 2017/4/21
 * Thank you for watching my code
 */

public class L {
    private static boolean debug = false;

    public static void e(String msg){
        if(debug){
            Log.e("okhttp",msg);
        }
    }
}
