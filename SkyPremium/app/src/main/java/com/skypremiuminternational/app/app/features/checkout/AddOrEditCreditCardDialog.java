package com.skypremiuminternational.app.app.features.checkout;

import static android.text.TextUtils.isEmpty;

import static com.skypremiuminternational.app.app.utils.Constants.REGEX_VALIDATION_CREDITCARD;
import static com.skypremiuminternational.app.app.utils.Constants.REGEX_VALIDATION_FULLNAME;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.skypremiuminternational.app.R;
import com.skypremiuminternational.app.app.utils.Constants;
import com.skypremiuminternational.app.app.utils.KBUtil;
import com.skypremiuminternational.app.app.view.SkyTextInputSignLayout;
import com.skypremiuminternational.app.domain.models.creditCard.Brand;
import com.skypremiuminternational.app.domain.models.creditCard.CardItem;
import com.skypremiuminternational.app.domain.models.creditCard.Country;
import com.skypremiuminternational.app.domain.models.creditCard.GetFormDataCreditCardResponse;
import com.skypremiuminternational.app.domain.models.creditCard.Month;
import com.skypremiuminternational.app.domain.models.creditCard.Region;
import com.skypremiuminternational.app.domain.models.creditCard.Year;
import com.stripe.android.model.Card;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;



public class AddOrEditCreditCardDialog extends BottomSheetDialogFragment {

  public static final String KEY_DIALOG_TYPE = "key";
  public static final String EXTRA_KEY = "visa_key";
  public static final String FORM_DATA_KEY = "form_data";
  public static final int ADD_CARD = 0;
  public static final int EDIT_CARD = 1;

  @BindView(R.id.img_checkbox)
  ImageView imgCheck;
  @BindView(R.id.img_close)
  ImageView imgClose;
  @BindView(R.id.img_delete)
  ImageView imgDelete;
  @BindView(R.id.tv_save)
  TextView tvSave;
  @BindView(R.id.tv_title)
  TextView tvTitle;
  @BindView(R.id.v_exp_month)
  View vExpiryMonth;
  @BindView(R.id.v_exp_year)
  View vExpiryYear;
  @BindView(R.id.v_card_company)
  View vCardCompany;
  @BindView(R.id.input_exp_month)
  SkyTextInputSignLayout inputExpMonth;
  @BindView(R.id.input_exp_year)
  SkyTextInputSignLayout inputExpYear;
  @BindView(R.id.input_cvc)
  SkyTextInputSignLayout inputCVC;
  @BindView(R.id.input_card_number)
  SkyTextInputSignLayout inputCardNumber;
  @BindView(R.id.txt_set_primary_credit)
  TextView txtSetPrimaryCredit;
  @BindView(R.id.img_card_brand)
  ImageView imgCardBrand;
  @BindView(R.id.root_layout)
  ViewGroup rootLayout;
  @BindView(R.id.layout_checkbox)
  ConstraintLayout layoutCheckBox;
  @BindView(R.id.layout_disable_checked)
  FrameLayout layoutDisableChecked;
  @BindView(R.id.input_card_company)
  SkyTextInputSignLayout inputCardCompany;
  @BindView(R.id.input_country)
  SkyTextInputSignLayout inputCountry;
//  @BindView(R.id.input_prefectures)
//  SkyTextInputSignLayout inputPrefectures;
  @BindView(R.id.v_prefectures)
  View vPrefectures;
  @BindView(R.id.img_prefectures)
  ImageView imgPrefectures;
  @BindView(R.id.input_municipalities)
  SkyTextInputSignLayout inputMunicipalities;
  @BindView(R.id.input_street_address1)
  SkyTextInputSignLayout inputStreetAddress1;
  @BindView(R.id.input_street_address2)
  SkyTextInputSignLayout inputStreetAddress2;
  @BindView(R.id.input_zip_code)
  SkyTextInputSignLayout inputZipCode;





  @BindView(R.id.input_owner_name)
  SkyTextInputSignLayout inputOwnerName;

