package com.skypremiuminternational.app.app.features.forgot_password.success;

import com.skypremiuminternational.app.app.internal.mvp.BaseFragmentPresenter;


import javax.inject.Inject;

import rx.subscriptions.CompositeSubscription;



public class ForgotPasswordSuccessPresenter extends
    BaseFragmentPresenter<ForgotPasswordSuccessView> {



  private CompositeSubscription compositeSubscription = new CompositeSubscription();

  @Inject
  public ForgotPasswordSuccessPresenter() {

    attachLoading();
  }

  @Override
  public void onStop() {
    compositeSubscription.clear();
    super.onStop();
  }

}
