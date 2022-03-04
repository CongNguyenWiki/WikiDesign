package com.skypremiuminternational.app.app.features.profile.my_profile;

import android.util.Log;

import com.skypremiuminternational.app.app.internal.mvp.BasePresenter;
import com.skypremiuminternational.app.app.internal.mvp.BaseSubscriber;
import com.skypremiuminternational.app.data.InternalStorageManager;
import com.skypremiuminternational.app.data.network.request.UploadAvatarRequest;
import com.skypremiuminternational.app.domain.interactor.auth.GetAppVersion;
import com.skypremiuminternational.app.domain.interactor.invite.GetInviteFriendDescription;
import com.skypremiuminternational.app.domain.interactor.user.ClearUserData;
import com.skypremiuminternational.app.domain.interactor.user.GetUserDetail;
import com.skypremiuminternational.app.domain.interactor.user.UploadUserAvatar;
import com.skypremiuminternational.app.domain.models.InviteFriendDescription;
import com.skypremiuminternational.app.domain.models.user.UploadAvatarResponse;
import com.skypremiuminternational.app.domain.models.user.UserDetailResponse;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.SingleSubscriber;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

public class ProfilePresenter extends BasePresenter<ProfileView> {

  CompositeSubscription compositeSubscription = new CompositeSubscription();
  GetUserDetail getUserDetail;
  ClearUserData clearUserData;
  UploadUserAvatar uploadUserAvatar;
  GetInviteFriendDescription getInviteFriendDescription;
  InternalStorageManager internalStorageManager;

  @Inject
  public ProfilePresenter(GetAppVersion getAppVersion, InternalStorageManager internalStorageManager,GetUserDetail getUserDetail,
                          ClearUserData clearUserData, UploadUserAvatar uploadUserAvatar,GetInviteFriendDescription getInviteFriendDescription
  ) {
    super(getAppVersion, internalStorageManager);
    this.getUserDetail = getUserDetail;
    this.clearUserData = clearUserData;
    this.uploadUserAvatar = uploadUserAvatar;
    this.getInviteFriendDescription = getInviteFriendDescription;
    this.internalStorageManager = internalStorageManager;

  }

  public void getUserDetailFromLocal(){
    getView().renderUserDetail(internalStorageManager.getUserDetail());
  }

  public void getUserDetailFromApi(){
    add(getUserDetail.execute(new SingleSubscriber<UserDetailResponse>() {
      @Override
      public void onSuccess(UserDetailResponse value) {
        internalStorageManager.saveUserDetail(value);
        getView().renderUserDetail(value);
      }

      @Override
      public void onError(Throwable error) {
        getView().showError(error);

      }
    }));
  }

  void uploadImageToServer(String uploadImageUrl, String imageType, String imageName) {

    UploadAvatarRequest uploadAvatarRequest = buildRequest(uploadImageUrl, imageType, imageName);
    Subscription subscription =
        uploadUserAvatar.execute(new BaseSubscriber<UploadAvatarResponse>() {
          @Override
          public void onSuccess(UploadAvatarResponse value) {
            getUserDetailFromApi();
          }

          @Override
          public void onError(Throwable error) {
            Timber.e(error);
          }
        }, uploadAvatarRequest);

    compositeSubscription.add(subscription);
  }

  private UploadAvatarRequest buildRequest(String uploadImageUrl, String imageType,
                                           String imageName) {

    UploadAvatarRequest.Value value =
        new UploadAvatarRequest.Value(uploadImageUrl, imageType, imageName);

    UploadAvatarRequest.CustomAttribute customAttribute =
        new UploadAvatarRequest.CustomAttribute("avatar", value);

    List<UploadAvatarRequest.CustomAttribute> customAttributeList = new ArrayList<>();
    customAttributeList.add(customAttribute);

    UploadAvatarRequest.Customer customer = new UploadAvatarRequest.Customer(customAttributeList);

    return new UploadAvatarRequest(customer);
  }

  public void getReferralCodeAndDescription() {
    String referralCode = internalStorageManager.getUserDetail().getReferralCode() != null ? internalStorageManager.getUserDetail().getReferralCode() : "";

    Log.d("referralCode",referralCode);
    getView().showLoading();
    add(getInviteFriendDescription.execute(new SingleSubscriber<InviteFriendDescription>() {
      @Override
      public void onSuccess(InviteFriendDescription value) {
        getView().hideLoading();

        getView().goToInviteFriend(referralCode, internalStorageManager.getUserDetail().getSalutation(),
            internalStorageManager.getUserDetail().getFirstname(), internalStorageManager.getUserDetail().getLastname(),
            value.description, value.image, value.url);
      }

      @Override
      public void onError(Throwable error) {
        getView().hideLoading();
        getView().showError(error);
      }
    }));
  }





  public void onLogout() {

    Subscription subscription = clearUserData.execute(new BaseSubscriber<Boolean>() {
      @Override
      public void onSuccess(Boolean value) {

      }
    });
    compositeSubscription.add(subscription);
  }


  @Override
  public void add(Subscription subscription) {
    compositeSubscription.add(subscription);
  }
}
