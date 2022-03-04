package com.skypremiuminternational.app.app.features.profile.booking.occupancy;

import com.skypremiuminternational.app.app.internal.mvp.contract.Presentable;
import com.skypremiuminternational.app.app.internal.mvp.contract.Viewable;
import com.skypremiuminternational.app.data.model.ean.Child;

import java.util.List;



public interface OccupancyPickerView<T extends Presentable> extends Viewable<T> {
  void showChildAgePicker(String title, String[] childrenAges, int checkedItem, Child editingChild,
                          int editingChildPosition);

  void render(int roomCount, int adultCount, List<Child> children);

  void notifyChildCountChanged(int size);

  void notifyChildStatusChange(int position);

  void done(int roomCount, int adultCount, List<Child> children);

  void renderError(String errText);
}
