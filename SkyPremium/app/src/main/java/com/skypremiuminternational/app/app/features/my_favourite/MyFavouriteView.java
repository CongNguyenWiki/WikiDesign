package com.skypremiuminternational.app.app.features.my_favourite;

import com.skypremiuminternational.app.app.internal.mvp.contract.Presentable;
import com.skypremiuminternational.app.app.internal.mvp.contract.Viewable;
import com.skypremiuminternational.app.domain.models.favourite.FavouriteItem;

import java.util.List;

public interface MyFavouriteView<T extends Presentable> extends Viewable<T>  {

  void render(MyFavouritesViewState viewState);

  void showLoadingDialog(String message);

  void render(Throwable error);

  void updateCartCount(String count);
}
