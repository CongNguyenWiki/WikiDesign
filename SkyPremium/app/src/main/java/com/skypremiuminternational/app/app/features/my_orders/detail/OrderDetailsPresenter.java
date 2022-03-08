package com.skypremiuminternational.app.app.features.my_orders.detail;

import com.skypremiuminternational.app.app.internal.mvp.BasePresenter;
import com.skypremiuminternational.app.data.InternalStorageManager;
import com.skypremiuminternational.app.domain.interactor.auth.GetAppVersion;
import com.skypremiuminternational.app.domain.interactor.ean.GetISOCountryCodes;
import com.skypremiuminternational.app.domain.interactor.my_orders.GetOrderDetail;

import com.skypremiuminternational.app.domain.interactor.permission_profile.PermissionProfile;
import com.skypremiuminternational.app.domain.models.ean.ISOCountry;
import com.skypremiuminternational.app.domain.models.my_orders.detail.OrderDetailResponse;
import com.skypremiuminternational.app.domain.models.permissions.PermissionProfileItem;


import java.util.List;

import javax.inject.Inject;

import rx.SingleSubscriber;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;



public class OrderDetailsPresenter extends BasePresenter<OrderDetailsView> {
  private final GetISOCountryCodes getISOCountryCodes;
  private final GetOrderDetail getOrderDetail;
  private final CompositeSubscription compositeSubscription;
  private List<ISOCountry> countryCodeList;
  private final PermissionProfile getPermissionProfile;

  @Inject
  public OrderDetailsPresenter(GetAppVersion getAppVersion, InternalStorageManager internalStorageManager,
                               GetOrderDetail getOrderDetail, GetISOCountryCodes getISOCountryCodes, PermissionProfile getPermissionProfile) {
    super(getAppVersion, internalStorageManager);
    this.getOrderDetail = getOrderDetail;
    this.getISOCountryCodes = getISOCountryCodes;
    this.getPermissionProfile = getPermissionProfile;
    compositeSubscription = new CompositeSubscription();
  }

  public void getOrderDetail(int orderId) {
    getView().showLoading("Getting the order detail...");
    add(getOrderDetail.execute(new SingleSubscriber<OrderDetailResponse>() {
      @Override
      public void onSuccess(OrderDetailResponse value) {
        getView().hideLoading();
        getView().render(countryCodeList);
        getView().render(value);
      }

      @Override
      public void onError(Throwable error) {
        getView().hideLoading();
        getView().render(error);
      }
    }, new GetOrderDetail.Params(orderId)));
  }

  @Override
  public void add(Subscription subscription) {
    compositeSubscription.add(subscription);
  }

  public void getCountry(int orderId) {
    getView().showLoading("Getting the order detail...");
    add(getISOCountryCodes.execute(new SingleSubscriber<List<ISOCountry>>() {
      @Override
      public void onSuccess(List<ISOCountry> value) {
        OrderDetailsPresenter.this.countryCodeList = value;
        getOrderDetail(orderId);
      }

      @Override
      public void onError(Throwable error) {
        getView().hideLoading();
        getView().render(new Exception("Failed to load country codes"));
      }
    }));
  }

  public void getPermissionProfile(){
    getView().showLoading();
    add(getPermissionProfile.execute(new SingleSubscriber<List<PermissionProfileItem>>() {
      @Override
      public void onSuccess(List<PermissionProfileItem> value) {

        getView().renderListPermission(value);
        getView().hideLoading();
      }

      @Override
      public void onError(Throwable error) {

        getView().hideLoading();
      }
    }));
  }
}
