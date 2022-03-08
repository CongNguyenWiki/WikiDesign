package com.skypremiuminternational.app.app.internal.di.booking;


import com.skypremiuminternational.app.app.features.profile.booking.room.stepthree.OrderPlacedFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;


@Subcomponent
public interface OrderPlacedSubComponent extends AndroidInjector<OrderPlacedFragment> {

  @Subcomponent.Factory
  abstract class Factory implements AndroidInjector.Factory<OrderPlacedFragment> {}

}
