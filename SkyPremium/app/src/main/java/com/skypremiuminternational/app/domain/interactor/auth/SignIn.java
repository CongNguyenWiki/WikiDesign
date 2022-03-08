package com.skypremiuminternational.app.domain.interactor.auth;

import com.skypremiuminternational.app.data.network.request.SignInRequest;
import com.skypremiuminternational.app.domain.DataManager;
import com.skypremiuminternational.app.domain.executor.PostExecutionThread;
import com.skypremiuminternational.app.domain.executor.ThreadExecutor;
import com.skypremiuminternational.app.domain.interactor.base.UseCase;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import rx.Observable;
import rx.functions.Action1;

public class SignIn extends UseCase<ResponseBody, SignInRequest> {

  @Inject
  protected SignIn(DataManager dataManager, ThreadExecutor subscriberThread, PostExecutionThread observerThread) {
    super(dataManager, subscriberThread, observerThread);
  }

  @Override
  public Observable<ResponseBody> provideObservable(SignInRequest request) {
    return getDataManager().signIn(request).doOnNext(new Action1<ResponseBody>() {
      @Override
      public void call(ResponseBody responseBody) {
        try {
          String token = responseBody.string();
          getDataManager().saveUserToken(token);

        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });

  }
}
