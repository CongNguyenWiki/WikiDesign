package com.skypremiuminternational.app.app.features.profile.my_reservation.detail_cheftable_reservation;

import com.skypremiuminternational.app.app.internal.mvp.contract.Presentable;
import com.skypremiuminternational.app.app.internal.mvp.contract.Viewable;
import com.skypremiuminternational.app.domain.models.reservation.ReserveDetailChefTableItem;


public interface DetailsReservationChefTableView<T extends Presentable> extends Viewable<T> {

  void renderError(String msg);

  void renderBookingDetail(ReserveDetailChefTableItem reserveHistoryItem);







}
