package com.skypremiuminternational.app.app.features.navigation;

import com.skypremiuminternational.app.app.internal.mvp.contract.Presentable;
import com.skypremiuminternational.app.app.internal.mvp.contract.Viewable;
import com.skypremiuminternational.app.domain.models.user.UserDetailResponse;

public interface NavigationView<T extends Presentable> extends Viewable<T> {

  void renderUserDetail(UserDetailResponse value);
}
