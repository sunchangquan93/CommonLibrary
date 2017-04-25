package com.quanquan.qgodlibrary.rxbus;

/**
 * Created by qGod on 2017/4/15
 * Thank you for watching my code
 */

public class RxBusBaseMessage {

    private  int code;
    private Object object;
    private RxBusBaseMessage(){}
    public RxBusBaseMessage(int code, Object object){
        this.code=code;
        this.object=object;
    }

    public int getCode() {
        return code;
    }

    public Object getObject() {
        return object;
    }
}
