package com.skypremiuminternational.app.app.features.profile.my_reservation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.skypremiuminternational.app.R;
import com.skypremiuminternational.app.domain.models.reservation.ReserveHistoryItem;

import java.util.List;

public class MyReservationApdapter extends RecyclerView.Adapter<MyReservationViewHolder> {


  Context context;
  List<ReserveHistoryItem> historyItemList;
  ActionListener actionListener;

  @NonNull
  @Override
  public MyReservationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reservation,parent,false);
    MyReservationViewHolder holder = new MyReservationViewHolder(view);

    return holder;
  }

  @Override
  public void onBindViewHolder(@NonNull MyReservationViewHolder holder, int position) {
    holder.bind(historyItemList.get(position));
    holder.itemView.setOnClickListener(v -> {
      actionListener.onItemClicked(historyItemList.get(position).getHgwId(),historyItemList.get(position).getExtensionData().get(0).getReservation_type());

    });
  }

  @Override
  public int getItemCount() {
    return historyItemList != null ? historyItemList.size() : 0;
  }


  public  void setData(List<ReserveHistoryItem> list){
    this.historyItemList = list;
    notifyDataSetChanged();
  }


  public void setActionListener(ActionListener actionListener) {
    this.actionListener = actionListener;
  }

  public interface ActionListener {
    void onItemClicked(String bookingId,String typeReservation);

  }

}
