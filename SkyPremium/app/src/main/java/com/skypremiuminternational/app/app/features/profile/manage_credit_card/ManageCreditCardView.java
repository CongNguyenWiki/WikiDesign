package com.skypremiuminternational.app.app.features.profile.manage_credit_card;

import com.skypremiuminternational.app.app.internal.mvp.contract.Presentable;
import com.skypremiuminternational.app.app.internal.mvp.contract.Viewable;
import com.skypremiuminternational.app.domain.models.creditCard.CardItem;
import com.skypremiuminternational.app.domain.models.creditCard.GetFormDataCreditCardResponse;


import java.util.List;



public interface ManageCreditCardView<T extends Presentable> extends Viewable<T> {

  void render(List<CardItem> response);

  void renderFormData(CardItem cardItem, GetFormDataCreditCardResponse response);

  void hideAddOrUpdateCreditCardDialog();

  void showLoading(String s);

  void render(Throwable error);



}
