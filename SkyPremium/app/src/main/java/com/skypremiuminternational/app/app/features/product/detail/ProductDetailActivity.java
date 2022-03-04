package com.skypremiuminternational.app.app.features.product.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;




import com.google.gson.Gson;
import com.skypremiuminternational.app.R;
import com.skypremiuminternational.app.app.internal.mvp.BaseActivity;
import com.skypremiuminternational.app.app.utils.ImageUtils;
import com.skypremiuminternational.app.domain.models.products.CustomAttributesItem;
import com.skypremiuminternational.app.domain.models.products.DetailsItem;
import com.skypremiuminternational.app.domain.models.products.ProductItem;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;

public class ProductDetailActivity extends BaseActivity<ProductDetailPresenter> implements ProductDetailView<ProductDetailPresenter> {


  @BindView(R.id.imgProduct)
  ImageView imgProduct;
  @BindView(R.id.tvNameProduct)
  TextView tvNameProduct;
  @BindView(R.id.tvPrice)
  TextView tvPrice;
  @BindView(R.id.tvCategory)
  TextView tvCategory;
  @BindView(R.id.tvDescription)
  TextView tvDescription;
  @BindView(R.id.tvTitle_toolbar)
  TextView tvTitleToolbar;
  @BindView(R.id.tvJson)
  TextView tvJson;

  ProductItem productItem;

  public static void startMe(Context context, ProductItem item){
    Intent intent = new Intent(context, ProductDetailActivity.class);
    intent.putExtra("Product", new Gson().toJson(item));
    context.startActivity(intent);
  }




  @Inject
  @Override
  public void injectPresenter(ProductDetailPresenter presenter) {
    super.injectPresenter(presenter);
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    AndroidInjection.inject(this);
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_product_detail);
    ButterKnife.bind(this);

    productItem = new Gson().fromJson(getIntent().getStringExtra("Product"), ProductItem.class);

    if (productItem != null){
        presenter.getProductDetails(productItem.getSku());
    }
  }

  @OnClick(R.id.iv_toolbar_back)
  void onClickBack(){
    finish();
  }

  @Override
  public void renderProductDetails(DetailsItem detailsItem) {



    tvNameProduct.setText(detailsItem.getName());
    tvTitleToolbar.setText(detailsItem.getName());

    String s = new Gson().toJson(detailsItem);




    tvJson.setText(s);

    if (detailsItem.getPrice() != null && !TextUtils.isEmpty(detailsItem.getPrice())){
      tvPrice.setText(detailsItem.getPrice());
    }
    else {
      tvPrice.setVisibility(View.GONE);
    }

    ImageUtils.glideImgDefaulSky(imgProduct,"");


    for (CustomAttributesItem customAttribute : detailsItem.getCustomAttributes()) {
      if (customAttribute.getAttributeCode().equalsIgnoreCase("description")
          && !TextUtils.isEmpty(customAttribute.getValue().toString())) {
        tvDescription.setText(Html.fromHtml(customAttribute.getValue().toString()));
      }

    }




    }
}
