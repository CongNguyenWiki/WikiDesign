package com.skypremiuminternational.app.app.internal.di.profile;


import com.skypremiuminternational.app.app.features.profile.my_reservation.detail.DetailsResevationActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;


@Subcomponent
public interface ReservationDetailSubComponent extends AndroidInjector<DetailsResevationActivity> {

  @Subcomponent.Factory
  abstract class Factory implements AndroidInjector.Factory<DetailsResevationActivity> {}

}
