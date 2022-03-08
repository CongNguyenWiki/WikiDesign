package com.skypremiuminternational.app.app.features.shopping_cart;

import com.skypremiuminternational.app.app.internal.mvp.contract.Presentable;
import com.skypremiuminternational.app.app.internal.mvp.contract.Viewable;
import com.skypremiuminternational.app.domain.models.cart.CartDetailItem;
import com.skypremiuminternational.app.domain.models.cart.CartDetailResponse;

import java.util.List;


public interface ShoppingCartView<T extends Presentable> extends Viewable<T> {

  void renderListCart(CartDetailResponse cartDetailResponse);

  void renderError(String message);

}