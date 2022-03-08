package com.skypremiuminternational.app.app.internal.di.my_orders;



import com.skypremiuminternational.app.app.features.my_orders.detail.OrderDetailsActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;


@Subcomponent
public interface OrderDetailSubComponent extends AndroidInjector<OrderDetailsActivity> {


  @Subcomponent.Factory
  abstract class Factory implements AndroidInjector.Factory<OrderDetailsActivity> {}
}
