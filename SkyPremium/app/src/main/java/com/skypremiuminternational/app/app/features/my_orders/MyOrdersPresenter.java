package com.skypremiuminternational.app.app.features.my_orders;

import com.skypremiuminternational.app.app.internal.mvp.BasePresenter;
import com.skypremiuminternational.app.data.InternalStorageManager;
import com.skypremiuminternational.app.data.network.request.GetOrderHistoryRequest;
import com.skypremiuminternational.app.domain.interactor.auth.GetAppVersion;
import com.skypremiuminternational.app.domain.interactor.my_orders.GetMyOrders;
import com.skypremiuminternational.app.domain.models.my_orders.MyOrderResponse;

import javax.inject.Inject;

import rx.SingleSubscriber;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class MyOrdersPresenter extends BasePresenter<MyOrdersView> {


  final GetMyOrders getMyOrders;

  private GetOrderHistoryRequest filterRequest;
  InternalStorageManager internalStorageManager;



  CompositeSubscription compositeSubscription = new CompositeSubscription();

  @Inject
  public MyOrdersPresenter(GetAppVersion getAppVersion,
                           InternalStorageManager internalStorageManager,
                           GetMyOrders getMyOrders) {
    super(getAppVersion, internalStorageManager);
    this.getMyOrders = getMyOrders;
    this.internalStorageManager = internalStorageManager;
  }

  @Override
  public void add(Subscription subscription) {
    compositeSubscription.add(subscription);
  }



  public void getMyOrders() {

    add(getMyOrders.execute(new SingleSubscriber<MyOrderResponse>() {
      @Override
      public void onSuccess(MyOrderResponse value) {

        getView().renderMyOrder(value);
      }

      @Override
      public void onError(Throwable error) {


      }
    }, filterRequest));
  }

  InternalStorageManager  getInternalStorageManager(){
    return internalStorageManager;
  }


  public void setRequest(String filterSorting, String filterCategory) {
    if (filterCategory.equalsIgnoreCase("all")) {
      filterRequest = GetOrderHistoryRequest.getAll(filterSorting);
    } else {
      filterRequest = GetOrderHistoryRequest.getWithFILTER(filterCategory, filterSorting);
    }
  }



}
