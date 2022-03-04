package com.skypremiuminternational.app.app.features.product.detail;

import com.skypremiuminternational.app.app.internal.mvp.contract.Presentable;
import com.skypremiuminternational.app.app.internal.mvp.contract.Viewable;
import com.skypremiuminternational.app.domain.models.products.DetailsItem;


import java.util.List;

public interface ProductDetailView<T extends Presentable> extends Viewable<T> {
  void renderProductDetails(DetailsItem detailsItem);
}
