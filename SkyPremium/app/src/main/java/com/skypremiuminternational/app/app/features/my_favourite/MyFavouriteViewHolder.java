package com.skypremiuminternational.app.app.features.my_favourite;

import static com.skypremiuminternational.app.app.utils.DecimalUtil.roundTwoDecimals;
import static com.skypremiuminternational.app.domain.utils.ProductUtil.isActive;

import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.skypremiuminternational.app.R;
import com.skypremiuminternational.app.app.utils.Constants;
import com.skypremiuminternational.app.app.utils.DateParser;
import com.skypremiuminternational.app.app.utils.DateTimeCountDown;
import com.skypremiuminternational.app.app.utils.RatingUtils;
import com.skypremiuminternational.app.app.utils.ViewRatioUtils;
import com.skypremiuminternational.app.domain.models.favourite.FavouriteItem;
import com.skypremiuminternational.app.domain.models.products.TierItem;
import com.skypremiuminternational.app.domain.utils.ProductUtil;
import com.skypremiuminternational.app.domain.utils.URLUtils;
import com.willy.ratingbar.ScaleRatingBar;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;
import timber.log.Timber;

/**
 * Created by johnsonmaung on 9/19/17.
 */

public class MyFavouriteViewHolder extends RecyclerView.ViewHolder {

  @BindView(R.id.ll_product)
  LinearLayout ll;
  @BindView(R.id.iv_product)
  ImageView iv;
  @BindView(R.id.tvCategory)
  TextView tvCategory;
  @BindView(R.id.tvTitle_product)
  TextView tvProduct;
  @BindView(R.id.tvSubTitle_product)
  TextView tvSubCategory;
  @BindView(R.id.ivFav)
  ImageView ivFav;
  @BindView(R.id.iv_new)
  ImageView ivNewLabel;
  @BindView(R.id.ivTier)
  ImageView ivTier;
  @BindView(R.id.tvActualPrice)
  TextView tvActualPrice;
  @BindView(R.id.tvDiscountPrice)
  TextView tvDiscountPrice;
  @BindView(R.id.tvDiscountPercentage)
  TextView tvDiscountPercentage;
  @BindView(R.id.tv_loyaltyValue)
  TextView tvLoyaltyValue;
  @BindView(R.id.tvExpiryTime)
  TextView tvExpiryTime;
  @BindView(R.id.layout_count_down)
  ViewGroup layoutCountDown;
  @BindView(R.id.tvNoRating)
  TextView tvNoRating;
  @BindView(R.id.rtProductStar)
  ScaleRatingBar rtProductStar;

  //GOLD PRICE
  @BindView(R.id.tvGoldActualPrice)
  TextView tvGoldActualPrice;
  @BindView(R.id.tvGoldDiscountPrice)
  TextView tvGoldDiscountPrice;
  @BindView(R.id.tvGoldDiscountPercent)
  TextView tvGoldDiscountPercentage;

  //PLATINUM PRICE
  @BindView(R.id.tvPlatinumActualPrice)
  TextView tvPlatinumActualPrice;
  @BindView(R.id.tvPlatinumDiscountPrice)
  TextView tvPlatinumDiscountPrice;
  @BindView(R.id.tvPlatinumDiscountPercent)
  TextView tvPlatinumDiscountPercentage;

  @BindView(R.id.tv_platinum_loyalty_value)
  TextView tvPlatinumLoyaltyValue;
  @BindView(R.id.tv_gold_loyalty_value)
  TextView tvGoldLoyaltyValue;


  @BindView(R.id.ly_tier_price)
  LinearLayout lyTierPrice;
  @BindView(R.id.ly_normal_price)
  LinearLayout lyNormalPrice;

  @BindView(R.id.ly_platinum_prices_container)
  LinearLayout lyPlatinumPricesContainer;
  @BindView(R.id.ly_gold_prices_container)
  LinearLayout lyGoldPricesContainer;

  @BindView(R.id.ly_platinum)
  LinearLayout lyPlatinum;
  @BindView(R.id.ly_gold)
  LinearLayout lyGold;

  @BindView(R.id.tv_end_in)
  TextView tvEndIn;

  @BindView(R.id.tvAddToCart)
  TextView tvAddToCart;
  @BindView(R.id.tvBuyNow)
  TextView tvBuyNow;
  @BindView(R.id.tvViewDetail)
  TextView tvViewDetail;




