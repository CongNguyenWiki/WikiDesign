package com.skypremiuminternational.app.app.features.product;

import android.util.Log;

import com.skypremiuminternational.app.app.internal.mvp.BasePresenter;
import com.skypremiuminternational.app.app.internal.mvp.contract.Viewable;
import com.skypremiuminternational.app.data.InternalStorageManager;
import com.skypremiuminternational.app.domain.interactor.auth.GetAppVersion;
import com.skypremiuminternational.app.domain.interactor.product.GetProducts;

import javax.inject.Inject;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class ProductPresenter extends BasePresenter<ProductView> {


  final GetProducts getProducts;


  CompositeSubscription compositeSubscription = new CompositeSubscription();


  @Inject
  public ProductPresenter(GetAppVersion getAppVersion, InternalStorageManager internalStorageManager,
                          GetProducts getProducts) {
    super(getAppVersion, internalStorageManager);

    this.getProducts = getProducts;

  }

  @Override
  public void add(Subscription subscription) {
    compositeSubscription.add(subscription);
  }



  public void getProducts(){
    add(getProducts.asObservable().subscribe(
        productListResponse -> {

          getView().renderProduct(productListResponse.getItems());
        },
        throwable -> {
          Log.d("Error Presenter","  E: " + throwable.getMessage());
        }
    ));
  }
}
