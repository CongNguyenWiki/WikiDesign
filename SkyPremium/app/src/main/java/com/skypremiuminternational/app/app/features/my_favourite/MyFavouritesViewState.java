package com.skypremiuminternational.app.app.features.my_favourite;

import androidx.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.skypremiuminternational.app.domain.models.category.CategoryResponse;
import com.skypremiuminternational.app.domain.models.favourite.FavouriteItem;

import java.util.List;


@AutoValue
public abstract class MyFavouritesViewState {


  @Nullable
  abstract CategoryResponse category();

  @Nullable
  abstract List<FavouriteItem> myFavourites();

  @Nullable
  abstract Throwable error();

  public static MyFavouritesViewState create(CategoryResponse category,
                                             List<FavouriteItem> myFavourites, Throwable error) {
    return builder()
        .category(category)
        .myFavourites(myFavourites)
        .error(error)
        .build();
  }

  public static Builder builder() {
    return new AutoValue_MyFavouritesViewState.Builder();
  }

  @AutoValue.Builder
  public abstract static class Builder {
    public abstract Builder error(Throwable error);

    public abstract Builder myFavourites(List<FavouriteItem> myFavourites);

    public abstract Builder category(CategoryResponse category);


    public abstract MyFavouritesViewState build();
  }
}
