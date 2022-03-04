package com.skypremiuminternational.app.app.features.profile.my_reservation.detail_toptable_reservation;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.widget.NestedScrollView;

import com.skypremiuminternational.app.R;
import com.skypremiuminternational.app.app.features.profile.my_reservation.MyResevationsActivity;
import com.skypremiuminternational.app.app.internal.mvp.BaseActivity;
import com.skypremiuminternational.app.app.utils.Constants;
import com.skypremiuminternational.app.app.utils.CustomTypefaceSpan;
import com.skypremiuminternational.app.app.utils.ImageDataUtils;
import com.skypremiuminternational.app.app.utils.ImageUtils;
import com.skypremiuminternational.app.domain.models.comment_rating.ReviewDetailResponse;
import com.skypremiuminternational.app.domain.models.reservation.ReserveHistoryItem;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;

public class DetailsReservationTopTableActivity extends BaseActivity<DetailsReservationTopTablePresenter>
    implements DetailsReservationTopTableView<DetailsReservationTopTablePresenter>{


  public static final String WORKING_DAYS = "7";

  @BindView(R.id.tvTitle_toolbar)
  TextView tvTitleToolbar;
  @BindView(R.id.tv_num)
  TextView tvNum;
  @BindView(R.id.tv_restaurant_name)
  TextView tvRestaurantName;
  @BindView(R.id.tv_booking_date)
  TextView tvBookingDate;
  @BindView(R.id.img_top_table)
  ImageView imgTopTable;
  @BindView(R.id.tv_status_label)
  TextView tvStatusLabel;
  @BindView(R.id.tv_status)
  TextView tvStatus;
  @BindView(R.id.tv_reserve_date)
  TextView tv_reserveDate;
  @BindView(R.id.tv_reserve_time)
  TextView tv_reserveTime;
  @BindView(R.id.tv_reservation_menu_type)
  TextView tvReservationMenuType;
  @BindView(R.id.tv_grand_total)
  TextView tvGrandTotal;
  @BindView(R.id.tv_address)
  TextView tvAddress;
  @BindView(R.id.tv_open_hour)
  TextView tvOpenHour;
  @BindView(R.id.tv_website)
  TextView tvWebsite;
  @BindView(R.id.tv_sky_dollar_earn)
  TextView tvSkyDollarEarn;
  @BindView(R.id.tv_note_sky_dollar_redemed)
  TextView tvNoteSkyDollarRedemed;
  @BindView(R.id.tv_cancel_request)
  TextView tvCancelRequest;
  @BindView(R.id.label_sky_dollar_earn)
  TextView labelSkyDollarEarn;
  @BindView(R.id.label_status_remark)
  TextView labelStatusRemark;
  @BindView(R.id.tv_status_remark)
  TextView tvStatusRemark;
  @BindView(R.id.scrollview)
  NestedScrollView scrollview;
  @BindView(R.id.label_grand_total)
  TextView label_grand_total;




  ProgressDialog progressDialog;
  ReserveHistoryItem reserveDetailItem;
  String id;

  public static void startMe(Context context, String id) {
    Intent intent = new Intent(context, DetailsReservationTopTableActivity.class);
    intent.putExtra("id",id);
    context.startActivity(intent);
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    AndroidInjection.inject(this);
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail_reservation_toptable);
    ButterKnife.bind(this);

    progressDialog = new ProgressDialog(this);
    progressDialog.setCancelable(false);


    id = getIntent().getStringExtra("id");


    presenter.getBookingDetail(id);

  }

  @Inject
  @Override
  public void injectPresenter(DetailsReservationTopTablePresenter presenter) {
    super.injectPresenter(presenter);
  }

  @Override
  public void showLoading() {
    if(progressDialog !=null&& !progressDialog.isShowing()){
      progressDialog.setMessage(getResources().getString(R.string.loading));
      progressDialog.show();
    }
  }

  @Override
  public void hideLoading() {
    if(progressDialog !=null&& progressDialog.isShowing()){
      progressDialog.dismiss();
    }
  }

  void setTileToolbar(String titleToolbar){
    tvTitleToolbar.setText(titleToolbar);
  }

  void setToolbarBookingId(String bookingId){
    tvNum.setText(bookingId);

  }

  @Override
  public void renderError(String msg) {
    new AlertDialog.Builder(this).setMessage(msg).show();
  }

  @Override
  public void renderBookingDetail(ReserveHistoryItem reserveHistoryItem) {

    reserveDetailItem  = reserveHistoryItem;
    setUpContent();

  }


  @OnClick(R.id.ivNavigation_toolbar)
  public void onClickToolbar(){
    finish();
  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();
    finish();
  }


  private void setUpContent() {







    if (reserveDetailItem.getReservationStatus().equals(Constants.RESERVATION_STATUS_PENDING)){


      setTileToolbar(getResources().getString(R.string.request_number));
      setToolbarBookingId(reserveDetailItem.getReservationId());
      tvBookingDate.setText(res(R.string.request_made_on) + " " + convertDatetime1(reserveDetailItem.getReservationOnDate()));
      ImageUtils.grayScaleRev(imgTopTable,reserveDetailItem.getExtensionData().get(0).getImage_url());
      labelStatusRemark.setVisibility(View.GONE);
      tvStatusRemark.setVisibility(View.GONE);

      LinearLayout.LayoutParams params= new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
      params.setMargins(DptoPxConvertion(30), DptoPxConvertion(5), DptoPxConvertion(30), DptoPxConvertion(147));//pass int values for left,top,right,bottom
      tvWebsite.setLayoutParams(params);
    }
    if (reserveDetailItem.getReservationStatus().equals(Constants.RESERVATION_STATUS_CONFIRMED)){
      setTileToolbar(getResources().getString(R.string.reservation_number));
      setToolbarBookingId(reserveDetailItem.getReservationId());
      tvBookingDate.setText(res(R.string.reservation_made_on) + " " + convertDatetime1(reserveDetailItem.getReservationOnDate()));
      labelSkyDollarEarn.setVisibility(View.VISIBLE);
      tvSkyDollarEarn.setVisibility(View.VISIBLE);

      tvSkyDollarEarn.setText(TextUtils.isEmpty(reserveDetailItem.getReservationSkyDollarEarn()) ? "-" :  reserveDetailItem.getReservationSkyDollarEarn());
      if(TextUtils.isEmpty(reserveDetailItem.getReservationSkyDollarEarn())){
        tvNoteSkyDollarRedemed.setVisibility(View.GONE);
      }else {
        tvNoteSkyDollarRedemed.setVisibility(View.VISIBLE);

        LinearLayout.LayoutParams params= new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(DptoPxConvertion(30), DptoPxConvertion(5), DptoPxConvertion(30), DptoPxConvertion(106));//pass int values for left,top,right,bottom
        tvNoteSkyDollarRedemed.setLayoutParams(params);
        tvNoteSkyDollarRedemed.setText(res(R.string.note_sky_dollar_redemed_1) +
            " "+(TextUtils.isEmpty(reserveDetailItem.getExtensionData().get(0).getTopTable().getLoyaltyToptable()) ? WORKING_DAYS : reserveDetailItem.getExtensionData().get(0).getTopTable().getLoyaltyToptable()) +
            " "+res(R.string.note_sky_dollar_redemed_2));
      }

      ImageUtils.grayScaleRev(imgTopTable,reserveDetailItem.getExtensionData().get(0).getImage_url());
      labelStatusRemark.setVisibility(View.GONE);
      tvStatusRemark.setVisibility(View.GONE);
    }
    if (reserveDetailItem.getReservationStatus().equals(Constants.RESERVATION_STATUS_COMPLETED)) {
      setTileToolbar(getResources().getString(R.string.reservation_number));
      setToolbarBookingId(reserveDetailItem.getReservationId());
      tvBookingDate.setText(res(R.string.reservation_made_on) + " " + convertDatetime1(reserveDetailItem.getReservationOnDate()));
      labelSkyDollarEarn.setVisibility(View.VISIBLE);
      tvSkyDollarEarn.setVisibility(View.VISIBLE);
      LinearLayout.LayoutParams params= new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
      params.setMargins(DptoPxConvertion(30), DptoPxConvertion(5), DptoPxConvertion(30), DptoPxConvertion(140));//pass int values for left,top,right,bottom
      tvSkyDollarEarn.setLayoutParams(params);

      tvSkyDollarEarn.setText(TextUtils.isEmpty(reserveDetailItem.getReservationSkyDollarEarn()) ? "-" :  reserveDetailItem.getReservationSkyDollarEarn());
//      if(TextUtils.isEmpty(reserveDetailItem.getReservationSkyDollarEarn())){
//        tvNoteSkyDollarRedemed.setVisibility(View.GONE);
//      }else {
//        tvNoteSkyDollarRedemed.setVisibility(View.VISIBLE);
//        tvNoteSkyDollarRedemed.setText(res(R.string.note_sky_dollar_redemed_1) +
//            " "+(TextUtils.isEmpty(reserveDetailItem.getExtensionData().get(0).getTopTable().getLoyaltyToptable()) ? WORKING_DAYS : reserveDetailItem.getExtensionData().get(0).getTopTable().getLoyaltyToptable()) +
//            " "+res(R.string.note_sky_dollar_redemed_2));
//      }

      ImageUtils.grayScaleRev(imgTopTable,reserveDetailItem.getExtensionData().get(0).getImage_url());
      labelStatusRemark.setVisibility(View.GONE);
      tvStatusRemark.setVisibility(View.GONE);
    }


    if (reserveDetailItem.getReservationStatus().equals(Constants.RESERVATION_STATUS_RETRACTED)) {
      setTileToolbar(getResources().getString(R.string.request_number));
      setToolbarBookingId(reserveDetailItem.getReservationId());
      tvBookingDate.setText(res(R.string.request_made_on) + " " + convertDatetime1(reserveDetailItem.getReservationOnDate()));
      labelStatusRemark.setVisibility(View.VISIBLE);
      tvStatusRemark.setVisibility(View.VISIBLE);


      ImageUtils.grayScale(imgTopTable,reserveDetailItem.getExtensionData().get(0).getImage_url());

      if (!TextUtils.isEmpty(reserveDetailItem.getReservation_status_remarks()) && reserveDetailItem.getReservation_status_remarks() != null){
        tvStatusRemark.setText(reserveDetailItem.getReservation_status_remarks());
      }

      LinearLayout.LayoutParams params= new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
      params.setMargins(DptoPxConvertion(30), DptoPxConvertion(5), DptoPxConvertion(30), DptoPxConvertion(140));//pass int values for left,top,right,bottom
      tvStatusRemark.setLayoutParams(params);

    }

    if (reserveDetailItem.getReservationStatus().equals(Constants.RESERVATION_STATUS_CANCELLED)) {
      setTileToolbar(getResources().getString(R.string.reservation_number));
      setToolbarBookingId(reserveDetailItem.getReservationId());
      tvBookingDate.setText(res(R.string.reservation_made_on) + " " + convertDatetime1(reserveDetailItem.getReservationOnDate()));
      labelStatusRemark.setVisibility(View.VISIBLE);
      tvStatusRemark.setVisibility(View.VISIBLE);


      ImageUtils.grayScale(imgTopTable,reserveDetailItem.getExtensionData().get(0).getImage_url());

      if (!TextUtils.isEmpty(reserveDetailItem.getReservation_status_remarks()) && reserveDetailItem.getReservation_status_remarks() != null){
        tvStatusRemark.setText(reserveDetailItem.getReservation_status_remarks());
      }

      LinearLayout.LayoutParams params= new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
      params.setMargins(DptoPxConvertion(30), DptoPxConvertion(5), DptoPxConvertion(30), DptoPxConvertion(140));//pass int values for left,top,right,bottom
      tvStatusRemark.setLayoutParams(params);


    }

    if (reserveDetailItem.getReservationStatus().equals(Constants.RESERVATION_STATUS_UNSUCCESSFUL)) {
      setTileToolbar(getResources().getString(R.string.request_number));
      setToolbarBookingId(reserveDetailItem.getReservationId());
      tvBookingDate.setText(res(R.string.request_made_on) + " " + convertDatetime1(reserveDetailItem.getReservationOnDate()));

      ImageUtils.grayScale(imgTopTable,reserveDetailItem.getExtensionData().get(0).getImage_url());
      LinearLayout.LayoutParams params= new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
      params.setMargins(DptoPxConvertion(30), DptoPxConvertion(5), DptoPxConvertion(30), DptoPxConvertion(232));//pass int values for left,top,right,bottom
      tvWebsite.setLayoutParams(params);

    }



    tvRestaurantName.setText(reserveDetailItem.getRestaurantName());

    tvAddress.setText(reserveDetailItem.getLocation());

    tvReservationMenuType.setText(reserveDetailItem.getMenu_type());
    tvStatus.setText(toStatusTopTableStr(reserveDetailItem.getReservationStatus()));
    tv_reserveDate.setText(convertReservationDate(reserveDetailItem.getReservationDate()));
    if (reserveDetailItem.getExtensionData().get(0).getTopTable().getReservation_time().equals("-1")){
      tv_reserveTime.setText("-");
    }
    else {
      tv_reserveTime.setText(convertHourToAmPm(reserveDetailItem.getExtensionData().get(0).getTopTable().getReservation_time()));
    }

//    tvOpenHour.setText(reserveDetailItem.getExtensionData().get(0).getTopTable().getOpeningHour());
    if (reserveDetailItem.getExtensionData().get(0).getTopTable().getWebsite() != null && !TextUtils.isEmpty(reserveDetailItem.getExtensionData().get(0).getTopTable().getWebsite())){
      tvWebsite.setText(reserveDetailItem.getExtensionData().get(0).getTopTable().getWebsite());
      tvWebsite.setTextColor(getResources().getColor(R.color.colorAddToCart));
      tvWebsite.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);

    }
    else {
      tvWebsite.setText("-");
      tvWebsite.setTextColor(getResources().getColor(R.color.black));
    }



    String productPrice = null;
    productPrice = reserveDetailItem.getExtensionData().get(0).getTopTable().getPrice();

    if (productPrice != null
        && productPrice.equals("0")){
      label_grand_total.setVisibility(View.GONE);
      tvGrandTotal.setVisibility(View.GONE);
    }
    else {
      label_grand_total.setVisibility(View.VISIBLE);
      tvGrandTotal.setVisibility(View.VISIBLE);
      String grandTotal =  (TextUtils.isEmpty(reserveDetailItem.getExtensionData().get(0).getTopTable().getGrandTotal())||reserveDetailItem.getExtensionData().get(0).getTopTable().getGrandTotal().trim() .equalsIgnoreCase("$0.00")) ? "-" :  reserveDetailItem.getExtensionData().get(0).getTopTable().getGrandTotal();
      tvGrandTotal.setText(grandTotal);
    }






  }

  @OnClick(R.id.tv_website)
  void onClickWebsite(){
    if (reserveDetailItem.getExtensionData().get(0).getTopTable().getWebsite() != null && !TextUtils.isEmpty(reserveDetailItem.getExtensionData().get(0).getTopTable().getWebsite())){
//      Intent sendIntent = new Intent();
//      sendIntent.setAction(Intent.ACTION_VIEW);
//      sendIntent.setData(Uri.parse(reserveDetailItem.getExtensionData().get(0).getTopTable().getWebsite()));
//      startActivity(sendIntent);
      openBrowser(this,reserveDetailItem.getExtensionData().get(0).getTopTable().getWebsite());
    }
  }

  public static void openBrowser(final Context context, String url) {

    if (!url.startsWith("http://") && !url.startsWith("https://")) {
      url = "http://" + url;
    }

    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
    context.startActivity(intent);
  }

  private int DptoPxConvertion(int dpValue)
  {
    return (int)((dpValue * getBaseContext().getResources().getDisplayMetrics().density) + 0.5);
  }





  public static String convertHourToAmPm(String Time) {
    DateFormat f1 = new SimpleDateFormat("HH:mm"); //23:00
    Date d = null;
    try {
      d = f1.parse(Time);
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return Time;

    }
    DateFormat f2 = new SimpleDateFormat("h:mm a");

    String x = f2.format(d); // "11:00 PM"

    return x;
  }

  public static String convertDatetime(String Time){
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", java.util.Locale.ENGLISH);
    Date myDate = null;
    try {
      myDate = sdf.parse(Time);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    sdf.applyPattern("EEEE, d MMMM yyyy");
    String sMyDate = sdf.format(myDate);

    return sMyDate;
  }

  public static String convertDatetime1(String Time){
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", java.util.Locale.ENGLISH);
    Date myDate = null;
    try {
      myDate = sdf.parse(Time);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    sdf.applyPattern("EEE d MMM yyyy");
    String sMyDate = sdf.format(myDate);

    return sMyDate;
  }

  public static String convertReservationDate(String Time){
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", java.util.Locale.ENGLISH);
    Date myDate = null;
    try {
      myDate = sdf.parse(Time);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    sdf.applyPattern("d MMMM yyyy");
    String sMyDate = sdf.format(myDate);

    return sMyDate;
  }


  String toStatusTopTableStr(String status){
    if(status.equalsIgnoreCase(Constants.RESERVATION_STATUS_CONFIRMED)){
      return "Reservation Confirmed";
    }else if(status.equalsIgnoreCase(Constants.RESERVATION_STATUS_COMPLETED)){
      return "Reservation Completed";
    }else if(status.equalsIgnoreCase(Constants.RESERVATION_STATUS_RETRACTED)){
      return "Request Retracted";
    }else if(status.equalsIgnoreCase(Constants.RESERVATION_STATUS_CANCELLED)){
      return "Reservation Cancelled";
    }else if(status.equalsIgnoreCase(Constants.RESERVATION_STATUS_UNSUCCESSFUL)){
      return "Request Unsuccessful";
    }else if(status.equalsIgnoreCase(Constants.RESERVATION_STATUS_PENDING)){
      return "Request Pending";
    }else {
      return "-";
    }
  }



  String res(int res){
    return getResources().getString(res);
  }
}
