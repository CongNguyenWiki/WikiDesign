package com.skypremiuminternational.app.app.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.viewpager.widget.ViewPager;

public class WrapContentHeightViewPagerNoSwipe extends ViewPager {

  private boolean scrollable = false;

  public WrapContentHeightViewPagerNoSwipe(Context context) {
    super(context);
  }

  public WrapContentHeightViewPagerNoSwipe(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public void setScrollable(boolean scrollable) {
    this.scrollable = scrollable;
  }

  @Override
  public boolean onInterceptTouchEvent(MotionEvent ev) {
    return scrollable;
  }

  @Override
  public boolean onTouchEvent(MotionEvent ev) {
    return scrollable;
  }


  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

    int height = 0;
    for(int i = 0; i < getChildCount(); i++) {
      View child = getChildAt(i);
      child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
      int h = child.getMeasuredHeight();
      if(h > height) height = h;
    }

    heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);

    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
  }

}