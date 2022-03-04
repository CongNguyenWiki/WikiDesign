package com.skypremiuminternational.app.app.features.profile.my_reservation.detail_cheftable_reservation;

import static com.skypremiuminternational.app.app.utils.DateFormatter.convertDatetime;
import static com.skypremiuminternational.app.app.utils.DateFormatter.convertHourToAmPm1;
import static com.skypremiuminternational.app.app.utils.DateFormatter.convertReservationDate;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.skypremiuminternational.app.R;
import com.skypremiuminternational.app.app.internal.mvp.BaseActivity;
import com.skypremiuminternational.app.app.utils.Constants;

import com.skypremiuminternational.app.app.utils.ImageUtils;
import com.skypremiuminternational.app.domain.models.reservation.ReserveDetailChefTableItem;


import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;

public class DetailsReservationChefTableActivity extends BaseActivity<DetailsReservationChefTablePresenter>
    implements DetailsReservationChefTableView<DetailsReservationChefTablePresenter> {


  public static final String WORKING_DAYS = "3";

  @BindView(R.id.tvTitle_toolbar)
  TextView tvTitleToolbar;
  @BindView(R.id.tv_num)
  TextView tvNum;
  @BindView(R.id.tv_chef_name)
  TextView tvChefName;
  @BindView(R.id.tv_booking_made_on_time)
  TextView tv_bookingMadeOnTime;
  @BindView(R.id.img_chef_table)
  ImageView imgChefTable;
  @BindView(R.id.tv_status_label)
  TextView tvStatusLabel;
  @BindView(R.id.tv_status)
  TextView tvStatus;
  @BindView(R.id.tv_reserve_date)
  TextView tv_reserveDate;
  @BindView(R.id.tv_reserve_time)
  TextView tv_reserveTime;
  @BindView(R.id.tv_reservation_total_number_of_pax)
  TextView tvReservationNumberOfPax;
  @BindView(R.id.tv_reservation_number_host)
  TextView tv_reservationNumberHost;
  @BindView(R.id.tv_reservation_number_guest)
  TextView tv_reservationNumberGuest;
  @BindView(R.id.tv_special_occasion)
  TextView tvSpecialOccasion;
  @BindView(R.id.tv_cuisine_menu)
  TextView tvCuisineMenu;
  @BindView(R.id.tv_sky_dollar_earn)
  TextView tvSkyDollarEarn;
  @BindView(R.id.tv_note_sky_dollar_redemed)
  TextView tvNoteSkyDollarRedemed;
  @BindView(R.id.tv_cancel_request)
  TextView tvCancelRequest;
  @BindView(R.id.label_sky_dollar_earn)
  TextView labelSkyDollarEarn;
  @BindView(R.id.tv_special_instruction)
  TextView tvSpecialInstruction;
  @BindView(R.id.ln_date_time)
  LinearLayout lnDateTime;
  @BindView(R.id.ln_date_time_option1)
  LinearLayout lnDateTimeOption1;
  @BindView(R.id.ln_date_time_option2)
  LinearLayout lnDateTimeOption2;
  @BindView(R.id.ln_date_time_option3)
  LinearLayout lnDateTimeOption3;
  @BindView(R.id.tv_reserve_date_option1)
  TextView tvReserveDateOption1;
  @BindView(R.id.tv_reserve_date_option2)
  TextView tvReserveDateOption2;
  @BindView(R.id.tv_reserve_date_option3)
  TextView tvReserveDateOption3;
  @BindView(R.id.tv_reserve_time_option1)
  TextView tvReserveTimeOption1;
  @BindView(R.id.tv_reserve_time_option2)
  TextView tvReserveTimeOption2;
  @BindView(R.id.tv_reserve_time_option3)
  TextView tvReserveTimeOption3;
  @BindView(R.id.tv_label_cancel_remark)
  TextView tvLabelCancelRemark;
  @BindView(R.id.tv_cancel_remark)
  TextView tvCancelRemark;



  ProgressDialog progressDialog;
  ReserveDetailChefTableItem reserveDetailItem;
  String id;
  int totalGuest;

  public static void startMe(Context context, String id) {
    Intent intent = new Intent(context, DetailsReservationChefTableActivity.class);
    intent.putExtra("id",id);
    context.startActivity(intent);
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    AndroidInjection.inject(this);
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail_reservation_cheftable);
    ButterKnife.bind(this);

    progressDialog = new ProgressDialog(this);
    progressDialog.setCancelable(false);


    id = getIntent().getStringExtra("id");



    presenter.getBookingDetail(id);

  }

  @Inject
  @Override
  public void injectPresenter(DetailsReservationChefTablePresenter presenter) {
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
  public void renderBookingDetail(ReserveDetailChefTableItem reserveHistoryItem) {

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




    tvStatus.setText(toStatusChefTableStr(reserveDetailItem.getReservationStatus()));



    if (reserveDetailItem.getNumberGuests() != null && reserveDetailItem.getNumberHosts() != null){
      totalGuest = Integer.parseInt(reserveDetailItem.getNumberGuests()) + Integer.parseInt(reserveDetailItem.getNumberHosts());
    }
    tvReservationNumberOfPax.setText(new StringBuilder(String.valueOf(totalGuest)).append(" Guests"));
    tv_reservationNumberHost.setText(new StringBuilder(reserveDetailItem.getNumberHosts()).append(" Hosts"));
    tv_reservationNumberGuest.setText(new StringBuilder(reserveDetailItem.getNumberGuests()).append(" Guests"));
    tvCuisineMenu.setText(reserveDetailItem.getReservationCuisineData().getName() + " - " + "S$" + reserveDetailItem.getReservationCuisineData().getPrice());
    if (!TextUtils.isEmpty(reserveDetailItem.getSpecialOccasion()) && reserveDetailItem.getSpecialOccasion() != null){
      tvSpecialOccasion.setText(reserveDetailItem.getSpecialOccasion());
    }
    else {
      tvSpecialOccasion.setText("-");
    }
    if (!TextUtils.isEmpty(reserveDetailItem.getRequestRemarks()) && reserveDetailItem.getRequestRemarks() != null){
      tvSpecialInstruction.setText(reserveDetailItem.getRequestRemarks());
    }
    else {
      tvSpecialInstruction.setText("-");
    }




    if (reserveDetailItem.getReservationStatus().equals(Constants.RESERVATION_STATUS_PROCESSING)){
      tvCancelRequest.setVisibility(View.GONE);
      lnDateTime.setVisibility(View.GONE);
      lnDateTimeOption1.setVisibility(View.VISIBLE);
      lnDateTimeOption2.setVisibility(View.VISIBLE);
      lnDateTimeOption3.setVisibility(View.VISIBLE);
      tvLabelCancelRemark.setVisibility(View.GONE);
      tvCancelRemark.setVisibility(View.GONE);

      setTileToolbar(getResources().getString(R.string.request_number));
      setToolbarBookingId(reserveDetailItem.getReservationId());
      tvChefName.setText("Pending Chef's Table");
      tv_bookingMadeOnTime.setText(res(R.string.request_made_on) + " " + convertDatetime(reserveDetailItem.getReservation_time_submit()));

      if (!TextUtils.isEmpty(reserveDetailItem.getReservationDatetimeOptions().getReservationDateOptionsFirst())){
        tvReserveDateOption1.setText(convertReservationDate(reserveDetailItem.getReservationDatetimeOptions().getReservationDateOptionsFirst()));
      }
      else {
        tvReserveDateOption1.setText("-");
      }
      if (!TextUtils.isEmpty(reserveDetailItem.getReservationDatetimeOptions().getReservationDateOptionsSecond())){
        tvReserveDateOption2.setText(convertReservationDate(reserveDetailItem.getReservationDatetimeOptions().getReservationDateOptionsSecond()));
      }
      else {
        tvReserveDateOption2.setText("-");
      }
      if (!TextUtils.isEmpty(reserveDetailItem.getReservationDatetimeOptions().getReservationDateOptionsThird())){
        tvReserveDateOption3.setText(convertReservationDate(reserveDetailItem.getReservationDatetimeOptions().getReservationDateOptionsThird()));
      }
      else {
        tvReserveDateOption3.setText("-");
      }

      if (!TextUtils.isEmpty(reserveDetailItem.getReservationDatetimeOptions().getReservationTimeOptionsFirst())){
        tvReserveTimeOption1.setText(convertHourToAmPm1(reserveDetailItem.getReservationDatetimeOptions().getReservationTimeOptionsFirst()));
      }
      else {
        tvReserveTimeOption1.setText("-");
      }
      if (!TextUtils.isEmpty(reserveDetailItem.getReservationDatetimeOptions().getReservationTimeOptionsSecond())){
        tvReserveTimeOption2.setText(convertHourToAmPm1(reserveDetailItem.getReservationDatetimeOptions().getReservationTimeOptionsSecond()));
      }
      else {
        tvReserveTimeOption2.setText("-");
      }
      if (!TextUtils.isEmpty(reserveDetailItem.getReservationDatetimeOptions().getReservationTimeOptionsThird())){
        tvReserveTimeOption3.setText(convertHourToAmPm1(reserveDetailItem.getReservationDatetimeOptions().getReservationTimeOptionsThird()));
      }
      else {
        tvReserveTimeOption3.setText("-");
      }

      LinearLayout.LayoutParams params= new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
      params.setMargins(DptoPxConvertion(30), DptoPxConvertion(5), DptoPxConvertion(30), DptoPxConvertion(130));//pass int values for left,top,right,bottom
      tvSpecialInstruction.setLayoutParams(params);



      ImageUtils.grayScaleRev(imgChefTable,reserveDetailItem.getExtensionData().get(0).getChefTable().getReservation_image_detail());
    }
    if (reserveDetailItem.getReservationStatus().equals(Constants.RESERVATION_STATUS_CONFIRMED)){
      labelSkyDollarEarn.setVisibility(View.VISIBLE);
      tvSkyDollarEarn.setVisibility(View.VISIBLE);
      tvCancelRequest.setVisibility(View.GONE);
      lnDateTime.setVisibility(View.VISIBLE);
      lnDateTimeOption1.setVisibility(View.GONE);
      lnDateTimeOption2.setVisibility(View.GONE);
      lnDateTimeOption3.setVisibility(View.GONE);
      tvLabelCancelRemark.setVisibility(View.GONE);
      tvCancelRemark.setVisibility(View.GONE);


      setTileToolbar(getResources().getString(R.string.reservation_number));
      setToolbarBookingId(reserveDetailItem.getReservationId());
      tvChefName.setText(new StringBuilder("Chef's Table with ").append(reserveDetailItem.getChefName()));
      tv_bookingMadeOnTime.setText(res(R.string.reservation_made_on) + " " + convertDatetime(reserveDetailItem.getReservation_time_submit()));


      tv_reserveDate.setText(convertReservationDate(reserveDetailItem.getReservationConfirmDate()));
       tv_reserveTime.setText(convertHourToAmPm1(reserveDetailItem.getReservationConfirmTime()));


      ImageUtils.grayScaleRev(imgChefTable,reserveDetailItem.getExtensionData().get(0).getChefTable().getReservation_image_detail());

      tvSkyDollarEarn.setText(TextUtils.isEmpty(reserveDetailItem.getReservationSkyDollarEarn()) ? "-" :  reserveDetailItem.getReservationSkyDollarEarn());
      if(TextUtils.isEmpty(reserveDetailItem.getReservationSkyDollarEarn())){
        tvNoteSkyDollarRedemed.setVisibility(View.GONE);
      }else {
        tvNoteSkyDollarRedemed.setVisibility(View.VISIBLE);
        tvNoteSkyDollarRedemed.setText(res(R.string.note_sky_dollar_redemed_1) +
            " "+(TextUtils.isEmpty(reserveDetailItem.getExtensionData().get(0).getChefTable().getLoyaltyCheftable()) ? WORKING_DAYS : reserveDetailItem.getExtensionData().get(0).getChefTable().getLoyaltyCheftable()) +
            " "+res(R.string.note_sky_dollar_redemed_2));

        LinearLayout.LayoutParams params= new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(DptoPxConvertion(30), DptoPxConvertion(5), DptoPxConvertion(30), DptoPxConvertion(105));//pass int values for left,top,right,bottom
        tvNoteSkyDollarRedemed.setLayoutParams(params);

      }
    }
    if (reserveDetailItem.getReservationStatus().equals(Constants.RESERVATION_STATUS_COMPLETED)) {
      labelSkyDollarEarn.setVisibility(View.VISIBLE);
      tvSkyDollarEarn.setVisibility(View.VISIBLE);
      tvCancelRequest.setVisibility(View.GONE);
      lnDateTime.setVisibility(View.VISIBLE);
      lnDateTimeOption1.setVisibility(View.GONE);
      lnDateTimeOption2.setVisibility(View.GONE);
      lnDateTimeOption3.setVisibility(View.GONE);
      tvLabelCancelRemark.setVisibility(View.GONE);
      tvCancelRemark.setVisibility(View.GONE);


      setTileToolbar(getResources().getString(R.string.reservation_number));
      setToolbarBookingId(reserveDetailItem.getReservationId());
      tvChefName.setText(new StringBuilder("Chef's Table with ").append(reserveDetailItem.getChefName()));
      tv_bookingMadeOnTime.setText(res(R.string.reservation_made_on) + " " + convertDatetime(reserveDetailItem.getReservation_time_submit()));


      tv_reserveDate.setText(convertReservationDate(reserveDetailItem.getReservationConfirmDate()));
      tv_reserveTime.setText(convertHourToAmPm1(reserveDetailItem.getReservationConfirmTime()));


      ImageUtils.grayScaleRev(imgChefTable,reserveDetailItem.getExtensionData().get(0).getChefTable().getReservation_image_detail());
      LinearLayout.LayoutParams params= new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
      params.setMargins(DptoPxConvertion(30), DptoPxConvertion(5), DptoPxConvertion(30), DptoPxConvertion(140));//pass int values for left,top,right,bottom
      tvSkyDollarEarn.setLayoutParams(params);

      tvSkyDollarEarn.setText(TextUtils.isEmpty(reserveDetailItem.getReservationSkyDollarEarn()) ? "-" :  reserveDetailItem.getReservationSkyDollarEarn());
//      if(TextUtils.isEmpty(reserveDetailItem.getReservationSkyDollarEarn())){
//        tvNoteSkyDollarRedemed.setVisibility(View.GONE);
//      }else {
//        tvNoteSkyDollarRedemed.setVisibility(View.VISIBLE);
//        tvNoteSkyDollarRedemed.setText(res(R.string.note_sky_dollar_redemed_1) +
//            " "+(TextUtils.isEmpty(reserveDetailItem.getExtensionData().get(0).getChefTable().getLoyaltyCheftable()) ? WORKING_DAYS : reserveDetailItem.getExtensionData().get(0).getChefTable().getLoyaltyCheftable()) +
//            " "+res(R.string.note_sky_dollar_redemed_2));
//      }
    }

    if (reserveDetailItem.getReservationStatus().equals(Constants.RESERVATION_STATUS_RETRACTED)) {
      lnDateTime.setVisibility(View.GONE);
      tvCancelRequest.setVisibility(View.GONE);
      lnDateTimeOption1.setVisibility(View.VISIBLE);
      lnDateTimeOption2.setVisibility(View.VISIBLE);
      lnDateTimeOption3.setVisibility(View.VISIBLE);
      tvLabelCancelRemark.setVisibility(View.GONE);
      tvCancelRemark.setVisibility(View.GONE);


      setTileToolbar(getResources().getString(R.string.request_number));
      setToolbarBookingId(reserveDetailItem.getReservationId());
      tvChefName.setText("Retracted Chef's Table");
      tv_bookingMadeOnTime.setText(res(R.string.request_made_on) + " " + convertDatetime(reserveDetailItem.getReservation_time_submit()));


      if (!TextUtils.isEmpty(reserveDetailItem.getReservationDatetimeOptions().getReservationDateOptionsFirst())){
        tvReserveDateOption1.setText(convertReservationDate(reserveDetailItem.getReservationDatetimeOptions().getReservationDateOptionsFirst()));
      }
      else {
        tvReserveDateOption1.setText("-");
      }
      if (!TextUtils.isEmpty(reserveDetailItem.getReservationDatetimeOptions().getReservationDateOptionsSecond())){
        tvReserveDateOption2.setText(convertReservationDate(reserveDetailItem.getReservationDatetimeOptions().getReservationDateOptionsSecond()));
      }
      else {
        tvReserveDateOption2.setText("-");
      }
      if (!TextUtils.isEmpty(reserveDetailItem.getReservationDatetimeOptions().getReservationDateOptionsThird())){
        tvReserveDateOption3.setText(convertReservationDate(reserveDetailItem.getReservationDatetimeOptions().getReservationDateOptionsThird()));
      }
      else {
        tvReserveDateOption3.setText("-");
      }

      if (!TextUtils.isEmpty(reserveDetailItem.getReservationDatetimeOptions().getReservationTimeOptionsFirst())){
        tvReserveTimeOption1.setText(convertHourToAmPm1(reserveDetailItem.getReservationDatetimeOptions().getReservationTimeOptionsFirst()));
      }
      else {
        tvReserveTimeOption1.setText("-");
      }
      if (!TextUtils.isEmpty(reserveDetailItem.getReservationDatetimeOptions().getReservationTimeOptionsSecond())){
        tvReserveTimeOption2.setText(convertHourToAmPm1(reserveDetailItem.getReservationDatetimeOptions().getReservationTimeOptionsSecond()));
      }
      else {
        tvReserveTimeOption2.setText("-");
      }
      if (!TextUtils.isEmpty(reserveDetailItem.getReservationDatetimeOptions().getReservationTimeOptionsThird())){
        tvReserveTimeOption3.setText(convertHourToAmPm1(reserveDetailItem.getReservationDatetimeOptions().getReservationTimeOptionsThird()));
      }
      else {
        tvReserveTimeOption3.setText("-");
      }


      ImageUtils.grayScale(imgChefTable,reserveDetailItem.getExtensionData().get(0).getChefTable().getReservation_image_detail());

      LinearLayout.LayoutParams params= new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
      params.setMargins(DptoPxConvertion(30), DptoPxConvertion(5), DptoPxConvertion(30), DptoPxConvertion(140));//pass int values for left,top,right,bottom
      tvSpecialInstruction.setLayoutParams(params);


    }

    if (reserveDetailItem.getReservationStatus().equals(Constants.RESERVATION_STATUS_CANCELLED)) {
      lnDateTime.setVisibility(View.VISIBLE);
      tvCancelRequest.setVisibility(View.GONE);
      lnDateTimeOption1.setVisibility(View.GONE);
      lnDateTimeOption2.setVisibility(View.GONE);
      lnDateTimeOption3.setVisibility(View.GONE);
      tvLabelCancelRemark.setVisibility(View.VISIBLE);
      tvCancelRemark.setVisibility(View.VISIBLE);


      setTileToolbar(getResources().getString(R.string.reservation_number));
      setToolbarBookingId(reserveDetailItem.getReservationId());
      tvChefName.setText(new StringBuilder("Chef's Table with ").append(reserveDetailItem.getChefName()));
      tv_bookingMadeOnTime.setText(res(R.string.reservation_made_on) + " " + convertDatetime(reserveDetailItem.getReservation_time_submit()));


      tv_reserveDate.setText(convertReservationDate(reserveDetailItem.getReservationConfirmDate()));
      tv_reserveTime.setText(convertHourToAmPm1(reserveDetailItem.getReservationConfirmTime()));


      ImageUtils.grayScale(imgChefTable,reserveDetailItem.getExtensionData().get(0).getChefTable().getReservation_image_detail());

      if (!TextUtils.isEmpty(reserveDetailItem.getReservation_status_remarks())){
        tvCancelRemark.setText(reserveDetailItem.getReservation_status_remarks());
      }
      else {
        tvCancelRemark.setText("-");
      }

      LinearLayout.LayoutParams params= new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
      params.setMargins(DptoPxConvertion(30), DptoPxConvertion(5), DptoPxConvertion(30), DptoPxConvertion(140));//pass int values for left,top,right,bottom
      tvCancelRemark.setLayoutParams(params);


    }











  }

  private int DptoPxConvertion(int dpValue)
  {
    return (int)((dpValue * getBaseContext().getResources().getDisplayMetrics().density) + 0.5);
  }






  String toStatusChefTableStr(String status){
    if(status.equalsIgnoreCase(Constants.RESERVATION_STATUS_CONFIRMED)){
      return "Reservation Confirmed";
    }else if(status.equalsIgnoreCase(Constants.RESERVATION_STATUS_COMPLETED)){
      return "Reservation Completed";
    }else if(status.equalsIgnoreCase(Constants.RESERVATION_STATUS_RETRACTED)){
      return "Request Retracted";
    }else if(status.equalsIgnoreCase(Constants.RESERVATION_STATUS_CANCELLED)){
      return "Reservation Cancelled";
    } else if(status.equalsIgnoreCase(Constants.RESERVATION_STATUS_PROCESSING)){
      return "Request Processing";
    }else {
      return "-";
    }
  }



  String res(int res){
    return getResources().getString(res);
  }
}
