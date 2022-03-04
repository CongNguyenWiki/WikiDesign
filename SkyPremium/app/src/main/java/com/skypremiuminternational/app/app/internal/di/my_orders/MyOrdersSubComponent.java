package com.skypremiuminternational.app.app.internal.di.my_orders;


import com.skypremiuminternational.app.app.features.my_orders.MyOrderActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent
public interface MyOrdersSubComponent extends AndroidInjector<MyOrderActivity> {


  @Subcomponent.Factory
  abstract class Factory implements AndroidInjector.Factory<MyOrderActivity> {}
}
