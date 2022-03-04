package com.skypremiuminternational.app.app.internal.di.booking;


import com.skypremiuminternational.app.app.features.profile.booking.summary.BookingSummaryActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;


@Subcomponent
public interface BookingSummarySubComponent extends AndroidInjector<BookingSummaryActivity> {

  @Subcomponent.Factory
  abstract class Factory implements AndroidInjector.Factory<BookingSummaryActivity> {}

}
