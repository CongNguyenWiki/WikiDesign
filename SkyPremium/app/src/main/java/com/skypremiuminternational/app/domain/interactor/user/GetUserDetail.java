package com.skypremiuminternational.app.domain.interactor.user;

import com.skypremiuminternational.app.domain.DataManager;
import com.skypremiuminternational.app.domain.executor.PostExecutionThread;
import com.skypremiuminternational.app.domain.executor.ThreadExecutor;
import com.skypremiuminternational.app.domain.interactor.base.UseCase;
import com.skypremiuminternational.app.domain.models.user.UserDetailResponse;
import com.skypremiuminternational.app.domain.utils.mapper.UserMapper;

import javax.inject.Inject;

import rx.Observable;



public class GetUserDetail extends UseCase<UserDetailResponse, Void> {




  @Inject
  protected GetUserDetail(DataManager dataManager,
                                 ThreadExecutor subscriberThread,
                                 PostExecutionThread observerThread
                                  ) {
    super(dataManager, subscriberThread, observerThread);
  }

  @Override
  public Observable<UserDetailResponse> provideObservable(Void avoid) {
    return getDataManager().getUserDetailFromAPI().map(UserMapper::flatInfo).map(response -> {
      getDataManager().saveUserDetails(response);
      return response;});
  }
}
