package com.skypremiuminternational.app.app.features.product;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.skypremiuminternational.app.R;
import com.skypremiuminternational.app.app.features.product.detail.ProductDetailActivity;
import com.skypremiuminternational.app.app.features.product.listener.ProductActionListener;
import com.skypremiuminternational.app.app.internal.mvp.BaseActivity;
import com.skypremiuminternational.app.domain.models.products.ProductItem;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

public class ProductActivity extends BaseActivity<ProductPresenter> implements ProductView<ProductPresenter> {

  @BindView(R.id.rv)
  RecyclerView recyclerView;

  ProductAdapter adapter;

  List<ProductItem> lstProduct = new ArrayList<>();
  
  
  public static void startMe(Context context){
    Intent intent = new Intent(context,ProductActivity.class);
    context.startActivity(intent);
  }

  @Inject
  @Override
  public void injectPresenter(ProductPresenter presenter) {
    super.injectPresenter(presenter);
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    AndroidInjection.inject(this);
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_products);
    ButterKnife.bind(this);
    setupRecyclerView();
    presenter.getProducts();
  }

  private void setupRecyclerView() {
    if(adapter == null){
      adapter = new ProductAdapter();
    }
    recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

    adapter.setActionListener(new ProductActionListener() {
      @Override
      public void onItemClicked(Object item, int position) {
        ProductDetailActivity.startMe(ProductActivity.this,lstProduct.get(position));
      }
    });

    recyclerView.setAdapter(adapter);

  }

  @Override
  public void renderProduct(List list) {

    lstProduct = list;
    adapter.setData(list);

  }

}
