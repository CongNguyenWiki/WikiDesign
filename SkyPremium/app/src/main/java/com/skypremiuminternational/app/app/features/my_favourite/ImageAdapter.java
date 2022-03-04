package com.skypremiuminternational.app.app.features.my_favourite;


import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.skypremiuminternational.app.R;
import com.skypremiuminternational.app.domain.utils.URLUtils;

import java.util.List;


public class ImageAdapter extends PagerAdapter{
  Context mContext;
  List<String> dataset;


  public ImageAdapter(Context context, List<String> dataset) {
    this.mContext = context;
    this.dataset = dataset;
  }

  @Override
  public boolean isViewFromObject(View view, Object object) {
    return view == ((ImageView) object);
  }


  @Override
  public Object instantiateItem(ViewGroup container, int position) {
    ImageView imageView = new ImageView(mContext);
    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
    RequestOptions requestOptions = new RequestOptions();
    requestOptions.placeholder(R.drawable.placeholder);
    requestOptions.dontAnimate();
    requestOptions.error(R.drawable.white);
    Glide.with(imageView.getContext()).asBitmap()
        .load(URLUtils.formatImageURL(dataset.get(position)))
        .apply(requestOptions)
        .listener(URLUtils.getGlideListener(imageView.getContext(),dataset.get(position),imageView))
        .into(new SimpleTarget<Bitmap>() {
          @Override
          public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
            imageView.setImageBitmap(resource); // Possibly runOnUiThread()
          }
        });

    ((ViewPager) container).addView(imageView, 0);
    return imageView;
  }

  @Override
  public void destroyItem(ViewGroup container, int position, Object object) {
    ((ViewPager) container).removeView((ImageView) object);
  }

  @Override
  public int getCount() {
    return dataset.size();
  }
}