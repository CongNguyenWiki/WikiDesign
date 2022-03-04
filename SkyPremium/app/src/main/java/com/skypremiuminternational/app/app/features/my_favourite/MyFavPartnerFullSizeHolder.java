package com.skypremiuminternational.app.app.features.my_favourite;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.skypremiuminternational.app.R;
import com.skypremiuminternational.app.app.utils.Constants;
import com.skypremiuminternational.app.app.utils.CustomTypefaceSpan;
import com.skypremiuminternational.app.app.utils.DecimalUtil;
import com.skypremiuminternational.app.app.utils.ObjectUtil;
import com.skypremiuminternational.app.app.utils.listener.SaveImageActionListener;
import com.skypremiuminternational.app.app.view.EllipSizeTextView;
import com.skypremiuminternational.app.domain.models.favourite.DisplayReservationsCategory;
import com.skypremiuminternational.app.domain.models.favourite.FavouriteItem;
import com.skypremiuminternational.app.domain.utils.ProductUtil;
import com.willy.ratingbar.ScaleRatingBar;

import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyFavPartnerFullSizeHolder extends RecyclerView.ViewHolder {

  @BindView(R.id.llWhatUnique)
  LinearLayout llWhatUnique;
  @BindView(R.id.tvWhatUnique)
  EllipSizeTextView tvWhatUnique;
  @BindView(R.id.tvTitleBenefit)
  TextView tvTitleBenefit;
  @BindView(R.id.tvBenefits)
  EllipSizeTextView tvBenefits;
  @BindView(R.id.layoutSliderIndicators)
  LinearLayout layoutSliderIndicators;
  @BindView(R.id.viewPager)
  ViewPager viewPager;
  @BindView(R.id.tvTitle_travel_product)
  TextView tvTitle;
  @BindView(R.id.tvSubTitle_travel_product)
  TextView tvSubTitle;
  @BindView(R.id.tv_reserve)
  TextView tvReserve;
  @BindView(R.id.tv_view_detail)
  public TextView tvViewDetail;
  @BindView(R.id.ivFav_travel_product)
  public ImageView ivFavourite;
  @BindView(R.id.iv_new)
  ImageView ivNew;
  @BindView(R.id.iv_tier)
  ImageView ivTier;
  @BindView(R.id.tvPrice)
  TextView tvPrice;
  @BindView(R.id.groupUnique)
  ChipGroup groupUnique;
  @BindView(R.id.llReview)
  LinearLayout llReview;
  @BindView(R.id.tvRating)
  TextView tvRating;
  @BindView(R.id.tvCountReview)
  TextView tvCountReview;
  @BindView(R.id.tvDollars)
  TextView tvDollars;
  @BindView(R.id.imgNext)
  ImageView imgNext;
  @BindView(R.id.imgPrevious)
  ImageView imgPrevious;
  @BindView(R.id.rtStar)
  ScaleRatingBar rtStar;


  SaveImageActionListener listener;
  List<String> imgList = new ArrayList<>();

  DisplayReservationsCategory displayReservationsCategory;

  String reserveButtonTitle = null;
  String sku = null;
  String linkHGW = null;
  String restaurantIDS = null;

  public DisplayReservationsCategory getDisplayReservationsCategory() {
    return displayReservationsCategory;
  }

  public void setDisplayReservationsCategory(DisplayReservationsCategory displayReservationsCategory) {
    this.displayReservationsCategory = displayReservationsCategory;
  }

  public String getReserveButtonTitle() {
    return reserveButtonTitle;
  }

  public void setReserveButtonTitle(String reserveButtonTitle) {
    this.reserveButtonTitle = reserveButtonTitle;
  }

  public String getSku() {
    return sku;
  }

  public void setSku(String sku) {
    this.sku = sku;
  }

  public String getLinkHGW() {
    return linkHGW;
  }

  public void setLinkHGW(String linkHGW) {
    this.linkHGW = linkHGW;
  }

  public String getRestaurantIDS() {
    return restaurantIDS;
  }

  public void setRestaurantIDS(String restaurantIDS) {
    this.restaurantIDS = restaurantIDS;
  }

  public MyFavPartnerFullSizeHolder(final View itemView) {
    super(itemView);
    ButterKnife.bind(this, itemView);
  }

  public void bind(final FavouriteItem item) {
    String selectDisplayCatogry = "";

    if (ProductUtil.isValid(item.getProduct().getCurrentServerTime())) {
      if (ProductUtil.isNew(item.getProduct().getNewsFromDate(), item.getProduct().getNewsToDate(), item.getProduct().getCurrentServerTime())) {
        ivNew.setVisibility(View.VISIBLE);
      } else {
        ivNew.setVisibility(View.GONE);
      }
    } else {
      ivNew.setVisibility(View.GONE);
    }
//
//    if (item.getProduct().getDisplayPlatinumIcon() != null
//        && item.getProduct().getDisplayPlatinumIcon().equalsIgnoreCase(Constants.SHOW_PLATINUM_ICON)) {
//      ivTier.setVisibility(View.VISIBLE);
//    } else {
//      ivTier.setVisibility(View.INVISIBLE);
//    }
//

    tvTitle.setText(item.getProduct().getName());


    if (!TextUtils.isEmpty(item.getPillarName()))
      tvSubTitle.setText(item.getPillarName());
    else
      tvSubTitle.setText(item.getCategoryName());

    int favIcon = R.drawable.ic_favorite_accent;
    ivFavourite.setImageResource(favIcon);

    if (item.getProduct().getStatus().equals("1")){
      tvViewDetail.setTextColor(itemView.getResources().getColor(R.color.white));
      tvReserve.setTextColor(itemView.getResources().getColor(R.color.white));

    }
    else {
      tvViewDetail.setTextColor(itemView.getResources().getColor(R.color.whiteOpacity));
      tvReserve.setTextColor(itemView.getResources().getColor(R.color.whiteOpacity));
    }

    if(item.getExtensionAttributes() != null){
      if (item.getExtensionAttributes().getUnique() != null
          && item.getExtensionAttributes().getUnique() != null) {

        List<String> listUnique = (List<String>) item.getExtensionAttributes().getUnique();

        if (listUnique.size() > 0) {

          groupUnique.setVisibility(View.VISIBLE);
          groupUnique.removeAllViews();
          for (String unique : listUnique) {
            View view = LayoutInflater.from(itemView.getContext()).inflate(R.layout.item_chip, groupUnique, false);
            Chip chip = view.findViewById(R.id.chips_item_filter);
            chip.setText(unique);
            groupUnique.addView(chip);
            disableView(groupUnique, chip);
          }

        } else {
          groupUnique.setVisibility(View.GONE);
        }

      } else {
        groupUnique.setVisibility(View.GONE);
      }
    }else {
      groupUnique.setVisibility(View.VISIBLE);

    }

    if (imgList.size() > 0) {
      imgList.clear();
    }

    for (int i = 0; i < item.getProduct().getMediaGallery().getImages().size(); i++) {
      if(item.getProduct().getMediaGallery().getImages().get(i) !=null)
        imgList.add(item.getProduct().getMediaGallery().getImages().get(i).getFile());
    }

    if (imgList.size() >= 5) {
      imgList = imgList.subList(0, 5);
    }

    loadImageSliders(imgList, viewPager, itemView.getContext(),
        layoutSliderIndicators, imgNext, imgPrevious, item);

    if (imgList.size() <= 1) {
      imgNext.setVisibility(View.GONE);
      imgPrevious.setVisibility(View.GONE);
    }

    if (item.getProduct().getSelectDisplayCategory() != null)
      tvSubTitle.setText(item.getProduct().getSelectDisplayCategory());
    else
      tvSubTitle.setText(item.getCategoryName());

    if (!TextUtils.isEmpty(item.getProduct().getInformationUnique())) {
      llWhatUnique.setVisibility(View.VISIBLE);
//        tvWhatUnique.setText(HtmlCompat.fromHtml(customAttributesItem.getValue().toString().replaceAll("<li>","<li>&nbsp;"),0));
      tvWhatUnique.setHtml(item.getProduct().getInformationUnique(), new HtmlHttpImageGetter(tvWhatUnique));
    } else {
      tvWhatUnique.setHtml("", new HtmlHttpImageGetter(tvWhatUnique));
      llWhatUnique.setVisibility(View.GONE);
    }

    if (item.getProduct().getTitleTabBenefitPrivileges() != null) {
      tvTitleBenefit.setText(item.getProduct().getTitleTabBenefitPrivileges());
    }

    if (item.getProduct().getPartnersBenefits() != null) {
//        tvBenefits.setText(HtmlCompat.fromHtml(customAttributesItem.getValue().toString().replaceAll("<li>","<li>&nbsp;"),0));
      tvBenefits.setHtml(item.getProduct().getPartnersBenefits(), new HtmlHttpImageGetter(tvBenefits));
    } else {
      tvBenefits.setHtml("", new HtmlHttpImageGetter(tvBenefits));
    }
    if (item.getProduct().getEnableReviewTab().equals("1")) {
      llReview.setVisibility(View.VISIBLE);

      if (item.getExtensionAttributes() != null && item.getExtensionAttributes().getSummaryRating() > 0) {
        tvRating.setVisibility(View.GONE);
        if (!TextUtils.isEmpty(String.valueOf(item.getExtensionAttributes().getSummaryRating()))) {
          rtStar.setVisibility(View.VISIBLE);
          rtStar.setRating(item.getExtensionAttributes().getSummaryRating());
        } else {
          rtStar.setVisibility(View.GONE);
        }
      } else {
        rtStar.setVisibility(View.GONE);
        tvRating.setVisibility(View.VISIBLE);

        String noRating = "No rating yet";
        Typeface font1 = Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/Lato-Regular.ttf");


        SpannableStringBuilder spanString = new SpannableStringBuilder(noRating);

        spanString.setSpan(new CustomTypefaceSpan("", font1), 0, noRating.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        spanString.setSpan(new AbsoluteSizeSpan(12, true), 0, noRating.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        spanString.setSpan(new ForegroundColorSpan(Color.parseColor("#858585")), 0, noRating.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


        tvRating.setText(spanString);
      }
      if (item.getExtensionAttributes() != null &&  Integer.parseInt(item.getExtensionAttributes().getCountRating()) > 0) {
        tvCountReview.setVisibility(View.VISIBLE);
        tvCountReview.setText("" + item.getExtensionAttributes().getCountRating() + " reviews");
      } else {
        tvCountReview.setVisibility(View.GONE);
      }
      if (item.getExtensionAttributes() != null &&  !TextUtils.isEmpty(item.getExtensionAttributes().getRatingDollar())) {
        tvDollars.setVisibility(View.VISIBLE);
        tvDollars.setText("| " + item.getExtensionAttributes().getRatingDollar());
      } else {
        tvDollars.setVisibility(View.GONE);
      }

    } else {
      llReview.setVisibility(View.GONE);
    }
    if (item.getProduct().getDisplayPrice()!=null && item.getProduct().getDisplayPrice().equals("1")) {
      if (item.getProduct().getPrice() != null) {
        tvPrice.setVisibility(View.VISIBLE);

        double price = Double.parseDouble(item.getProduct().getPrice());
        String priceString = DecimalUtil.roundTowDecimalWithoutBrace(price);

        String finalString = "From " + "S$" + priceString;
        Typeface font1 = Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/Lato-Regular.ttf");
        Typeface font2 = Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/Lato-Semibold.ttf");

        SpannableStringBuilder spanString = new SpannableStringBuilder(finalString);

        spanString.setSpan(new CustomTypefaceSpan("", font1), 0, 4, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        spanString.setSpan(new AbsoluteSizeSpan(12, true), 0, 4, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        spanString.setSpan(new CustomTypefaceSpan("", font2), 5, finalString.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        spanString.setSpan(new AbsoluteSizeSpan(18, true), 5, finalString.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);


        tvPrice.setText(spanString);
      }
    } else {
      tvPrice.setVisibility(View.GONE);
    }
    if(item.getExtensionAttributes() != null) {
      displayReservationsCategory = item.getExtensionAttributes().getDisplayReservationsCategory();
    }

//    if (!ObjectUtil.isNull(item) && !ObjectUtil.isNull(item.getCustomAttributes())) {
//      for (CustomAttributesItem customAttributesItem : item.getCustomAttributes()) {
//        if (customAttributesItem.getAttributeCode().equalsIgnoreCase("reserve_button_title")) {
//          reserveButtonTitle = customAttributesItem.getValue().toString();
//          Log.e("LOG_INFO", "reserveButtonTitle: " + reserveButtonTitle);
//        } else if (customAttributesItem.getAttributeCode().equalsIgnoreCase("reserve_button_link")) {
//          sku = customAttributesItem.getValue().toString();
//          Log.e("LOG_INFO", "sku: " + sku);
//        } else if (customAttributesItem.getAttributeCode().equalsIgnoreCase("hgw_link")) {
//          linkHGW = customAttributesItem.getValue().toString();
//          Log.e("LOG_INFO", "linkHGW: " + linkHGW);
//        } else if (customAttributesItem.getAttributeCode().equalsIgnoreCase("restaurant_IDS")) {
//          restaurantIDS = customAttributesItem.getValue().toString();
//          Log.e("LOG_INFO", "restaurant_IDS: " + restaurantIDS);
//        }
//      }
//    }

    if (displayReservationsCategory != null) {
      reserveButtonTitle = displayReservationsCategory.getTitle();
      sku = displayReservationsCategory.getLink();
      linkHGW = displayReservationsCategory.getHgwLink();
      restaurantIDS = displayReservationsCategory.getRestaurantIDS();
      if (displayReservationsCategory.getEnable()) {
        if (!ObjectUtil.isNull(sku) && !ObjectUtil.isEmptyStr(sku)) {
          tvReserve.setVisibility(View.VISIBLE);
          tvViewDetail.setVisibility(View.VISIBLE);
        } else if (!TextUtils.isEmpty(restaurantIDS)) {
          tvReserve.setVisibility(View.VISIBLE);
          tvViewDetail.setVisibility(View.VISIBLE);
        } else if (!ObjectUtil.isNull(linkHGW) && !ObjectUtil.isEmptyStr(linkHGW)) {
          tvReserve.setVisibility(View.VISIBLE);
          tvViewDetail.setVisibility(View.VISIBLE);
        } else if (isTravel(item)) {
          tvReserve.setVisibility(View.VISIBLE);
          tvViewDetail.setVisibility(View.VISIBLE);
        } else {
          tvReserve.setVisibility(View.VISIBLE);
          tvViewDetail.setVisibility(View.VISIBLE);
        }
      } else if (isTravel(item)) {
        tvReserve.setVisibility(View.VISIBLE);
        tvViewDetail.setVisibility(View.VISIBLE);
      } else {
        tvReserve.setVisibility(View.GONE);
        tvViewDetail.setVisibility(View.VISIBLE);
      }
    } else if (isTravel(item)) {
      tvReserve.setVisibility(View.VISIBLE);
      tvViewDetail.setVisibility(View.VISIBLE);
    }else {
      tvReserve.setVisibility(View.GONE);
      tvViewDetail.setVisibility(View.VISIBLE);
    }

    /*Handle display reserve button*/
    if (!ObjectUtil.isNull(reserveButtonTitle) && !ObjectUtil.isEmptyStr(reserveButtonTitle)) {
      tvReserve.setText(reserveButtonTitle);
    }
  }

  private boolean isTravel(FavouriteItem item) {

    for (String id : item.getProduct().getCategoryIds()) {
      if (Integer.parseInt(id) == Constants.TRAVEL_ID) {
        return true;
      }
    }

    return false;
  }

  private String roundDecimal(String price) {
    if (price != null && !TextUtils.isEmpty(price)) {
      return new DecimalFormat("#.00").format(Double.parseDouble(price));
    } else {
      return "0.00";
    }
  }

  private static void disableView(ViewGroup layout, Chip chip) {
    layout.setEnabled(false);
    for (int i = 0; i < layout.getChildCount(); i++) {
      View child = layout.getChildAt(i);
      if (child instanceof ViewGroup) {
        disableView((ViewGroup) child, chip);
      } else {
        child.setEnabled(false);
      }
    }
  }

  private void loadImageSliders(List<String> sliderImages,
                                ViewPager viewPager, Context context,
                                LinearLayout layoutSliderIndicators,
                                ImageView imgNext, ImageView imgPrevious,
                                FavouriteItem itemsItem) {

    viewPager.removeAllViews();
    viewPager.setOffscreenPageLimit(1);
    viewPager.setAdapter(new ImageAdapter(context, sliderImages));
    viewPager.setVisibility(View.VISIBLE);
    setUpSliderIndicator(sliderImages.size(), layoutSliderIndicators, context);
    imgNext.setVisibility(View.VISIBLE);
    imgPrevious.setVisibility(View.VISIBLE);

    imgNext.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
        if (listener != null) {
          listener.saveImgPosition(getAdapterPosition(), viewPager.getCurrentItem());
        }

      }
    });

    imgPrevious.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        viewPager.setCurrentItem(viewPager.getCurrentItem() - 1, true);
        if (listener != null) {
          listener.saveImgPosition(getAdapterPosition(), viewPager.getCurrentItem());
        }
      }
    });

    viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

      }

      @Override
      public void onPageSelected(int position) {
        setCurrentSliderIndicator(position, layoutSliderIndicators, context);
        if (listener != null) {
          listener.saveImgPosition(getAdapterPosition(), position);
        }
      }

      @Override
      public void onPageScrollStateChanged(int state) {

      }
    });
    viewPager.setCurrentItem(itemsItem.getImgPos());

  }

  private void setCurrentSliderIndicator(int position, LinearLayout layoutSliderIndicators, Context context) {
    int childCount = layoutSliderIndicators.getChildCount();
    for (int i = 0; i < childCount; i++) {
      ImageView imageView = (ImageView) layoutSliderIndicators.getChildAt(i);
      if (i == position) {
        imageView.setImageDrawable(
            ContextCompat.getDrawable(context, R.drawable.background_slider_indicator_active)
        );
      } else {
        imageView.setImageDrawable(
            ContextCompat.getDrawable(context, R.drawable.background_slider_indicator_inactive)
        );
      }
    }
  }

  private void setUpSliderIndicator(int count, LinearLayout layoutSliderIndicators, Context context) {
    layoutSliderIndicators.removeAllViews();
    ImageView[] indicators = new ImageView[count];
    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
    );
    layoutParams.setMargins(8, 0, 8, 0);

    for (int i = 0; i < indicators.length; i++) {
      indicators[i] = new ImageView(context);
      indicators[i].setImageDrawable(ContextCompat.getDrawable(
          context,
          R.drawable.background_slider_indicator_inactive
      ));
      indicators[i].setLayoutParams(layoutParams);
      layoutSliderIndicators.addView(indicators[i]);
    }
    layoutSliderIndicators.setVisibility(View.VISIBLE);
    setCurrentSliderIndicator(0, layoutSliderIndicators, context);
  }

  public boolean isStringInt(String s) {
    try {
      Integer.parseInt(s);
      return true;
    } catch (NumberFormatException ex) {
      return false;
    }
  }

  public void setListener(SaveImageActionListener listener) {
    this.listener = listener;
  }

}
