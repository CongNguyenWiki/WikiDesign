package com.skypremiuminternational.app.app.internal.di.booking;


import com.skypremiuminternational.app.app.features.profile.booking.detail.property.PropertyDetailActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;


@Subcomponent
public interface PropertyDetailSubComponent extends AndroidInjector<PropertyDetailActivity> {

  @Subcomponent.Factory
  abstract class Factory implements AndroidInjector.Factory<PropertyDetailActivity> {}

}
