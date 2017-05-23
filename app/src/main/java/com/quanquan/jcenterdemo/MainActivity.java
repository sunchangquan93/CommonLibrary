package com.quanquan.jcenterdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.quanquan.qgodlibrary.okhttp.callback.StringCallback;
import com.quanquan.qgodlibrary.okhttp.manager.OkHttpManager;
import com.quanquan.qgodlibrary.utils.LogUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.Call;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @InjectView(R.id.btn)
    Button btn;

    private ArrayList<ImageItem> imageItems;

    //图片选择
    @OnClick(R.id.btn)
    void onClick(){
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());
        imagePicker.setMultiMode(true);   //多选
        imagePicker.setShowCamera(true);  //显示拍照按钮
        imagePicker.setSelectLimit(9);    //最多选择9张
        imagePicker.setCrop(false);       //不进行裁剪
        Intent intent = new Intent(this, ImageGridActivity.class);
        startActivityForResult(intent, 100);
    }
    //图片回传
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == 100) {
                imageItems = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
            } else {
                Toast.makeText(this, "没有选择图片", Toast.LENGTH_SHORT).show();
            }
        }
    }
    @InjectView(R.id.textview)
    TextView tvprogress;
    @InjectView(R.id.textviewsum)
    TextView tvSum;
    @InjectView(R.id.progress)
    ProgressBar progressbar;

    public static final String URL_FORM_UPLOAD ="http://server.jeasonlzy.com/OkHttpUtils/upload";
    @OnClick(R.id.btn_upload)
    void onUpload(){
        Map<String,String> params=new HashMap<>();
        params.put("param1", "paramValue1");
        params.put("param2", "paramValue2");

        Map<String,File> files=new HashMap<>();
        for (ImageItem imageItem : imageItems) {
            File file=new File(imageItem.path);
            files.put(imageItem.name,file);
        }

        OkHttpManager.post().url(URL_FORM_UPLOAD)
                .addHeader("header1", "headerValue1")//
                .addHeader("header2", "headerValue2")
                .params(params)
                .files("file",files)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void inProgress(float progress, long total) {
                tvSum.setText("总大小"+(total/1024/1024)+"M");
                LogUtils.e("sunchangquan","progress="+progress);
                int pro=(int)(progress*100);
                tvprogress.setText(pro+"%");
                progressbar.setProgress(pro);
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);



//        Observable<Object> observable = RxView.clicks(btn);
//        observable.throttleFirst(2, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
//            @Override
//            public void accept(Object o) throws Exception {
//                LogUtils.e(TAG, "点击事件clicks----");
//
//            }
//        });
//        Disposable subscribe = RxTextView.textChanges(editText).subscribe(new Consumer<CharSequence>() {
//            @Override
//            public void accept(CharSequence charSequence) throws Exception {
//                LogUtils.e(TAG, "accept----" + charSequence.toString());
//            }
//        });

        // Make sure to unsubscribe the subscription
    }
}
