package com.skypremiuminternational.app.domain.interactor.credit_card;

import com.skypremiuminternational.app.domain.DataManager;
import com.skypremiuminternational.app.domain.executor.PostExecutionThread;
import com.skypremiuminternational.app.domain.executor.ThreadExecutor;
import com.skypremiuminternational.app.domain.interactor.base.UseCase;
import com.skypremiuminternational.app.domain.models.creditCard.GetFormDataCreditCardResponse;

import javax.inject.Inject;

import rx.Observable;


public class GetFormDataCreditCard extends UseCase<GetFormDataCreditCardResponse, Void> {

  @Inject
  protected GetFormDataCreditCard(DataManager dataManager, ThreadExecutor subscriberThread,
                          PostExecutionThread observerThread) {
    super(dataManager, subscriberThread, observerThread);
  }

  @Override
  public Observable<GetFormDataCreditCardResponse> provideObservable(Void d) {
    return getDataManager().getFormDataCreditCard();
  }
}
