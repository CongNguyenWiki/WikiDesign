package com.skypremiuminternational.app.domain.interactor.my_reservation;

import com.skypremiuminternational.app.domain.DataManager;
import com.skypremiuminternational.app.domain.executor.PostExecutionThread;
import com.skypremiuminternational.app.domain.executor.ThreadExecutor;
import com.skypremiuminternational.app.domain.interactor.base.UseCase;
import com.skypremiuminternational.app.domain.models.reservation.ReserveHistoryRespone;

import javax.inject.Inject;

import rx.Observable;

public class GetHistoryAll extends UseCase<ReserveHistoryRespone, GetHistoryAll.Params> {

  @Inject
  protected GetHistoryAll(DataManager dataManager, ThreadExecutor subscriberThread,
                          PostExecutionThread observerThread) {
    super(dataManager, subscriberThread, observerThread);
  }

  @Override
  public Observable<ReserveHistoryRespone> provideObservable(Params params) {
    return getDataManager().getHistoryAll(params.sortBy,params.currentPage,params.pageSize);
  }

  public static class Params{
    String currentPage;
    String pageSize;
    String sortBy;

    public Params(String sortBy,String currentPage, String pageSize) {
      this.currentPage = currentPage;
      this.pageSize = pageSize;
      this.sortBy = sortBy;
    }
  }
}
