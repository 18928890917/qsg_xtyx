package com.xxsc.xtyx.middle;

import android.widget.ImageView;

import com.xiaocoder.android.fw.general.imageloader.XCIImageLoader;

/**
 * Created by xiaocoder on 2015/11/16.
 */
public class XTYXImage {

    private static XCIImageLoader imageLoader;

    public static XCIImageLoader getImageLoader() {
        return imageLoader;
    }

    public static void initImager(XCIImageLoader imageLoader) {
        XTYXImage.imageLoader = imageLoader;
    }

    /**
     * 图片加载
     */
    public static void displayImage(String uri, ImageView imageView, Object... options) {
        imageLoader.display(uri, imageView, options);
    }

    public static void displayImage(String uri, ImageView imageView) {
        imageLoader.display(uri, imageView);
    }

}
