package com.skypremiuminternational.app.app.internal.di.navigation;

import com.skypremiuminternational.app.app.features.navigation.NavigationDialogFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;


@Subcomponent
public interface NavigationSubComponent extends AndroidInjector<NavigationDialogFragment> {

  @Subcomponent.Factory
  abstract class Factory implements AndroidInjector.Factory<NavigationDialogFragment> {}

}
