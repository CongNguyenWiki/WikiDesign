package com.skypremiuminternational.app.app.internal.di.booking;


import com.skypremiuminternational.app.app.features.profile.booking.HotelBookingDialogFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;


@Subcomponent
public interface HotelBookingSubComponent extends AndroidInjector<HotelBookingDialogFragment> {

  @Subcomponent.Factory
  abstract class Factory implements AndroidInjector.Factory<HotelBookingDialogFragment> {}

}
