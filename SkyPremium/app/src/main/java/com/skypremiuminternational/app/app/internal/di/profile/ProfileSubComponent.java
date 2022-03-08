package com.skypremiuminternational.app.app.internal.di.profile;



import com.skypremiuminternational.app.app.features.profile.my_profile.ProfileActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;


@Subcomponent
public interface ProfileSubComponent extends AndroidInjector<ProfileActivity> {

  @Subcomponent.Factory
  abstract class Factory implements AndroidInjector.Factory<ProfileActivity> {}

}
