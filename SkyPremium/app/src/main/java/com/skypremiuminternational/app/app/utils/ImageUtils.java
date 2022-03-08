package com.skypremiuminternational.app.app.utils;

import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.skypremiuminternational.app.R;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class ImageUtils {


  /**
   * using Glide to load image (Single)
   * @param imageView
   * @param url
   */
  public static  void glideImg(ImageView imageView , String url){
    RequestOptions requestOptions = new RequestOptions();
    requestOptions.placeholder(R.drawable.placeholder);
    requestOptions.dontAnimate();
    requestOptions.error(R.drawable.ic_logo);
    Glide.with(imageView.getContext())
        .load(url)
        .apply(requestOptions)
        .into(imageView);

  }
  public static  void glideImgDefaulShite(ImageView imageView , String url){
    RequestOptions requestOptions = new RequestOptions();
    requestOptions.placeholder(R.drawable.placeholder);
    requestOptions.dontAnimate();
    requestOptions.error(R.drawable.ic_logo);
    Glide.with(imageView.getContext())
        .load(url)
        .apply(requestOptions)
        .into(imageView);

  }
  public static  void glideImgDefaulSky(ImageView imageView , String url){
    RequestOptions requestOptions = new RequestOptions();
    requestOptions.placeholder(R.drawable.placeholder);
    requestOptions.dontAnimate();
    requestOptions.error(R.drawable.placeholder);
    Glide.with(imageView.getContext())
        .load(url)
        .apply(requestOptions)
        .into(imageView);

  }

  public static  void glideImgBlurSky(ImageView imageView , String url){
    RequestOptions requestOptions = new RequestOptions();
    requestOptions.placeholder(R.drawable.placeholder);
    requestOptions.transform(new BlurTransformation(25));
    requestOptions.error(R.drawable.placeholder);
    Glide.with(imageView.getContext())
            .load(url)
            .apply(requestOptions)
            .into(imageView);



  }
  public static void grayScale(ImageView imageView,String url){

    RequestOptions requestOptions = new RequestOptions();
    requestOptions.placeholder(R.drawable.placeholder);
    requestOptions.error(R.drawable.placeholder);

    Glide.with(imageView.getContext())
        .load(url)
        .apply(requestOptions)
        .into(imageView);

    ColorMatrix colorMatrix = new ColorMatrix();
    colorMatrix.setSaturation(0);
    ColorMatrixColorFilter filter = new ColorMatrixColorFilter(colorMatrix);
    imageView.setColorFilter(filter);
  }

  public static void grayScaleRev(ImageView imageView,String url){

    RequestOptions requestOptions = new RequestOptions();
    requestOptions.placeholder(R.drawable.placeholder);
    requestOptions.error(R.drawable.placeholder);

    Glide.with(imageView.getContext())
        .load(url)
        .apply(requestOptions)
        .into(imageView);

    ColorMatrix colorMatrix = new ColorMatrix();
    colorMatrix.setSaturation(1);
    ColorMatrixColorFilter filter = new ColorMatrixColorFilter(colorMatrix);
    imageView.setColorFilter(filter);
  }

}
