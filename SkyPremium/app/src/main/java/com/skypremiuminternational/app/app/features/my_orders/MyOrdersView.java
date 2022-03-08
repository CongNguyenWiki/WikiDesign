package com.skypremiuminternational.app.app.features.my_orders;

import com.skypremiuminternational.app.app.internal.mvp.contract.Presentable;
import com.skypremiuminternational.app.app.internal.mvp.contract.Viewable;
import com.skypremiuminternational.app.domain.models.my_orders.MyOrderResponse;

public interface MyOrdersView<T extends Presentable> extends Viewable<T> {

  void renderMyOrder(MyOrderResponse response);

}
