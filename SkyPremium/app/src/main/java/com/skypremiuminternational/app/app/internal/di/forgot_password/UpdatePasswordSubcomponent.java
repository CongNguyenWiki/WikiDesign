package com.skypremiuminternational.app.app.internal.di.forgot_password;

import com.skypremiuminternational.app.app.features.forgot_password.update.UpdatePasswordActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;


@Subcomponent
public interface UpdatePasswordSubcomponent
    extends AndroidInjector<UpdatePasswordActivity> {

  @Subcomponent.Factory
  abstract class Factory implements AndroidInjector.Factory<UpdatePasswordActivity> { }
}