  private FavouriteItem item;
  private Subscription countDownSubscription;

  public MyFavouriteViewHolder(final View itemView) {
    super(itemView);
    ButterKnife.bind(this, itemView);
  }

  public Subscription bind(final FavouriteItem item) {


    if (item.getExtensionAttributes() != null) {
      float rate = item.getExtensionAttributes().summaryRating;
      float iRate = RatingUtils.getRatingRound(rate);
      float fRate = RatingUtils.getRating(rate);
      if (item.getExtensionAttributes().summaryRating > 0) {
        rtProductStar.setRating(fRate);
        rtProductStar.setVisibility(View.VISIBLE);
        tvNoRating.setVisibility(View.GONE);
      } else {
        rtProductStar.setVisibility(View.GONE);
        tvNoRating.setVisibility(View.VISIBLE);
        tvNoRating.setTypeface(null, Typeface.ITALIC);
      }
    }

    ViewRatioUtils.setProduct(ll, iv, itemView.getContext());
    if (item.getProduct().getSpecialFromDate() == null) {
      item.getProduct().setSpecialFromDate("");
    } else if (item.getProduct().getSpecialToDate() == null) {
      item.getProduct().setSpecialToDate("");
    } else if (item.getProduct().getSpecialPrice() == null) {
      item.getProduct().setSpecialPrice("");
    }
    tvLoyaltyValue.setText("");
    tvPlatinumLoyaltyValue.setText("");
    tvDiscountPercentage.setText("");
    this.item = item;
    unSubscribe();
    ivFav.setVisibility(View.VISIBLE);
    tvActualPrice.setPaintFlags(tvActualPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    tvPlatinumActualPrice.setPaintFlags(tvActualPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    tvGoldActualPrice.setPaintFlags(tvActualPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    int pos = getAdapterPosition();
    if (item.getExtensionAttributes() != null) {
      if (isActive(item.getProduct().getNewsFromDate(), item.getProduct().getNewsToDate(), item.getProduct().getCurrentServerTime())) {
        ivNewLabel.setVisibility(View.VISIBLE);
      } else {
        ivNewLabel.setVisibility(View.INVISIBLE);
      }
    }
    if (item.getExtensionAttributes() != null) {
      if (item.getProduct().getDisplayPlatinumIcon() != null && item.getProduct().getDisplayPlatinumIcon().equalsIgnoreCase(Constants.SHOW_PLATINUM_ICON)) {
        ivTier.setVisibility(View.VISIBLE);
      } else {
        ivTier.setVisibility(View.INVISIBLE);
      }
    }


    ivFav.setEnabled(true);


    //====== Logic count down =======

    //logicCountDownLabel(item);
    logicButtonAndLabelCountDown(item);
    //====== Logic count down =======

    //oooooooooooooooooooooooooooooo LOGIC PRICE oooooooooooooooooooooooooooooooooo
    logicPriceWithTierPrice(item);

    //oooooooooooooooooooooooooooooo LOGIC PRICE oooooooooooooooooooooooooooooooooo
    //2020325 - WIKI Toan Tran - add a flag for check state
    boolean flag = false;

    if (ProductUtil.isValid(item.getProduct().getSmallImage())) {
      //2020325 - WIKI Toan Tran - have a image
      flag = true;
      Timber.e(URLUtils.formatImageURL(item.getProduct().getSmallImage().toString()),
          URLUtils.formatImageURL(item.getProduct().getSmallImage().toString()));

      RequestOptions requestOptions = new RequestOptions();
      requestOptions.placeholder(R.drawable.placeholder);
      requestOptions.dontAnimate();
      requestOptions.error(R.drawable.white);
      Glide.with(itemView.getContext())
          .load(URLUtils.formatImageURL(item.getProduct().getSmallImage().toString()))
          .apply(requestOptions)
          .listener(URLUtils.getGlideListener(itemView.getContext(), item.getProduct().getSmallImage().toString(), iv))
          .into(iv);
    }
    if (ProductUtil.isValid(item.getProduct().getLoyaltyValueToEarn())) {
      tvLoyaltyValue.setText(item.getProduct().getMessageSkyDollar());
      tvLoyaltyValue.setAllCaps(false);
    }

    // <<START>>2020325 - WIKI Toan Tran - add a flag for check state
    if (!flag) {
      RequestOptions requestOptions = new RequestOptions();
      requestOptions.placeholder(R.drawable.placeholder);
      requestOptions.dontAnimate();
      requestOptions.error(R.drawable.white);
      Glide.with(itemView.getContext())
          .load(R.drawable.white)
          .apply(requestOptions)
          .into(iv);
    }
    // <<END>>2020325 - WIKI Toan Tran - Set default image

    int favIcon =
        R.drawable.ic_favorite_accent;
    ivFav.setImageResource(favIcon);

    tvProduct.setText(item.getProduct().getName());
    tvSubCategory.setText(item.getCategoryName());
    return countDownSubscription;
  }

  private void logicCountDownLabel(FavouriteItem item) {
    if (!item.getExtensionAttributes()
        .isInStock()) {
      layoutCountDown.setVisibility(View.VISIBLE);
      tvExpiryTime.setVisibility(View.VISIBLE);
      layoutCountDown.setBackgroundColor(
          ContextCompat.getColor(itemView.getContext(), R.color.colorSaleNotStarted));
      tvExpiryTime.setText(itemView.getContext().getString(R.string.sale_not_started));
      tvEndIn.setVisibility(View.GONE);
    } else {
      if (item.getProduct().getDealStatus().equalsIgnoreCase("1") || item.getProduct().isFlashSales()) {
        if (!item.getExtensionAttributes()
            .isInStock()) {
          layoutCountDown.setVisibility(View.VISIBLE);
          tvExpiryTime.setVisibility(View.VISIBLE);
          layoutCountDown.setBackgroundColor(
              ContextCompat.getColor(itemView.getContext(), R.color.colorSaleNotStarted));
          tvExpiryTime.setText(itemView.getContext().getString(R.string.sale_not_started));
          tvEndIn.setVisibility(View.GONE);
        } else {
          if (item.getProduct().isFlashSales() && !item.getProduct().getDealStatus().equalsIgnoreCase("1")) {
            layoutCountDown.setVisibility(View.VISIBLE);
            tvExpiryTime.setText(itemView.getContext().getString(R.string.sale_over));
            layoutCountDown.setBackgroundColor(
                ContextCompat.getColor(itemView.getContext(), R.color.colorSaleEnded));
            tvEndIn.setVisibility(View.GONE);
          } else {
            if (item.getProduct().getDealFromDate() != null && item.getProduct().getDealToDate() != null && item.getProduct().getCurrentServerTime() != null) {
              Resources resources = itemView.getContext().getResources();
              initLayoutCountDown(item, item.getProduct().getDealFromDate(), item.getProduct().getDealToDate(), Constants.PATTERN_CURRENT_SERVER_TIME, resources);
              countDownSubscription = DateTimeCountDown.init(Constants.PATTERN_CURRENT_SERVER_TIME, item.getProduct().getDealFromDate(),
                  item.getProduct().getDealToDate(), item.getProduct().getCurrentServerTime())
                  .subscribe(new DateTimeCountDown.CountDownSubscriber() {
                    @Override
                    public void onNext(DateTimeCountDown.CountDown countDown) {
                      if ((countDown.alreadyPast() || !item.getProduct().getDealStatus().equalsIgnoreCase("1")) && item.getExtensionAttributes().isInStock()) {

                        unSubscribe();
                        countDownSubscription = null;
                        //<<START>> 20200325 - WIKI Toan Tran - fix fav show with sale ended
                        ivFav.setVisibility(View.VISIBLE);
                        ivFav.setEnabled(true);
                        //<<END>> 20200325 - WIKI Toan Tran - fix fav show with sale ended
                        tvExpiryTime.setText(itemView.getContext().getString(R.string.sale_over));
                        layoutCountDown.setBackgroundColor(
                            ContextCompat.getColor(itemView.getContext(), R.color.colorSaleEnded));
                        tvEndIn.setVisibility(View.GONE);
                      } else if (countDown.hasNotReached()) {
                        layoutCountDown.setBackgroundColor(
                            ContextCompat.getColor(itemView.getContext(), R.color.colorSaleNotStarted));
                        tvExpiryTime.setText(itemView.getContext().getString(R.string.sale_not_started));

                        tvEndIn.setVisibility(View.GONE);
                      } else {
                        tvEndIn.setVisibility(View.VISIBLE);
                        layoutCountDown.setBackgroundColor(
                            ContextCompat.getColor(itemView.getContext(), R.color.light_blue));
                        tvExpiryTime.setText(String.format(Locale.getDefault(), "%s %s:%s:%s",
                            resources
                                .getQuantityString(R.plurals.days, countDown.day(), countDown.day()),
                            String.valueOf(countDown.hr()), countDown.min(), countDown.sec()));
                      }

                    }
                  });
            } else {
              layoutCountDown.setVisibility(View.VISIBLE);
              tvExpiryTime.setVisibility(View.VISIBLE);
              layoutCountDown.setBackgroundColor(
                  ContextCompat.getColor(itemView.getContext(), R.color.colorSaleNotStarted));
              tvExpiryTime.setText(itemView.getContext().getString(R.string.sale_not_started));
              tvEndIn.setVisibility(View.GONE);
            }
          }
        }
      } else {
        layoutCountDown.setVisibility(View.INVISIBLE);
      }
    }
    // IF OUT STOCK
    if (!item.getExtensionAttributes()
        .isInStock()) {
      layoutCountDown.setVisibility(View.VISIBLE);
      tvExpiryTime.setVisibility(View.VISIBLE);
      layoutCountDown.setBackgroundColor(
          ContextCompat.getColor(itemView.getContext(), R.color.colorSaleNotStarted));
      tvExpiryTime.setText(itemView.getContext().getString(R.string.sale_not_started));
      tvEndIn.setVisibility(View.GONE);
    }
  }

  private void logicPriceWithDailyDeal(FavouriteItem item) {
    String status = "Fixed";
    if (!ProductUtil.isValid(item.getProduct().getCurrentServerTime())) {
      status = "Invalid";
    } else {
      if (ProductUtil.isValid(item.getProduct().getSpecialFromDate())) {
        if (ProductUtil.isValid(item.getProduct().getSpecialToDate())) {
          if (!ProductUtil.isValid(item.getProduct().getSpecialPrice())) {
            status = "Fixed";
          } else {
            if (ProductUtil.compareDate(Constants.PATTERN_CURRENT_SERVER_TIME, item.getProduct().getSpecialFromDate(), item.getProduct().getCurrentServerTime())
                && !ProductUtil.compareDate(Constants.PATTERN_CURRENT_SERVER_TIME, item.getProduct().getSpecialToDate(), item.getProduct().getCurrentServerTime())) {
              status = "Started";
            } else {
              status = "Ended";
            }
          }
        } else {
          if (!ProductUtil.isValid(item.getProduct().getSpecialPrice())) {
            status = "Disabled";
          } else {
            if (ProductUtil.compareDate(Constants.PATTERN_CURRENT_SERVER_TIME, item.getProduct().getSpecialFromDate(), item.getProduct().getCurrentServerTime())) {
              if (!ProductUtil.isValid(item.getProduct().getSpecialPrice())) {
                status = "Fixed";
              } else {
                status = "Started";
              }
            } else {
              status = "Fixed";
            }
          }
        }
      } else if (!ProductUtil.isValid(item.getProduct().getSpecialToDate()) && (!ProductUtil.isValid(item.getProduct().getSpecialPrice()))) {
        status = "Disabled";
      } else {
        status = "Fixed";
      }
    }

    // IF FLASH SALE AND DAILY DEAL
    if (ProductUtil.isValid(getDiscountValue(item.getProduct().getDealStatus().equalsIgnoreCase("1")))
        && ((!status.equalsIgnoreCase("Disabled") && !status.equalsIgnoreCase("Fixed")) || isCommingsoon())) {
      if (item.getProduct().isFlashSales()) {
        if (ProductUtil.isValid(item.getProduct().getDealValue())) {
          showDiscountPrice(item.getProduct().getPrice(), item.getProduct().getDealValue(), item.getProduct().getDealDiscountType());

        } else {
          if (ProductUtil.isValid(item.getProduct().getSpecialPrice())) {
            showDiscountPrice(item.getProduct().getPrice(), item.getProduct().getSpecialPrice(), item.getProduct().getDealDiscountType());

          } else {
            showOririginalPrice(item.getProduct().getPrice());
          }
        }
      } else {
        if (ProductUtil.isValid(item.getProduct().getSpecialPrice())) {
          showDiscountPrice(item.getProduct().getPrice(), getDiscountValue(item.getProduct().getDealStatus().equalsIgnoreCase("1")), item.getProduct().getDealDiscountType());

        } else {
          showOririginalPrice(item.getProduct().getPrice());
        }
      }
    } else {
      showOririginalPrice(item.getProduct().getPrice());
    }

    if (!item.getProduct().isFlashSales() && !item.getProduct().getDealStatus().equalsIgnoreCase("1")) {
      //IN DATE
      if (isActive(item.getProduct().getSpecialFromDate()
          , item.getProduct().getSpecialToDate(), item.getProduct().getCurrentServerTime())) {
        if (ProductUtil.isValid(item.getProduct().getSpecialPrice()) && ProductUtil.isValid(item.getProduct().getPrice())) {
          tvDiscountPrice.setVisibility(View.VISIBLE);
          tvActualPrice.setVisibility(View.VISIBLE);
          tvDiscountPercentage.setVisibility(View.VISIBLE);
          tvDiscountPrice.setText(
              String.format("S$%s", roundTwoDecimals(Double.parseDouble(item.getProduct().getSpecialPrice()))));
          tvActualPrice.setText(String.format("U.P. " + "S$%s", roundTwoDecimals(Double.parseDouble(item.getProduct().getPrice()))));
          double discountPercentage =
              ((Double.valueOf(item.getProduct().getPrice()) - Double.valueOf(getDiscountValue(item.getProduct().getDealStatus().equalsIgnoreCase("1"))))
                  / Double.valueOf(item.getProduct().getPrice()) * 100);
          tvDiscountPercentage.setText("SAVE " + Math.round(discountPercentage) + "%");

        } else {
          //OUT DATE
          tvActualPrice.setVisibility(View.GONE);
          tvDiscountPercentage.setVisibility(View.GONE);
          tvDiscountPrice.setVisibility(View.VISIBLE);
          tvDiscountPrice.setText(
              String.format("S$%s", roundTwoDecimals(Double.parseDouble(item.getProduct().getPrice()))));
        }
      }
    }


  }

  private void logicButtonAndLabelCountDown(final FavouriteItem item) {
    String buttonType = !TextUtils.isEmpty(item.getProduct().getButtonType()) ? item.getProduct().getButtonType() : "in_stock";
    String timeSaleStatus = item.getProduct().getTimeSalesStatus();


    if (buttonType.equalsIgnoreCase(Constants.STATE_SOLD_OUT)) {
      //COMING SOON
      showGrayLabel(itemView, itemView.getResources().getString(R.string.txt_sold_out));
    } else {
      if (buttonType.equalsIgnoreCase(Constants.STATE_SALE_NOT_START)) {
        //COMING SOON
        showGrayLabel(itemView, itemView.getResources().getString(R.string.sale_not_started));
      } else if (buttonType.equalsIgnoreCase(Constants.STATE_SALE_OVER)) {
        //SALE_OVER
        showGrayLabel(itemView, itemView.getResources().getString(R.string.sale_over));
      } else if (buttonType.equalsIgnoreCase(Constants.STATE_IN_STOCK)) {
        //Blank
        if (!TextUtils.isEmpty(timeSaleStatus)
            && item.getProduct().getTimeSalesStatus().equalsIgnoreCase(Constants.ENABLE_TIME_SALE)) {
          //Show count down
          showCountDownLabel(itemView, item);
        } else {
          //Blank
          hideLabel();
        }
      } else {
        //Blank
        hideLabel();
      }
    }
  }

  void showCountDownLabel(View itemView, FavouriteItem item) {
    Resources resources = itemView.getContext().getResources();
    String fromDate = item.getProduct().getTimeSaleTier().get(0).getFromDate();
    String toDate = item.getProduct().getTimeSaleTier().get(0).getToDate();
    initLayoutCountDown(item, fromDate, toDate, Constants.PATTERN_CURRENT_SERVER_TIME, resources);
    countDownSubscription = DateTimeCountDown.init(Constants.PATTERN_CURRENT_SERVER_TIME, fromDate,
        toDate, item.getProduct().getCurrentServerTime())
        .subscribe(new DateTimeCountDown.CountDownSubscriber() {
          @Override
          public void onNext(DateTimeCountDown.CountDown countDown) {
            if (countDown.alreadyPast()) {

              unSubscribe();
              countDownSubscription = null;
              //<<START>> 20200325 - WIKI Toan Tran - fix fav show with sale ended
              ivFav.setVisibility(View.VISIBLE);
              ivFav.setEnabled(true);
              //<<END>> 20200325 - WIKI Toan Tran - fix fav show with sale ended
              tvExpiryTime.setText(itemView.getContext().getString(R.string.sale_over));
              layoutCountDown.setBackgroundColor(
                  ContextCompat.getColor(itemView.getContext(), R.color.colorSaleEnded));
              tvEndIn.setVisibility(View.GONE);
            } else if (countDown.hasNotReached()) {
              layoutCountDown.setBackgroundColor(
                  ContextCompat.getColor(itemView.getContext(), R.color.colorSaleNotStarted));
              tvExpiryTime.setText(itemView.getContext().getString(R.string.sale_not_started));

              tvEndIn.setVisibility(View.GONE);
            } else {
              tvEndIn.setVisibility(View.VISIBLE);
              layoutCountDown.setBackgroundColor(
                  ContextCompat.getColor(itemView.getContext(), R.color.light_blue));
              tvExpiryTime.setText(String.format(Locale.getDefault(), "%s %s:%s:%s",
                  resources
                      .getQuantityString(R.plurals.days, countDown.day(), countDown.day()),
                  String.valueOf(countDown.hr()), countDown.min(), countDown.sec()));
            }

          }
        });

  }

  void showGrayLabel(View itemView, String content) {
    layoutCountDown.setVisibility(View.VISIBLE);
    tvExpiryTime.setVisibility(View.VISIBLE);
    layoutCountDown.setBackgroundColor(
        ContextCompat.getColor(itemView.getContext(), R.color.colorSaleNotStarted));
    tvExpiryTime.setText(content);
    tvEndIn.setVisibility(View.GONE);
  }

  void hideLabel() {
    layoutCountDown.setVisibility(View.INVISIBLE);
    tvExpiryTime.setVisibility(View.INVISIBLE);
    tvEndIn.setVisibility(View.GONE);
  }

  private void logicPriceWithTierPrice(FavouriteItem item) {
    String typePrice = item.getProduct().getTypePrice();
    if (typePrice != null) {
      switch (typePrice) {
        case Constants.PRICE_ORIGINAL: {
          lyNormalPrice.setVisibility(View.VISIBLE);
          lyTierPrice.setVisibility(View.GONE);


          tvActualPrice.setVisibility(View.GONE);
          tvDiscountPercentage.setVisibility(View.GONE);
          tvDiscountPrice.setVisibility(View.VISIBLE);
          tvDiscountPrice.setText(
              String.format("S$%s", roundTwoDecimals(Double.parseDouble(item.getProduct().getPrice()))));

          break;
        }

        case Constants.PRICE_SPECIAL: {
          lyTierPrice.setVisibility(View.GONE);
          lyNormalPrice.setVisibility(View.VISIBLE);

          tvActualPrice.setVisibility(View.VISIBLE);
          tvDiscountPercentage.setVisibility(View.VISIBLE);
          tvDiscountPrice.setVisibility(View.VISIBLE);

          tvDiscountPrice.setText(
              String.format("S$%s", roundTwoDecimals(Double.parseDouble(item.getProduct().getSpecialPrice()))));
          tvDiscountPercentage.setText("SAVE " + Math.round(ProductUtil.coverDiscountPercent(Double.valueOf(item.getProduct().getPrice())
              , Double.valueOf(item.getProduct().getSpecialPrice()))) + "%");
          tvActualPrice.setText(String.format("U.P. " + "S$%s", roundTwoDecimals(Double.valueOf(item.getProduct().getPrice()))));


          break;
        }
        case Constants.PRICE_TIER: {
          /*tvActualPrice.setVisibility(View.GONE);
          tvDiscountPercentage.setVisibility(View.GONE);
          tvDiscountPrice.setVisibility(View.GONE);
          tvDiscountPrice.setText(
              String.format("S$%s", "TIER_PRICE"));*/
          lyNormalPrice.setVisibility(View.GONE);

          lyTierPrice.setVisibility(View.VISIBLE);
          tvPlatinumLoyaltyValue.setVisibility(View.VISIBLE);
          tvGoldLoyaltyValue.setVisibility(View.GONE);
          if (item.getProduct().getTierPriceValue() != null) {
            for (TierItem tierItem : item.getProduct().getTierPriceValue()) {
              if (tierItem.getGroupCode().toUpperCase().contains("GOLD")) {
                lyGoldPricesContainer.setVisibility(View.VISIBLE);
                //double originalPrice


                tvGoldDiscountPrice.setText(String.format("S$%s", roundTwoDecimals(Double.valueOf(tierItem.getValue()))));
                tvGoldActualPrice.setText(String.format("U.P. " + "S$%s", roundTwoDecimals(Double.valueOf(item.getProduct().getPrice()))));
                tvGoldDiscountPercentage.setText("SAVE " + Math.round(ProductUtil.coverDiscountPercent(Double.valueOf(item.getProduct().getPrice()),
                    Double.valueOf(tierItem.getValue()))) + "%");

              } else if (tierItem.getGroupCode().toUpperCase().contains("PLATINUM")) {
                lyPlatinumPricesContainer.setVisibility(View.VISIBLE);

                tvPlatinumDiscountPrice.setText(String.format("S$%s", roundTwoDecimals(Double.valueOf(tierItem.getValue()))));
                tvPlatinumActualPrice.setText(String.format("U.P. " + "S$%s", roundTwoDecimals(Double.valueOf(item.getProduct().getPrice()))));
                tvPlatinumDiscountPercentage.setText("SAVE " + Math.round(ProductUtil.coverDiscountPercent(Double.valueOf(item.getProduct().getPrice()),
                    Double.valueOf(tierItem.getValue()))) + "%");
              }
            }
          } else {
            lyTierPrice.setVisibility(View.GONE);
            lyNormalPrice.setVisibility(View.VISIBLE);

            tvActualPrice.setVisibility(View.GONE);
            tvDiscountPercentage.setVisibility(View.GONE);
            tvDiscountPrice.setVisibility(View.VISIBLE);
            tvDiscountPrice.setText(
                String.format("S$%s", roundTwoDecimals(Double.parseDouble(item.getProduct().getPrice()))));
          }


          break;
        }
        default: {
          lyTierPrice.setVisibility(View.GONE);

          tvActualPrice.setVisibility(View.GONE);
          tvDiscountPercentage.setVisibility(View.GONE);
          tvDiscountPrice.setVisibility(View.VISIBLE);
          tvDiscountPrice.setText(
              String.format("S$%s", roundTwoDecimals(Double.parseDouble(item.getProduct().getPrice()))));
        }
      }
    } else {
      tvDiscountPrice.setText(
          String.format("S$%s", roundTwoDecimals(Double.parseDouble(item.getProduct().getPrice()))));
    }
  }

  private void initLayoutCountDown(FavouriteItem itemsItem, String fromTime, String toTime, String pattern, Resources resources) {
    Date currentServerTime = null;
    Date toTimeStamp = null;
    Date fromTimeStamp = null;

    String txtHr = "", txtMin = "", txtSec = "";
    int day = 0;
    long diff = 0;
    try {
      toTimeStamp = DateParser.with(Constants.PATTERN_DATE_TIME).parse(toTime);
      fromTimeStamp = DateParser.with(Constants.PATTERN_DATE_TIME).parse(fromTime);
      currentServerTime = DateParser.with(pattern).parse(itemsItem.getProduct().getCurrentServerTime());
    } catch (ParseException e) {
      e.printStackTrace();
    }

    diff = toTimeStamp.getTime() - currentServerTime.getTime();


    final boolean alreadyPast = diff < 0;
    final boolean hasNotReached = currentServerTime.before(fromTimeStamp);

    int favIcon = R.drawable.ic_favorite_accent;
    ivFav.setImageResource(favIcon);
    if ((alreadyPast)) {
      layoutCountDown.setVisibility(View.VISIBLE);
      unSubscribe();
      countDownSubscription = null;
      //<<START>> 20200325 - WIKI Toan Tran - fix fav show with sale ended
      ivFav.setVisibility(View.VISIBLE);
      ivFav.setEnabled(true);
      //<<END>> 20200325 - WIKI Toan Tran - fix fav show with sale ended
      tvExpiryTime.setText(itemView.getContext().getString(R.string.sale_over));
      layoutCountDown.setBackgroundColor(
          ContextCompat.getColor(itemView.getContext(), R.color.colorSaleEnded));
      tvEndIn.setVisibility(View.GONE);

    } else if (hasNotReached) {
      layoutCountDown.setVisibility(View.VISIBLE);
      layoutCountDown.setBackgroundColor(
          ContextCompat.getColor(itemView.getContext(), R.color.colorSaleNotStarted));
      tvExpiryTime.setText(itemView.getContext().getString(R.string.sale_not_started));
      tvEndIn.setVisibility(View.GONE);

    } else {
      //=====
      long seconds = Math.abs(diff) / 1000;

      day = (int) TimeUnit.SECONDS.toDays(seconds);

      int hr = (int) TimeUnit.SECONDS.toHours(seconds) - day * 24;
      txtHr = hr > 9 ? String.valueOf(hr) : "0" + hr;
      int min =
          (int) (TimeUnit.SECONDS.toMinutes(seconds) - (TimeUnit.SECONDS.toHours(seconds)
              * 60));
      txtMin = min > 9 ? String.valueOf(min) : "0" + min;
      int sec =
          (int) (TimeUnit.SECONDS.toSeconds(seconds) - (TimeUnit.SECONDS.toMinutes(seconds)
              * 60));

      txtSec = sec > 9 ? String.valueOf(sec) : "0" + sec;
      //=====
      layoutCountDown.setVisibility(View.VISIBLE);
      tvEndIn.setVisibility(View.VISIBLE);
      layoutCountDown.setBackgroundColor(
          ContextCompat.getColor(itemView.getContext(), R.color.light_blue));
      tvExpiryTime.setText(String.format(Locale.getDefault(), "%s %s:%s:%s",
          resources
              .getQuantityString(R.plurals.days, day, day),
          String.valueOf(txtHr), txtMin, txtSec));

    }
  }

  private String roundDecimal(String price) {
    if (price != null && !TextUtils.isEmpty(price)) {
      return new DecimalFormat("###,###,###,###.00").format(Double.parseDouble(price));
    } else {
      return "0.00";
    }
  }

  private String getDiscountValue(boolean dealStatus) {
    if (dealStatus) {
      return item.getProduct().getDealValue();
    } else {
      return item.getProduct().getSpecialPrice();
    }
  }

  private boolean hasDiscount(String discountType, String discountPercentage) {
    return discountPercentage != null && !discountPercentage.trim()
        .equals("");
  }

  private void unSubscribe() {
    if (countDownSubscription != null && !countDownSubscription.isUnsubscribed()) {
      Timber.e("un subscribe " + item.getProduct().getName() + " at " + getAdapterPosition());
      countDownSubscription.unsubscribe();
    }
  }

  void showDiscountPrice(String price, String discountValue, String discountType) {
    tvActualPrice.setVisibility(View.VISIBLE);
    tvDiscountPercentage.setVisibility(View.VISIBLE);
    tvDiscountPrice.setVisibility(View.VISIBLE);
    double discount = 0;
    double original = 0;

    //calcular price
    if (discountType.equals(Constants.DISCOUNT_TYPE_PERCENT)) {

      original = Double.parseDouble(price.trim());

      try {
        discount = original - (((Double.valueOf(price.trim()) / 100) * (100 - Double.valueOf(discountValue.trim()))));


        tvDiscountPercentage.setText("SAVE " + Math.round(100 - Double.parseDouble(discountValue)) + "%");
      } catch (NullPointerException ex) {
        tvDiscountPercentage.setVisibility(View.INVISIBLE);
      }

    } else {
      try {
        double discountPercentage =
            ((Double.valueOf(price.trim()) - Double.valueOf(discountValue.trim()))
                / Double.valueOf(price.trim()) * 100);
        tvDiscountPercentage.setText("SAVE " + Math.round(discountPercentage) + "%");
      } catch (NullPointerException ex) {
        tvDiscountPercentage.setVisibility(View.INVISIBLE);
      }
      discount = Double.parseDouble(discountValue.trim());
      original = Double.parseDouble(price.trim());
    }


    tvDiscountPrice.setText(String.format("S$%s", roundTwoDecimals(discount)));
    tvActualPrice.setText(String.format("U.P. " + "S$%s", roundTwoDecimals(original)));
  }


  void showOririginalPrice(String price) {
    tvActualPrice.setVisibility(View.GONE);
    tvDiscountPercentage.setVisibility(View.GONE);
    tvDiscountPrice.setVisibility(View.VISIBLE);
    tvDiscountPrice.setText(String.format("S$%s", roundTwoDecimals(Double.parseDouble(price))));
  }

  boolean isCommingsoon() {
    return tvExpiryTime.getText().toString().equalsIgnoreCase(itemView.getContext().getString(R.string.sale_not_started));
  }
}
