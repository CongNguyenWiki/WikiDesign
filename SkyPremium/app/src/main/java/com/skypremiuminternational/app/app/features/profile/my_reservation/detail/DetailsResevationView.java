package com.skypremiuminternational.app.app.features.profile.my_reservation.detail;

import com.skypremiuminternational.app.app.internal.mvp.contract.Presentable;
import com.skypremiuminternational.app.app.internal.mvp.contract.Viewable;
import com.skypremiuminternational.app.domain.models.permissions.PermissionProfileItem;
import com.skypremiuminternational.app.domain.models.reservation.OutletItem;
import com.skypremiuminternational.app.domain.models.reservation.ReserveHistoryItem;

import java.util.List;

public interface DetailsResevationView<T extends Presentable> extends Viewable<T> {

  void renderGetOutletSuccess(List<OutletItem> list, int action);

  void renderGetOutletFailed();

  void renderError(String msg);

  void renderBookingDetail(ReserveHistoryItem reserveHistoryItem);

  void render(List<PermissionProfileItem> value);
}
