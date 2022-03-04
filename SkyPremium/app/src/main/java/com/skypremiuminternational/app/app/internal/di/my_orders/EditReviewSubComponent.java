package com.skypremiuminternational.app.app.internal.di.my_orders;



import com.skypremiuminternational.app.app.features.my_orders.detail.edit_review.EditReviewDialogFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;


@Subcomponent
public interface EditReviewSubComponent extends AndroidInjector<EditReviewDialogFragment> {

  @Subcomponent.Factory
  abstract class Factory implements AndroidInjector.Factory<EditReviewDialogFragment> {}

}
