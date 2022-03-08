package com.skypremiuminternational.app.domain.interactor.user;

import com.skypremiuminternational.app.data.network.request.UpdateUserRequest;
import com.skypremiuminternational.app.domain.DataManager;
import com.skypremiuminternational.app.domain.executor.PostExecutionThread;
import com.skypremiuminternational.app.domain.executor.ThreadExecutor;
import com.skypremiuminternational.app.domain.interactor.base.UseCase;
import com.skypremiuminternational.app.domain.models.user.UserDetailResponse;

import javax.inject.Inject;

import rx.Observable;

public class UpdateUserDetails extends UseCase<UserDetailResponse, UpdateUserRequest> {

  @Inject
  protected UpdateUserDetails(DataManager dataManager, ThreadExecutor subscriberThread, PostExecutionThread observerThread) {
    super(dataManager, subscriberThread, observerThread);
  }

  @Override
  public Observable<UserDetailResponse> provideObservable(UpdateUserRequest request) {
    return getDataManager().updateUserDetail(request);
  }
}
