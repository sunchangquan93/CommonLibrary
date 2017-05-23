package com.quanquan.qgodlibrary.utils;


import java.text.SimpleDateFormat;

/**
 * Created by qGod on 2017/4/15
 * Thank you for watching my code
 * 格式化工具类
 */

public class SimpleDateFormatUtils {

    private static SimpleDateFormatUtils instance;

    private SimpleDateFormatUtils(){}

    public static SimpleDateFormatUtils getInstance(){
        if(instance==null){
            instance=new SimpleDateFormatUtils();
        }
        return instance;
    }
    /**
     *
     * @param format yy:MM:dd HH:mm;ss
     */
    public SimpleDateFormat getFormat(String format){
        return new MyThreadLocal(format).get();
    }

    class MyThreadLocal extends ThreadLocal<SimpleDateFormat>{
        /**
         * 格式化样式
         */
        private String format;

        public MyThreadLocal(String format){
            this.format=format;
        }
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat(format);
        }
    }
}
