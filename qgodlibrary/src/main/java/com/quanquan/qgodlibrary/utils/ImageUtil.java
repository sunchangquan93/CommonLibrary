package com.quanquan.qgodlibrary.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * Created by qGod on 2017/4/15
 * Thank you for watching my code
 * 图片压缩工具类
 */

public class ImageUtil {

    /**
     * 质量压缩
     */
    public static Bitmap compressImage(Bitmap bitmap){
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);//质量压缩方法,100表示不压缩
        int options=90;
        while(baos.toByteArray().length/1024>100){// 循环判断如果压缩后图片是否大于100kb,大于继续压缩
            bitmap.compress(Bitmap.CompressFormat.JPEG,options,baos);
            options-=10;
        }
        ByteArrayInputStream bais=new ByteArrayInputStream(baos.toByteArray());
        Bitmap newBitmap = BitmapFactory.decodeStream(bais, null, null);
        return newBitmap;
    }


}
