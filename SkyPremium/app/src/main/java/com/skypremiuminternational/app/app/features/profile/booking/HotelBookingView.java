package com.skypremiuminternational.app.app.features.profile.booking;

import com.skypremiuminternational.app.app.internal.mvp.contract.Presentable;
import com.skypremiuminternational.app.app.internal.mvp.contract.Viewable;



public interface HotelBookingView<T extends Presentable> extends Viewable<T> {

  void render(HotelBookingViewState viewState);
}
