package com.skypremiuminternational.app.app.features.forgot_password.update;

import com.skypremiuminternational.app.app.internal.mvp.BasePresenter;
import com.skypremiuminternational.app.app.internal.mvp.BaseSubscriber;
import com.skypremiuminternational.app.data.InternalStorageManager;
import com.skypremiuminternational.app.data.network.request.PasswordHashRequest;
import com.skypremiuminternational.app.data.network.request.UpdatePasswordRequest;
import com.skypremiuminternational.app.domain.interactor.auth.GetAppVersion;
import com.skypremiuminternational.app.domain.interactor.auth.GetPasswordHash;
import com.skypremiuminternational.app.domain.interactor.auth.UpdatePassword;
import com.skypremiuminternational.app.domain.interactor.user.GetUserDetail;
import com.skypremiuminternational.app.domain.models.CustomAttribute;
import com.skypremiuminternational.app.domain.models.user.UserDetailResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class UpdatePasswordPresenter extends BasePresenter<UpdatePasswordView> {

  private GetUserDetail getUserDetail;

  private UpdatePassword updatePassword;

  UpdatePasswordViewState viewState;

  private CompositeSubscription compositeSubscription = new CompositeSubscription();

  @Inject
  public UpdatePasswordPresenter(GetAppVersion getAppVersion, InternalStorageManager internalStorageManager,
                                 GetUserDetail getUserDetail, UpdatePassword updatePassword) {
    super(getAppVersion, internalStorageManager);
    viewState = UpdatePasswordViewState.builder()
            .error(null)
            .isLoading(false)
            .isSuccess(false)
            .message(null)
            .userDetailResponse(null)
            .build();
    this.getUserDetail = getUserDetail;
    this.updatePassword = updatePassword;
    attachLoading(updatePassword);
  }

  @Override
  public void onStop() {
    compositeSubscription.clear();
    super.onStop();
  }

  @Override
  public void add(Subscription subscription) {
    compositeSubscription.add(subscription);
  }

  public void getUserDetail() {

    Subscription subscription = getUserDetail.execute(new BaseSubscriber<UserDetailResponse>() {
      @Override
      public void onSuccess(UserDetailResponse response) {
        viewState = UpdatePasswordViewState.builder()
                .error(null)
                .isLoading(false)
                .isSuccess(false)
                .message(null)
                .userDetailResponse(response)
                .build();

        getView().render(viewState);
      }

      @Override
      public void onError(Throwable error) {
        super.onError(error);
        error.printStackTrace();
        viewState = UpdatePasswordViewState.builder()
                .error(error)
                .isLoading(false)
                .isSuccess(false)
                .message(null)
                .userDetailResponse(null)
                .build();

        getView().render(viewState);
      }
    });

    compositeSubscription.add(subscription);
  }



  public void updatePassword(UserDetailResponse userDetailResponse,String currentPassword, String newPassword) {

    viewState = UpdatePasswordViewState.builder()
            .error(null)
            .isLoading(true)
            .isSuccess(false)
            .message(null)
            .userDetailResponse(viewState.userDetailResponse())
            .build();
    getView().render(viewState);



    UpdatePasswordRequest updatePasswordRequest =
            new UpdatePasswordRequest(currentPassword,newPassword);




    Subscription subscription = updatePassword.execute(new BaseSubscriber<ResponseBody>() {
      @Override
      public void onSuccess(ResponseBody response) {
        viewState = UpdatePasswordViewState.builder()
                .error(null)
                .isLoading(false)
                .isSuccess(true)
                .message(response.toString())
                .userDetailResponse(viewState.userDetailResponse())
                .build();
        getView().render(viewState);
      }

      @Override
      public void onError(Throwable error) {
        super.onError(error);
        error.printStackTrace();
        viewState = UpdatePasswordViewState.builder()
                .error(error)
                .isLoading(false)
                .isSuccess(false)
                .message("")
                .userDetailResponse(viewState.userDetailResponse())
                .build();
        getView().render(viewState);
      }
    }, updatePasswordRequest);
    compositeSubscription.add(subscription);
  }
}
