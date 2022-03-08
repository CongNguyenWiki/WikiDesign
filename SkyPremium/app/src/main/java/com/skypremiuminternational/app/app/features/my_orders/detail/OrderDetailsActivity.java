package com.skypremiuminternational.app.app.features.my_orders.detail;

import static com.skypremiuminternational.app.app.utils.DecimalUtil.roundTwoDecimals;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.skypremiuminternational.app.R;
import com.skypremiuminternational.app.app.internal.mvp.BaseActivity;
import com.skypremiuminternational.app.app.utils.Constants;
import com.skypremiuminternational.app.app.utils.DataUtils;
import com.skypremiuminternational.app.app.utils.ErrorMessageFactory;
import com.skypremiuminternational.app.app.utils.Validator;
import com.skypremiuminternational.app.domain.models.ean.ISOCountry;
import com.skypremiuminternational.app.domain.models.my_orders.Address;
import com.skypremiuminternational.app.domain.models.my_orders.MyOrderItem;
import com.skypremiuminternational.app.domain.models.my_orders.Payment;
import com.skypremiuminternational.app.domain.models.my_orders.billingaddress.BillingAddress;
import com.skypremiuminternational.app.domain.models.my_orders.detail.ItemsItem;
import com.skypremiuminternational.app.domain.models.my_orders.detail.OrderDetailResponse;
import com.skypremiuminternational.app.domain.models.permissions.PermissionProfileItem;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;



