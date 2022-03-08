package com.skypremiuminternational.app.app.internal.di.booking;


import com.skypremiuminternational.app.app.features.profile.booking.daterangepicker.DateRangePickerDialogFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;


@Subcomponent
public interface DateRangePickerSubcomponent extends AndroidInjector<DateRangePickerDialogFragment> {

  @Subcomponent.Factory
  abstract class Factory implements AndroidInjector.Factory<DateRangePickerDialogFragment> {}

}
