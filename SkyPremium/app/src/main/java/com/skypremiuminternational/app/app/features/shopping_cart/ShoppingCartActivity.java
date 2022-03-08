package com.skypremiuminternational.app.app.features.shopping_cart;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.skypremiuminternational.app.R;

import com.skypremiuminternational.app.app.features.navigation.NavigationDialogFragment;
import com.skypremiuminternational.app.app.internal.mvp.BaseActivity;
import com.skypremiuminternational.app.domain.models.cart.CartDetailResponse;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;

public class ShoppingCartActivity extends BaseActivity<ShoppingCartPresenter> implements ShoppingCartView<ShoppingCartPresenter> {

  @BindView(R.id.tvTitle_toolbar)
  TextView tvTitleToolbar;
  @BindView(R.id.rvCart)
  RecyclerView rvCart;
  @BindView(R.id.tvJson)
  TextView tvJson;


  ShoppingCartAdapter adapter;

  public static void startMe(Context context){
    Intent intent = new Intent(context, ShoppingCartActivity.class);
    context.startActivity(intent);
  }


  @Override
  public void onCreate(Bundle savedInstanceState) {
    AndroidInjection.inject(this);
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_shopping_cart);
    ButterKnife.bind(this);
    setUpRecyclerView();
    presenter.getCartInfo();

    tvTitleToolbar.setText("My Shopping Cart");
  }


  @OnClick(R.id.ivNavigation_toolbar)
  void openMenu() {
    NavigationDialogFragment.newInstance().show(getSupportFragmentManager(), "Navigation");

  }

  private void setUpRecyclerView() {
      if(adapter == null){
        adapter = new ShoppingCartAdapter();
      }
      rvCart.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
      rvCart.setAdapter(adapter);
  }

  @Inject
  @Override
  public void injectPresenter(ShoppingCartPresenter presenter) {
    super.injectPresenter(presenter);
  }

  @Override
  public void renderListCart(CartDetailResponse response) {
    Gson gson = new Gson();

    tvJson.setText("Json: "+ gson.toJson(response.getItems()));

    adapter.setDataList(response.getItems(),response.getVirtual());

  }

  @Override
  public void renderError(String message) {
    Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
  }
}
