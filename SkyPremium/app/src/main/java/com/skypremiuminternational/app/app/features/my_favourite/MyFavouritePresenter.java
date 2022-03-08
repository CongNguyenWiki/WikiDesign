package com.skypremiuminternational.app.app.features.my_favourite;

import android.util.Log;

import com.skypremiuminternational.app.app.internal.mvp.BasePresenter;
import com.skypremiuminternational.app.app.utils.Constants;
import com.skypremiuminternational.app.data.InternalStorageManager;
import com.skypremiuminternational.app.data.network.request.GetFavouriteRequest;
import com.skypremiuminternational.app.domain.exception.cart.CartLimitException;
import com.skypremiuminternational.app.domain.interactor.auth.GetAppVersion;
import com.skypremiuminternational.app.domain.interactor.cart.AddToCart;
import com.skypremiuminternational.app.domain.interactor.cart.GetCartCount;
import com.skypremiuminternational.app.domain.interactor.category.GetCategory;
import com.skypremiuminternational.app.domain.interactor.product.GetFavourites;
import com.skypremiuminternational.app.domain.interactor.product.RemoveFromFavourite;
import com.skypremiuminternational.app.domain.models.category.CategoryResponse;
import com.skypremiuminternational.app.domain.models.category.ChildData;
import com.skypremiuminternational.app.domain.models.category.ChildData_;
import com.skypremiuminternational.app.domain.models.favourite.FavouriteItem;
import com.skypremiuminternational.app.domain.models.favourite.FavouriteResponse;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import rx.SingleSubscriber;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

public class MyFavouritePresenter extends BasePresenter<MyFavouriteView> {


  final GetFavourites getFavourites;
  GetFavouriteRequest filteredRequest;
  GetCartCount getCartCount;
  GetCategory getCategory;
  AddToCart addToCart;
  RemoveFromFavourite removeFromFavourite;
  MyFavouritesViewState viewState;
  CompositeSubscription compositeSubscription =  new CompositeSubscription();

  @Inject
  public MyFavouritePresenter(GetAppVersion getAppVersion, InternalStorageManager internalStorageManager,
                              GetCategory getCategory,GetFavourites getFavourites,RemoveFromFavourite removeFromFavourite,
                              AddToCart addToCart,GetCartCount getCartCount) {
    super(getAppVersion, internalStorageManager);

    this.getCategory = getCategory;
    this.getFavourites = getFavourites;
    this.removeFromFavourite = removeFromFavourite;
    this.addToCart = addToCart;
    this.getCartCount = getCartCount;
    viewState =
        MyFavouritesViewState.builder().error(null).myFavourites(null).category(null).build();
  }

  @Override
  public void add(Subscription subscription) {
    compositeSubscription.add(subscription);
  }


  void removeFromFav(String wishlistItemId) {
    getView().showLoadingDialog("Remove Favourites...");

    RemoveFromFavourite.Params params = new RemoveFromFavourite.Params(wishlistItemId);

    add(removeFromFavourite.execute(new SingleSubscriber<FavouriteResponse>() {
      @Override
      public void onSuccess(FavouriteResponse value) {
        getView().hideLoading();
        if (value.status) {
          getFavourites();
        } else {
          viewState = MyFavouritesViewState.builder()
              .error(new Exception(value.message))
              .myFavourites(viewState.myFavourites())
              .category(viewState.category())
              .build();
          getView().render(viewState);
        }
      }

      @Override
      public void onError(Throwable error) {
        getView().hideLoading();
        viewState = MyFavouritesViewState.builder()
            .error(error)
            .category(viewState.category())
            .myFavourites(viewState.myFavourites())
            .build();
        getView().render(viewState);
      }
    }, params));
  }

  void getCategory() {
    add(getCategory.execute(new SingleSubscriber<CategoryResponse>() {
      @Override
      public void onSuccess(CategoryResponse value) {
        viewState = MyFavouritesViewState.builder()
            .error(null)
            .myFavourites(viewState.myFavourites()).category(value)
            .build();
        getFavourites();
      }

      @Override
      public void onError(Throwable error) {
        viewState = MyFavouritesViewState.builder()
            .error(error)
            .category(viewState.category())
            .myFavourites(viewState.myFavourites())
            .build();
        getView().render(viewState);
      }
    }));
  }

