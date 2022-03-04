package com.skypremiuminternational.app.app.internal.di.landing;

import com.skypremiuminternational.app.app.features.landing.LandingActivity;
import com.skypremiuminternational.app.app.features.signin.SignInActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent
public interface LandingSubComponent extends AndroidInjector<LandingActivity> {

  @Subcomponent.Factory
  abstract class Factory implements AndroidInjector.Factory<LandingActivity> {
  }
}
