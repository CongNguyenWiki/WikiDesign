package com.skypremiuminternational.app.app.internal.di.my_favourite;

import com.skypremiuminternational.app.app.features.my_favourite.MyFavouriteActivity;
import com.skypremiuminternational.app.app.features.navigation.NavigationDialogFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;


@Subcomponent
public interface MyFavouriteSubComponent extends AndroidInjector<MyFavouriteActivity> {

  @Subcomponent.Factory
  abstract class Factory implements AndroidInjector.Factory<MyFavouriteActivity> {}

}
