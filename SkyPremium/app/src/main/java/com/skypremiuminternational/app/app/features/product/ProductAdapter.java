package com.skypremiuminternational.app.app.features.product;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.skypremiuminternational.app.R;
import com.skypremiuminternational.app.app.features.product.listener.ProductActionListener;
import com.skypremiuminternational.app.domain.models.products.ProductItem;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder> {



  List<ProductItem> dataList;
  ProductActionListener actionListener;

  public void setActionListener(ProductActionListener actionListener) {
    this.actionListener = actionListener;
  }

  @NonNull
  @Override
  public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product,parent,false);
    ProductViewHolder holder = new ProductViewHolder(view);

    holder.cons_product.setOnClickListener(v-> {
      if (actionListener != null && holder.getAdapterPosition() != RecyclerView.NO_POSITION){
        actionListener.onItemClicked(dataList.get(holder.getAdapterPosition()),holder.getAdapterPosition());
      }
    });




    return holder;
  }

  @Override
  public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
    holder.bind(dataList.get(position));
  }

  @Override
  public int getItemCount() {
    return dataList == null ? 0 : dataList.size();
  }

  public void setData(List<ProductItem> list){
    this.dataList = list;
    notifyDataSetChanged();
  }
}
