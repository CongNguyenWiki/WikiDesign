package com.skypremiuminternational.app.app.features.splash;

import com.skypremiuminternational.app.app.internal.mvp.contract.Presentable;
import com.skypremiuminternational.app.app.internal.mvp.contract.Viewable;

public interface SplashView<T extends Presentable> extends Viewable<T> {


  void gotoLanding();

  void gotoSignIn();

//  void updateProgress(String progressName);

  void render(Boolean value);

  void showError(String error);
}
