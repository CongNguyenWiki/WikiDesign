package com.skypremiuminternational.app.app.features.profile.booking;



import com.skypremiuminternational.app.app.internal.mvp.BaseFragmentPresenter;
import com.skypremiuminternational.app.domain.interactor.auth.CheckUserLoggedIn;

import javax.inject.Inject;

import rx.subscriptions.CompositeSubscription;


public class HotelBookingPresenter extends BaseFragmentPresenter<HotelBookingView> {

  private final CheckUserLoggedIn checkUserLoggedIn;

  private CompositeSubscription compositeSubscription = new CompositeSubscription();

  @Inject
  public HotelBookingPresenter(CheckUserLoggedIn checkUserLoggedIn) {
    this.checkUserLoggedIn = checkUserLoggedIn;
    attachLoading(checkUserLoggedIn);
  }

  public void checkUserLoggedIn() {
    /*Subscription subscription = checkUserLoggedIn.execute(new BaseSubscriber<Boolean>() {
      @Override public void onSuccess(Boolean aBoolean) {
        SplashViewState viewState = SplashViewState.builder().setUserLoggedIn(aBoolean).build();
        getView().render(viewState);
      }

      @Override public void onError(Throwable error) {
        super.onError(error);
        error.printStackTrace();
      }
    });
    compositeSubscription.add(subscription);*/
  }

  @Override
  public void onStop() {
    compositeSubscription.clear();
    super.onStop();
  }
}
