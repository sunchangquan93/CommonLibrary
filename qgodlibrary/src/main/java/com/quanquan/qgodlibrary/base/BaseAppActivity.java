package com.quanquan.qgodlibrary.base;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.quanquan.qgodlibrary.utils.ActivityManagerUtils;
import com.quanquan.qgodlibrary.utils.ToastUtils;

/**
 * Created by qGod on 2017/4/15
 * Thank you for watching my code
 */
public abstract class BaseAppActivity extends AppCompatActivity {
    protected String TAG;
    protected Context context;
    protected Activity activity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        activity = this;
        TAG = getClass().getSimpleName();
        if (bindLayoutId() != 0) {
            setContentView(bindLayoutId());
            ActivityManagerUtils.addActivity(this);
            initNavigationTitle();
            doBusiness();
        } else {
            throw new IllegalStateException("layoutid is null!");
        }

    }
    /**
     * 绑定布局id
     *
     * @return
     */
    protected abstract int bindLayoutId();

    /**
     * 设置导航栏
     */
    protected abstract void initNavigationTitle();

    /**
     * 业务逻辑
     */
    protected abstract void doBusiness();

    /**
     * Toast显示,string
     */
    public void showToast(String stringContent) {
        ToastUtils.showToastByString(context, stringContent);
    }

    /**
     * Toast显示,int
     */
    public void showToast(int stringId) {
        ToastUtils.showToastByInt(context, stringId);
    }

    //设置透明状态栏
    public void transparentStatusBar() {
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManagerUtils.removeActivity(this);
    }

}
