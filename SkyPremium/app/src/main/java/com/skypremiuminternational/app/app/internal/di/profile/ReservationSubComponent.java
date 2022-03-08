package com.skypremiuminternational.app.app.internal.di.profile;


import com.skypremiuminternational.app.app.features.profile.my_reservation.MyResevationsActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;


@Subcomponent
public interface ReservationSubComponent extends AndroidInjector<MyResevationsActivity> {

  @Subcomponent.Factory
  abstract class Factory implements AndroidInjector.Factory<MyResevationsActivity> {}

}
