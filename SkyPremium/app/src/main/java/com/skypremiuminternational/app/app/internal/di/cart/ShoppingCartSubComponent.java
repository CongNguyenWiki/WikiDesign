package com.skypremiuminternational.app.app.internal.di.cart;



import com.skypremiuminternational.app.app.features.shopping_cart.ShoppingCartActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;



@Subcomponent
public interface ShoppingCartSubComponent extends AndroidInjector<ShoppingCartActivity> {

  @Subcomponent.Factory
  abstract class Factory implements AndroidInjector.Factory<ShoppingCartActivity> {}

}
