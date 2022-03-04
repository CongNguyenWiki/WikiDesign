package com.skypremiuminternational.app.app.utils;

import static com.google.common.base.Preconditions.checkArgument;

import androidx.annotation.NonNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * Created by codigo on 2/4/18.
 */

public class DateFormatter {
  private static final String[] MONTHS = new String[]{
      "Jan", "Feb", "Mar", "Apr", "May", "Jun", "July", "Aug", "Sep", "Oct", "Nov", "Dec"
  };

  @Inject
  public DateFormatter() {

  }

  @NonNull
  public String format(String rawDate) {
    String formattedDate = "";
    if (rawDate == null) return formattedDate;
    try {
      Date date = DateParser.with(Constants.PATTERN_DATE_TIME).parse(rawDate);
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(date);
      formattedDate = calendar.get(Calendar.DAY_OF_MONTH) + " ";
      formattedDate += MONTHS[calendar.get(Calendar.MONTH)] + " " + calendar.get(Calendar.YEAR);
    } catch (ParseException e) {
      Timber.e(e);
    }
    return formattedDate;
  }


  public static String getDayOfMonthSuffix(final int n) {
    checkArgument(n >= 1 && n <= 31, "illegal day of month: " + n);
    if (n >= 11 && n <= 13) {
      return "th";
    }
    switch (n % 10) {
      case 1:  return "st";
      case 2:  return "nd";
      case 3:  return "rd";
      default: return "th";
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
    DateFormat f2 = new SimpleDateFormat("hh:mm a");

    String x = f2.format(d); // "11:00 PM"

    return x;
  }

  public static String convertHourToAmPm1(String Time) {
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
    sdf.applyPattern("EEE dd MMM yyyy");
    String sMyDate = sdf.format(myDate);

    return sMyDate;
  }

  public static String convertReservationDate(String Time){
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", java.util.Locale.ENGLISH);
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

  public static String convertDateShort(String Time){
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", java.util.Locale.ENGLISH);
    Date myDate = null;
    try {
      myDate = sdf.parse(Time);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    sdf.applyPattern("d MMM yyyy");
    String sMyDate = sdf.format(myDate);

    return sMyDate;
  }

  public static String convertDatetimeRegisterEvent(String Time){
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", java.util.Locale.ENGLISH);
    Date myDate = null;
    try {
      myDate = sdf.parse(Time);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    sdf.applyPattern("EEE, dd MMM yyyy, h.mma");
    String sMyDate = sdf.format(myDate);

    return sMyDate;
  }


}
