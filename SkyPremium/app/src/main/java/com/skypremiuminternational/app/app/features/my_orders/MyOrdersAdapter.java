package com.skypremiuminternational.app.app.features.my_orders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.skypremiuminternational.app.R;
import com.skypremiuminternational.app.app.features.my_orders.listener.ItemClickListener;
import com.skypremiuminternational.app.domain.models.my_orders.MyOrderItem;

import java.util.List;

public class MyOrdersAdapter extends RecyclerView.Adapter<MyOrdersViewHolder> {



  List<MyOrderItem> data;
  private ItemClickListener<MyOrderItem> itemClickListener;

  public void setItemClickListener(ItemClickListener<MyOrderItem> itemClickListener) {
    this.itemClickListener = itemClickListener;
  }

  @NonNull
  @Override
  public MyOrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_orders,parent,false);
    MyOrdersViewHolder holder = new MyOrdersViewHolder(view);

    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (itemClickListener != null
            && holder.getAdapterPosition() != RecyclerView.NO_POSITION) {
          itemClickListener.onItemClicked(data.get(holder.getAdapterPosition()));
        }
      }
    });

    return holder;
  }

  @Override
  public void onBindViewHolder(@NonNull MyOrdersViewHolder holder, int position) {
    holder.bind(this.data.get(position));
  }



  public void setData(List<MyOrderItem> list){
    this.data =  list;
    notifyDataSetChanged();
  }


  @Override
  public int getItemCount() {
    return data == null ? 0 : data.size();
  }
}
