package com.skypremiuminternational.app.app.features.faq;

import com.skypremiuminternational.app.app.internal.mvp.contract.Presentable;
import com.skypremiuminternational.app.app.internal.mvp.contract.Viewable;
import com.skypremiuminternational.app.domain.models.permissions.PermissionCheckoutItem;

import java.util.List;



public interface FaqView<T extends Presentable> extends Viewable<T> {
  void render(FaqViewState viewState);
}
