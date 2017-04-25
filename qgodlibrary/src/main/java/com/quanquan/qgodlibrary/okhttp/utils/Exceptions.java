package com.quanquan.qgodlibrary.okhttp.utils;

/**
 * Created by qGod on 2017/4/21
 * Thank you for watching my code
 * 自定义异常
 */

public class Exceptions {
    public static void illegalArgument(String msg, Object... params){
        throw new IllegalArgumentException(String.format(msg,params));
    }
}
