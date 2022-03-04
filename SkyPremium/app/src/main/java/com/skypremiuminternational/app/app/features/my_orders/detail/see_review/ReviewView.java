package com.skypremiuminternational.app.app.features.my_orders.detail.see_review;

import com.skypremiuminternational.app.app.internal.mvp.contract.Presentable;
import com.skypremiuminternational.app.app.internal.mvp.contract.Viewable;
import com.skypremiuminternational.app.domain.models.comment_rating.RatingResult;
import com.skypremiuminternational.app.domain.models.comment_rating.ReviewDetailResponse;


public interface ReviewView <T extends Presentable> extends Viewable<T> {

  void render();

  void renderReviewDetail(ReviewDetailResponse reviewDetailResponse);
  void renderDelete(RatingResult ratingResult);
}

