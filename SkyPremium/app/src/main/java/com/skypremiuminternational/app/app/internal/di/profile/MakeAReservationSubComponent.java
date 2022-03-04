package com.skypremiuminternational.app.app.internal.di.profile;


import com.skypremiuminternational.app.app.features.profile.my_reservation.make_reservation.MakeAReservationDialogFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;


@Subcomponent
public interface MakeAReservationSubComponent extends AndroidInjector<MakeAReservationDialogFragment> {

  @Subcomponent.Factory
  abstract class Factory implements AndroidInjector.Factory<MakeAReservationDialogFragment> {}

}
