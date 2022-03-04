package com.skypremiuminternational.app.domain.interactor.membership;

import com.skypremiuminternational.app.data.network.request.GetMyMembershipStatementRequest;
import com.skypremiuminternational.app.domain.DataManager;
import com.skypremiuminternational.app.domain.executor.PostExecutionThread;
import com.skypremiuminternational.app.domain.executor.ThreadExecutor;
import com.skypremiuminternational.app.domain.interactor.base.UseCase;
import com.skypremiuminternational.app.domain.models.mymembership_statement.ListMemberShipResponse;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class GetMembershipStatement extends UseCase<List<ListMemberShipResponse>, GetMyMembershipStatementRequest> {
  @Inject
  public GetMembershipStatement(DataManager dataManager, ThreadExecutor subscriberThread,
                                   PostExecutionThread observerThread) {
    super(dataManager, subscriberThread, observerThread);
  }

  @Override
  public Observable<List<ListMemberShipResponse>> provideObservable(GetMyMembershipStatementRequest getMyMembershipStatementRequest) {
    return getDataManager().getMyMembershipStatement(getMyMembershipStatementRequest);
  }
}
