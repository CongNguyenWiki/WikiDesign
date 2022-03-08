package com.skypremiuminternational.app.app.features.signin;

import com.skypremiuminternational.app.app.internal.mvp.BasePresenter;
import com.skypremiuminternational.app.app.internal.mvp.BaseSubscriber;
import com.skypremiuminternational.app.app.utils.PreferenceUtils;
import com.skypremiuminternational.app.data.InternalStorageManager;
import com.skypremiuminternational.app.data.network.request.SignInRequest;
import com.skypremiuminternational.app.domain.DataManager;
import com.skypremiuminternational.app.domain.interactor.auth.GetAppVersion;
import com.skypremiuminternational.app.domain.interactor.auth.PutLogin;
import com.skypremiuminternational.app.domain.interactor.auth.SignIn;
import com.skypremiuminternational.app.domain.interactor.category.GetCategoryFromApi;
import com.skypremiuminternational.app.domain.interactor.config.GetBackgroundLogin;
import com.skypremiuminternational.app.domain.interactor.user.GetUserDetail;
import com.skypremiuminternational.app.domain.models.category.CategoryResponse;
import com.skypremiuminternational.app.domain.models.config.BackgroundLogin;
import com.skypremiuminternational.app.domain.models.user.UserDetailResponse;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import rx.SingleSubscriber;
import rx.Subscription;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

public class SignInPresenter extends BasePresenter<SignInView> {

  CompositeSubscription compositeSubscription = new CompositeSubscription();




  final SignIn signIn;
  final PutLogin putLogin;
  final GetUserDetail getUserDetail;
  final GetBackgroundLogin getBackgroundLogin;
//  final GetCategoryFromApi getCategoryFromApi;
  final InternalStorageManager internalStorageManager;


  @Inject
  public SignInPresenter(GetAppVersion getAppVersion, InternalStorageManager internalStorageManager,
                         SignIn signIn,PutLogin putLogin,GetUserDetail getUserDetail,
                         GetBackgroundLogin getBackgroundLogin/*,GetCategoryFromApi getCategoryFromApi*/
                         ) {
    super(getAppVersion, internalStorageManager);

    this.signIn = signIn;
    this.putLogin = putLogin;
    this.getUserDetail = getUserDetail;
    this.getBackgroundLogin = getBackgroundLogin;
//    this.getCategoryFromApi = getCategoryFromApi;
    this.internalStorageManager = internalStorageManager;


  }

  @Override
  public void add(Subscription subscription) {
    compositeSubscription.add(subscription);
  }

  public void signIn(SignInRequest request){
    getView().showLoading();
    add(signIn.asObservable(request)
        .subscribe( responseBody -> {
          putLogin();
          getUserDetail();
    }, throwable -> {
      getView().showError(throwable);
      getView().hideLoading();
    }));
  }

  private void getUserDetail() {
    getView().showLoading();
    add(getUserDetail.execute(new SingleSubscriber<UserDetailResponse>() {
      @Override
      public void onSuccess(UserDetailResponse value) {
        getView().gotoLanding();
        getView().hideLoading();

      }

      @Override
      public void onError(Throwable error) {
        getView().hideLoading();
      }
    }));
  }

  public void putLogin(){
    add(putLogin.execute(new BaseSubscriber<ResponseBody>() {
      @Override
      public void onSuccess(ResponseBody response) {

      }

      @Override
      public void onError(Throwable error) {
      }
    }));
  }

//  public void getCategoryFromApi(){
//    Subscription subscription = getCategoryFromApi.execute(new SingleSubscriber<CategoryResponse>() {
//      @Override
//      public void onSuccess(CategoryResponse value) {
//
//
//
//      }
//
//      @Override
//      public void onError(Throwable error) {
//
//      }
//    });
//    compositeSubscription.add(subscription);
//
//
//  }



  public void getBackgroundLogin() {
    add(getBackgroundLogin.asObservable().subscribe(reponse -> {
      if(reponse.size()>0){

        getView().renderBackground(reponse.get(0).getBackgroundImage());
        internalStorageManager.saveBackgroungLogin(reponse);
      }
    },throwable -> {

    }));
  }

  List<BackgroundLogin> getBackgroundLoginLocal(){
    return internalStorageManager.getBackgroundLogin();
  }
}
