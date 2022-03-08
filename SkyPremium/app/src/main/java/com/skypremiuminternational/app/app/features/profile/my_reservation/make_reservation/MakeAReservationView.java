package com.skypremiuminternational.app.app.features.profile.my_reservation.make_reservation;

import com.skypremiuminternational.app.app.internal.mvp.contract.Presentable;
import com.skypremiuminternational.app.app.internal.mvp.contract.Viewable;
import com.skypremiuminternational.app.domain.models.reservation.ReservationTimeResponse;

public interface MakeAReservationView <T extends Presentable> extends Viewable<T> {


  void renderReservationTime(ReservationTimeResponse reservationTimeResponse);


  void renderRestaurantMsg(String msg);

  void renderReservationTimeFailed();
}
