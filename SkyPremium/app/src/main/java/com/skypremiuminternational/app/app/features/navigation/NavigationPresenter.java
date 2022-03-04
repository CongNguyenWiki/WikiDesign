package com.skypremiuminternational.app.app.features.navigation;

import com.skypremiuminternational.app.app.internal.mvp.BaseFragmentPresenter;
import com.skypremiuminternational.app.app.internal.mvp.BaseSubscriber;
import com.skypremiuminternational.app.data.InternalStorageManager;
import com.skypremiuminternational.app.data.network.request.UploadAvatarRequest;
import com.skypremiuminternational.app.domain.interactor.auth.PutLogout;
import com.skypremiuminternational.app.domain.interactor.user.ClearUserData;
import com.skypremiuminternational.app.domain.interactor.user.GetUserDetail;
import com.skypremiuminternational.app.domain.interactor.user.UploadUserAvatar;
import com.skypremiuminternational.app.domain.models.user.UploadAvatarResponse;
import com.skypremiuminternational.app.domain.models.user.UserDetailResponse;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import rx.SingleSubscriber;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

public class NavigationPresenter extends BaseFragmentPresenter<NavigationView> {

  private final CompositeSubscription compositeSubscription = new CompositeSubscription();
  private final InternalStorageManager internalStorageManager;
  private PutLogout putLogout;
  private ClearUserData clearUserData;
  UploadUserAvatar uploadUserAvatar;
  private GetUserDetail getUserDetail;



  @Inject
  public NavigationPresenter(InternalStorageManager internalStorageManager,ClearUserData clearUserData,
                             PutLogout putLogout,UploadUserAvatar uploadUserAvatar,
                             GetUserDetail getUserDetail
  ){
    this.internalStorageManager = internalStorageManager;
    this.putLogout = putLogout;
    this.clearUserData = clearUserData;
    this.uploadUserAvatar = uploadUserAvatar;
    this.getUserDetail = getUserDetail;
  }


  @Override
  public void onStop() {
    compositeSubscription.clear();
    super.onStop();
  }

  public void onLogout() {

    Subscription subscription = clearUserData.execute(new BaseSubscriber<Boolean>() {
      @Override
      public void onSuccess(Boolean value) {

      }
    });
    Subscription subscription1  = putLogout.execute(new SingleSubscriber<ResponseBody>() {
      @Override
      public void onSuccess(ResponseBody value) {

      }

      @Override
      public void onError(Throwable error) {

      }
    });
    compositeSubscription.add(subscription);
    compositeSubscription.add(subscription1);
  }

  void uploadImageToServer(String uploadImageUrl, String imageType, String imageName) {

    UploadAvatarRequest uploadAvatarRequest = buildRequest(uploadImageUrl, imageType, imageName);
    Subscription subscription =
            uploadUserAvatar.execute(new BaseSubscriber<UploadAvatarResponse>() {
              @Override
              public void onSuccess(UploadAvatarResponse value) {
                getUserDetailsFromApi();
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

  public void getUserDetailsLocal(){
    getView().renderUserDetail(internalStorageManager.getUserDetail());
  }



  public void getUserDetailsFromApi(){
    Subscription subscription = getUserDetail.execute(new BaseSubscriber<UserDetailResponse>() {
      @Override
      public void onSuccess(UserDetailResponse value) {
        internalStorageManager.saveUserDetail(value);
        getView().renderUserDetail(value);
      }

      @Override
      public void onError(Throwable error) {
        super.onError(error);
      }
    });
    compositeSubscription.add(subscription);


  }

  public InternalStorageManager getInternalStore(){
    return internalStorageManager;
  }


}
