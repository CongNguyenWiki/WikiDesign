package com.skypremiuminternational.app.app.features.signin;

import com.skypremiuminternational.app.app.internal.mvp.contract.Presentable;
import com.skypremiuminternational.app.app.internal.mvp.contract.Viewable;

import okhttp3.ResponseBody;

public interface SignInView<T extends Presentable> extends Viewable<T> {


  void gotoLanding();

  void showError(Throwable throwable);

  void renderBackground(String url);
}
