package com.skypremiuminternational.app.domain.interactor.permission_profile;

import com.skypremiuminternational.app.domain.DataManager;
import com.skypremiuminternational.app.domain.executor.PostExecutionThread;
import com.skypremiuminternational.app.domain.executor.ThreadExecutor;
import com.skypremiuminternational.app.domain.interactor.base.UseCase;
import com.skypremiuminternational.app.domain.models.permissions.PermissionProfileItem;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class PermissionProfile extends UseCase<List<PermissionProfileItem>, Void> {

    @Inject
    protected PermissionProfile(DataManager dataManager, ThreadExecutor subscriberThread,
                                PostExecutionThread observerThread) {
        super(dataManager, subscriberThread, observerThread);
    }

    @Override
    public Observable<List<PermissionProfileItem>> provideObservable(Void avoid) {
        return getDataManager().getPermissionProfile();
    }
}