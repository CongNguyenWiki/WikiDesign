package com.skypremiuminternational.app.app.features.my_favourite;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.google.android.material.tabs.TabLayout;
import com.skypremiuminternational.app.R;
import com.skypremiuminternational.app.app.features.my_favourite.detail.MyFavouritesDetailActivity;
import com.skypremiuminternational.app.app.features.navigation.NavigationDialogFragment;
import com.skypremiuminternational.app.app.features.landing.LandingActivity;
import com.skypremiuminternational.app.app.features.search.SearchActivity;
import com.skypremiuminternational.app.app.internal.mvp.LocationAwareActivity;
import com.skypremiuminternational.app.app.utils.Constants;
import com.skypremiuminternational.app.app.utils.ErrorMessageFactory;
import com.skypremiuminternational.app.app.utils.WebViewURL;
import com.skypremiuminternational.app.domain.interactor.cart.AddToCart;
import com.skypremiuminternational.app.domain.models.category.CategoryResponse;
import com.skypremiuminternational.app.domain.models.category.ChildData;
import com.skypremiuminternational.app.domain.models.category.ChildData_;
import com.skypremiuminternational.app.domain.models.favourite.FavouriteItem;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;
import timber.log.Timber;

public class MyFavouriteActivity extends LocationAwareActivity<MyFavouritePresenter> implements MyFavouriteView<MyFavouritePresenter> {


  @BindView(R.id.rvMyFavourites)
  RecyclerView recyclerView;
  @BindView(R.id.tvJson)
  TextView tvJson;
  @BindView(R.id.tab_layout)
  TabLayout tabLayout;
  @BindView(R.id.tvCategory_filter)
  TextView tvCategoryFilter;
  @BindView(R.id.tvTitle_toolbar)
  TextView tvTitleToolbar;
  @BindView(R.id.tvSort_filter)
  TextView tvSortFilter;
  @BindView(R.id.ahbn)
  AHBottomNavigation ahbn;
  @BindView(R.id.ll_empty)
  LinearLayout llEmpty;
  @BindView(R.id.ly_cart_count)
  FrameLayout lyCartCount;
  @BindView(R.id.tv_cart_count)
  TextView tvCartCount;

  @Inject
  ErrorMessageFactory errorMessageFactory;



  ProgressDialog progressDialog;

  private String partnerType = Constants.FAVOURITE_PARTNER_TYPE_PARTNERS;
  private String selectedCategoryID = null;
  private int selectedSorting = 0;

  private String[] partnerCategory = new String[]{};
  private String[] partnerCategoryIds = new String[]{};

  private String[] productCategory = new String[]{};
  private String[] productCategoryIds = new String[]{};

  FavPartnerAdapter favPartnerAdapter;
  FavProductAdapter favProductAdapter;

  public static void startMe(Context context){
    Intent intent = new Intent(context,MyFavouriteActivity.class);
    context.startActivity(intent);
  }

  @Inject
  @Override
  public void injectPresenter(MyFavouritePresenter presenter) {
    super.injectPresenter(presenter);
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    AndroidInjection.inject(this);
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activiry_my_favourite);
    ButterKnife.bind(this);

    tvTitleToolbar.setText(getResources().getString(R.string.my_favourite));

    setupRecyclerView();
    setUpTab();
    presenter.setRequest(selectedCategoryID, partnerType, getSelectedSortField(),
            Constants.sortDirectionFav[selectedSorting]);
    presenter.getFavourites();

    setupBottomMenu();

