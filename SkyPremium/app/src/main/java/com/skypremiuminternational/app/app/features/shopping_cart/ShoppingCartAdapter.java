package com.skypremiuminternational.app.app.features.shopping_cart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.skypremiuminternational.app.R;

import com.skypremiuminternational.app.domain.models.cart.CartDetailItem;


import java.util.List;



public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartViewHolder> {



  List<CartDetailItem> dataList;
  private Integer[] counts;
  private boolean containVirtualProduct;
  private int currentFocusPosition = RecyclerView.NO_POSITION;

  public Integer[] getCounts() {
    return counts;
  }




  @NonNull
  @Override
  public ShoppingCartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shopping_cart,parent,false);
    ShoppingCartViewHolder holder = new ShoppingCartViewHolder(view);





    return holder;
  }

  @Override
  public void onBindViewHolder(@NonNull ShoppingCartViewHolder holder, int position) {
    holder.bind(dataList.get(position), position, containVirtualProduct,
        currentFocusPosition == position && getItemCount() != 1);

  }

  @Override
  public int getItemCount() {
    return dataList == null ? 0 : dataList.size();
  }

  public void setDataList(List<CartDetailItem> items, boolean containVirtualProduct) {

    dataList = items;
    this.counts = new Integer[dataList.size()];
    for (int i = 0; i < dataList.size(); i++) {
      CartDetailItem item = dataList.get(i);
      counts[i] = item.getQty();
    }
    this.containVirtualProduct = containVirtualProduct;
    notifyDataSetChanged();
  }

}
