package com.skypremiuminternational.app.app.features.my_orders;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.j256.ormlite.stmt.query.In;
import com.skypremiuminternational.app.R;
import com.skypremiuminternational.app.app.features.my_orders.detail.OrderDetailsActivity;
import com.skypremiuminternational.app.app.features.my_orders.listener.ItemClickListener;
import com.skypremiuminternational.app.app.internal.mvp.BaseActivity;
import com.skypremiuminternational.app.app.utils.Constants;
import com.skypremiuminternational.app.domain.models.my_orders.MyOrderItem;
import com.skypremiuminternational.app.domain.models.my_orders.MyOrderResponse;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;
import timber.log.Timber;

public class MyOrderActivity extends BaseActivity<MyOrdersPresenter>
    implements MyOrdersView<MyOrdersPresenter> {



  @BindView(R.id.tvTitle_toolbar)
  TextView tvTitle_toolbar;
  @BindView(R.id.rvMyOrders)
  RecyclerView rv;
  /*@BindView(R.id.spnCategory_filter) AppCompatSpinner spnCategory;
  @BindView(R.id.spnSort_filter) AppCompatSpinner spnSort;*/
  @BindView(R.id.tvTitle_toolbar_amount)
  TextView txtToolbarAmount;
  @BindView(R.id.layout_no_order)
  RelativeLayout layoutNoOrder;
  @BindView(R.id.tvCategory_filter)
  TextView tvCategoryFilter;
  @BindView(R.id.tvSort_filter)
  TextView tvSortFilter;


  MyOrdersAdapter adapter;
  private int selectedSorting = 0;
  private int selectedCategory = 0;






  public static void startMe(Context context) {
    Intent intent = new Intent(context, MyOrderActivity.class);
    context.startActivity(intent);
  }


  @Inject
  @Override
  public void injectPresenter(MyOrdersPresenter presenter) {
    super.injectPresenter(presenter);
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    AndroidInjection.inject(this);
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_my_orders);
    ButterKnife.bind(this);


    tvTitle_toolbar.setText(getResources().getString(R.string.my_orders));

    tvSortFilter.setText(String.format("Sort By: %s", Constants.sortingArrOrder[selectedSorting]));
    tvCategoryFilter.setText(
        String.format("Refine: %s", Constants.categoryArrOrder[selectedCategory]));

    setupRecyclerAdapter();


    fetchOrderHistory();

  }

  private void setupRecyclerAdapter() {
    rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    adapter = new MyOrdersAdapter();
    adapter.setItemClickListener(new ItemClickListener<MyOrderItem>() {
      @Override
      public void onItemClicked(MyOrderItem item) {
        Timber.i("OrderItem : " + item);
          OrderDetailsActivity.startMe(MyOrderActivity.this, item);

      }
    });
    rv.setAdapter(adapter);
  }

  @OnClick(R.id.ivNavigation_toolbar)
  void onClickBack(){
    finish();
  }




  @Override
  public void renderMyOrder(MyOrderResponse response) {
    Timber.i("total : " + response.getTotalCount());
    txtToolbarAmount.setText("(" + String.valueOf(response.getTotalCount()) + ")");
    if (response.getItems().size() > 0) {
      rv.setVisibility(View.VISIBLE);
      layoutNoOrder.setVisibility(View.GONE);
      adapter.setData(response.getItems());
    } else {
      rv.setVisibility(View.GONE);
      layoutNoOrder.setVisibility(View.VISIBLE);
    }
  }

  @OnClick(R.id.tvSort_filter)
  public void onClickSort() {
    new AlertDialog.Builder(this).setTitle("SORT BY:")
        .setItems(Constants.sortingArrOrder, (dialog, item) -> {
          selectedSorting = item;
          tvSortFilter.setText(String.format("Sort By: %s", Constants.sortingArrOrder[item]));
          fetchOrderHistory();
        })
        .setNegativeButton("Cancel", null)
        .show();
  }

  @OnClick(R.id.tvCategory_filter)
  public void onClickCategory() {
    new AlertDialog.Builder(this).setTitle("REFINE: ")
        .setItems(Constants.categoryArrOrder, (dialog, item) -> {
          selectedCategory = item;
          tvCategoryFilter.setText(String.format("Refine: %s", Constants.categoryArrOrder[item]));
          fetchOrderHistory();
        })
        .setNegativeButton("Cancel", null)
        .show();
  }

  private void fetchOrderHistory() {
    getPresenter().setRequest(Constants.sortingArrOrder[selectedSorting],
        Constants.categoryArrOrder[selectedCategory]);
    getPresenter().getMyOrders();
  }

}
