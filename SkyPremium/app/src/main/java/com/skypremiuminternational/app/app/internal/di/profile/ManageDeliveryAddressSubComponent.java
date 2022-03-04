package com.skypremiuminternational.app.app.internal.di.profile;

import com.skypremiuminternational.app.app.features.profile.manage_delivery_address.ManageDeliveryAddressActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;


@Subcomponent
public interface ManageDeliveryAddressSubComponent extends AndroidInjector<ManageDeliveryAddressActivity> {

  @Subcomponent.Factory
  abstract class Factory implements AndroidInjector.Factory<ManageDeliveryAddressActivity> {}

}
