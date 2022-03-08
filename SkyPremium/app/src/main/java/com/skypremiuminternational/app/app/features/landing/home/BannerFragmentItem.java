package com.skypremiuminternational.app.app.features.landing.home;

import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.skypremiuminternational.app.R;
import com.skypremiuminternational.app.app.utils.Constants;
import com.skypremiuminternational.app.app.utils.RxBus;




import butterknife.BindView;
import butterknife.ButterKnife;

public class BannerFragmentItem extends Fragment {

  @BindView(R.id.img)
  ImageView img;
  @BindView(R.id.llMain)
  LinearLayout llMain;
  @BindView(R.id.llCategory)
  LinearLayout llCategory;

  @BindView(R.id.tvName)
  TextView tvName;
  @BindView(R.id.flGrayBg)
  FrameLayout flGrayBg;
  @BindView(R.id.tvMainContent)
  TextView tvMainContent;
  @BindView(R.id.tvHeader)
  TextView tvHeader;
  @BindView(R.id.tvCustomerName)
  TextView tvCustomerName;



  HomeCategoryResponse listData;
  int position;
  MainBannerSliderAdapter adapter;





  public static BannerFragmentItem newInstance(HomeCategoryResponse listData, int position, MainBannerSliderAdapter mainBannerSliderAdapter) {
    BannerFragmentItem myFragment = new BannerFragmentItem();
    myFragment.listData = listData;
    myFragment.position = position;
    myFragment.adapter = mainBannerSliderAdapter;
    return myFragment;
  }



  @Override
  @Nullable
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.item_main_banner, null);
    ButterKnife.bind(this,view);

    logicVisibleContentLayout();
    showImage();
    showContent();


    Banner thisBanner =  listData.getBanners().get(position);
    view.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
//        if (!TextUtils.isEmpty(thisBanner.getTargetUrl())) {
//          switch (thisBanner.getTargetUrl()) {
//            case "travel.html":
//              NavigationEvent n0 = new NavigationEvent(Constants.DEEP_LINK_LANDING_TRAVEL,Constants.TRAVEL, thisBanner);
//              n0.setFromBanner(true);
//              RxBus.get().post(n0);
//              break;
//
//            case "wine-dine.html":
//              NavigationEvent n1 = new NavigationEvent(Constants.DEEP_LINK_LANDING_WINE,Constants.WINE_AND_DINE, thisBanner);
//              n1.setFromBanner(true);
//              RxBus.get().post(n1);
//              break;
//
//            case "shopping.html":
//              NavigationEvent n2 = new NavigationEvent(Constants.DEEP_LINK_LANDING_SHOPPING,Constants.SHOPPING, thisBanner);
//              n2.setFromBanner(true);
//              RxBus.get().post(n2);
//              break;
//
//            case "wellness.html":
//              NavigationEvent n3 = new NavigationEvent(Constants.DEEP_LINK_LANDING_WELLNESS,Constants.WELLNESS, thisBanner);
//              n3.setFromBanner(true);
//              RxBus.get().post(n3);
//              break;
//
//            case "wine-dine/white-glove.html":
//              NavigationEvent n4 = new NavigationEvent(Constants.DEEP_LINK_LANDING_WINE,Constants.WHITE_GLOVE_ID, thisBanner);
//              n4.setFromBanner(true);
//              RxBus.get().post(n4);
//              break;
//
//            case "e-store.html":
//              NavigationEvent n5 = new NavigationEvent(Constants.E_STORE, thisBanner);
//              n5.setFromBanner(true);
//              RxBus.get().post(n5);
//              break;
//
//            case "wellness/handpicked-experiences.html":
//              NavigationEvent n6 = new NavigationEvent(Constants.DEEP_LINK_LANDING_WELLNESS,Constants.EXPERIENCES_ID, thisBanner);
//              n6.setFromBanner(true);
//              RxBus.get().post(n6);
//              break;
//
//          }
//        } else {
//          NavigationEvent n7 = new NavigationEvent(Constants.DEEP_LINK_LANDING_HOME,"", thisBanner);
//          n7.setFromBanner(true);
//          RxBus.get().post(n7);
//        }
      }
    });


    return view;
  }

  private void showContent() {
    Banner banner = listData.getBanners().get(position);

    if(banner.getMainContent() !=null){
      StringBuilder builder = new StringBuilder();
      builder.append(banner.getHeader());
      tvHeader.setText(Html.fromHtml(builder.toString()));
    } else {
      tvHeader.setText("");
    }
    if(banner.getMainContent() !=null){
      tvMainContent.setText(Html.fromHtml(banner.getMainContent()));
    }else {
      tvMainContent.setText("");
    }
    if(banner.getName() !=null){
      tvName.setText(Html.fromHtml(banner.getName()));
    }
    else{
      tvName.setText("");
    }
    if(adapter.getCustomerName() !=null){
      tvCustomerName.setText(Html.fromHtml(adapter.getCustomerName()));
    }
    else{
      tvCustomerName.setText("");
    }

  }

  private void showImage() {
    RequestOptions requestOptions = new RequestOptions();
    requestOptions.placeholder(R.drawable.placeholder);
    requestOptions.dontAnimate();
    requestOptions.centerCrop();
    requestOptions.error(R.drawable.white);
    Glide.with(getActivity())
        .load(listData.getBanners().get(position).getImage())
        .apply(requestOptions)
        .into(img);
  }

  private void logicVisibleContentLayout() {
    if(position == 0){
      llMain.setVisibility(View.VISIBLE);
      llCategory.setVisibility(View.GONE);
      flGrayBg.setVisibility(View.GONE);
    }else {
      llMain.setVisibility(View.GONE);
      llCategory.setVisibility(View.VISIBLE);
      flGrayBg.setVisibility(View.VISIBLE);
    }
  }

}
