package com.skypremiuminternational.app.app.features.profile.my_profile;

import com.skypremiuminternational.app.app.internal.mvp.contract.Presentable;
import com.skypremiuminternational.app.app.internal.mvp.contract.Viewable;
import com.skypremiuminternational.app.domain.models.user.UserDetailResponse;

public interface ProfileView<T extends Presentable> extends Viewable<T>{

  void renderUserDetail(UserDetailResponse userDetailResponse);

  void goToInviteFriend(String code, String salutation, String firstname, String referralCode,
                        String description,String imgBannerUrl, String url);

  void showError(Throwable throwable);
}
