package com.skypremiuminternational.app.app.internal.di.my_orders;


import com.skypremiuminternational.app.app.features.my_orders.detail.see_review.ReviewContentDialogFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent
public interface SeeReviewSubComponent extends AndroidInjector<ReviewContentDialogFragment> {

  @Subcomponent.Factory
  abstract class Factory implements AndroidInjector.Factory<ReviewContentDialogFragment> {}

}
