package com.skypremiuminternational.app.app.features.shopping_cart;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;



import com.skypremiuminternational.app.R;
import com.skypremiuminternational.app.app.utils.AspectRatioImageView;
import com.skypremiuminternational.app.app.utils.DecimalUtil;
import com.skypremiuminternational.app.app.utils.ImageUtils;
import com.skypremiuminternational.app.domain.models.cart.CartDetailItem;

import butterknife.BindView;
import butterknife.ButterKnife;



public class ShoppingCartViewHolder extends RecyclerView.ViewHolder {
  @BindView(R.id.img_product)
  AspectRatioImageView imgProduct;
  @BindView(R.id.txt_product_name)
  TextView txtProductName;
  @BindView(R.id.txt_kind_of_product)
  TextView txtKindOfProduct;
  @BindView(R.id.txt_product_amount)
  TextView txtProductAmount;
  @BindView(R.id.eidt_qty)
  EditText editQty;
  @BindView(R.id.txt_amount)
  TextView txtAmount;
  @BindView(R.id.layout_main)
  LinearLayout layoutMain;
  @BindView(R.id.tv_loyaltyValue)
  TextView tvLoyaltyValue;

  public ShoppingCartViewHolder(View itemView) {
    super(itemView);
    ButterKnife.bind(this, itemView);
  }

  public void bind(CartDetailItem response, int position, boolean containVirtualProduct,
                   boolean shouldRequestFocus) {
    if (shouldRequestFocus) {
      editQty.requestFocus();
    }

    if ((position % 2) != 0) {
      layoutMain.setBackgroundColor(ContextCompat.getColor(layoutMain.getContext(), R.color.white));
    } else {
      layoutMain.setBackgroundColor(
          ContextCompat.getColor(layoutMain.getContext(), R.color.background));
    }


    ImageUtils.glideImgDefaulSky(imgProduct,"");


    txtProductName.setText(response.getName());
    txtProductAmount.setText(
        String.format("S$%s",
            DecimalUtil.roundTwoDecimals(Double.parseDouble(response.getPrice()))));
    editQty.setText(String.valueOf(response.getQty()));
    String amount =
        DecimalUtil.roundTwoDecimals((response.getQty() * Double.parseDouble(response.getPrice())));
    txtAmount.setText(String.format("S$%s", amount));
    if (containVirtualProduct) {
      editQty.setEnabled(false);
    } else {
      editQty.setEnabled(response.isQtyEditable());
    }
  }
}
