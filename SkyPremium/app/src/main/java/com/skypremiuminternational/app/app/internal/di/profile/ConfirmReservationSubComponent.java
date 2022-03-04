package com.skypremiuminternational.app.app.internal.di.profile;


import com.skypremiuminternational.app.app.features.profile.my_reservation.confirm_create_reservation.ConfirmReservationDialogFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;


@Subcomponent
public interface ConfirmReservationSubComponent extends AndroidInjector<ConfirmReservationDialogFragment> {

  @Subcomponent.Factory
  abstract class Factory implements AndroidInjector.Factory<ConfirmReservationDialogFragment> {}

}
