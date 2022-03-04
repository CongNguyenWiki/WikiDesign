package com.skypremiuminternational.app.app.features.product;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.skypremiuminternational.app.R;
import com.skypremiuminternational.app.app.utils.ImageUtils;
import com.skypremiuminternational.app.domain.models.products.ProductItem;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductViewHolder extends RecyclerView.ViewHolder {

  @BindView(R.id.cons_product)
  ConstraintLayout cons_product;
  @BindView(R.id.tvProducName)
  TextView tvProductName;
  @BindView(R.id.tvProductPrice)
  TextView tvProductPrice;
  @BindView(R.id.iv)
  ImageView imageView;


  public ProductViewHolder(@NonNull View itemView) {
    super(itemView);
    ButterKnife.bind(this, itemView);
  }



  public void bind(ProductItem item){
    tvProductName.setText(item.getName());
    tvProductPrice.setText(item.getPrice());


    ImageUtils.glideImg(imageView,item.getImageThumbnailUrl());
  }
}
