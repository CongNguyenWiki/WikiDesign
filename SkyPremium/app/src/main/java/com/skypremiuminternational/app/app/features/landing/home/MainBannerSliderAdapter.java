package com.skypremiuminternational.app.app.features.landing.home;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;



public class MainBannerSliderAdapter extends FragmentPagerAdapter {
  Context context;
  HomeCategoryResponse dataList;
  String customerName;
  public MainBannerSliderAdapter(Context context, FragmentManager fragmentManager) {
    super(fragmentManager);
    this.context = context;
  }

  // Returns total number of pages
  @Override
  public int getCount() {
    return dataList == null ? 0 : dataList.getBanners().size();
  }

  // Returns the fragment to display for that page
  @Override
  public Fragment getItem(int position) {
    return BannerFragmentItem.newInstance(dataList, position, MainBannerSliderAdapter.this);
  }

  public void setDataList(HomeCategoryResponse dataList) {
    this.dataList = dataList;
    notifyDataSetChanged();
  }

  public String getCustomerName() {
    return customerName;
  }

  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }

  // Returns the page title for the top indicator
  @Override
  public CharSequence getPageTitle(int position) {
    return null;
  }

}