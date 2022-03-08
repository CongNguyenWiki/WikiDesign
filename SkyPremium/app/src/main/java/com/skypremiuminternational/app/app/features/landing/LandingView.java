package com.skypremiuminternational.app.app.features.landing;

import com.skypremiuminternational.app.app.internal.mvp.contract.Presentable;
import com.skypremiuminternational.app.app.internal.mvp.contract.Viewable;
import com.skypremiuminternational.app.domain.models.gift_redeemtion.GiftPopUpResponse;

import java.util.List;

public interface LandingView<T extends Presentable> extends Viewable<T> {
  void updateCartCount(String count);
  void renderGiftPopupResponse(List<GiftPopUpResponse> giftPopUpResponse);
}
