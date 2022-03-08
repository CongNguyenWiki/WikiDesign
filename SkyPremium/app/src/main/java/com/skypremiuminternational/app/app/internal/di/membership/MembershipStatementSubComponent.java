package com.skypremiuminternational.app.app.internal.di.membership;


import com.skypremiuminternational.app.app.features.membership.MembershipStatementActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent
public interface MembershipStatementSubComponent extends AndroidInjector<MembershipStatementActivity> {

  @Subcomponent.Factory
  abstract class Factory implements AndroidInjector.Factory<MembershipStatementActivity>{}
}
