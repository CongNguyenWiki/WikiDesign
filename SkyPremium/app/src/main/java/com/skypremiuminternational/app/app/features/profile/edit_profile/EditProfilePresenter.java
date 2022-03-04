package com.skypremiuminternational.app.app.features.profile.edit_profile;

import android.util.Log;

import com.skypremiuminternational.app.app.internal.mvp.BasePresenter;
import com.skypremiuminternational.app.data.InternalStorageManager;
import com.skypremiuminternational.app.data.network.request.UpdateUserRequest;
import com.skypremiuminternational.app.domain.interactor.auth.GetAppVersion;
import com.skypremiuminternational.app.domain.interactor.user.GetUserDetail;
import com.skypremiuminternational.app.domain.interactor.user.UpdateUserDetails;
import com.skypremiuminternational.app.domain.models.country_code.CountryCode;
import com.skypremiuminternational.app.domain.models.nationality.Nationality;
import com.skypremiuminternational.app.domain.models.phone_code.PhoneCode;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class EditProfilePresenter extends BasePresenter<EditProfileView> {


  final GetUserDetail getUserDetaill;
  final UpdateUserDetails updateUserDetails;
  final InternalStorageManager internalStorageManager;

  CompositeSubscription compositeSubscription = new CompositeSubscription();

  @Inject
  public EditProfilePresenter(GetAppVersion getAppVersion,
                              InternalStorageManager internalStorageManager,
                              GetUserDetail getUserDetail, UpdateUserDetails updateUserDetails) {
    super(getAppVersion, internalStorageManager);

    this.getUserDetaill = getUserDetail;
    this.updateUserDetails = updateUserDetails;
    this.internalStorageManager = internalStorageManager;
  }

  @Override
  public void add(Subscription subscription) {
    compositeSubscription.add(subscription);
  }



  void getUserInfo(){

    getView().showLoading();
    add(getUserDetaill.asObservable().subscribe(
        userDetailResponse -> {
          getView().hideLoading();
          getView().renderUserDetail(userDetailResponse);
        },
        throwable -> {
          getView().hideLoading();
        }
    ));
  }


  void updateUserDetails(UpdateUserRequest request){
    getView().showLoading();
    add(updateUserDetails.asObservable(request).subscribe(userDetailsResponse ->{
      getView().hideLoading();
      getView().showToast("Update successfully.");
      getView().updateUserProfileSuccess();
    }, throwable -> {
      getView().hideLoading();

      getView().showToast("Update user details failed.");
      Log.d("EDIT_PROFILE","update user details : " + throwable.getMessage());
    }));
  }

  public void getZoneConfig() {
    List<CountryCode> countryCodes =  internalStorageManager.getCountryCodes();
    List<Nationality> nationalities =  internalStorageManager.getNationality();
    PhoneCode phoneCodes =  internalStorageManager.getPhoneCodes();

    if (countryCodes != null && nationalities !=null && phoneCodes != null) {
      getView().returnZoneConfig(countryCodes,nationalities,phoneCodes);
    }
  }
}
