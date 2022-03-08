package com.skypremiuminternational.app.app.features.profile.manage_credit_card;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.skypremiuminternational.app.R;
import com.skypremiuminternational.app.app.utils.Constants;
import com.skypremiuminternational.app.domain.models.creditCard.CardItem;

import butterknife.BindView;
import butterknife.ButterKnife;



public class CreditCardViewHolder extends RecyclerView.ViewHolder {

  @BindView(R.id.tvCardNumber)
  TextView tvCardNumber;
  @BindView(R.id.tvCardDate)
  TextView tvCardDate;
  @BindView(R.id.txtEdit)
  TextView txtEdit;
  @BindView(R.id.imgCardBrand)
  ImageView imgCardBrand;
  @BindView(R.id.tvName)
  TextView tvName;
  @BindView(R.id.tvPostalCode)
  TextView tvPostalCode;
  @BindView(R.id.tvAddress)
  TextView tvAddress;
  @BindView(R.id.tvCountryName)
  TextView tvCountryName;
  @BindView(R.id.tvState)
  TextView tvState;
  @BindView(R.id.imgDefautCard)
  ImageView imgDefautCard;




  public CreditCardViewHolder(final View itemView) {
    super(itemView);
    ButterKnife.bind(this, itemView);
  }

  public void bind(CardItem item) {
    if (item.getBrand() != null) {
      if (item.getBrand().equalsIgnoreCase(Constants.VISA_BRAND)) {
        imgCardBrand.setImageResource(R.drawable.ic_visa_blue_background);
      } else if (item.getBrand().equalsIgnoreCase(Constants.MASTER_BRAND)) {
        imgCardBrand.setImageResource(R.drawable.ic_master_card_with_text);
      } else {
        imgCardBrand.setImageResource(R.drawable.ic_american_express_blue_background);
      }
    } else {
      imgCardBrand.setImageResource(R.drawable.ic_logo_visa);
    }
    if (item.getDefault() == true){
      imgDefautCard.setVisibility(View.VISIBLE);
    }
    else {
      imgDefautCard.setVisibility(View.GONE);
    }
    tvCardNumber.setText("XXXX XXXX XXXX " + item.getLast4());
    tvCardDate.setText("Expiritation: "+item.getExpMonth() + "/" + item.getExpYear());
    tvName.setText(item.getName());
    tvPostalCode.setText(item.getPostalCode());
    if(item.getCity() == null || item.getCity().equalsIgnoreCase("-")){
      tvAddress.setText("" + item.getAddressStreet1());
    }else if (item.getPostalCode() == null || item.getPostalCode().equalsIgnoreCase("-")){
      tvAddress.setText(""+ item.getCity() + "" + item.getAddressStreet1());
    }else if (item.getAddressStreet1() == null || item.getAddressStreet1().equalsIgnoreCase("-")){
      tvAddress.setText( item.getCity() + "");
    }else {
      tvAddress.setText(item.getCity() + "-" + item.getAddressStreet1());
    }
    tvCountryName.setText(item.getCountryName());
    tvState.setText(item.getState());


  }
}