    presenter.getCategory();

  }

  @Override
  protected void onResume() {
    super.onResume();
    presenter.getCartCount();
  }

  private void setupBottomMenu() {
    ahbn.setTitleTextSizeInSp(9, 9);
    ahbn.setAccentColor(ContextCompat.getColor(this, R.color.colorAccent));
    ahbn.setDefaultBackgroundColor(Color.parseColor("#FFFFFF"));
    ahbn.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
    ahbn.setInactiveColor(Color.parseColor("#C0B8B6"));

    AHBottomNavigationItem item1 =
            new AHBottomNavigationItem(R.string.travel_all_caps, R.drawable.ic_travel_tab,
                    R.color.colorAccent);
    AHBottomNavigationItem item2 =
            new AHBottomNavigationItem(R.string.wine_all_caps, R.drawable.ic_wine_tab,
                    R.color.colorAccent);
    AHBottomNavigationItem item3 =
            new AHBottomNavigationItem(R.string.home_all_caps, R.drawable.ic_logo_tab,
                    R.color.colorAccent);
    AHBottomNavigationItem item4 =
            new AHBottomNavigationItem(R.string.shopping_all_caps, R.drawable.ic_shopping_tab,
                    R.color.colorAccent);
    AHBottomNavigationItem item5 =
            new AHBottomNavigationItem(R.string.wellness_all_caps, R.drawable.ic_wellness_tab,
                    R.color.colorAccent);

    ahbn.addItem(item1);
    ahbn.addItem(item2);
    ahbn.addItem(item3);
    ahbn.addItem(item4);
    ahbn.addItem(item5);
    ahbn.setCurrentItem(-1);

    ahbn.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
      @Override
      public boolean onTabSelected(int position, boolean wasSelected) {

        switch (position) {
          case 0 : {
            LandingActivity.startMe(MyFavouriteActivity.this, WebViewURL.TRAVEL,position,true);
            return true;
          }
          case 1 : {
            LandingActivity.startMe(MyFavouriteActivity.this, WebViewURL.WINE_DINE,position,true);
            return true;
          }
          case 2 : {
            LandingActivity.startMe(MyFavouriteActivity.this, WebViewURL.DEFAULT,position,true);
            return true;
          }
          case 3 : {
            LandingActivity.startMe(MyFavouriteActivity.this, WebViewURL.SHOPPING,position,true);
            return true;
          }
          case 4 : {
            LandingActivity.startMe(MyFavouriteActivity.this, WebViewURL.WELLNESS,position,true);
            return true;
          }
        }

        return false;
      }
    });
  }

  private void setupRecyclerView() {
    int spanCount = 2;
    int spacing = 8;
    final boolean includeEdge = false;

//    recyclerView.addItemDecoration(new ProductGridSpacesItemDecoration(2,
//            MetricsUtils.convertDpToPixel(spacing, this), includeEdge));

    if (partnerType != null && partnerType.equals(Constants.FAVOURITE_PARTNER_TYPE_PARTNERS)) {
      recyclerView.setLayoutManager(new LinearLayoutManager(this));
      favPartnerAdapter = new FavPartnerAdapter();
      recyclerView.setAdapter(favPartnerAdapter);

      favPartnerAdapter.setActionListener(new FavPartnerAdapter.ActionListener() {
        @Override
        public void onClickedItem(FavouriteItem item) {
          MyFavouritesDetailActivity.startMe(MyFavouriteActivity.this,item);
        }

        @Override
        public void onClickedFavourite(FavouriteItem item) {
          presenter.removeFromFav(item.getWishlistItemId());
        }
        @Override
        public void onItemClickedWithViewMenu(FavouriteItem item) {
          MyFavouritesDetailActivity.startMe(MyFavouriteActivity.this,item);
        }

        @Override
        public void onReserveNow(FavouriteItem item, int position, MyFavPartnerFullSizeHolder holder) {

        }
      });


    }
    else if (partnerType != null && partnerType.equals(Constants.FAVOURITE_PARTNER_TYPE_PRODUCTS)){
      recyclerView.setLayoutManager(new GridLayoutManager(this, spanCount));
      favProductAdapter = new FavProductAdapter();
      recyclerView.setAdapter(favProductAdapter);
      favProductAdapter.setItemActionListener(new FavProductAdapter.ActionListener<FavouriteItem>() {
        @Override
        public void onClickedItem(FavouriteItem item) {

          MyFavouritesDetailActivity.startMe(MyFavouriteActivity.this,item);

        }

        @Override
        public void onClickedFavourite(FavouriteItem item) {
          presenter.removeFromFav(item.getWishlistItemId());
        }

        @Override
        public void onItemClickedBuyNow(FavouriteItem item) {

        }

        @Override
        public void onItemClickedAddToCart(List<FavouriteItem> dataList,int position) {

          presenter.addToCart(
              new AddToCart.Params(dataList.get(position).getProduct().getSku()
                  , 1
                  , dataList.get(position).getProduct().getName()
                  , dataList.get(position).getProduct().getTypeId()));

        }
      });
    }
//    else if (partnerType != null && partnerType.equals(Constants.FAVOURITE_PARTNER_TYPE_EVENT)) {
//      recyclerView.setLayoutManager(new LinearLayoutManager(this));
//      //rv.setAdapter(favouriteUpComingEventAdapter);
//    }
  }

  private void setUpTab() {
    tabLayout.addTab(tabLayout.newTab().setText(R.string.label_partners));
    tabLayout.addTab(tabLayout.newTab().setText(R.string.label_products));
//    tabLayout.addTab(tabLayout.newTab().setText(R.string.event));
    tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

    tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
      @Override
      public void onTabSelected(TabLayout.Tab tab) {
        selectedCategoryID = null;
        if (tab.getPosition() == 0){
          tvCategoryFilter.setVisibility(View.VISIBLE);
          tvCategoryFilter.setText(String.format("Refine: %s", "All"));
          partnerType = Constants.FAVOURITE_PARTNER_TYPE_PARTNERS;
          setupRecyclerView();
          fetchFavourites();
        }
        else if (tab.getPosition() == 1){
          tvCategoryFilter.setVisibility(View.VISIBLE);
          tvCategoryFilter.setText(String.format("Refine: %s", "All"));
          partnerType = Constants.FAVOURITE_PARTNER_TYPE_PRODUCTS;
          setupRecyclerView();
          fetchFavourites();
        }
//        else if (tab.getPosition() == 2){
//          tvCategoryFilter.setVisibility(View.INVISIBLE);
//          tvSortFilter.setText(String.format("Sort By: %s", Constants.sortingEventArrFav[selectedSorting]));
//          partnerType = Constants.FAVOURITE_PARTNER_TYPE_EVENT;
//          setupRecyclerView();
//          fetchFavourites();
//        }



      }

      @Override
      public void onTabUnselected(TabLayout.Tab tab) {

      }

      @Override
      public void onTabReselected(TabLayout.Tab tab) {

      }
    });

    if (tabLayout.getSelectedTabPosition() == 0){
      partnerType = Constants.FAVOURITE_PARTNER_TYPE_PARTNERS;
    }
    else if (tabLayout.getSelectedTabPosition() == 1){
      partnerType = Constants.FAVOURITE_PARTNER_TYPE_PRODUCTS;
    }
