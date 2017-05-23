package com.quanquan.qgodlibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.util.List;

/**
 * Created by qGod on 2017/4/15
 * Thank you for watching my code
 * 软件盘工具类
 */

public class SoftKeyUtils {
    private static SoftKeyUtils instance;
    private Context context;

    private SoftKeyUtils(Context context){
        this.context=context.getApplicationContext();
    }

    public static SoftKeyUtils getInstance(Context context){
        if(instance==null){
            instance=new SoftKeyUtils(context);
        }
        return instance;
    }

    /**
     * 第一种隐藏软件盘方法（当显示，点击可隐藏，当隐藏，点击可显示）
     */
    public void oneHidekeySoft(){
        InputMethodManager ims= (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        ims.toggleSoftInput(0,InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 隐藏软键盘(只适用于Activity，不适用于Fragment)
     */
    public void hideSoftKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 隐藏软键盘(可用于Activity，Fragment)
     * 参数二传入当前界面有所获取焦点的view
     */
    public void hideSoftKeyboard(Context context, List<View> viewList) {
        if (viewList == null) return;

        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);

        for (View v : viewList) {
            inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


}
