package com.skypremiuminternational.app.app.features.navigation.expandable.listener;

import com.skypremiuminternational.app.domain.models.category.ChildData;
import com.skypremiuminternational.app.domain.models.category.ChildData_;

public interface IActionCLickListener{

    void onClickedItem(ChildData item);

    void onClickedSubItem(String name, ChildData_ item);

    void onClickedSubItem_(String name, ChildData item);
}
