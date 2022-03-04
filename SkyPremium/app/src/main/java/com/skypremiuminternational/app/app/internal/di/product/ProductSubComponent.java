package com.skypremiuminternational.app.app.internal.di.product;
import com.skypremiuminternational.app.app.features.product.ProductActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;


@Subcomponent
public interface ProductSubComponent extends AndroidInjector<ProductActivity> {

  @Subcomponent.Factory
  abstract class Factory implements AndroidInjector.Factory<ProductActivity> {}

}
