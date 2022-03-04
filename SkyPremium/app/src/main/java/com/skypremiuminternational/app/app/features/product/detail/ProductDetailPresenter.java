package com.skypremiuminternational.app.app.features.product.detail;

import com.skypremiuminternational.app.app.internal.mvp.BasePresenter;
import com.skypremiuminternational.app.app.internal.mvp.BaseSubscriber;
import com.skypremiuminternational.app.data.InternalStorageManager;
import com.skypremiuminternational.app.domain.interactor.auth.GetAppVersion;
import com.skypremiuminternational.app.domain.interactor.product.GetProductDetails;
import com.skypremiuminternational.app.domain.models.products.DetailsItem;

import javax.inject.Inject;

import rx.SingleSubscriber;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class ProductDetailPresenter extends BasePresenter<ProductDetailView> {

  GetProductDetails getProductDetails;

  CompositeSubscription compositeSubscription = new CompositeSubscription();

  @Inject
  public ProductDetailPresenter(GetAppVersion getAppVersion, InternalStorageManager internalStorageManager,
                                GetProductDetails getProductDetails) {
    super(getAppVersion, internalStorageManager);
    this.getProductDetails = getProductDetails;
  }

  public void getProductDetails(String sku){

    add(getProductDetails.execute(new BaseSubscriber<DetailsItem>() {
      @Override
      public void onSuccess(DetailsItem value) {
        getView().renderProductDetails(value);
      }

      @Override
      public void onError(Throwable error) {

      }
    },sku));

  }


  @Override
  public void add(Subscription subscription) {
    compositeSubscription.add(subscription);
  }
}
