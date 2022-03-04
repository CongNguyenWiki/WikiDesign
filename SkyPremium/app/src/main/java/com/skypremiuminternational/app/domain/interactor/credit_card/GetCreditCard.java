package com.skypremiuminternational.app.domain.interactor.credit_card;

import com.skypremiuminternational.app.domain.DataManager;
import com.skypremiuminternational.app.domain.executor.PostExecutionThread;
import com.skypremiuminternational.app.domain.executor.ThreadExecutor;
import com.skypremiuminternational.app.domain.interactor.base.UseCase;
import com.skypremiuminternational.app.domain.models.creditCard.CreditCardResponse;

import javax.inject.Inject;

import rx.Observable;

public class GetCreditCard extends UseCase<CreditCardResponse, Void> {

  @Inject
  protected GetCreditCard(DataManager dataManager, ThreadExecutor subscriberThread,
                             PostExecutionThread observerThread) {
    super(dataManager, subscriberThread, observerThread);
  }

  @Override
  public Observable<CreditCardResponse> provideObservable(Void d) {
    return getDataManager().getCreditCard();
  }
}
