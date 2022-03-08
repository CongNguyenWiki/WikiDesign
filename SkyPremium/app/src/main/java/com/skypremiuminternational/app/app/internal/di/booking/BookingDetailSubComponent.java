package com.skypremiuminternational.app.app.internal.di.booking;


import com.skypremiuminternational.app.app.features.profile.booking.detail.BookingDetailActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;


@Subcomponent
public interface BookingDetailSubComponent extends AndroidInjector<BookingDetailActivity> {

  @Subcomponent.Factory
  abstract class Factory implements AndroidInjector.Factory<BookingDetailActivity> {}

}
