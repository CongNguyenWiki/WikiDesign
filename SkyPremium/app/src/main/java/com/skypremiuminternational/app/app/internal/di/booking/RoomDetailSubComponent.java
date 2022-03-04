package com.skypremiuminternational.app.app.internal.di.booking;


import com.skypremiuminternational.app.app.features.profile.booking.detail.room.RoomDetailDialogFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;


@Subcomponent
public interface RoomDetailSubComponent extends AndroidInjector<RoomDetailDialogFragment> {

  @Subcomponent.Factory
  abstract class Factory implements AndroidInjector.Factory<RoomDetailDialogFragment> {}

}
