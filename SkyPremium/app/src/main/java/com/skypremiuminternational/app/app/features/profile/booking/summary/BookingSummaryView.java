package com.skypremiuminternational.app.app.features.profile.booking.summary;

import com.skypremiuminternational.app.app.features.profile.booking.room.RoomCheckoutPresenter;
import com.skypremiuminternational.app.app.internal.mvp.contract.Presentable;
import com.skypremiuminternational.app.app.internal.mvp.contract.Viewable;
import com.skypremiuminternational.app.domain.models.booking.PriceCheckResult;

import java.util.List;

public interface BookingSummaryView<T extends Presentable> extends Viewable<T> {

  void render(PriceCheckResult result, List<String> roomNameOccupancies,
              List<String> groupedOccupancies);

  void render(Throwable error);

  void goToRoomCheckout(RoomCheckoutPresenter.Params params);
}
