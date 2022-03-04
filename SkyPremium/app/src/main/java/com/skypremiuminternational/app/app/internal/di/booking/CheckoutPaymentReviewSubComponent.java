package com.skypremiuminternational.app.app.internal.di.booking;


import com.skypremiuminternational.app.app.features.profile.booking.room.steptwo.CheckoutPaymentReviewFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;



@Subcomponent
public interface CheckoutPaymentReviewSubComponent extends AndroidInjector<CheckoutPaymentReviewFragment> {

  @Subcomponent.Factory
  abstract class Factory implements AndroidInjector.Factory<CheckoutPaymentReviewFragment> {}

}
