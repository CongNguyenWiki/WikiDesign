package com.skypremiuminternational.app.app.features.my_favourite;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.skypremiuminternational.app.R;
import com.skypremiuminternational.app.domain.models.favourite.FavouriteItem;

import java.util.List;

public class FavProductAdapter extends RecyclerView.Adapter<MyFavouriteViewHolder> {



  List<FavouriteItem> dataList;

  ActionListener<FavouriteItem> itemActionListener;

  @NonNull
  @Override
  public MyFavouriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_estore,parent,false);
    MyFavouriteViewHolder holder = new MyFavouriteViewHolder(view);
    //FavProductViewHolder h = new FavProductViewHolder(view);

    return holder;
  }

  @Override
  public void onBindViewHolder(@NonNull MyFavouriteViewHolder holder, int position) {
    holder.bind(dataList.get(position));

    holder.ll.setOnClickListener(view -> itemActionListener.onClickedItem(dataList.get(holder.getAdapterPosition())));

    holder.ivFav.setOnClickListener(view -> itemActionListener.onClickedFavourite(dataList.get(holder.getAdapterPosition())));

    holder.tvAddToCart.setOnClickListener(view -> itemActionListener.onItemClickedAddToCart(dataList,holder.getAdapterPosition()));

    holder.tvBuyNow.setOnClickListener(view -> itemActionListener.onItemClickedBuyNow(dataList.get(holder.getAdapterPosition())));

  }

  @Override
  public int getItemCount() {
    return dataList == null ? 0 : dataList.size();
  }

  public void setData(List<FavouriteItem> list){
    this.dataList = list;
    notifyDataSetChanged();
  }

  public void setItemActionListener(ActionListener<FavouriteItem> itemActionListener) {
    this.itemActionListener = itemActionListener;
  }

  interface ActionListener<T> {
    void onClickedItem(FavouriteItem item);

    void onClickedFavourite(FavouriteItem item);

    void onItemClickedBuyNow(FavouriteItem item);

    void onItemClickedAddToCart(List<FavouriteItem> dataList,int position);

  }
}
