package com.skypremiuminternational.app.app.features.profile.my_reservation.detail_toptable_reservation;

import com.skypremiuminternational.app.app.internal.mvp.contract.Presentable;
import com.skypremiuminternational.app.app.internal.mvp.contract.Viewable;
import com.skypremiuminternational.app.domain.models.reservation.ReserveHistoryItem;

public interface DetailsReservationTopTableView<T extends Presentable> extends Viewable<T> {

  void renderError(String msg);

  void renderBookingDetail(ReserveHistoryItem reserveHistoryItem);







}
