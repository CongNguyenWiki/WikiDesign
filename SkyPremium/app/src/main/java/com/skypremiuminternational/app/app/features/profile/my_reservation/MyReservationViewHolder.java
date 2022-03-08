package com.skypremiuminternational.app.app.features.profile.my_reservation;

import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.skypremiuminternational.app.R;
import com.skypremiuminternational.app.app.utils.Constants;
import com.skypremiuminternational.app.app.utils.DateParser;
import com.skypremiuminternational.app.domain.models.reservation.ReserveHistoryItem;
import com.skypremiuminternational.app.domain.models.reservation.ReserveResultDataItem;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyReservationViewHolder extends RecyclerView.ViewHolder {


  @BindView(R.id.tvRestaurantName)
  public TextView tvRestaurantName;
  @BindView(R.id.tvReservationNumber)
  public TextView tvReservationNumber;
  @BindView(R.id.tvReservationDate)
  public TextView tvReservationDate;
  @BindView(R.id.tvName)
  public TextView tvName;
  //  @BindView(R.id.tvLastName)
//  public TextView tvLastName;
  @BindView(R.id.tvStatus)
  public TextView tvStatus;
  @BindView(R.id.tv_sky_dollar_redeemed)
  public TextView tvSkyDollarRedeemed;
  @BindView(R.id.tvReservationTime)
  public TextView tvReservationTime;
  @BindView(R.id.ln_skydollar_redeem)
  public LinearLayout ln_skydollar_redeem;






  public void bind(ReserveHistoryItem reserveHistoryItem) {
    String reservationData = reserveHistoryItem.getReservationData();
    Gson gson = new Gson();
    ReserveResultDataItem reserveResultDataItem = gson.fromJson(reservationData, ReserveResultDataItem.class);








    if (reserveHistoryItem.getExtensionData().get(0).getReservation_type() != null && reserveHistoryItem.getExtensionData().get(0).getReservation_type().equalsIgnoreCase("top_table") && reserveHistoryItem.getExtensionData().get(0).getTopTable() != null){
      tvStatus.setText(toStatusTopTableStr(reserveHistoryItem.getReservationStatus()));
      tvRestaurantName.setText(reserveHistoryItem.getRestaurantName());

      if (reserveHistoryItem.getExtensionData().get(0).getTopTable().getReservation_time().equals("-1")){
        tvReservationTime.setText("-");
      }
      else {
        tvReservationTime.setText(convertHourToAmPm(reserveHistoryItem.getExtensionData().get(0).getTopTable().getReservation_time()));
      }

      try {
        tvReservationDate.setText(DateParser.changeFormatDate(Constants.PATTERN_DATE_SHORT, "dd/MM/yyyy", reserveHistoryItem.getReservationDate()));
      } catch (ParseException e) {
        tvReservationDate.setText(reserveHistoryItem.getReservationDate());
        e.printStackTrace();
      }



      if (reserveHistoryItem.getExtensionData() != null && reserveHistoryItem.getExtensionData().size() >0){
        String firstName = "",lastName = "";
        if (reserveHistoryItem.getExtensionData().get(0).getFirstName() != null && !TextUtils.isEmpty(reserveHistoryItem.getExtensionData().get(0).getFirstName())){
          firstName = reserveHistoryItem.getExtensionData().get(0).getFirstName();
        }
        if (reserveHistoryItem.getExtensionData().get(0).getLastName() != null && !TextUtils.isEmpty(reserveHistoryItem.getExtensionData().get(0).getLastName()) ){
          lastName = reserveHistoryItem.getExtensionData().get(0).getLastName();
        }
        tvName.setText(firstName+ " "+lastName);
      }
      else {
        tvName.setText("-");
      }


      if (reserveHistoryItem.getReservationStatus().equals(Constants.RESERVATION_STATUS_CONFIRMED)) {
        tvReservationNumber.setText("Booking Number: " + reserveHistoryItem.getReservationId());
        String skyDollar = (TextUtils.isEmpty(reserveHistoryItem.getReservationSkyDollarEarn()) || reserveHistoryItem.getReservationSkyDollarEarn().trim().equalsIgnoreCase("0")) ? "-" : reserveHistoryItem.getReservationSkyDollarEarn();
        tvSkyDollarRedeemed.setText(skyDollar);
      }

      if (reserveHistoryItem.getReservationStatus().equals(Constants.RESERVATION_STATUS_COMPLETED)) {
        tvReservationNumber.setText("Booking Number: " + reserveHistoryItem.getReservationId());
        String skyDollar = (TextUtils.isEmpty(reserveHistoryItem.getReservationSkyDollarEarn()) || reserveHistoryItem.getReservationSkyDollarEarn().trim().equalsIgnoreCase("0")) ? "-" : reserveHistoryItem.getReservationSkyDollarEarn();
        tvSkyDollarRedeemed.setText(skyDollar);
      }
      if (reserveHistoryItem.getReservationStatus().equals(Constants.RESERVATION_STATUS_RETRACTED)) {
        tvReservationNumber.setText("Request Number: " + reserveHistoryItem.getReservationId());
        tvSkyDollarRedeemed.setText("-");
      }
      if (reserveHistoryItem.getReservationStatus().equals(Constants.RESERVATION_STATUS_CANCELLED)) {
        tvReservationNumber.setText("Booking Number: " + reserveHistoryItem.getReservationId());
        tvSkyDollarRedeemed.setText("-");
      }
      if (reserveHistoryItem.getReservationStatus().equals(Constants.RESERVATION_STATUS_UNSUCCESSFUL)) {
        tvReservationNumber.setText("Request Number: " + reserveHistoryItem.getReservationId());
        tvSkyDollarRedeemed.setText("-");
      }
      if (reserveHistoryItem.getReservationStatus().equals(Constants.RESERVATION_STATUS_PENDING)) {
        tvReservationNumber.setText("Request Number: " + reserveHistoryItem.getReservationId());
        tvSkyDollarRedeemed.setText("-");
      }







    }
    if (reserveHistoryItem.getExtensionData().get(0).getReservation_type() != null && reserveHistoryItem.getExtensionData().get(0).getReservation_type().equalsIgnoreCase("chef_table") && reserveHistoryItem.getExtensionData().get(0).getChefTable() !=null) {
      tvStatus.setText(toStatusChefTableStr(reserveHistoryItem.getReservationStatus()));


      if (reserveHistoryItem.getReservationStatus().equalsIgnoreCase(Constants.RESERVATION_STATUS_PROCESSING)) {
        tvRestaurantName.setText("Pending Chef's Table");
        tvReservationNumber.setText("Request Number: " + reserveHistoryItem.getReservationId());
        tvReservationTime.setText("Pending");
        tvReservationDate.setText("Pending");
        tvSkyDollarRedeemed.setText("-");
      }
      if (reserveHistoryItem.getReservationStatus().equalsIgnoreCase(Constants.RESERVATION_STATUS_RETRACTED)) {
        tvRestaurantName.setText("Retracted Chef's Table");
        tvReservationNumber.setText("Request Number: " + reserveHistoryItem.getReservationId());
        tvReservationTime.setText("-");
        tvReservationDate.setText("-");
        tvSkyDollarRedeemed.setText("-");
      }
      if (reserveHistoryItem.getReservationStatus().equalsIgnoreCase(Constants.RESERVATION_STATUS_CANCELLED)) {
        tvRestaurantName.setText(new StringBuilder("Chef's Table with ").append(reserveHistoryItem.getChefName()));
        tvReservationNumber.setText("Booking Number: " + reserveHistoryItem.getReservationId());
        tvSkyDollarRedeemed.setText("-");
        tvReservationTime.setText(convertHourToAmPm(reserveHistoryItem.getReservationConfirmTime()));

        try {
          tvReservationDate.setText(DateParser.changeFormatDate(Constants.PATTERN_DATE_SHORT, "dd/MM/yyyy", reserveHistoryItem.getReservationConfirmDate()));
        } catch (ParseException e) {
          tvReservationDate.setText(reserveHistoryItem.getReservationConfirmDate());
          e.printStackTrace();
        }
      }
      if (reserveHistoryItem.getReservationStatus().equalsIgnoreCase(Constants.RESERVATION_STATUS_CONFIRMED)) {
        tvRestaurantName.setText(new StringBuilder("Chef's Table with ").append(reserveHistoryItem.getChefName()));
        tvReservationNumber.setText("Booking Number: " + reserveHistoryItem.getReservationId());
        String skyDollar = (TextUtils.isEmpty(reserveHistoryItem.getReservationSkyDollarEarn()) || reserveHistoryItem.getReservationSkyDollarEarn().trim().equalsIgnoreCase("0")) ? "-" : reserveHistoryItem.getReservationSkyDollarEarn();
        tvSkyDollarRedeemed.setText(skyDollar);
        tvReservationTime.setText(convertHourToAmPm(reserveHistoryItem.getReservationConfirmTime()));

        try {
          tvReservationDate.setText(DateParser.changeFormatDate(Constants.PATTERN_DATE_SHORT, "dd/MM/yyyy", reserveHistoryItem.getReservationConfirmDate()));
        } catch (ParseException e) {
          tvReservationDate.setText(reserveHistoryItem.getReservationConfirmDate());
          e.printStackTrace();
        }
      }
      if (reserveHistoryItem.getReservationStatus().equalsIgnoreCase(Constants.RESERVATION_STATUS_COMPLETED)) {
        tvRestaurantName.setText(new StringBuilder("Chef's Table with ").append(reserveHistoryItem.getChefName()));
        tvReservationNumber.setText("Booking Number: " + reserveHistoryItem.getReservationId());
        String skyDollar = (TextUtils.isEmpty(reserveHistoryItem.getReservationSkyDollarEarn()) || reserveHistoryItem.getReservationSkyDollarEarn().trim().equalsIgnoreCase("0")) ? "-" : reserveHistoryItem.getReservationSkyDollarEarn();
        tvSkyDollarRedeemed.setText(skyDollar);
        tvReservationTime.setText(convertHourToAmPm(reserveHistoryItem.getReservationConfirmTime()));

        try {
          tvReservationDate.setText(DateParser.changeFormatDate(Constants.PATTERN_DATE_SHORT, "dd/MM/yyyy", reserveHistoryItem.getReservationConfirmDate()));
        } catch (ParseException e) {
          tvReservationDate.setText(reserveHistoryItem.getReservationConfirmDate());
          e.printStackTrace();
        }
      }




      if (reserveHistoryItem.getExtensionData() != null && reserveHistoryItem.getExtensionData().size() > 0) {
        String firstName = "", lastName = "";
        if (reserveHistoryItem.getExtensionData().get(0).getFirstName() != null && !TextUtils.isEmpty(reserveHistoryItem.getExtensionData().get(0).getFirstName())) {
          firstName = reserveHistoryItem.getExtensionData().get(0).getFirstName();
        }
        if (reserveHistoryItem.getExtensionData().get(0).getLastName() != null && !TextUtils.isEmpty(reserveHistoryItem.getExtensionData().get(0).getLastName())) {
          lastName = reserveHistoryItem.getExtensionData().get(0).getLastName();
        }
        tvName.setText(firstName + " " + lastName);
      } else {
        tvName.setText("-");
      }



    }
    if (reserveHistoryItem.getExtensionData().get(0).getReservation_type() != null && reserveHistoryItem.getExtensionData().get(0).getReservation_type().equalsIgnoreCase("restaurant")) {
      tvRestaurantName.setText(reserveHistoryItem.getRestaurantName());
      tvStatus.setText(toStatusStr(reserveHistoryItem.getReservationStatus()));
      tvReservationNumber.setText("Reservation Number: " + reserveHistoryItem.getReservationId());

      tvReservationTime.setText(reserveHistoryItem.getReservationDate()
              .substring(reserveHistoryItem.getReservationDate().lastIndexOf(" ")+1, reserveHistoryItem.getReservationDate().lastIndexOf(" ") + 6));

      try {
        tvReservationDate.setText(DateParser.changeFormatDate(Constants.PATTERN_DATE_SHORT, "dd/MM/yyyy", reserveHistoryItem.getReservationDate()));
      } catch (ParseException e) {
        tvReservationDate.setText(reserveHistoryItem.getReservationDate());
        e.printStackTrace();
      }

      if (reserveResultDataItem.getDiner() != null){
        String firstName = "",lastName = "";

        if (reserveResultDataItem.getDiner().getFirstName() != null && !TextUtils.isEmpty(reserveResultDataItem.getDiner().getFirstName())){
          firstName = reserveResultDataItem.getDiner().getFirstName();
        }
        if (reserveResultDataItem.getDiner().getLastName() != null && !TextUtils.isEmpty(reserveResultDataItem.getDiner().getLastName())){
          lastName = reserveResultDataItem.getDiner().getLastName();
        }

        tvName.setText(firstName+ " "+lastName);

      }
      else {
        tvName.setText("-");

      }

      String skyDollar =  (TextUtils.isEmpty(reserveHistoryItem.getReservationSkyDollarEarn())||reserveHistoryItem.getReservationSkyDollarEarn().trim() .equalsIgnoreCase("0")) ? "-" :  reserveHistoryItem.getReservationSkyDollarEarn();
      tvSkyDollarRedeemed.setText(skyDollar);
    }







  }




  String toStatusTopTableStr(String s){
    if(s.equalsIgnoreCase(Constants.RESERVATION_STATUS_CONFIRMED)){
      return "Reservation Confirmed";
    }else if(s.equalsIgnoreCase(Constants.RESERVATION_STATUS_COMPLETED)){
      return "Reservation Completed";
    }else if(s.equalsIgnoreCase(Constants.RESERVATION_STATUS_RETRACTED)){
      return "Request Retracted";
    }else if(s.equalsIgnoreCase(Constants.RESERVATION_STATUS_CANCELLED)){
      return "Reservation Cancelled";
    }else if(s.equalsIgnoreCase(Constants.RESERVATION_STATUS_UNSUCCESSFUL)){
      return "Request Unsuccessful";
    }else if(s.equalsIgnoreCase(Constants.RESERVATION_STATUS_PENDING)){
      return "Request Pending";
    }else {
      return "-";
    }
  }

  String toStatusChefTableStr(String s){
    if(s.equalsIgnoreCase(Constants.RESERVATION_STATUS_CONFIRMED)){
      return "Reservation Confirmed";
    }else if(s.equalsIgnoreCase(Constants.RESERVATION_STATUS_COMPLETED)){
      return "Reservation Completed";
    }else if(s.equalsIgnoreCase(Constants.RESERVATION_STATUS_RETRACTED)){
      return "Request Retracted";
    } else if(s.equalsIgnoreCase(Constants.RESERVATION_STATUS_CANCELLED)){
      return "Reservation Cancelled";
    }else if(s.equalsIgnoreCase(Constants.RESERVATION_STATUS_PROCESSING)){
      return "Request Processing";
    }else {
      return "-";
    }
  }

  String toStatusStr(String s){
    if(s.equalsIgnoreCase("1")){
      return this.itemView.getResources().getString(R.string.confirmed);
    }else if(s.equalsIgnoreCase("4")){
      return this.itemView.getResources().getString(R.string.cancelled);
    }else {
      return "-";
    }
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


  public MyReservationViewHolder(@NonNull View itemView) {
    super(itemView);
    ButterKnife.bind(this, itemView);
  }
}
