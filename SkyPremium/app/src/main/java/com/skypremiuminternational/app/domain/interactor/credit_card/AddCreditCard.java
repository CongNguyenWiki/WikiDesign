package com.skypremiuminternational.app.domain.interactor.credit_card;

import com.skypremiuminternational.app.data.network.request.AddCreditCardRequest;
import com.skypremiuminternational.app.domain.DataManager;
import com.skypremiuminternational.app.domain.executor.PostExecutionThread;
import com.skypremiuminternational.app.domain.executor.ThreadExecutor;
import com.skypremiuminternational.app.domain.interactor.base.UseCase;
import com.skypremiuminternational.app.domain.models.creditCard.CardItem;

import javax.inject.Inject;

import rx.Observable;


public class AddCreditCard extends UseCase<CardItem, AddCreditCardRequest> {

  @Inject
  protected AddCreditCard(DataManager dataManager, ThreadExecutor subscriberThread, PostExecutionThread observerThread) {
    super(dataManager, subscriberThread, observerThread);
  }

  @Override
  public Observable<CardItem> provideObservable(AddCreditCardRequest request) {
    return getDataManager().addCreditCard(request);
  }
}
