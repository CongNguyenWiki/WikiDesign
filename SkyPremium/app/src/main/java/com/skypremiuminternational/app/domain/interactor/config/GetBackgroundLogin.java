package com.skypremiuminternational.app.domain.interactor.config;

import com.skypremiuminternational.app.domain.DataManager;
import com.skypremiuminternational.app.domain.executor.PostExecutionThread;
import com.skypremiuminternational.app.domain.executor.ThreadExecutor;
import com.skypremiuminternational.app.domain.interactor.base.UseCase;
import com.skypremiuminternational.app.domain.models.config.BackgroundLogin;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class GetBackgroundLogin extends UseCase<List<BackgroundLogin>,Void> {

    @Inject
    protected GetBackgroundLogin(DataManager dataManager, ThreadExecutor subscriberThread, PostExecutionThread observerThread) {
        super(dataManager, subscriberThread, observerThread);
    }

    @Override
    public Observable<List<BackgroundLogin>> provideObservable(Void unused) {
        return getDataManager().getBackgroundLogin();
    }
}
