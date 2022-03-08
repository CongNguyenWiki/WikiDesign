package com.skypremiuminternational.app.app.internal.di.splash;



import com.skypremiuminternational.app.app.features.splash.SplashActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;


@Subcomponent
public interface SplashSubComponent extends AndroidInjector<SplashActivity> {

  @Subcomponent.Factory
  abstract class Factory implements AndroidInjector.Factory<SplashActivity> {}

}
