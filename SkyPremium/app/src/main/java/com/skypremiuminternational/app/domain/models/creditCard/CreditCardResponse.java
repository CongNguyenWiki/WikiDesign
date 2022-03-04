package com.skypremiuminternational.app.domain.models.creditCard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class CreditCardResponse implements Serializable {

  @SerializedName("cards")
  @Expose
  private List<CardItem> cards;

  public List<CardItem> getCards() {
    return cards;
  }

  public void setCards(List<CardItem> cards) {
    this.cards = cards;
  }
}
