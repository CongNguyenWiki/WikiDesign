package com.skypremiuminternational.app.app.internal.di.booking;


import com.skypremiuminternational.app.app.features.profile.booking.room.stepone.CheckoutGuestDetailFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;


@Subcomponent
public interface CheckoutGuestDetailSubComponent extends AndroidInjector<CheckoutGuestDetailFragment> {

  @Subcomponent.Factory
  abstract class Factory implements AndroidInjector.Factory<CheckoutGuestDetailFragment> {}

}