public class OrderDetailsActivity extends BaseActivity<OrderDetailsPresenter>
    implements OrderDetailsView<OrderDetailsPresenter> {
  private static final int REQUEST_CODE_WRITE_EXTERNAL = 123;

  @BindView(R.id.txt_title_billing)
  TextView tvBillingAddress;
  @BindView(R.id.txt_billing_customer_name)
  TextView tvBillingCustomerName;
  @BindView(R.id.txt_billing_customer_phone)
  TextView tvBillingCustomerPhone;
  @BindView(R.id.txt_billing_customer_address)
  TextView tvBillingCustomerAddress;
  @BindView(R.id.layout_root)
  ViewGroup layoutRoot;
  @BindView(R.id.txt_shipping_customer_name)
  TextView txtCustomerName;
  @BindView(R.id.txt_shipping_phone)
  TextView txtShippingPhone;
  @BindView(R.id.txt_shipping_address)
  TextView txtShippingAddress;
  @BindView(R.id.txt_credit_card)
  TextView txtCreditCard;
  @BindView(R.id.img_visa)
  ImageView imgCreditCard;
  @BindView(R.id.recycler_product)
  RecyclerView recyclerProduct;
  @BindView(R.id.txt_processing_state)
  TextView txtState;
  @BindView(R.id.txt_amt_grand_total)
  TextView txtGrandTotal;
  @BindView(R.id.tvTitle_toolbar)
  TextView txtToolbarTitle;
  @BindView(R.id.tvTitle_toolbar_amount)
  TextView txtToolbarAmount;
  @BindView(R.id.tvTitle_toolbar_status)
  TextView tvToolbarStatus;
  @BindView(R.id.txt_amt_subtotal)
  TextView txtAmtSubtotal;
  @BindView(R.id.layout_discount)
  FrameLayout layoutDiscount;
  @BindView(R.id.txt_discount_type)
  TextView txtDiscountType;
  @BindView(R.id.txt_amt_discount)
  TextView txtDiscountAmount;
  @BindView(R.id.txt_amt_sky_dollar_earn)
  TextView txtSkyoDollarAmount;
  @BindView(R.id.layout_shipping_fee)
  FrameLayout layoutShippingFee;
  @BindView(R.id.txt_amt_shipping_fee)
  TextView txtShippingFee;
  @BindView(R.id.layout_tax)
  FrameLayout layoutTax;
  @BindView(R.id.txt_amt_tax)
  TextView txtAmtTax;
  @BindView(R.id.layout_delivery_fee)
  FrameLayout layoutDeliveryFee;
  @BindView(R.id.txt_amt_delivery_fee)
  TextView txtAmtDeliveryFee;
  @Inject
  ErrorMessageFactory errorMessageFactory;

  @BindView(R.id.txt_title_delivery_address)
  TextView txt_title_delivery_address;

  @BindView(R.id.layout_discount_promocode)
  FrameLayout layout_discount_promocode;
  @BindView(R.id.txt_discount_type_promocode)
  TextView txt_discount_type_promocode;
  @BindView(R.id.txt_amt_discount_promocode)
  TextView txt_amt_discount_promocode;
  @BindView(R.id.txt_print)
  TextView txtPrint;


  private MyOrderItem item;
  private ProgressDialog progressDialog;
  private OrderDetailItemAdapter adapter;
  private List<ISOCountry> countryCodeList;


  List<Bitmap> bitmapList = new ArrayList<>();

  public static void startMe(Context context) {
    Intent intent = new Intent(context, OrderDetailsActivity.class);
    context.startActivity(intent);
  }

  public static void startMe(Context context, MyOrderItem item) {
    Intent intent = new Intent(context, OrderDetailsActivity.class);
    intent.putExtra("orderObj", new Gson().toJson(item));
    context.startActivity(intent);
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    AndroidInjection.inject(this);
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_order_details);
    ButterKnife.bind(this);
    DataUtils dataUtils = new DataUtils(this);
    dataUtils.readObject("LIST");

    setupRecyclerView();
    String jsonMyObject = null;
    Bundle bundle = getIntent().getExtras();
    if (bundle != null) {
      jsonMyObject = bundle.getString("orderObj");
    }
    item = new Gson().fromJson(jsonMyObject, MyOrderItem.class);

    setupToolbar();
    progressDialog = new ProgressDialog(this);
    progressDialog.setCancelable(false);

    presenter.getCountry(item.getEntityId());
    presenter.getPermissionProfile();
  }

  private void setupToolbar() {
    txtToolbarTitle.setText(getString(R.string.title_order_id));
    txtToolbarAmount.setText(String.valueOf(item.getIncrementId()));
  }

  private void setupRecyclerView() {
    recyclerProduct.setNestedScrollingEnabled(false);
    recyclerProduct.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
    adapter = new OrderDetailItemAdapter(getSupportFragmentManager());
    recyclerProduct.setAdapter(adapter);
    recyclerProduct.setHasFixedSize(true);
  }

  @Inject
  @Override
  public void injectPresenter(OrderDetailsPresenter presenter) {
    super.injectPresenter(presenter);
  }

  @OnClick(R.id.ivNavigation_toolbar)
  void onClickBack() {
    finish();
  }

  @Override
  public void showLoading(String message) {
    if (!this.isDestroyed()) {
      progressDialog.setMessage(message);
      progressDialog.show();
    }
  }

  @Override
  public void render(Throwable error) {
    if (!this.isDestroyed()) {
      new AlertDialog.Builder(this).setMessage(errorMessageFactory.getErrorMessage(error))
          .setCancelable(false)
          .setPositiveButton(R.string.btn_label_ok, (dialog, which) -> dialog.dismiss())
          .show();
    }
  }

  @Override
  public void render(OrderDetailResponse orderDetail) {
    if (orderDetail != null) {

      if(orderDetail.getPayment().getMethod().contains("free")){
        txtCreditCard.setVisibility(View.VISIBLE);
        txtCreditCard.setText(getString(R.string.card_selection_not_required_title));
        txt_title_delivery_address.setVisibility(View.VISIBLE);
        txtCustomerName.setVisibility(View.VISIBLE);
        txtShippingPhone.setVisibility(View.VISIBLE);
        txtShippingAddress.setVisibility(View.VISIBLE);
      }else {
        txtCreditCard.setVisibility(View.VISIBLE);
        txtCustomerName.setVisibility(View.VISIBLE);
        txtShippingPhone.setVisibility(View.VISIBLE);
        txtShippingAddress.setVisibility(View.VISIBLE);
      }

        Payment payment = orderDetail.getPayment();
        String creditCardNumber;
        String type;

        if (orderDetail.getExtensionAttributes().getShippingAssignments().size() > 0) {
          Address address = orderDetail.getExtensionAttributes()
                  .getShippingAssignments()
                  .get(0)
                  .getShipping()
                  .getAddress();
          if (address != null) {
            renderShippingAddress(address);
          }
        }

        BillingAddress billingAddress = orderDetail.getBillingAddress();
        if (billingAddress != null) {
          showBillingAddressView(true);
          renderBillingAddress(billingAddress);
        } else {
          showBillingAddressView(false);
        }

        if (payment != null) {
          if (payment.getCcLast4() != null) {
            creditCardNumber = payment.getCcLast4();
          } else {
            creditCardNumber = getString(R.string.txt_null_creditcard);
          }
        } else {
          creditCardNumber = getString(R.string.txt_null_creditcard);
        }
      if(orderDetail.getPayment().getMethod().contains("free")){
        txtCreditCard.setVisibility(View.VISIBLE);
        txtCreditCard.setText(getString(R.string.no_payment_information_required));
        txtCreditCard.setTextSize(13);
      }else {
        txtCreditCard.setText(
                String.format("%s %s", getString(R.string.txt_first_sign_creditcard), creditCardNumber));
      }

        type = payment.getCcType();
        if (type != null) {
          if (!TextUtils.isEmpty(type)) {
            if (type.equalsIgnoreCase(Constants.VISA_BRAND)) {
              imgCreditCard.setImageResource(R.drawable.ic_visa_blue_background);
            } else if (type.equalsIgnoreCase(Constants.MASTER_BRAND)) {
              imgCreditCard.setImageResource(R.drawable.ic_master_pay);
            } else {
              imgCreditCard.setImageResource(R.drawable.ic_american_express_blue_background);
            }
          } else {
            imgCreditCard.setVisibility(View.INVISIBLE);
          }
        } else {
          imgCreditCard.setVisibility(View.INVISIBLE);
        }


      String state = orderDetail.getStatus();
      String paymentStatus = orderDetail.getPaymentStatus();
      if(paymentStatus!=null){
        txtState.setVisibility(View.VISIBLE);
        txtState.setText(orderDetail.getPaymentStatus());
      }else {
        txtState.setVisibility(View.GONE);
      }

      if (state != null) {
        if (!TextUtils.isEmpty(state)) {
          tvToolbarStatus.setVisibility(View.VISIBLE);
          String status ="";
          if (item.getStatus().equalsIgnoreCase(Constants.ORDER_PENDING)) {
            status = getResources().getString(R.string.txt_delivering);
          } else if (item.getStatus().equalsIgnoreCase(Constants.ORDER_COMPLETE)) {
            status = getResources().getString(R.string.txt_collected);
          } else if (item.getStatus().equalsIgnoreCase(Constants.ORDER_HOLDED)) {
            status = getResources().getString(R.string.txt_holded);
          } else if (item.getStatus().equalsIgnoreCase(Constants.ORDER_PROCESSING)) {
            status = getResources().getString(R.string.txt_processing);
          } else if (item.getStatus().equalsIgnoreCase(Constants.ORDER_CLOSED)) {
            status = getResources().getString(R.string.txt_closed);
          } else if (item.getStatus().equalsIgnoreCase(Constants.ORDER_FAIL)) {
            status = getResources().getString(R.string.txt_fail);
          } else if (item.getStatus().equalsIgnoreCase(Constants.ORDER_SHIPPING)) {
            status = getResources().getString(R.string.txt_shipping);
          } else if (item.getStatus().equalsIgnoreCase(Constants.ORDER_CANCELLED)) {
            status = getResources().getString(R.string.txt_cancelled);
          }
          String output = String.format("(%s)", status);
          tvToolbarStatus.setText(output);
        } else {
          txtState.setVisibility(View.INVISIBLE);
          tvToolbarStatus.setVisibility(View.GONE);
        }
      } else {
        txtState.setVisibility(View.INVISIBLE);
      }
      // === Subtotals
      /*if (orderDetail.getSubtotal() != null && !TextUtils.isEmpty(orderDetail.getSubtotal())) {

        double subTotals  = 0;

        for(ItemsItem item : orderDetail.getItems()){
          subTotals += item.getQtyOrdered() * (Double.parseDouble(item.getPrice()) + Double.parseDouble(item.getTaxAmount()));
        }

        txtAmtSubtotal.setText(
            String.format("S$%s", roundTwoDecimals(subTotals)));

      } else {
        txtAmtSubtotal.setText(
            String.format("S$%s", roundTwoDecimals(0)));
      }*/
      double subTotals  = 0;
      if(orderDetail !=null){
        for(ItemsItem item : orderDetail.getItems()){
          subTotals += item.getQtyOrdered() * (Double.parseDouble(item.getPrice()))+ Double.parseDouble(item.getTaxAmount());
        }
      }

      txtAmtSubtotal.setText(
          String.format("S$%s", roundTwoDecimals(subTotals)));
      // === Subtotals

      // === Grand totals
      if (orderDetail.getGrandTotal() != null && !TextUtils.isEmpty(orderDetail.getGrandTotal())) {
        txtGrandTotal.setText(
            String.format("S$%s",
                roundTwoDecimals(Double.parseDouble(orderDetail.getGrandTotal()))));
      } else {
        txtGrandTotal.setText(
            String.format("S$%s", roundTwoDecimals(0)));
      }
      // === Grand totals

      // === promocode
      if (orderDetail.getCouponCode() != null && !TextUtils.isEmpty(
          orderDetail.getCouponCode()) && !orderDetail.getExtensionAttributes().getIsApplyCouponFreeShipping().equalsIgnoreCase("1")) {
        // discount type == coupon code
        layout_discount_promocode.setVisibility(View.VISIBLE);
        txt_discount_type_promocode.setText(R.string.hint_promocode);

        String discountAmount = orderDetail.getDiscountAmount();
        if (discountAmount.startsWith("-")) {
          discountAmount = discountAmount.substring(1);
        }

        txt_amt_discount_promocode.setText(
            String.format("-S$%s", roundTwoDecimals(Double.parseDouble(discountAmount))));
      } else if (orderDetail.getCouponCode() != null && !TextUtils.isEmpty(
          orderDetail.getCouponCode()) && orderDetail.getExtensionAttributes().getIsApplyCouponFreeShipping().equalsIgnoreCase("1")) {
        // discount type == coupon code
        layout_discount_promocode.setVisibility(View.VISIBLE);
        txt_discount_type_promocode.setText(R.string.hint_promocode);

        txt_amt_discount_promocode.setText(orderDetail.getCouponCode() + " " + "APPLIED");
      }// === promocode

      // === sky dollar redeem
      if (orderDetail.getTotalLoyaltyValueRedeemed() != null && !TextUtils.isEmpty(
          orderDetail.getTotalLoyaltyValueRedeemed())) {

        try {
          // discount type == coupon code
          if (Float.parseFloat(orderDetail.getTotalLoyaltyValueRedeemed()) > 0) {
            layoutDiscount.setVisibility(View.VISIBLE);
            txtDiscountType.setText(R.string.hint_loyalty_sky_dollars_redeemed);
            String discountAmount = "S$" + roundTwoDecimals(
                Double.parseDouble(orderDetail.getTotalLoyaltyValueRedeemed()));
            txtDiscountAmount.setText(discountAmount);
          } else {
            layoutDiscount.setVisibility(View.GONE);
          }
        } catch (Exception e) {
          layoutDiscount.setVisibility(View.GONE);
        }

      } else {
        layoutDiscount.setVisibility(View.GONE);
        layout_discount_promocode.setVisibility(View.GONE);
      }
      // === sky dollar redeem

      // === sky dollar
      if(orderDetail.getExtensionAttributes() != null ){
        txtSkyoDollarAmount.setVisibility(View.VISIBLE);
        txtSkyoDollarAmount.setText(String.format("S$%s",roundTwoDecimals(orderDetail.getExtensionAttributes().getTotalSkyDollarEarn())));
      }
      // === sky dollar




      // ==== Shipping fee
//      if (orderDetail.getShippingFee() != null && !TextUtils.isEmpty(
//          orderDetail.getShippingFee())) {
//        try {
//          if (Float.parseFloat(orderDetail.getShippingFee()) > 0) {
//            layoutShippingFee.setVisibility(View.VISIBLE);
//            txtShippingFee.setText(String.format("S$%s",
//                roundTwoDecimals(Double.parseDouble(orderDetail.getShippingFee()))));
//          } else {
//            layoutShippingFee.setVisibility(View.GONE);
//          }
//        } catch (Exception e) {
//          layoutShippingFee.setVisibility(View.GONE);
//        }
//      } else {
//        layoutShippingFee.setVisibility(View.GONE);
//      }

      layoutShippingFee.setVisibility(View.GONE);
      // ==== Shipping fee


      // ==== Delivery fee
      if (orderDetail.getDeliveryFee() != null && !TextUtils.isEmpty(
          orderDetail.getDeliveryFee())) {
        try {
            layoutDeliveryFee.setVisibility(View.VISIBLE);
            txtAmtDeliveryFee.setText(String.format("S$%s",
                roundTwoDecimals(Double.parseDouble(orderDetail.getDeliveryFee()))));

        } catch (Exception e) {
          layoutDeliveryFee.setVisibility(View.GONE);
        }
      } else {
        layoutDeliveryFee.setVisibility(View.GONE);
      }
      // ==== Delivery fee

      // === Tax
      if (orderDetail.getTaxAmount() != null && !TextUtils.isEmpty(
          orderDetail.getTaxAmount())) {
        if (orderDetail.getTaxAmount().equalsIgnoreCase("0")) {
          layoutTax.setVisibility(View.GONE);
        } else {
          layoutTax.setVisibility(View.VISIBLE);
          txtAmtTax.setText(
              String.format("S$%s",
                  roundTwoDecimals(Double.parseDouble(orderDetail.getTaxAmount()))));
        }
      } else {
        layoutTax.setVisibility(View.GONE);
      }


      // === Tax

    }
    adapter.setDataList(orderDetail.getItems());
    adapter.setStatus(item.getStatus());
  }

  private void showBillingAddressView(boolean visible) {
    int visibility = visible ? View.VISIBLE : View.GONE;
    tvBillingAddress.setVisibility(visibility);
    tvBillingCustomerAddress.setVisibility(visibility);
    tvBillingCustomerName.setVisibility(visibility);
    tvBillingCustomerPhone.setVisibility(visibility);
  }

  private void renderBillingAddress(BillingAddress address) {

    String unitNumber = "";
    String countryName = null;
    String stateName = "";
    String company = "";
    String city = "";

    company = address.getCompany();
    city = address.getCity();
    if (address.getExtensionAttributes() != null
        && address.getExtensionAttributes().getUnitNumber() != null) {
      unitNumber = address.getExtensionAttributes().getUnitNumber();
    }
    String salutation = address.getPrefix() == null ? "" : address.getPrefix();
//    if (Validator.isTextValid(salutation) && !salutation.endsWith(".")) {
//      salutation += ".";
//    }

    tvBillingCustomerName.setText(salutation + " " +
        String.format("%s %s",address.getFirstname(), address.getLastname()));
    if (address.getTelephone() == null){
      tvBillingCustomerPhone.setText("");
    }else {
      tvBillingCustomerPhone.setText(String.format("%s", address.getTelephone()));
    }

    if (countryCodeList != null && countryCodeList.size() > 0) {
      for (ISOCountry countryCode : countryCodeList) {
        if (address.getCountryId() != null && countryCode.country_code.equalsIgnoreCase(
            address.getCountryId().trim())) {
          countryName = countryCode.country_name;
          break;
        }
      }
    }

    if (Validator.isTextValid(address.getRegion())) {
      stateName = address.getRegion();
    }

    StringBuilder streets = new StringBuilder();
    if (address.getStreet() != null && address.getStreet().size() > 0) {
      for (String street : address.getStreet()) {
        streets.append(street);
        streets.append(", ");
      }

      if (streets.length() > 0) {
        String str = streets.substring(0, streets.lastIndexOf(","));
        String fullAdd ="";
        fullAdd = company != null ? company : "";

        if (unitNumber != null && !TextUtils.isEmpty(unitNumber)) {
          if (company != null && !TextUtils.isEmpty(company) && !company.equalsIgnoreCase("-") ) {
            fullAdd += "\n" + unitNumber;
          }else {
            fullAdd += "" + unitNumber;
          }
        }else if (unitNumber.equalsIgnoreCase("-")){

        }
        if (str != null && !TextUtils.isEmpty(str)) {
          if (unitNumber != null && !TextUtils.isEmpty(unitNumber) && !unitNumber.equalsIgnoreCase("-")) {
            fullAdd += " " + str;
          }else {
            fullAdd += "" + str;
          }
        }

        if (city != null && !TextUtils.isEmpty(city) && !city.equalsIgnoreCase("-")) {
          fullAdd += "\n" + city;
        }
        if (stateName != null && !TextUtils.isEmpty(stateName) && !stateName.equalsIgnoreCase("-")) {
          fullAdd += " " + stateName;
        }
        if (countryName != null && !TextUtils.isEmpty(countryName) && !countryName.equalsIgnoreCase("-")) {

          fullAdd += "\n" + countryName;
        }
        if (address.getPostcode() != null && !TextUtils.isEmpty(address.getPostcode()) && !address.getPostcode().equalsIgnoreCase("-")) {
          fullAdd += " " + address.getPostcode();
        }

        tvBillingCustomerAddress.setText(fullAdd);
      }
    }
  }

  private void renderShippingAddress(Address address) {

    String unitNumber = "";
    String countryName = null;
    String stateName = "";
    String company = "";
    String city = "";

    city  = address.getCity();
    company = address.getCompany();
    stateName = address.getRegion();
    if (address.getOrderDetailAddExtenshion() != null
        && address.getOrderDetailAddExtenshion().getUnitNumber() != null) {
      if (!TextUtils.isEmpty(address.getOrderDetailAddExtenshion().getUnitNumber())) {
        unitNumber = address.getOrderDetailAddExtenshion().getUnitNumber();
      }
    }
    if (countryCodeList != null && countryCodeList.size() > 0) {
      for (ISOCountry countryCode : countryCodeList) {
        if (address.getCountryId() != null && countryCode.country_code.equalsIgnoreCase(
            address.getCountryId().trim())) {
          countryName = countryCode.country_name;
          break;
        }
      }
    }

    String salutation = address.getPrefix()  == null ? "" : address.getPrefix();
//    if (Validator.isTextValid(salutation) && !salutation.endsWith(".")) {
//      salutation += ".";
//    }
    txtCustomerName.setText(salutation + " " +
        String.format("%s %s",address.getFirstname(), address.getLastname()));
    if (address.getTelephone() == null){
      txtShippingPhone.setText("");

    }else {
      txtShippingPhone.setText(address.getTelephone());

    }

    StringBuilder streets = new StringBuilder();
    if (address.getStreet() != null && address.getStreet().size() > 0) {
      for (String street : address.getStreet()) {
        streets.append(street);
        streets.append(", ");
      }

      if (streets.length() > 0) {
        String str = streets.substring(0, streets.lastIndexOf(","));
        String fullAdd ="";
        if(company !=null && !TextUtils.isEmpty(company) && !company.equalsIgnoreCase("-")){
          fullAdd = company;
        }

        if (unitNumber != null && !TextUtils.isEmpty(unitNumber) && !unitNumber.equalsIgnoreCase("-")) {
          if (company != null && !TextUtils.isEmpty(company) && !company.equalsIgnoreCase("-")) {
            fullAdd += "\n" + unitNumber;
          }else {
            fullAdd += "" + unitNumber;
          }
        }
        if (str != null && !TextUtils.isEmpty(str)) {
          if (unitNumber != null && !TextUtils.isEmpty(unitNumber) && !unitNumber.equalsIgnoreCase("-")) {
            fullAdd += " " + str;
          }else {
            fullAdd += "" + str;
          }
        }

        if (city != null && !TextUtils.isEmpty(city) && !city.equalsIgnoreCase("-")) {
          fullAdd += "\n" + city;
          txtShippingAddress.setText(fullAdd);
        }
        if (stateName != null && !TextUtils.isEmpty(stateName) && !stateName.equalsIgnoreCase("-")) {
          fullAdd += " " + stateName;
          txtShippingAddress.setText(fullAdd);
        }
        if (countryName != null && !TextUtils.isEmpty(countryName) && !countryName.equalsIgnoreCase("-")) {

          fullAdd += "\n" + countryName;
          txtShippingAddress.setText(fullAdd);
        }
        if (address.getPostcode() != null && !TextUtils.isEmpty(address.getPostcode()) && !address.getPostcode().equalsIgnoreCase("-")) {
          fullAdd += " " + address.getPostcode();
          txtShippingAddress.setText(fullAdd);
        }

      }
    }
  }

  @Override
  public void render(List<ISOCountry> countryCodeList) {
    this.countryCodeList = countryCodeList;
  }

  @Override
  public void renderListPermission(List<PermissionProfileItem> value) {

//    for (PermissionProfileItem item : value){
//      if (item.getNavItem().equalsIgnoreCase("My Orders")){
//
//        txtPrint.setOnClickListener(new View.OnClickListener() {
//          @Override
//          public void onClick(View v) {
//            if (item.getGetPermissionCanUse().equalsIgnoreCase("allow")){
//
//                if (isPermissionGranted()) {
//                  takeScreenshot();
//                } else if (ActivityCompat.shouldShowRequestPermissionRationale(OrderDetailsActivity.this,
//                        Manifest.permission.ACCESS_FINE_LOCATION)) {
//                  showRationaleAndRequestPermission();
//                } else {
//                  ActivityCompat.requestPermissions(OrderDetailsActivity.this,
//                          new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
//                          REQUEST_CODE_WRITE_EXTERNAL);
//                }
//
//            }
//            else {
//              showDialogNotUse();
//            }
//          }
//        });
//      }
//    }

  }

  public void showDialogNotUse() {
    new android.app.AlertDialog.Builder(this).setMessage("We are sorry. You do not have the permissions to utilize this function.")
            .setCancelable(false)
            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

              }
            })
            .show();
  }


  @Override
  public void hideLoading() {
    if (!this.isDestroyed() && progressDialog.isShowing()) {
      progressDialog.dismiss();
    }
  }

  public boolean isPermissionGranted() {
    return ContextCompat.checkSelfPermission(this,
        Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
  }

  @OnClick(R.id.txt_print)
  void onClickedPrint() {
    if (isPermissionGranted()) {
      takeScreenshot();
    } else if (ActivityCompat.shouldShowRequestPermissionRationale(this,
        Manifest.permission.ACCESS_FINE_LOCATION)) {
      showRationaleAndRequestPermission();
    } else {
      ActivityCompat.requestPermissions(this,
          new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
          REQUEST_CODE_WRITE_EXTERNAL);
    }
  }

  private void showRationaleAndRequestPermission() {
    Toast.makeText(this, "Plz grant permission", Toast.LENGTH_SHORT).show();
    ActivityCompat.requestPermissions(this,
        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
        REQUEST_CODE_WRITE_EXTERNAL);
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                         @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    switch (requestCode) {
      case REQUEST_CODE_WRITE_EXTERNAL: {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
          takeScreenshot();
        }
      }
    }
  }

  private void takeScreenshot() {
    Date now = new Date();
    android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);

    try {
      String mPath = null;
      Bitmap bitmap = loadBitmapFromView(layoutRoot, layoutRoot.getWidth(), layoutRoot.getHeight());
      if (bitmap != null) {
        mPath = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "title", null);
      }

      openScreenshot(mPath);
    } catch (Throwable e) {
      e.printStackTrace();
    }
  }

  private void openScreenshot(String path) {
    Intent intent = new Intent();
    intent.setAction(Intent.ACTION_VIEW);
    Uri uri = Uri.parse(path);
    intent.setData(uri);
    startActivity(intent);
  }

  private Bitmap loadBitmapFromView(View v, int width, int height) {
    Bitmap b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
    Canvas c = new Canvas(b);
    v.draw(c);
    return b;
  }


  public void setDataBitmap(List<Bitmap> bitmapList){
    this.bitmapList = bitmapList;
  }


  public List<Bitmap> getDataBitmap(){
    return this.bitmapList;
  }
  public void addBitmap(Bitmap bitmap){
    this.bitmapList.add(bitmap);
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    DataUtils dataUtils = new DataUtils(this);
    dataUtils.writeObject("LIST",bitmapList);
  }
}
