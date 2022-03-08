package com.skypremiuminternational.app.app.features.my_favourite.detail;

import com.skypremiuminternational.app.app.internal.mvp.BasePresenter;
import com.skypremiuminternational.app.data.InternalStorageManager;
import com.skypremiuminternational.app.domain.interactor.auth.GetAppVersion;

import javax.inject.Inject;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;


public class MyFavouritesPresenter extends BasePresenter<MyFavouritesDetailView> {

  CompositeSubscription compositeSubscription = new CompositeSubscription();
  InternalStorageManager internalStorageManager;


  @Inject
  public MyFavouritesPresenter(GetAppVersion getAppVersion, InternalStorageManager internalStorageManager) {
    super(getAppVersion, internalStorageManager);
    this.internalStorageManager = internalStorageManager;
  }

  @Override
  public void add(Subscription subscription) {
    compositeSubscription.add(subscription);
  }

  String getCustomerToken(){
    return internalStorageManager.getUserAuthToken();
  }
}