package com.example.root.vma.controller;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class PictureUtils {
    public static Bitmap getScaledBitmap (String path, int destWidth, int destHeight){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path,options);

        float srcWidth  =  options.outWidth;
        float srcHeight  = options.outHeight;

        int inScaleSize = 1;
        if(srcHeight > destHeight || srcWidth > destWidth){
            float heightScale = srcHeight /destHeight;
            float widthScale = srcWidth / destWidth;

            inScaleSize = Math.round(heightScale > widthScale ? heightScale : widthScale);
        }

        options = new BitmapFactory.Options();
        options.inSampleSize = inScaleSize;

        return  BitmapFactory.decodeFile(path,options);
    }
}
