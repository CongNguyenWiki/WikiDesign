package com.skypremiuminternational.app.app.features.product;

import com.skypremiuminternational.app.app.internal.mvp.contract.Presentable;
import com.skypremiuminternational.app.app.internal.mvp.contract.Viewable;
import com.skypremiuminternational.app.domain.models.products.ProductItem;

import java.util.List;

public interface ProductView<T extends Presentable> extends Viewable<T> {


  void renderProduct(List<ProductItem> productItems);
}
