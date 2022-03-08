package com.skypremiuminternational.app.app.features.profile.my_reservation;

import com.skypremiuminternational.app.app.internal.mvp.contract.Presentable;
import com.skypremiuminternational.app.app.internal.mvp.contract.Viewable;


import com.skypremiuminternational.app.domain.models.reservation.ReserveHistoryItem;
import com.skypremiuminternational.app.domain.models.reservation.ReserveHistoryRespone;

public interface MyReservationsView<T extends Presentable> extends Viewable<T> {

  void renderHistoryReservation(ReserveHistoryRespone reserveHistoryRespone);

  void renderError(String msg);

  void renderGotoBookingDetail(ReserveHistoryItem reserveHistoryItem);


}
