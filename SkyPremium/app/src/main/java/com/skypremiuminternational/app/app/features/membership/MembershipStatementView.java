package com.skypremiuminternational.app.app.features.membership;

import com.skypremiuminternational.app.app.internal.mvp.contract.Presentable;
import com.skypremiuminternational.app.app.internal.mvp.contract.Viewable;
import com.skypremiuminternational.app.domain.models.mymembership_statement.InforMembershipResponse;
import com.skypremiuminternational.app.domain.models.mymembership_statement.ListMemberShipResponse;
import com.skypremiuminternational.app.domain.models.mymembership_statement.MyMembershipStatementItem;
import com.skypremiuminternational.app.domain.models.user.UserDetailResponse;

import java.util.List;

public interface MembershipStatementView <T extends Presentable> extends Viewable<T> {

  void renderUserDetail(UserDetailResponse response);

  void renderMembershipStatemnet(List<MyMembershipStatementItem> responseList);

  void renderInfoMembership(List<InforMembershipResponse> responseList);

}
