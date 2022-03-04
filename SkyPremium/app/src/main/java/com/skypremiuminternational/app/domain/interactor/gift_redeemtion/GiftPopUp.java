package com.skypremiuminternational.app.domain.interactor.gift_redeemtion;



import com.skypremiuminternational.app.data.network.request.GiftPopUpRequest;
import com.skypremiuminternational.app.domain.DataManager;
import com.skypremiuminternational.app.domain.executor.PostExecutionThread;
import com.skypremiuminternational.app.domain.executor.ThreadExecutor;
import com.skypremiuminternational.app.domain.interactor.base.UseCase;
import com.skypremiuminternational.app.domain.models.gift_redeemtion.GiftPopUpResponse;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class GiftPopUp extends UseCase<List<GiftPopUpResponse>, GiftPopUpRequest> {

    @Inject
    protected GiftPopUp(DataManager dataManager, ThreadExecutor subscriberThread, PostExecutionThread observerThread) {
        super(dataManager, subscriberThread, observerThread);
    }


    @Override
    public Observable<List<GiftPopUpResponse>> provideObservable(GiftPopUpRequest giftPopUpRequest) {
        return getDataManager().showPopUp(giftPopUpRequest);
    }
}
