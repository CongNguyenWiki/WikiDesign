package com.skypremiuminternational.app.app.features.splash;




import com.skypremiuminternational.app.app.internal.mvp.BasePresenter;
import com.skypremiuminternational.app.data.InternalStorageManager;
import com.skypremiuminternational.app.domain.interactor.auth.GetAppVersion;
import com.skypremiuminternational.app.domain.interactor.category.GetCategoryFromApi;
import com.skypremiuminternational.app.domain.interactor.config.GetCountryCodes;
import com.skypremiuminternational.app.domain.interactor.config.GetNationalities;
import com.skypremiuminternational.app.domain.interactor.config.GetPhoneCodes;
import com.skypremiuminternational.app.domain.models.Version;
import com.skypremiuminternational.app.domain.models.category.CategoryResponse;
import com.skypremiuminternational.app.domain.models.country_code.CountryCode;
import com.skypremiuminternational.app.domain.models.nationality.Nationality;
import com.skypremiuminternational.app.domain.models.phone_code.PhoneCode;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Single;
import rx.SingleSubscriber;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import rx.subscriptions.Subscriptions;

public class SplashPresenter extends BasePresenter<SplashView> {


  final GetAppVersion getAppVersion;
  final InternalStorageManager internalStorageManager;
  final GetCountryCodes getCountryCodes;
  final GetNationalities getNationalities;
  final GetPhoneCodes getPhoneCodes;
  final GetCategoryFromApi getCategoryFromApi;


  CompositeSubscription compositeSubscription = new CompositeSubscription();

  @Inject
  public SplashPresenter(GetAppVersion getAppVersion, InternalStorageManager internalStorageManager,
                         GetCountryCodes getCountryCodes, GetNationalities getNationalities,
                         GetPhoneCodes getPhoneCodes, GetCategoryFromApi getCategoryFromApi) {
    super(getAppVersion, internalStorageManager);
    this.getAppVersion = getAppVersion;
    this.internalStorageManager = internalStorageManager;
    this.getPhoneCodes =  getPhoneCodes;
    this.getNationalities = getNationalities;
    this.getCountryCodes = getCountryCodes;
    this.getCategoryFromApi = getCategoryFromApi;
  }

  public void savePromptedVersion(String string){
    internalStorageManager.savePromptedVersion(string);
  }


  public void checkLogin(){
    if(!internalStorageManager.getUserAuthToken().isEmpty() && internalStorageManager.getUserDetail() != null ){
      getView().gotoLanding();
    } else {
      getView().gotoSignIn();
    }
  }

  public void getPromptedVersion(){
    add(getAppVersion.asObservable().subscribe(
        appVersion -> {
        getView();
    },
        error ->{

    }));

    add(getAppVersion.execute(new SingleSubscriber<Version.AppVersion>() {
      @Override
      public void onSuccess(Version.AppVersion value) {

      }

      @Override
      public void onError(Throwable error) {

      }
    }));

  }


//  void updateConfig(){
//      getNationalities();
//      getCountryCodes();
//      getPhoneCodes();
//  }


  void getAllData() {

    getView().showLoading();

    Observable<List<Nationality>> getNationalityResponse = getNationalities.asObservable();
    Observable<List<CountryCode>> getCountryCodeResponse = getCountryCodes.asObservable();
    Observable<PhoneCode> phoneCodeResponse = getPhoneCodes.asObservable();
    Observable<CategoryResponse> categoryResponse = getCategoryFromApi.asObservable();


    Observable.zip(getNationalityResponse, getCountryCodeResponse,phoneCodeResponse,categoryResponse, (nationalities, countryCode,phoneCode,category) -> {
      if (nationalities != null && countryCode != null && phoneCode != null && category != null){
        internalStorageManager.saveNationality(nationalities);
        internalStorageManager.saveCountryCodes(countryCode);
        internalStorageManager.savePhoneCodes(phoneCode);

        return true;
      }
      else {
        return false;
      }
    }).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Subscriber<Boolean>() {
              @Override
              public void onCompleted() {

              }

              @Override
              public void onError(Throwable error) {
                getView().hideLoading();
                getView().showError(error.getMessage());
                getView().render(false);
              }

              @Override
              public void onNext(Boolean value) {
                getView().hideLoading();
                getView().render(value);
              }
            });



  }

//  void getNationalities(){
//    add(getNationalities.asObservable().subscribe(response ->{
//      internalStorageManager.saveNationality(response);
//      //getView().updateProgress("National");
//    }, throwable -> {
//
//    }));
//  }
//
//  void getCountryCodes(){
//    add(getCountryCodes.asObservable().subscribe(response ->{
//      internalStorageManager.saveCountryCodes(response);
//
//      //getView().updateProgress("Country codes");
//
//    }, throwable -> {
//
//    }));
//  }
//
//  void getPhoneCodes(){
//    add(getPhoneCodes.asObservable().subscribe(response ->{
//      internalStorageManager.savePhoneCodes(response);
//
//      //getView().updateProgress("Phone codes");
//    }, throwable -> {
//
//    }));
//  }




  @Override
  public void add(Subscription subscription) {
    compositeSubscription.add(subscription);
  }
}
