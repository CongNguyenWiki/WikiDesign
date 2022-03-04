package com.skypremiuminternational.app.domain.interactor.product;

import com.skypremiuminternational.app.data.network.request.GetFavouriteRequest;
import com.skypremiuminternational.app.domain.DataManager;
import com.skypremiuminternational.app.domain.executor.PostExecutionThread;
import com.skypremiuminternational.app.domain.executor.ThreadExecutor;
import com.skypremiuminternational.app.domain.interactor.base.UseCase;
import com.skypremiuminternational.app.domain.models.favourite.FavouriteItem;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
public class GetFavourites
        extends UseCase<List<FavouriteItem>, GetFavouriteRequest> {

  @Inject
  protected GetFavourites(DataManager dataManager, ThreadExecutor subscriberThread,
                          PostExecutionThread observerThread) {
    super(dataManager, subscriberThread, observerThread);
  }

  @Override
  public Observable<List<FavouriteItem>> provideObservable(GetFavouriteRequest request) {
    return getDataManager().getFavourites(request);
  }
}

