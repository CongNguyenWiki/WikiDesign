package com.skypremiuminternational.app.app.internal.di.profile;



import com.skypremiuminternational.app.app.features.profile.edit_profile.EditProfileActivity;
import com.skypremiuminternational.app.app.features.profile.my_profile.ProfileActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;


@Subcomponent
public interface EditProfileSubComponent extends AndroidInjector<EditProfileActivity> {

  @Subcomponent.Factory
  abstract class Factory implements AndroidInjector.Factory<EditProfileActivity> {}

}
