package com.skypremiuminternational.app.app.features.profile.manage_credit_card;

import android.util.Log;

import com.skypremiuminternational.app.app.internal.mvp.BasePresenter;
import com.skypremiuminternational.app.data.InternalStorageManager;
import com.skypremiuminternational.app.data.network.request.AddCreditCardRequest;
import com.skypremiuminternational.app.data.network.request.UpdateCreditCardRequest;
import com.skypremiuminternational.app.domain.interactor.auth.GetAppVersion;
import com.skypremiuminternational.app.domain.interactor.credit_card.AddCreditCard;
import com.skypremiuminternational.app.domain.interactor.credit_card.DeleteCreditCard;
import com.skypremiuminternational.app.domain.interactor.credit_card.GetCreditCard;


import com.skypremiuminternational.app.domain.interactor.credit_card.GetFormDataCreditCard;
import com.skypremiuminternational.app.domain.interactor.credit_card.UpdateCreditCard;
import com.skypremiuminternational.app.domain.models.creditCard.CardItem;
import com.skypremiuminternational.app.domain.models.creditCard.CreditCardResponse;
import com.skypremiuminternational.app.domain.models.creditCard.GetFormDataCreditCardResponse;


import java.io.IOException;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import rx.SingleSubscriber;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;


public class ManageCreditCardPresenter extends BasePresenter<ManageCreditCardView> {


  private CompositeSubscription compositeSubscription = new CompositeSubscription();
  GetCreditCard getCreditCard;
  GetFormDataCreditCard getFormDataCreditCard;
  AddCreditCard addCreditCard;
  UpdateCreditCard updateCreditCard;
  DeleteCreditCard deleteCreditCard;

  @Inject
  public ManageCreditCardPresenter(GetAppVersion getAppVersion,
                                   InternalStorageManager internalStorageManager,
                                   GetCreditCard getCreditCard,
                                   GetFormDataCreditCard getFormDataCreditCard,
                                   AddCreditCard addCreditCard,
                                   UpdateCreditCard updateCreditCard,
                                   DeleteCreditCard deleteCreditCard) {
    super(getAppVersion, internalStorageManager);
    this.getCreditCard = getCreditCard;
    this.getFormDataCreditCard = getFormDataCreditCard;
    this.addCreditCard = addCreditCard;
    this.updateCreditCard = updateCreditCard;
    this.deleteCreditCard = deleteCreditCard;
  }

  @Override
  public void add(Subscription subscription) {
    compositeSubscription.add(subscription);
  }

  @Override
  public void onStop() {
    compositeSubscription.clear();
    super.onStop();
  }


  public void getListCreditCards() {
    getView().showLoading("Getting Credit Cards...");
    add(getCreditCard.execute(new SingleSubscriber<CreditCardResponse>() {
      @Override
      public void onSuccess(CreditCardResponse value) {
        getView().hideLoading();
        getView().render(value.getCards());
      }

      @Override
      public void onError(Throwable error) {
        getView().hideLoading();
        getView().render(new Exception("Failed to load credit cards"));
      }
    }));
  }

  public void getListFormDataCreditCards(CardItem item) {
    getView().showLoading("Getting Form Data Credit Cards...");
    add(getFormDataCreditCard.execute(new SingleSubscriber<GetFormDataCreditCardResponse>() {
      @Override
      public void onSuccess(GetFormDataCreditCardResponse value) {
        getView().hideLoading();
        getView().renderFormData(item,value);
      }

      @Override
      public void onError(Throwable error) {
        getView().hideLoading();
        getView().render(new Exception("Failed to load form data credit cards"));
      }
    }));
  }

  public void addCreditCards(CardItem creditCard) {

    AddCreditCardRequest.CardItem addCardRequest = new AddCreditCardRequest.CardItem();
    addCardRequest.setBrand(creditCard.getBrand());
    addCardRequest.setCardNumber(creditCard.getCard_number());
    addCardRequest.setCardCvc(creditCard.getCard_cvc());
    addCardRequest.setExpMonth(creditCard.getExpMonth());
    addCardRequest.setExpYear(creditCard.getExpYear());
    addCardRequest.setName(creditCard.getName());
    addCardRequest.setCountry(creditCard.getCountry());
    addCardRequest.setStateRegionId(creditCard.getStateRegionId());
    addCardRequest.setState(creditCard.getState());
    addCardRequest.setPostalCode(creditCard.getPostalCode());
    addCardRequest.setCity(creditCard.getCity());
    addCardRequest.setAddressStreet1(creditCard.getAddressStreet1());
    addCardRequest.setAddressStreet2(creditCard.getAddressStreet2());
    addCardRequest.setDefault(creditCard.getDefault());

    AddCreditCardRequest addRequest = new AddCreditCardRequest(addCardRequest);

    getView().showLoading("Adding Credit Cards...");
    add(addCreditCard.execute(new SingleSubscriber<CardItem>() {
      @Override
      public void onSuccess(CardItem value) {
        getView().hideAddOrUpdateCreditCardDialog();
        getListCreditCards();
      }

      @Override
      public void onError(Throwable error) {
        getView().hideLoading();
        getView().render(new Exception(error));
        Log.d("Request", "" + addRequest);
      }
    },addRequest));



  }

  public void updateCreditCard(CardItem creditCard) {

    UpdateCreditCardRequest.CardItem addCardRequest = new UpdateCreditCardRequest.CardItem();
    addCardRequest.setExpMonth(creditCard.getExpMonth());
    addCardRequest.setExpYear(creditCard.getExpYear());
    addCardRequest.setName(creditCard.getName());
    addCardRequest.setCountry(creditCard.getCountry());
    addCardRequest.setStateRegionId(creditCard.getStateRegionId());
    addCardRequest.setState(creditCard.getState());
    addCardRequest.setPostalCode(creditCard.getPostalCode());
    addCardRequest.setCity(creditCard.getCity());
    addCardRequest.setAddressStreet1(creditCard.getAddressStreet1());
    addCardRequest.setAddressStreet2(creditCard.getAddressStreet2());
    addCardRequest.setDefault(creditCard.getDefault());

    UpdateCreditCardRequest updateRequest = new UpdateCreditCardRequest(addCardRequest);

    UpdateCreditCard.UpdateRequestCreditCardParams request = new UpdateCreditCard.UpdateRequestCreditCardParams();
    request.setCardId(creditCard.getId());
    request.setUpdateCreditCardRequest(updateRequest);

    getView().showLoading("Updating Credit Cards...");
    add(updateCreditCard.execute(new SingleSubscriber<CardItem>() {
      @Override
      public void onSuccess(CardItem value) {
        getView().hideAddOrUpdateCreditCardDialog();
        getListCreditCards();
      }

      @Override
      public void onError(Throwable error) {
        getView().hideLoading();
        getView().render(new Exception(error));
      }
    },request));

  }

  public void deleteCreditCards(String cardId) {
    getView().showLoading("Delete Credit Cards...");
    deleteCreditCard.execute(new SingleSubscriber<ResponseBody>() {
      @Override
      public void onSuccess(ResponseBody value) {

        String reponse = null;
        try {
          reponse = value.string();

          if (reponse.equalsIgnoreCase("true")){
            getView().hideAddOrUpdateCreditCardDialog();
            getListCreditCards();
          }
          else {
            getView().hideLoading();
            getView().render(new Exception(reponse));
          }

        } catch (IOException e) {
          e.printStackTrace();
        }


      }

      @Override
      public void onError(Throwable error) {
        getView().hideLoading();
        getView().render(new Exception(error));
      }
    },cardId);



  }


}
