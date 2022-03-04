package com.skypremiuminternational.app.app.internal.di.booking;


import com.skypremiuminternational.app.app.features.profile.booking.history.BookingsHistoryActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;



@Subcomponent
public interface BookingHistorySubComponent extends AndroidInjector<BookingsHistoryActivity> {

  @Subcomponent.Factory
  abstract class Factory implements AndroidInjector.Factory<BookingsHistoryActivity> {}

}
