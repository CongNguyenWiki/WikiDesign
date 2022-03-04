package com.skypremiuminternational.app.app.features.profile.booking.room.steptwo;

import com.skypremiuminternational.app.app.internal.mvp.contract.Presentable;
import com.skypremiuminternational.app.app.internal.mvp.contract.Viewable;
import com.skypremiuminternational.app.domain.models.PaymentDetail;

public interface CheckoutPaymentReviewView<T extends Presentable> extends Viewable<T> {

  void renderError(CheckoutPaymentReviewPresenter.ValidationResult validationResult,
                   boolean showDialog);

  void startBooking(PaymentDetail paymentDetail);

  void showMandatoryNotFilledError();
}
