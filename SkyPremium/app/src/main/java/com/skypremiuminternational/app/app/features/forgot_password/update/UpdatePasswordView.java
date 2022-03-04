package com.skypremiuminternational.app.app.features.forgot_password.update;

import com.skypremiuminternational.app.app.internal.mvp.contract.Presentable;
import com.skypremiuminternational.app.app.internal.mvp.contract.Viewable;



public interface UpdatePasswordView<T extends Presentable> extends Viewable<T> {

  void render(UpdatePasswordViewState viewState);
}
