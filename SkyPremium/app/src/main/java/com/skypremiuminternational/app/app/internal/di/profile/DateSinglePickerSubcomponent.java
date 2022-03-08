package com.skypremiuminternational.app.app.internal.di.profile;


import com.skypremiuminternational.app.app.features.profile.my_reservation.datesinglepicker.DateSinglePickerDialogFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent
public interface DateSinglePickerSubcomponent extends AndroidInjector<DateSinglePickerDialogFragment> {

  @Subcomponent.Factory
  abstract class Factory implements AndroidInjector.Factory<DateSinglePickerDialogFragment> {}

}
