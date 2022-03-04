package com.skypremiuminternational.app.app.features.membership;

import com.skypremiuminternational.app.app.internal.mvp.BasePresenter;
import com.skypremiuminternational.app.app.internal.mvp.BaseSubscriber;
import com.skypremiuminternational.app.data.InternalStorageManager;
import com.skypremiuminternational.app.data.network.request.GetMyMembershipStatementRequest;
import com.skypremiuminternational.app.domain.interactor.auth.GetAppVersion;
import com.skypremiuminternational.app.domain.interactor.membership.GetInfoMembership;
import com.skypremiuminternational.app.domain.interactor.membership.GetMembershipStatement;
import com.skypremiuminternational.app.domain.interactor.user.GetUserDetail;
import com.skypremiuminternational.app.domain.models.mymembership_statement.ListMemberShipResponse;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class MembershipStatementPresenter extends BasePresenter<MembershipStatementView> {


  private GetMembershipStatement getMembershipStatement;
  private GetInfoMembership getInfoMembership;
  private GetUserDetail getUserDetail;
  private GetMyMembershipStatementRequest filterRequest;

  CompositeSubscription compositeSubscription = new CompositeSubscription();

  @Inject
  public MembershipStatementPresenter(GetAppVersion getAppVersion,
                                      InternalStorageManager internalStorageManager,
                                      GetMembershipStatement getMembershipStatement,
                                      GetInfoMembership getInfoMembership,
                                      GetUserDetail getUserDetail
                                      ) {
    super(getAppVersion, internalStorageManager);

    this.getMembershipStatement = getMembershipStatement;
    this.getInfoMembership = getInfoMembership;
    this.getUserDetail = getUserDetail;
  }

  @Override
  public void add(Subscription subscription) {
    compositeSubscription.add(subscription);
  }



  public void getUserDetails(){
    add(getUserDetail.asObservable().subscribe(response -> {
      getView().renderUserDetail(response);
    },throwable -> {

    }));
  }

  public void getMembershipStatement(){

    add(getMembershipStatement.execute(new BaseSubscriber<List<ListMemberShipResponse>>() {
      @Override
      public void onSuccess(List<ListMemberShipResponse> value) {


        getView().renderMembershipStatemnet(value.get(0).getData());
      }

      @Override
      public void onError(Throwable error) {
        super.onError(error);
      }
    },filterRequest));

  }

  public void getInfoMembership(){
    add(getInfoMembership.asObservable().subscribe(inforMembershipResponses -> {
      getView().renderInfoMembership(inforMembershipResponses);
    }, throwable -> {

    }));
  }

  public void setRequest(String filterSorting, String filterCategory,String memberNumber,String limit,String currentPage) {

    filterRequest = GetMyMembershipStatementRequest.getWithFILTER(filterCategory, filterSorting,memberNumber,limit,currentPage);

  }

}
