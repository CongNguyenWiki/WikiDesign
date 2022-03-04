package com.skypremiuminternational.app.app.internal.di.my_orders;



import com.skypremiuminternational.app.app.features.my_orders.detail.review.ReviewDialogFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent
public interface ReviewSubComponent extends AndroidInjector<ReviewDialogFragment> {

  @Subcomponent.Factory
  abstract class Factory implements AndroidInjector.Factory<ReviewDialogFragment> {}

}

