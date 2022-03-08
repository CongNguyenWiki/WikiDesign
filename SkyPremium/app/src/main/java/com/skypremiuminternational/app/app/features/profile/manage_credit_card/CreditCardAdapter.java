package com.skypremiuminternational.app.app.features.profile.manage_credit_card;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.skypremiuminternational.app.R;
import com.skypremiuminternational.app.app.utils.listener.ItemEditClickListener;
import com.skypremiuminternational.app.domain.models.creditCard.CardItem;


import java.util.ArrayList;
import java.util.List;


public class CreditCardAdapter extends RecyclerView.Adapter<CreditCardViewHolder> {

  private List<CardItem> dataList = new ArrayList<>();
  private ItemEditClickListener<CardItem> itemEditClickListener;


  public void setDataList(List<CardItem> dataList) {
    this.dataList = dataList;
    notifyDataSetChanged();
  }

  public void setItemEditClickListener(ItemEditClickListener<CardItem> itemEditClickListener) {
    this.itemEditClickListener = itemEditClickListener;
  }

  @Override
  public CreditCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_credit_card, parent, false);
    final CreditCardViewHolder holder = new CreditCardViewHolder(view);

    holder.txtEdit.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (itemEditClickListener != null
            && holder.getAdapterPosition() != RecyclerView.NO_POSITION) {
          itemEditClickListener.onItemEditClicked(dataList.get(holder.getAdapterPosition()));
        }
      }
    });

    return holder;
  }

  @Override
  public void onBindViewHolder(CreditCardViewHolder holder, int position) {
    holder.bind(dataList.get(position));
  }

  @Override
  public int getItemCount() {
    return dataList != null ? dataList.size() : 0;
  }
}
