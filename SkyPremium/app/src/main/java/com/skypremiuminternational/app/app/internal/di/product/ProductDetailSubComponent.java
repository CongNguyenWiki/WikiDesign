package com.skypremiuminternational.app.app.internal.di.product;


import com.skypremiuminternational.app.app.features.product.detail.ProductDetailActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;



@Subcomponent
public interface ProductDetailSubComponent extends AndroidInjector<ProductDetailActivity> {

  @Subcomponent.Factory
  abstract class Factory implements AndroidInjector.Factory<ProductDetailActivity> {}

}
