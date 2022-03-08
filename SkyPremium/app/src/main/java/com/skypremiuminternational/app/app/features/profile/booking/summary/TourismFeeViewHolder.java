package com.skypremiuminternational.app.app.features.profile.booking.summary;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.skypremiuminternational.app.R;
import com.skypremiuminternational.app.domain.models.booking.TourismFee;


import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;



public class TourismFeeViewHolder extends RecyclerView.ViewHolder {

  @BindView(R.id.tv_tourism_fee)
  TextView tvTourismFee;
  @BindView(R.id.tv_tourism_fee_label)
  TextView tvLabel;

  public TourismFeeViewHolder(View itemView) {
    super(itemView);
    ButterKnife.bind(this, itemView);
  }

  public void bind(TourismFee tf) {
    if(tf.feesType().equalsIgnoreCase("mandatory_tax")){
      tvLabel.setText("mandatory tax");
    }
    DecimalFormat df = new DecimalFormat("###,###,##0.00");
    try {
      tvTourismFee.setText(tf.currency() + " " +df.format(Double.parseDouble(tf.price())));
    }catch (NullPointerException ex){
      tvTourismFee.setText(tf.currency() + " " +tf.price());
    }
  }
}
