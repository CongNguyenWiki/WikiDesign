package com.skypremiuminternational.app.app.features.landing;

import com.skypremiuminternational.app.app.internal.mvp.BasePresenter;
import com.skypremiuminternational.app.app.internal.mvp.BaseSubscriber;
import com.skypremiuminternational.app.data.InternalStorageManager;
import com.skypremiuminternational.app.data.network.request.GiftPopUpRequest;
import com.skypremiuminternational.app.domain.DataManager;
import com.skypremiuminternational.app.domain.interactor.auth.GetAppVersion;
import com.skypremiuminternational.app.domain.interactor.cart.GetCartCount;
import com.skypremiuminternational.app.domain.interactor.gift_redeemtion.GiftPopUp;
import com.skypremiuminternational.app.domain.interactor.rating_comment.GetRatingOption;
import com.skypremiuminternational.app.domain.models.gift_redeemtion.GiftPopUpResponse;

import java.util.List;

import javax.inject.Inject;

import rx.SingleSubscriber;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

public class LandingPresenter extends BasePresenter<LandingView> {

  private final GetRatingOption getRatingOption;
  private final GetCartCount getCartCount;
  private final GiftPopUp giftPopUp;
  DataManager dataManager;


  CompositeSubscription compositeSubscription = new CompositeSubscription();

  InternalStorageManager internalStorageManager;

  @Inject
  public LandingPresenter(GetAppVersion getAppVersion,
                          InternalStorageManager internalStorageManager,
                          DataManager dataManager,
                          GetRatingOption getRatingOption,
                          GetCartCount getCartCount,
                          GiftPopUp giftPopUp) {
    super(getAppVersion, internalStorageManager);
    this.internalStorageManager = internalStorageManager;
    this.dataManager = dataManager;
    this.getRatingOption = getRatingOption;
    this.getCartCount = getCartCount;
    this.giftPopUp = giftPopUp;
  }

  @Override
  public void add(Subscription subscription) {
    compositeSubscription.add(subscription);
  }

  public void getRatingOption() {
    add(getRatingOption.asObservable()
        .subscribe(s -> {
          dataManager.saveRatingOption(s);
        }, Timber::e));
  }

  public void getCartCount() {
    add(getCartCount.asObservable()
        .subscribe(s -> getView().updateCartCount(s), Timber::e));
  }

  public void showPopUp(int userId, int storeId){
    GiftPopUpRequest giftPopUpRequest = new GiftPopUpRequest(userId,storeId);

    add(giftPopUp.execute(new SingleSubscriber<List<GiftPopUpResponse>>() {
      @Override
      public void onSuccess(List<GiftPopUpResponse> value) {
        getView().renderGiftPopupResponse(value);
      }

      @Override
      public void onError(Throwable error) {
          error.printStackTrace();
      }
    },giftPopUpRequest));
  }




  String getCustomerToken(){
    return internalStorageManager.getUserAuthToken();
  }

  DataManager getDataManager(){
    return  this.dataManager;
  }
}
