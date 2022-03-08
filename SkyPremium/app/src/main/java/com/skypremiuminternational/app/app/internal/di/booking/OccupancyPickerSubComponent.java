package com.skypremiuminternational.app.app.internal.di.booking;


import com.skypremiuminternational.app.app.features.profile.booking.occupancy.OccupancyPickerDialogFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;


@Subcomponent
public interface OccupancyPickerSubComponent extends AndroidInjector<OccupancyPickerDialogFragment> {

  @Subcomponent.Factory
  abstract class Factory implements AndroidInjector.Factory<OccupancyPickerDialogFragment> {}

}
