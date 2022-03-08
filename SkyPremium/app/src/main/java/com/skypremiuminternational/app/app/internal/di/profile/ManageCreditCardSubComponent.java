package com.skypremiuminternational.app.app.internal.di.profile;


import com.skypremiuminternational.app.app.features.profile.manage_credit_card.ManageCreditCardActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;


@Subcomponent
public interface ManageCreditCardSubComponent extends AndroidInjector<ManageCreditCardActivity> {

  @Subcomponent.Factory
  abstract class Factory implements AndroidInjector.Factory<ManageCreditCardActivity> {}

}
