package com.skypremiuminternational.app.app.features.my_orders.detail;

import com.skypremiuminternational.app.app.internal.mvp.contract.Presentable;
import com.skypremiuminternational.app.app.internal.mvp.contract.Viewable;
import com.skypremiuminternational.app.domain.models.ean.ISOCountry;
import com.skypremiuminternational.app.domain.models.my_orders.detail.OrderDetailResponse;
import com.skypremiuminternational.app.domain.models.permissions.PermissionProfileItem;


import java.util.List;



public interface OrderDetailsView<T extends Presentable> extends Viewable<T> {

  void showLoading(String s);

  void hideLoading();

  void render(Throwable error);

  void render(OrderDetailResponse orderDetail);

  void render(List<ISOCountry> countryCodeList);

  void renderListPermission(List<PermissionProfileItem> value);

}
