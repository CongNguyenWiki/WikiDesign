package com.skypremiuminternational.app.domain.interactor.credit_card;

import com.skypremiuminternational.app.data.network.request.UpdateCreditCardRequest;
import com.skypremiuminternational.app.domain.DataManager;
import com.skypremiuminternational.app.domain.executor.PostExecutionThread;
import com.skypremiuminternational.app.domain.executor.ThreadExecutor;
import com.skypremiuminternational.app.domain.interactor.base.UseCase;
import com.skypremiuminternational.app.domain.models.creditCard.CardItem;

import javax.inject.Inject;

import rx.Observable;


public class UpdateCreditCard extends UseCase<CardItem, UpdateCreditCard.UpdateRequestCreditCardParams> {

  @Inject
  protected UpdateCreditCard(DataManager dataManager, ThreadExecutor subscriberThread, PostExecutionThread observerThread) {
    super(dataManager, subscriberThread, observerThread);
  }

  @Override
  public Observable<CardItem> provideObservable(UpdateCreditCard.UpdateRequestCreditCardParams request) {
    return getDataManager().updateCreditCard(request);
  }

  public static class UpdateRequestCreditCardParams{
    String cardId;
    UpdateCreditCardRequest updateCreditCardRequest;

    public UpdateRequestCreditCardParams() {
    }

    public String getCardId() {
      return cardId;
    }

    public void setCardId(String cardId) {
      this.cardId = cardId;
    }

    public UpdateCreditCardRequest getUpdateCreditCardRequest() {
      return updateCreditCardRequest;
    }

    public void setUpdateCreditCardRequest(UpdateCreditCardRequest updateCreditCardRequest) {
      this.updateCreditCardRequest = updateCreditCardRequest;
    }
  }
}