  private Unbinder unbinder;
  private boolean isChecked;
  private ActionListener actionListener;
  private CardItem creditCard;
  private GetFormDataCreditCardResponse getFormDataCreditCardResponse;
  private View contentView;


  List<Brand> brandList = new ArrayList<>();
  List<Month> monthList = new ArrayList<>();
  List<Year> yearList = new ArrayList<>();
  List<Country> countryList = new ArrayList<>();
  List<Region> regionList = new ArrayList<>();

  int brandSelectedPos=0;
  int monthSelectedPos=0;
  int yearSelectedPos=0;
  int countrySelectedPos=0;
  int regionSelectedPos=0;

  String brandValue="",countryValue="",regionValue="";

  @SuppressLint("RestrictedApi")
  @Override
  public void setupDialog(Dialog dialog, int style) {
    super.setupDialog(dialog, style);

    contentView = View.inflate(getContext(), R.layout.dialog_edit_visa_bottomsheet, null);
    unbinder = ButterKnife.bind(this, contentView);

    Bundle bundle = getArguments();
    int key = bundle.getInt(KEY_DIALOG_TYPE, ADD_CARD);
    creditCard = (CardItem) bundle.getSerializable(EXTRA_KEY);
    getFormDataCreditCardResponse = (GetFormDataCreditCardResponse) bundle.getSerializable(FORM_DATA_KEY);

    DisplayMetrics displayMetrics = getActivity().getResources().getDisplayMetrics();
    int height = displayMetrics.heightPixels;
    int maxHeight = (int) (height * 0.88);
    dialog.setContentView(contentView);

    BottomSheetBehavior mBehavior = BottomSheetBehavior.from((View) contentView.getParent());
    mBehavior.setPeekHeight(maxHeight);

    ((View) contentView.getParent()).setBackgroundColor(
        getResources().getColor(R.color.transparent));

    setUpData();

    if (key == ADD_CARD) {
      imgCheck.setEnabled(false);
      tvTitle.setText(getString(R.string.txt_add_credit_card));
      imgDelete.setVisibility(View.GONE);
      layoutCheckBox.setVisibility(View.GONE);
    } else {
      imgCheck.setEnabled(true);
      tvTitle.setText(getString(R.string.txt_edit_credit_card));
      imgDelete.setVisibility(View.VISIBLE);
      if(creditCard.getBrand().equalsIgnoreCase(Constants.AMERICAN_EXPRESS_BRAND)){
        inputCVC.setText("****");
      }else {
        inputCVC.setText("****");
      }
      inputCVC.setEnabled(false);
    }

    if (creditCard != null) {
      renderCreditCard(creditCard);
    }

    inputCardNumber.edt.setOnEditorActionListener((v, actionId, event) -> {
      if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId
          == EditorInfo.IME_ACTION_NEXT)) {
        hideKeyboard();
        validateCard();

      }
      return false;
    });

    inputCardNumber.setItemClickListener(item -> {
      if (item != null) {
        if (item.equalsIgnoreCase("empty")) {
          imgCardBrand.setVisibility(View.INVISIBLE);
        }
      }
    });
  }

  private void setUpData() {
    if (getFormDataCreditCardResponse != null){
      brandList = getFormDataCreditCardResponse.getBrands();
      monthList = getFormDataCreditCardResponse.getMonth();
      yearList = getFormDataCreditCardResponse.getYear();
      countryList = getFormDataCreditCardResponse.getCountries();
    }
  }

  private void renderCreditCard(CardItem creditCard) {
    inputOwnerName.setText(creditCard.getName());
    if (creditCard.getLast4() != null && !isEmpty(creditCard.getLast4())) {
      inputCardNumber.setMaxLength(("XXXX XXXX XXXX " + creditCard.getLast4()).length());
      inputCardNumber.setText("XXXX XXXX XXXX " + creditCard.getLast4());
      inputCardNumber.setEnabled(false);
    }else {
      inputCardNumber.setMaxLength(16);
    }
    if (creditCard.getBrand() != null && !TextUtils.isEmpty(creditCard.getBrand())) {
      checkCreditCardType(creditCard.getBrand());
      inputCardCompany.setText(creditCard.getBrand());
      vCardCompany.setEnabled(false);
      inputCardCompany.setEnabled(false);

    } else {
      imgCardBrand.setVisibility(View.INVISIBLE);
      vCardCompany.setEnabled(true);
      inputCardCompany.setEnabled(true);
    }
    if (creditCard.getExpMonth() != 0) {
      inputExpMonth.setText(String.valueOf(creditCard.getExpMonth()));
    }
    if (creditCard.getExpYear() != 0) {
      inputExpYear.setText(String.valueOf(creditCard.getExpYear()));
    }

    if (creditCard.getCountry() != null){
      if (countryList != null && countryList.size() >0){
        for (int i=0;i<countryList.size();i++){
          if (creditCard.getCountry().contains(countryList.get(i).getValue())){
            countrySelectedPos = i;
            countryValue = countryList.get(countrySelectedPos).getValue();
            inputCountry.setText(countryList.get(countrySelectedPos).getLabel());


            if(!TextUtils.isEmpty(creditCard.getStateRegionId()) && creditCard.getStateRegionId() != null){

              if (countryList.get(countrySelectedPos).getRegions() != null){
                for (int j = 0;j < countryList.get(countrySelectedPos).getRegions().size();j++){
                  if (creditCard.getStateRegionId().contains(countryList.get(countrySelectedPos).getRegions().get(j).getValue())){
                    regionSelectedPos = j;
                    regionValue = countryList.get(countrySelectedPos).getRegions().get(j).getValue();
//                    inputPrefectures.disableEditTextView();
                    vPrefectures.setVisibility(View.VISIBLE);
                    imgPrefectures.setVisibility(View.VISIBLE);
//                    inputPrefectures.setText(countryList.get(countrySelectedPos).getRegions().get(j).getLabel());
                  }
                }
              }
            }
            else {
//                inputPrefectures.enableEditTextView();
                vPrefectures.setVisibility(View.GONE);
                imgPrefectures.setVisibility(View.GONE);
//                inputPrefectures.setText(creditCard.getState());

            }

          }
        }
      }
    }

    if (creditCard.getPostalCode() != null){
      inputZipCode.setText(creditCard.getPostalCode());
    }
//    if (creditCard.getCity() != null){
//      inputMunicipalities.setText(creditCard.getCity());
//    }
    if (creditCard.getAddressStreet1() != null){
      inputStreetAddress1.setText(creditCard.getAddressStreet1());
    }

    inputStreetAddress2.setText(creditCard.getAddressStreet2());





    if (creditCard.getDefault() != null) {
      if (creditCard.getDefault()) {
        imgCheck.setBackgroundResource(R.drawable.ic_checked);
        imgCheck.setEnabled(false);
        txtSetPrimaryCredit.setEnabled(false);

        imgDelete.setVisibility(View.INVISIBLE);
        layoutDisableChecked.setVisibility(View.VISIBLE);
      } else {
        layoutDisableChecked.setVisibility(View.GONE);
        imgDelete.setVisibility(View.VISIBLE);
      }
    }
  }

  @OnClick(R.id.img_delete)
  void onClickDelete() {
    if (creditCard != null) {
      actionListener.onDeleteClicked(creditCard);
    }
  }

  @OnClick({R.id.img_checkbox, R.id.txt_set_primary_credit})
  void onClickChecked() {
    if (!isChecked) {
      isChecked = true;
      imgCheck.setBackgroundResource(R.drawable.ic_checked);
    } else {
      isChecked = false;
      imgCheck.setBackgroundResource(R.drawable.ic_circle);
    }
  }

  @OnClick(R.id.tv_save)
  void onSave() {
    if (actionListener != null) {
      if (validateCardData()) {
        actionListener.onSaveClicked(buildCreditCard());
      }
    }
  }

  @OnClick(R.id.img_close)
  void onClickedClose() {
    dismiss();
  }


  @OnClick(R.id.v_exp_month)
  void onClickMonth(){
    if (monthList.size()>0){
      String[] monthItems =  new String[monthList.size()];
      monthItems = getMonthList(monthList);
      new AlertDialog.Builder(getActivity()).setTitle("Choose Month :")
          .setItems(monthItems, (dialog, position) -> {
            monthSelectedPos =  position;
            inputExpMonth.setText(monthList.get(monthSelectedPos).getLabel());
          })
          .setNegativeButton("Cancel", null)
          .show();

    }
    else {
      Toast.makeText(getActivity(), "List month is empty ", Toast.LENGTH_SHORT).show();
    }
  }

  private String[] getMonthList(List<Month> monthList) {
    List<String> items = new ArrayList<>();
    for(Month item  : monthList){
      items.add(item.getLabel());
    }
    return (String[]) items.toArray(new String[items.size()]);
  }




  @OnClick(R.id.v_card_company)
  void onClickCardCompany(){
    if (brandList.size()>0){
      String[] brandItems =  new String[brandList.size()];
      brandItems = getBrandList(brandList);
      new AlertDialog.Builder(getActivity()).setTitle("Choose Company Brand :")
          .setItems(brandItems, (dialog, position) -> {
            brandSelectedPos =  position;
            brandValue = brandList.get(brandSelectedPos).getValue();
            inputCardCompany.setText(brandList.get(brandSelectedPos).getLabel());
          })
          .setNegativeButton("Cancel", null)
          .show();

    }
  }

  private String[] getBrandList(List<Brand> brandList) {
    List<String> items = new ArrayList<>();
    for(Brand item  : brandList){
      items.add(item.getLabel());
    }
    return (String[]) items.toArray(new String[items.size()]);
  }


  @OnClick(R.id.v_exp_year)
  void onClickYear(){
    if (yearList.size()>0){
      String[] yearItems =  new String[yearList.size()];
      yearItems = getYearList(yearList);
      new AlertDialog.Builder(getActivity()).setTitle("Choose Year :")
          .setItems(yearItems, (dialog, position) -> {
            yearSelectedPos =  position;
            inputExpYear.setText(yearList.get(yearSelectedPos).getLabel());
          })
          .setNegativeButton("Cancel", null)
          .show();
    }
  }

  private String[] getYearList(List<Year> monthList) {
    List<String> items = new ArrayList<>();
    for(Year item  : monthList){
      items.add(item.getLabel());
    }
    return (String[]) items.toArray(new String[items.size()]);
  }



  @OnClick(R.id.v_country)
  void onClickCountry(){
    if (countryList.size()>0){
      String[] countryItems =  new String[countryList.size()];
      countryItems = getCountryList(countryList);
      new AlertDialog.Builder(getActivity()).setTitle("Choose Country :")
          .setItems(countryItems, (dialog, position) -> {
            countrySelectedPos =  position;
            countryValue = countryList.get(countrySelectedPos).getValue();
            inputCountry.setText(countryList.get(countrySelectedPos).getLabel());

            if (countryList.get(countrySelectedPos).getRegions() != null){
//              inputPrefectures.disableEditTextView();
              vPrefectures.setVisibility(View.VISIBLE);
              imgPrefectures.setVisibility(View.VISIBLE);
              String labelRegion ="";
              for (int i=0;i<countryList.get(countrySelectedPos).getRegions().size();i++){
                if (countryList.get(countrySelectedPos).getRegions().get(i).getValue().contains("852")){
                  //Auto fill label "TOKYO TO" when choose country Japan
                  regionSelectedPos = i;
                  regionValue = countryList.get(countrySelectedPos).getRegions().get(regionSelectedPos).getValue();
                  labelRegion = countryList.get(countrySelectedPos).getRegions().get(regionSelectedPos).getLabel();
                }
                else {
                  regionValue ="";
                }

              }
              if (TextUtils.isEmpty(labelRegion)){
//                inputPrefectures.setText("");
              }else {
//                inputPrefectures.setText(labelRegion);
              }


            }
            else {
              regionValue ="";
//              inputPrefectures.setText("");
//              inputPrefectures.enableEditTextView();
              vPrefectures.setVisibility(View.GONE);
              imgPrefectures.setVisibility(View.GONE);
            }
          })
          .setNegativeButton("Cancel", null)
          .show();
    }

  }

  private String[] getCountryList(List<Country> countryList) {
    List<String> items = new ArrayList<>();
    for(Country item  : countryList){
      items.add(item.getLabel());
    }
    return (String[]) items.toArray(new String[items.size()]);
  }

  @OnClick(R.id.v_prefectures)
  void onClickRegion(){
    if (getFormDataCreditCardResponse != null && getFormDataCreditCardResponse.getCountries().get(countrySelectedPos).getRegions() != null){
//      inputPrefectures.disableEditTextView();
      vPrefectures.setVisibility(View.VISIBLE);
      imgPrefectures.setVisibility(View.VISIBLE);
      regionList = getFormDataCreditCardResponse.getCountries().get(countrySelectedPos).getRegions();
      String[] regionItems =  new String[regionList.size()];
      regionItems = getRegionList(regionList);
      new AlertDialog.Builder(getActivity()).setTitle("Choose Prefectures :")
          .setItems(regionItems, (dialog, position) -> {
            regionSelectedPos =  position;
            regionValue = regionList.get(regionSelectedPos).getValue();
//            inputPrefectures.setText(regionList.get(regionSelectedPos).getLabel());


          })
          .setNegativeButton("Cancel", null)
          .show();

    }
    else {
//      inputPrefectures.enableEditTextView();
    }

  }



  private String[] getRegionList(List<Region> regionList) {
    List<String> items = new ArrayList<>();
    for(Region item  : regionList){
      items.add(item.getLabel());
    }
    return (String[]) items.toArray(new String[items.size()]);
  }







  private boolean validateCardData() {
    boolean isValidate = true;

    if (isEmpty(inputCardCompany.getText())){
      inputCardCompany.showError(getString(R.string.required_field));
      isValidate = false;
    }
    if (isEmpty(inputOwnerName.getText())) {
      inputOwnerName.showError(getString(R.string.required_field));
      isValidate = false;
    }
    else {
      if (!isEmpty(inputOwnerName.getText())) {
        if (inputOwnerName.getText().length()> 64){
          inputOwnerName.showError("Please enter no more than 64 characters.");
          isValidate = false;
        }
        else if (!inputOwnerName.getText().matches(REGEX_VALIDATION_CREDITCARD)) {
          inputOwnerName.showError(getString(R.string.validation_sensitive_symbol));
          isValidate = false;
        }else if (!inputOwnerName.getText().matches(REGEX_VALIDATION_FULLNAME)){
          inputOwnerName.showError(getString(R.string.validation_fullname));
          isValidate = false;
        }

      }

    }
    if (isEmpty(inputCardNumber.getText())) {
      inputCardNumber.showError(getString(R.string.required_field));
      isValidate = false;
    }
    if (isEmpty(inputExpMonth.getText())) {

      inputExpMonth.showError(getString(R.string.required_field));
      isValidate = false;
    }
    if (isEmpty(inputExpYear.getText())) {
      inputExpYear.showError(getString(R.string.required_field));
      isValidate = false;
    }
    if (isEmpty(inputCVC.getText())) {
      inputCVC.showError(getString(R.string.required_field));
      isValidate = false;
    } else {
      Card card = new Card(inputCardNumber.getText(),0,0, "");
      if(!isEmpty(inputCardNumber.getText())){
        if(card.getType().equalsIgnoreCase(Constants.AMERICAN_EXPRESS_BRAND)){
          // just AMERICAN EXPRESS
          if (inputCVC.getText().length() != 3) {
            createErrorDialog(getString(R.string.error_invalid));
            inputCVC.showError(getString(R.string.error_expired_cvc));
            isValidate = false ;
          }
        }else if (inputCVC.getText().length() !=4) {
          createErrorDialog(getString(R.string.error_invalid ));
          inputCVC.showError(getString(R.string.error_expired_cvc ));
          isValidate = false;
        }
      }
    }
    if (isEmpty(inputCountry.getText())) {
      inputCountry.showError(getString(R.string.required_field));
      isValidate = false;
    }
    if (isEmpty(inputZipCode.getText())) {
      inputZipCode.showError(getString(R.string.required_field));
      isValidate = false;
    }
    else {
      if (!isEmpty(inputZipCode.getText()) && !inputZipCode.getText().matches(REGEX_VALIDATION_CREDITCARD)) {
        inputZipCode.showError(getString(R.string.digit));
        isValidate = false;
      }
    }
//    if (isEmpty(inputPrefectures.getText())) {
//      inputPrefectures.showError(getString(R.string.required_field));
//      isValidate = false;
//    }
//    if (isEmpty(inputMunicipalities.getText())) {
//      inputMunicipalities.showError(getString(R.string.required_field));
//      isValidate = false;
//    }
//    else {
//      if (!isEmpty(inputMunicipalities.getText())) {
//        if (inputMunicipalities.getText().length()> 256){
//          inputMunicipalities.showError("Please enter no more than 256 characters.");
//          isValidate = false;
//        }
//        else if (!inputMunicipalities.getText().matches(REGEX_VALIDATION_CREDITCARD)) {
//          inputMunicipalities.showError(getString(R.string.validation_sensitive_symbol));
//          isValidate = false;
//        }
//      }
//
//    }
    if (isEmpty(inputStreetAddress1.getText())) {
      inputStreetAddress1.showError(getString(R.string.required_field));
      isValidate = false;
    }
    else {
      if (!isEmpty(inputStreetAddress1.getText()) && inputStreetAddress1.getText().length()> 127) {
        inputStreetAddress1.showError("Please enter no more than 127 characters.");
        isValidate = false;
      }
    }
//    if (isEmpty(inputStreetAddress2.getText())) {
//      inputStreetAddress2.showError(getString(R.string.required_field));
//      isValidate = false;
//    }



//    else {
//      if (!isEmpty(inputStreetAddress2.getText()) && inputStreetAddress2.getText().length()> 127) {
//        inputStreetAddress2.showError("Please enter no more than 127 characters.");
//        isValidate = false;
//      }
//    }
//
//    if(inputStreetAddress2.getText().length() > 127){
//      inputStreetAddress2.showError("Please enter no more than 127 characters.");
//      isValidate = false;
//    }

    return isValidate;
  }

  private void createErrorDialog(String message) {
    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
    builder
        .setMessage(message)
        .setPositiveButton(android.R.string.ok, (dialog, which) -> dialog.dismiss())
        .show();
  }

  private void validateCard() {
    Card card = new Card(inputCardNumber.getText(), 0, 0, "");
    checkCreditCardType(card.getType());
  }

  private CardItem buildCreditCard() {
    if (creditCard != null) {
      if (countryList.get(countrySelectedPos).getRegions() != null){
        creditCard.setExpMonth(Integer.parseInt(inputExpMonth.getText()));
        creditCard.setExpYear(Integer.parseInt(inputExpYear.getText()));
        creditCard.setName(inputOwnerName.getText());
        creditCard.setCountry(countryValue);
        creditCard.setStateRegionId(regionValue);
//        creditCard.setState(inputPrefectures.getText());
        creditCard.setPostalCode(inputZipCode.getText());
//        creditCard.setCity(inputMunicipalities.getText());
        creditCard.setAddressStreet1(inputStreetAddress1.getText());
        creditCard.setAddressStreet2(inputStreetAddress2.getText());
        creditCard.setDefault(isChecked);
        return creditCard;
      }
      else {
        creditCard.setExpMonth(Integer.parseInt(inputExpMonth.getText()));
        creditCard.setExpYear(Integer.parseInt(inputExpYear.getText()));
        creditCard.setName(inputOwnerName.getText());
        creditCard.setCountry(countryValue);
        creditCard.setStateRegionId("");
//        creditCard.setState(inputPrefectures.getText());
        creditCard.setPostalCode(inputZipCode.getText());
//        creditCard.setCity(inputMunicipalities.getText());
        creditCard.setAddressStreet1(inputStreetAddress1.getText());
        creditCard.setAddressStreet2(inputStreetAddress2.getText());
        creditCard.setDefault(isChecked);
        return creditCard;
      }

    }
    else {
      creditCard = new CardItem();
      if (countryList.get(countrySelectedPos).getRegions() != null){
        creditCard.setBrand(brandValue);
        creditCard.setCard_number(inputCardNumber.getText());
        creditCard.setCard_cvc(inputCVC.getText());
        creditCard.setExpMonth(Integer.parseInt(inputExpMonth.getText()));
        creditCard.setExpYear(Integer.parseInt(inputExpYear.getText()));
        creditCard.setName(inputOwnerName.getText());
        creditCard.setCountry(countryValue);
        creditCard.setStateRegionId(regionValue);
//        creditCard.setState(inputPrefectures.getText());
        creditCard.setPostalCode(inputZipCode.getText());
//        creditCard.setCity(inputMunicipalities.getText());
        creditCard.setAddressStreet1(inputStreetAddress1.getText());
        creditCard.setAddressStreet2(inputStreetAddress2.getText());
        creditCard.setDefault(isChecked);
        return creditCard;
      }
      else {
        creditCard.setBrand(brandValue);
        creditCard.setCard_number(inputCardNumber.getText());
        creditCard.setCard_cvc(inputCVC.getText());
        creditCard.setExpMonth(Integer.parseInt(inputExpMonth.getText()));
        creditCard.setExpYear(Integer.parseInt(inputExpYear.getText()));
        creditCard.setName(inputOwnerName.getText());
        creditCard.setCountry(countryValue);
        creditCard.setStateRegionId("");
//        creditCard.setState(inputPrefectures.getText());
        creditCard.setPostalCode(inputZipCode.getText());
//        creditCard.setCity(inputMunicipalities.getText());
        creditCard.setAddressStreet1(inputStreetAddress1.getText());
        creditCard.setAddressStreet2(inputStreetAddress2.getText());
        creditCard.setDefault(isChecked);
        return creditCard;
      }

    }

  }

  public void setActionListener(ActionListener actionListener) {
    this.actionListener = actionListener;
  }

  public interface ActionListener {
    void onSaveClicked(CardItem creditCard);

    void onDeleteClicked(CardItem creditCard);
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    if (unbinder != null) {
      unbinder.unbind();
    }
  }

  private void checkCreditCardType(String type) {
    if (!TextUtils.isEmpty(type)) {
      if (type.equalsIgnoreCase(Constants.VISA_BRAND)) {
        imgCardBrand.setImageResource(R.drawable.ic_visa_blue_background);
        imgCardBrand.setVisibility(View.VISIBLE);
      } else if (type.equalsIgnoreCase(Constants.MASTER_BRAND)) {
        imgCardBrand.setImageResource(R.drawable.ic_master_card_with_text);
        imgCardBrand.setVisibility(View.VISIBLE);
      } else if (type.equalsIgnoreCase(Constants.AMERICAN_EXPRESS_BRAND)) {
        imgCardBrand.setImageResource(R.drawable.ic_american_express_blue_background);
        imgCardBrand.setVisibility(View.VISIBLE);

      } else if (type.equalsIgnoreCase(Constants.DISCOVER_BRAND)) {
        imgCardBrand.setVisibility(View.INVISIBLE);
      } else if (type.equalsIgnoreCase(Constants.DINERS_CLUB_BRAND)) {
        imgCardBrand.setVisibility(View.INVISIBLE);
      } else if (type.equalsIgnoreCase(Constants.JCB_BRAND)) {
        imgCardBrand.setVisibility(View.INVISIBLE);
      } else {
        inputCardNumber.showError(getString(R.string.invalid_card));
        imgCardBrand.setVisibility(View.INVISIBLE);
      }
    }
  }

  public void hideKeyboard() {
    if (contentView != null) {
      KBUtil.hideKeyboard(getActivity(), contentView);
    }
  }
}
