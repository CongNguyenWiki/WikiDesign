package com.skypremiuminternational.app.app.internal.di.booking;


import com.skypremiuminternational.app.app.features.profile.booking.cancellationpolicy.CancellationPolicyDialogFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent
public interface CancellationPolicySubComponent extends AndroidInjector<CancellationPolicyDialogFragment> {

  @Subcomponent.Factory
  abstract class Factory implements AndroidInjector.Factory<CancellationPolicyDialogFragment> {}

}
