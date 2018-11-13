package com.tanker.life.manager.imageload;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.tanker.life.R;

import java.io.File;

/**
 * @author : Tanker
 * @email :zhoukai@zto.cn
 * @date : 2018/11/06
 * @describe : 图片加载
 */
public class ImageLoadManager {

    /**
     * 普通加载网络图片
     *
     * @param context
     * @param url     网络图片
     * @return
     */
    public static RequestBuilder<Drawable> load(Context context, String url) {
        return Glide.with(context).load(url);
    }

    /**
     * 加载本地图片
     *
     * @param context
     * @param filePath
     * @return
     */
    public static RequestBuilder<Drawable> loadFile(Context context, String filePath) {
        return Glide.with(context).load(new File(filePath));
    }

    /**
     * 返回图片为BitMap类型
     *
     * @param context
     * @param url
     * @param options
     * @return
     */
    public static RequestBuilder<Bitmap> load(Context context, String url, RequestOptions options) {
        return Glide.with(context).applyDefaultRequestOptions(options).asBitmap().load(url);
    }


    /**
     * 支持设置图片大小
     *
     * @param width
     * @param height
     * @return
     */
    public static RequestOptions getOptions(int width, int height) {
        return new RequestOptions()
                .override(width, height)
                .placeholder(R.mipmap.image_place)
                .error(R.mipmap.image_error)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
    }

//    new RequestOptions()
//                .placeholder(R.mipmap.ic_launcher)				//加载成功之前占位图
//                .error(R.mipmap.ic_launcher)					//加载错误之后的错误图
//                .override(400,400)								//指定图片的尺寸
//    //指定图片的缩放类型为fitCenter （等比例缩放图片，宽或者是高等于ImageView的宽或者是高。）
//                .fitCenter()
//    //指定图片的缩放类型为centerCrop （等比例缩放图片，直到图片的狂高都大于等于ImageView的宽度，然后截取中间的显示。）
//                .centerCrop()
//                .circleCrop()//指定图片的缩放类型为centerCrop （圆形）
//                .skipMemoryCache(true)							//跳过内存缓存
//                .diskCacheStrategy(DiskCacheStrategy.ALL)		//缓存所有版本的图像
//                .diskCacheStrategy(DiskCacheStrategy.NONE)		//跳过磁盘缓存
//                .diskCacheStrategy(DiskCacheStrategy.DATA)		//只缓存原来分辨率的图片
//                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)	//只缓存最终的图片

}