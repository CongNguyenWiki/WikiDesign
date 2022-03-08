package com.skypremiuminternational.app.app.features.shopping_cart;

import com.skypremiuminternational.app.app.internal.mvp.BasePresenter;
import com.skypremiuminternational.app.data.InternalStorageManager;
import com.skypremiuminternational.app.domain.interactor.auth.GetAppVersion;
import com.skypremiuminternational.app.domain.interactor.cart.GetCartInfo;
import com.skypremiuminternational.app.domain.models.cart.CartDetailResponse;

import javax.inject.Inject;

import rx.SingleSubscriber;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;


public class ShoppingCartPresenter extends BasePresenter<ShoppingCartView> {

  CompositeSubscription compositeSubscription = new CompositeSubscription();

  private final GetCartInfo getCartInfo;




  @Inject
  public ShoppingCartPresenter(GetAppVersion getAppVersion, InternalStorageManager internalStorageManager,
                                GetCartInfo getCartInfo
  ) {
    super(getAppVersion, internalStorageManager);
    this.getCartInfo = getCartInfo;
  }

  public void getCartInfo(){
    add(getCartInfo.execute(new SingleSubscriber<CartDetailResponse>() {
      @Override
      public void onSuccess(CartDetailResponse value) {
        getView().renderListCart(value);
      }

      @Override
      public void onError(Throwable error) {
        getView().renderError(error.getMessage());

      }
    }));

  }

  @Override
  public void add(Subscription subscription) {
    compositeSubscription.add(subscription);
  }
}
