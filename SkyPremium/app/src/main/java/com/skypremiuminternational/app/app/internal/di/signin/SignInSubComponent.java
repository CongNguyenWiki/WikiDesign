package com.skypremiuminternational.app.app.internal.di.signin;

import com.skypremiuminternational.app.app.features.signin.SignInActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent
public interface SignInSubComponent extends AndroidInjector<SignInActivity> {

  @Subcomponent.Factory
  abstract class Factory implements AndroidInjector.Factory<SignInActivity> {
  }
}
