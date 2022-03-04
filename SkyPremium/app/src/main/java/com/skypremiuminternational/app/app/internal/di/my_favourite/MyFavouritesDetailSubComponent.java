package com.skypremiuminternational.app.app.internal.di.my_favourite;


import com.skypremiuminternational.app.app.features.my_favourite.detail.MyFavouritesDetailActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;


@Subcomponent
public interface MyFavouritesDetailSubComponent extends AndroidInjector<MyFavouritesDetailActivity> {

  @Subcomponent.Factory
  abstract class Factory implements AndroidInjector.Factory<MyFavouritesDetailActivity> {}

}
