package com.quanquan.qgodlibrary.base;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.quanquan.qgodlibrary.utils.ToastUtils;

/**
 * Created by qGod on 2017/4/15
 * Thank you for watching my code
 */
public abstract class BaseAppFragment extends Fragment {
    protected Context context;
    protected Activity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        activity = getActivity();
    }
    /**
     * 绑定布局id
     */
    protected abstract int bindLayoutId();
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

}
