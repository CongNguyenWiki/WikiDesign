package com.skypremiuminternational.app.domain.interactor.membership;

import com.skypremiuminternational.app.domain.DataManager;
import com.skypremiuminternational.app.domain.executor.PostExecutionThread;
import com.skypremiuminternational.app.domain.executor.ThreadExecutor;
import com.skypremiuminternational.app.domain.interactor.base.UseCase;
import com.skypremiuminternational.app.domain.models.mymembership_statement.InforMembershipResponse;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class GetInfoMembership extends UseCase<List<InforMembershipResponse>,Void> {

  @Inject
  public GetInfoMembership(DataManager dataManager, ThreadExecutor subscriberThread,
                              PostExecutionThread observerThread) {
    super(dataManager, subscriberThread, observerThread);
  }

  @Override
  public Observable<List<InforMembershipResponse>> provideObservable(Void unused) {
    return getDataManager().getInfoMembership();
  }
}
