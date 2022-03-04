package com.skypremiuminternational.app.app.internal.di.booking;

import com.skypremiuminternational.app.app.features.profile.booking.room.RoomCheckoutActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;



@Subcomponent
public interface RoomCheckoutSubComponent extends AndroidInjector<RoomCheckoutActivity> {

  @Subcomponent.Factory
  abstract class Factory implements AndroidInjector.Factory<RoomCheckoutActivity> {}

}
