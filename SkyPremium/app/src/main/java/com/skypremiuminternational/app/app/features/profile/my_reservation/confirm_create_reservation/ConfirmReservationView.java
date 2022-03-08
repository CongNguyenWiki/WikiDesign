package com.skypremiuminternational.app.app.features.profile.my_reservation.confirm_create_reservation;

import com.skypremiuminternational.app.app.internal.mvp.contract.Presentable;
import com.skypremiuminternational.app.app.internal.mvp.contract.Viewable;
import com.skypremiuminternational.app.domain.models.reservation.CancelReservationResponse;
import com.skypremiuminternational.app.domain.models.reservation.ReservationResultResponse;

public interface ConfirmReservationView<T extends Presentable> extends Viewable<T> {

  void renderCreateSuccess(ReservationResultResponse reservationResultResponse);

  void renderCreateFailed();

  void renderCancel(CancelReservationResponse response);

  void renderCancelError(String msg);

}