//    else if (tabLayout.getSelectedTabPosition() == 2){
//      partnerType = Constants.FAVOURITE_PARTNER_TYPE_EVENT;
//    }


  }


  private void fetchFavourites() {
//    if (partnerType != null && partnerType == Constants.FAVOURITE_PARTNER_TYPE_EVENT){
//      presenter.setRequest(selectedCategoryID, partnerType, getSelectedSortField(),
//              Constants.sortDirectionEventFav[selectedSorting]);
//      presenter.getFavourites();
//    }
//    else {
      presenter.setRequest(selectedCategoryID, partnerType, getSelectedSortField(),
              Constants.sortDirectionFav[selectedSorting]);
      presenter.getFavourites();

//    }

  }

  private String getSelectedSortField() {
//    if (partnerType != null && partnerType == Constants.FAVOURITE_PARTNER_TYPE_EVENT){
//      String sortField = Constants.sortEventFieldFav[selectedSorting];
//      return sortField;
//    }
//    else {
      String sortField = Constants.sortFieldFav[selectedSorting];

      if (Constants.sortingArrFav[selectedSorting].equals("Nearest First")) {
        sortField = Constants.sortFieldFav[selectedSorting];
        if (this.location != null) {
          sortField += location.getLongitude() + "_" + location.getLatitude();
        } else {
          sortField += "0_0";
        }
      }
      return sortField;

//    }

  }



  @Override
  public void render(MyFavouritesViewState viewState) {
    setupRecyclerView();
    if (viewState.error() != null) {
      showToast(viewState.error().getLocalizedMessage());
      return;
    }
    if (viewState.category() != null) {
      setCategoryPartner(viewState.category());
      setCategoryProduct(viewState.category());

    }
    if (viewState.myFavourites() != null && viewState.myFavourites().size() > 0) {
      llEmpty.setVisibility(View.GONE);
      recyclerView.setVisibility(View.VISIBLE);
      if (partnerType != null && partnerType == Constants.FAVOURITE_PARTNER_TYPE_PARTNERS) {
        favPartnerAdapter.setDataList(viewState.myFavourites());
      } else if (partnerType != null && partnerType == Constants.FAVOURITE_PARTNER_TYPE_PRODUCTS){
        favProductAdapter.setData(viewState.myFavourites());
      }
//      else if (partnerType != null && partnerType == Constants.FAVOURITE_PARTNER_TYPE_EVENT){
//        favouriteUpComingEventAdapter.set(viewState.myFavourites());
//      }
    }
    if (viewState.myFavourites() != null && viewState.myFavourites().size() == 0) {
      llEmpty.setVisibility(View.VISIBLE);
      recyclerView.setVisibility(View.GONE);
      if (partnerType != null && partnerType == Constants.FAVOURITE_PARTNER_TYPE_PARTNERS) {
        favPartnerAdapter.setDataList(viewState.myFavourites());
      } else if (partnerType != null && partnerType == Constants.FAVOURITE_PARTNER_TYPE_PRODUCTS){
        favProductAdapter.setData(viewState.myFavourites());
      }
//      else if (partnerType != null && partnerType == Constants.FAVOURITE_PARTNER_TYPE_EVENT){
//        favouriteUpComingEventAdapter.set(viewState.myFavourites());
//      }
    }
  }

  private void setCategoryPartner(CategoryResponse category) {
    int countActive = 0;
    for (ChildData item : category.getChildrenData()) {
      if (item.getIsActive() && isInLimitRefine(item.getId())) {
        countActive++;
      }
    }
    if (partnerCategory != null
        && partnerCategory.length == countActive + 1) {
      return; //already set
    }

    if (countActive <= 0) {
      return;
    }
    List<String> partnerCategoryTemp = new ArrayList<>();
    List<String> partnerCategoryIdsTemp = new ArrayList<>();

    for (ChildData childData : category.getChildrenData()) {
      if (childData.getIsActive() && isInLimitRefine(childData.getId())) {
        partnerCategoryTemp.add(childData.getName());
        partnerCategoryIdsTemp.add(childData.getId().toString());
      }
    }
    partnerCategoryIdsTemp.add(0, null);
    partnerCategoryTemp.add(0, "All");

    partnerCategory = partnerCategoryTemp.toArray(new String[countActive + 1]);
    partnerCategoryIds = partnerCategoryIdsTemp.toArray(new String[countActive + 1]);
  }

  boolean isInLimitRefine(Integer id) {
    switch (id) {
      case 24:
        return true; // travel
      case 4:
        return true; // Shopping
      case 5:
        return true; // Wine & Dine
      case 6:
        return true; // Wellness
    }

    return false;
  }


  private void setCategoryProduct(CategoryResponse category) {
    int countActive = 0;
    ChildData estoreData = new ChildData();
    for (ChildData item : category.getChildrenData()) {
      if (item.getId() == 55) {
        estoreData = item;
        for (ChildData_ item2 : estoreData.getChildrenData()) {
          if (item2.getIsActive()) {
            countActive++;
          }
        }
      }
    }


    if (productCategory != null
        && productCategory.length == countActive + 1) {
      return; //already set
    }

    if (countActive <= 0) {
      return;
    }
    List<String> productCategoryTemp = new ArrayList<>();
    List<String> productCategoryIdsTemp = new ArrayList<>();

    for (ChildData_ childData : estoreData.getChildrenData()) {
      if (childData.getIsActive()) {
        productCategoryTemp.add(childData.getName());
        productCategoryIdsTemp.add(childData.getId().toString());
      }
    }

    productCategoryTemp.add(0, "All");
    productCategoryIdsTemp.add(0, null);

    productCategory = productCategoryTemp.toArray(new String[countActive + 1]);
    productCategoryIds = productCategoryIdsTemp.toArray(new String[countActive + 1]);
  }




  @Override
  public void render(Throwable error) {
    Timber.e(error);
    Toast.makeText(this, errorMessageFactory.getErrorMessage(error), Toast.LENGTH_SHORT).show();
  }

  @Override
  public void updateCartCount(String count) {

    if (count == null && TextUtils.isEmpty(count)) {
      lyCartCount.setVisibility(View.INVISIBLE);
      tvCartCount.setVisibility(View.INVISIBLE);
    } else {
      if (!count.equalsIgnoreCase("0")) {
        lyCartCount.setVisibility(View.VISIBLE);
        tvCartCount.setVisibility(View.VISIBLE);
        tvCartCount.setText(count);
      } else {
        lyCartCount.setVisibility(View.INVISIBLE);
        tvCartCount.setVisibility(View.INVISIBLE);
      }
    }

  }

  @OnClick(R.id.tvCategory_filter)
  void onClickCategory() {
    boolean isPartners = tabLayout.getSelectedTabPosition() == 0;
    final String[] categoryIDArr = isPartners ? partnerCategoryIds : productCategoryIds;
    final String[] categoryArr = isPartners ? partnerCategory : productCategory;

    new AlertDialog.Builder(this).setTitle("REFINE: ")
            .setItems(categoryArr, (dialog, item) -> {
              selectedCategoryID = categoryIDArr[item];
              tvCategoryFilter.setText(String.format("Refine: %s", categoryArr[item]));
              fetchFavourites();
            })
            .setNegativeButton("Cancel", null)
            .show();
  }

  @OnClick(R.id.tvSort_filter)
  void onClickSort() {

//    if (partnerType != null && partnerType == Constants.FAVOURITE_PARTNER_TYPE_EVENT){
//      new AlertDialog.Builder(this).setTitle("SORT BY:")
//              .setItems(Constants.sortingEventArrFav, new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int item) {
//                  selectedSorting = item;
//                  tvSortFilter.setText(String.format("Sort By: %s", Constants.sortingEventArrFav[item]));
//                  fetchFavourites();
//                }
//              })
//              .setNegativeButton("Cancel", null)
//              .show();
//    }
//    else {
      new AlertDialog.Builder(this).setTitle("SORT BY:")
              .setItems(Constants.sortingArrFav, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int item) {
                  selectedSorting = item;
                  tvSortFilter.setText(String.format("Sort By: %s", Constants.sortingArrFav[item]));
                  fetchFavourites();
                }
              })
              .setNegativeButton("Cancel", null)
              .show();


//    }


  }

  @OnClick(R.id.ivCart_toolbar)
  void openCart() {
    LandingActivity.startMe(MyFavouriteActivity.this, WebViewURL.SHOPPING_CART,-1,true);
  }
  @OnClick(R.id.ivSearch_toolbar)
  void openSearch() {
    SearchActivity.startMe(this);
  }

  @OnClick(R.id.ivNavigation_toolbar)
  void onClickMenuNavigation(){
    NavigationDialogFragment.newInstance().show(getSupportFragmentManager(), "Navigation");
  }

  @OnClick(R.id.btn_estore)
  void onClickEstore(){
    LandingActivity.startMe(this, WebViewURL.ESTORE,-1,true);
  }




  @Override
  public void showLoadingDialog(String message) {
    if(progressDialog == null ){
      progressDialog =  new ProgressDialog(this);
    }
    progressDialog.setMessage(message);
    progressDialog.show();
  }



  @Override
  public void hideLoading() {
    if(progressDialog !=null  && progressDialog.isShowing()){
      progressDialog.dismiss();
    }
  }
}
