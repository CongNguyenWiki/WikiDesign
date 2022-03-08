package com.skypremiuminternational.app.domain.interactor.product;

import com.skypremiuminternational.app.domain.DataManager;
import com.skypremiuminternational.app.domain.executor.PostExecutionThread;
import com.skypremiuminternational.app.domain.executor.ThreadExecutor;
import com.skypremiuminternational.app.domain.interactor.base.UseCase;
import com.skypremiuminternational.app.domain.models.products.DetailsItem;


import javax.inject.Inject;

import rx.Observable;



public class GetProductDetails extends UseCase<DetailsItem,String> {



  @Inject
  protected GetProductDetails(DataManager dataManager, ThreadExecutor subscriberThread, PostExecutionThread observerThread) {
    super(dataManager, subscriberThread, observerThread);
  }



  @Override
  public Observable<DetailsItem> provideObservable(String sku) {
    return getDataManager().getProductDetails(sku);
  }
}