  void setRequest(String selectedCategoryID, String partnerType, String field, String direction) {

    if (selectedCategoryID == null) {
      filteredRequest = GetFavouriteRequest.getWithoutCategory(partnerType, field, direction);
    } else {
      filteredRequest = GetFavouriteRequest.getWithCategory(selectedCategoryID, partnerType, field, direction);
    }
  }


  void getFavourites() {
    getView().showLoadingDialog("Fetching Favourites...");
    add(getFavourites.asObservable(filteredRequest)
//        .map(favourites -> {
//          for (FavouriteItem item : favourites) {
//            if (item.getExtensionAttributes().isExistProductPermissions()) {
//              item.setCanShow(item.getExtensionAttributes().isGrantProductPermissionsView());
//              item.setCanUse(item.getExtensionAttributes().isGrantProductPermissionsUse());
//            } else {
//              item.setCanShow(item.getExtensionAttributes().isGrantCategoryPermissionsView());
//              item.setCanUse(item.getExtensionAttributes().isGrantCategoryPermissionsUse());
//            }
//          }
//          return favourites;
//        })
        .subscribe(favouritesWrapper -> {
          addCategoryNames(favouritesWrapper, viewState.category());
          viewState = MyFavouritesViewState.builder()
              .error(null)
              .myFavourites(favouritesWrapper)
              .category(viewState.category())
              .build();
          getView().render(viewState);
          getView().hideLoading();
        }, throwable -> {
          getView().hideLoading();
          viewState = MyFavouritesViewState.builder()
              .error(throwable)
              .category(viewState.category())
              .myFavourites(viewState.myFavourites())
              .build();
          getView().render(viewState);
        }));
  }

  private void addCategoryNames(List<FavouriteItem> itemsItems,
                                CategoryResponse categories) {
    for (FavouriteItem item : itemsItems) {
      String categoryName = getCategoryName(categories.getChildrenData(), item);
      item.setCategoryName(categoryName);
    }
  }

  private String getCategoryName(List<ChildData> categories, FavouriteItem item) {
    StringBuilder subCatBuilder = new StringBuilder();
    for (String id : item.getProduct().getCategoryIds()) {
      for (ChildData pillar : categories) {
        if (!id.equals("" + pillar.getId())) {
          for (ChildData_ subCat : pillar.getChildrenData()) {
            if (id.equals("" + subCat.getId())) {
              subCatBuilder.append(subCat.getName());
              subCatBuilder.append(",");
//              if (id.equals(Constants.TOP_TABLE_ID)) {
//                item.setTopTable(true);
//              }
//              if (id.equals(Constants.CHEF_TABLE_ID)) {
//                item.setChefTable(true);
//              }

            }

          }
        } else {
          item.setPillarName(pillar.getName());
          item.setPillarId(id);
        }
      }
    }
    String subCategories = subCatBuilder.toString();
    if (subCategories.endsWith(",")) {
      subCategories = subCategories.substring(0, subCategories.lastIndexOf(","));
    }
    return subCategories;
  }

  static class FavouritesWrapper {
    final List<FavouriteItem> responseList;
    final int totalCount;

    FavouritesWrapper(List<FavouriteItem> responseList, int totalCount) {
      this.responseList = responseList;
      this.totalCount = totalCount;
    }
  }


  public void addToCart(final AddToCart.Params params) {
    getView().showLoadingDialog("Add to cart ...");
    add(addToCart.asObservable(params).subscribe(cart -> {
      getView().hideLoading();
      getView().render(new Exception("You added  \""+params.name+"\" to  your shopping cart."));
      getCartCount();
    }, throwable -> {
      getView().hideLoading();
      if (throwable instanceof IOException) {
        //getView().showAddToCartFailedDialog(params, throwable);
      } else if (throwable instanceof CartLimitException) {
        CartLimitException cartLimitException = (CartLimitException) throwable;
        if (cartLimitException.getLimitErrors() != null
            && cartLimitException.getLimitErrors().size() > 0) {
          getView().render(new Exception(cartLimitException.getLimitErrors().get(0).message));
        }
      } else {
        getView().render(throwable);
        getView().render(new Exception("Failed to add product to cart"));

      }
    }));
  }

  public void getCartCount() {
    add(getCartCount.asObservable()
        .subscribe(s -> getView().updateCartCount(s), Timber::e));
  }

}
