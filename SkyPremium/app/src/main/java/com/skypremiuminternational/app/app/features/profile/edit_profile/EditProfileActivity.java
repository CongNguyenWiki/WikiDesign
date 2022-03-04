package com.skypremiuminternational.app.app.features.profile.edit_profile;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.j256.ormlite.stmt.query.In;
import com.skypremiuminternational.app.R;
import com.skypremiuminternational.app.app.features.forgot_password.update.UpdatePasswordActivity;
import com.skypremiuminternational.app.app.features.skytextinputlayout.SkyTextInputLayout;
import com.skypremiuminternational.app.app.internal.mvp.BaseActivity;
import com.skypremiuminternational.app.app.utils.Constants;
import com.skypremiuminternational.app.app.utils.PasswordTransformationMethod;
import com.skypremiuminternational.app.app.utils.SalutationUtils;
import com.skypremiuminternational.app.app.utils.StringUtil;
import com.skypremiuminternational.app.app.utils.Validator;
import com.skypremiuminternational.app.data.network.request.UpdateUserRequest;
import com.skypremiuminternational.app.domain.models.country_code.CountryCode;
import com.skypremiuminternational.app.domain.models.nationality.Nationality;
import com.skypremiuminternational.app.domain.models.phone_code.PhoneCode;
import com.skypremiuminternational.app.domain.models.user.CustomAttribute;
import com.skypremiuminternational.app.domain.models.user.UserDetailResponse;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;
import timber.log.Timber;

public class EditProfileActivity extends BaseActivity<EditProfilePresenter>
    implements EditProfileView<EditProfilePresenter> {


  @BindView(R.id.tvTitle_toolbar)
  TextView tvTitle_toolbar;
  @BindView(R.id.llMemberShip)
  LinearLayout llMemberShip;
  @BindView(R.id.tvExpire)
  TextView tvExpire;
  @BindView(R.id.tvExpireDate)
  TextView tvExpireDate;
  @BindView(R.id.tvMsg)
  TextView tvMsg;
  @BindView(R.id.tvStatus)
  TextView tvStatus;
  @BindView(R.id.stilEmail)
  SkyTextInputLayout stilEmail;
  @BindView(R.id.stilPassword)
  SkyTextInputLayout stilPassword;
  @BindView(R.id.stilDob)
  SkyTextInputLayout stilDob;
  @BindView(R.id.stilSalutation)
  SkyTextInputLayout stilSalutation;
  @BindView(R.id.stilFirstName)
  SkyTextInputLayout stilFirstName;
  @BindView(R.id.stilLastName)
  SkyTextInputLayout stilLastName;
  @BindView(R.id.stilMobileNumber)
  SkyTextInputLayout stilMobileNumber;
  @BindView(R.id.stilFacebook)
  SkyTextInputLayout stilFacebook;
  @BindView(R.id.stilInstagram)
  SkyTextInputLayout stilInstagram;
  @BindView(R.id.stilTelegram)
  SkyTextInputLayout stilTelegram;
  @BindView(R.id.ivFemale)
  ImageView ivFemale;
  @BindView(R.id.ivMale)
  ImageView ivMale;
  @BindView(R.id.tvMale)
  TextView tvFemale;
  @BindView(R.id.tvFemale)
  TextView tvMale;
  @BindView(R.id.stilNationality)
  SkyTextInputLayout stilNationality;
  @BindView(R.id.stilCountry)
  SkyTextInputLayout stilCountry;
  @BindView(R.id.llPhoneCode)
  LinearLayout llPhoneCode;
  @BindView(R.id.tvPhoneCode)
  TextView tvPhoneCode;
  @BindView(R.id.tv_renew_membership)
  TextView txtRenewMembership;

  UserDetailResponse userDetail;
  UpdateUserRequest.Customer customer;
  int salutationItem;
  boolean isMale = false;

  private String freshworksCrmContactId = "";

  ProgressDialog progressDialog;

  private List<CountryCode> countryCodes = new ArrayList<>();
  private List<Nationality> nationalities = new ArrayList<>();
  private PhoneCode phoneCodes;

  private int phoneCodeItem;
  private int countryItem;
  private int nationalityItem;

  private CountryCode selectedCountryCode;
  private Nationality selectedNationality;

  public static void startMe(Context context) {
    Intent intent = new Intent(context, EditProfileActivity.class);
    context.startActivity(intent);
  }

  @Inject
  @Override
  public void injectPresenter(EditProfilePresenter presenter) {
    super.injectPresenter(presenter);
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    AndroidInjection.inject(this);
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_edit_profile);
    ButterKnife.bind(this);


    presenter.getZoneConfig();

    if (userDetail == null) {
      presenter.getUserInfo();
    } else {
      renderUserDetail(userDetail);
    }
    stilDob.setOnTouchListener((v, event) -> {
      if (event.getAction() == MotionEvent.ACTION_UP) {
        showBirthdayDialog();
      }
      return false;
    });
  }

  @Override
  public void onStart() {
    super.onStart();

    stilPassword.setTransformationMethod(new PasswordTransformationMethod());

    tvTitle_toolbar.setText(getResources().getString(R.string.edit_profile));


    stilDob.setOnTouchListener((v, event) -> {
      if (event.getAction() == MotionEvent.ACTION_UP) {
        showBirthdayDialog();
      }
      return false;
    });
  }

  void showBirthdayDialog() {
    Calendar c = Calendar.getInstance();
    Calendar cMin = Calendar.getInstance();
    Calendar cMax = Calendar.getInstance();
    cMax.add(Calendar.YEAR, Constants.MIN_AGE);

    if (!TextUtils.isEmpty(stilDob.getText().toString())) {
      try {
        c.setTime(Constants.DATE_FORMAT.parse(stilDob.getText().toString()));
      } catch (ParseException e) {
        e.printStackTrace();
      }
    } else {
      c = cMax;
    }
    DatePickerDialog datePickerDialog = new DatePickerDialog(this,
            (view, year, monthOfYear, dayOfMonth) -> stilDob.setText(Constants.DATE_FORMAT.format(
                    new GregorianCalendar(year, monthOfYear, dayOfMonth).getTime())), c.get(Calendar.YEAR),
            c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
    datePickerDialog.getDatePicker().setMaxDate(cMax.getTime().getTime());
    datePickerDialog.show();
  }

  @Override
  public void renderUserDetail(UserDetailResponse response) {
    setUserDetails(response);
  }

  @Override
  public void updateUserProfileSuccess() {
    finish();
  }

  private void setUserDetails(UserDetailResponse response) {
    this.userDetail = response;
    stilEmail.setText(response.getEmail());

    stilFirstName.setText(response.getFirstname());
    stilLastName.setText(response.getLastname());
    for (CustomAttribute customAttribute : response.getCustomAttributes()) {
      if (customAttribute.getAttributeCode().equals("salutation")) {
        for (int i = 0; i < Constants.SALUTATIONS_NEW.length; i++) {
          String salutation = Constants.SALUTATIONS_NEW[i];
          String value = customAttribute.getValue();
          if (salutation.equalsIgnoreCase(value)) {
            salutationItem = i;
          }
        }
        stilSalutation.setText(customAttribute.getValue());
      } else if (customAttribute.getAttributeCode().equals("registration_phone_number")) {
        stilMobileNumber.setText(customAttribute.getValue());
      } else if (customAttribute.getAttributeCode().equals("facebook")) {
        stilFacebook.setText(customAttribute.getValue());
      } else if (customAttribute.getAttributeCode().equals("instagram")) {
        stilInstagram.setText(customAttribute.getValue());
      } else if (customAttribute.getAttributeCode().equals("telegram")) {
        stilTelegram.setText(customAttribute.getValue());
      } else if (customAttribute.getAttributeCode().equals("freshworks_crm_contact_id")) {
        freshworksCrmContactId = customAttribute.getValue();
      } else if (customAttribute.getAttributeCode().equals("member_type")) {
        if (customAttribute.getValue().equalsIgnoreCase("CM")) {
          llMemberShip.setVisibility(View.GONE);
        }
      } else if (customAttribute.getAttributeCode().equals("member_status")) {
        if (customAttribute.getValue().equals("AC")) {
          tvExpire.setText("Your membership is ");
          tvStatus.setText("active");
          tvMsg.setText(" and will expire on ");
        } else {
          tvExpire.setText("Your membership has ");
          tvStatus.setText("expired");
          tvMsg.setText(" on ");
        }
      } else if (customAttribute.getAttributeCode().equals("member_expiry_date")) {
        if (customAttribute.getValue() != null) {
          try {
            java.util.Date convertedDate =
                Constants.DATE_YEAR_FORMAT.parse(customAttribute.getValue());
            tvExpireDate.setText(String.format(Constants.DATE_FORMAT.format(convertedDate)));
          } catch (ParseException e) {
            e.printStackTrace();
          }
        }
      }
    }
    if (response.getGender() != null) {
      if (response.getGender() == 1) {
        isMale = true;
        ivMale.setImageDrawable(getResources().getDrawable(R.drawable.ic_check_circle_accent));
        ivFemale.setImageDrawable(
            getResources().getDrawable(R.drawable.ic_radio_button_unchecked_accent));
      } else if (response.getGender() == 2){
        isMale = false;
        ivMale.setImageDrawable(
            getResources().getDrawable(R.drawable.ic_radio_button_unchecked_accent));
        ivFemale.setImageDrawable(getResources().getDrawable(R.drawable.ic_check_circle_accent));
      }
      else {

        ivMale.setImageDrawable(
            getResources().getDrawable(R.drawable.ic_radio_button_unchecked_accent));
        ivFemale.setImageDrawable(
            getResources().getDrawable(R.drawable.ic_radio_button_unchecked_accent));

      }
    }
    if (response.getDob() != null) {
      try {
        java.util.Date convertedDate = Constants.DATE_FORMAT_DOB.parse(response.getDob());
        stilDob.setText(String.format(Constants.DATE_FORMAT.format(convertedDate)));
      } catch (ParseException e) {
        e.printStackTrace();
      }
    }


    setUserCountry();
    setUserNationality();
    setUserPhoneCodes();

  }

  @OnClick(R.id.ivMale)
  void onClickMale(){
    isMale = true;
    ivMale.setImageDrawable(getResources().getDrawable(R.drawable.ic_check_circle_accent));
    ivFemale.setImageDrawable(
        getResources().getDrawable(R.drawable.ic_radio_button_unchecked_accent));
  }

  @OnClick(R.id.ivFemale)
  void onClickFemale(){
    isMale = false;
    ivMale.setImageDrawable(
        getResources().getDrawable(R.drawable.ic_radio_button_unchecked_accent));
    ivFemale.setImageDrawable(getResources().getDrawable(R.drawable.ic_check_circle_accent));
  }

  private void setUserCountry() {

    String countryCodeId = null;
    if (userDetail.getCustomAttributes() != null) {
      for (CustomAttribute customAttribute : userDetail.getCustomAttributes()) {
        if (customAttribute.getAttributeCode().equals("address_country")) {
          countryCodeId = customAttribute.getValue();
          break;
        }
      }
    }
    //presenter.GetisoCountry(countryCodeId);
    for (int i = 0; i < countryCodes.size(); i++) {
      CountryCode countryCode = countryCodes.get(i);
      if (String.valueOf(countryCode.getId()).equals(countryCodeId)) {
        countryItem = i;
        stilCountry.setText(StringUtil.toTitleCase(countryCode.getName().split(" ")));
        Timber.e(countryCode.getId() + countryCode.getName());
        selectedCountryCode = new CountryCode(countryCode.getId(), countryCode.getName());
      }
    }
    progressDialog.hide();
  }

  private void setUserNationality() {
    String nationalityId = null;
    if (userDetail.getCustomAttributes() != null) {
      for (CustomAttribute customAttribute : userDetail.getCustomAttributes()) {
        if (customAttribute.getAttributeCode().equals("nationality")) {
          nationalityId = customAttribute.getValue();
          break;
        }
      }
    }
    for (int i = 0; i < nationalities.size(); i++) {
      Nationality nationality = nationalities.get(i);
      if (String.valueOf(nationality.getId()).equals(nationalityId)) {
        nationalityItem = i;
        stilNationality.setText(StringUtil.toTitleCase(nationality.getName().split(" ")));
        selectedNationality = new Nationality(nationality.getId(), nationality.getName());
      }
    }
  }

  private void setUserPhoneCodes() {
    String contactCountryCode = null;
    if (userDetail.getCustomAttributes() != null) {
      for (CustomAttribute customAttribute : userDetail.getCustomAttributes()) {
        if (customAttribute.getAttributeCode().equals("contact_country_code")) {
          contactCountryCode = customAttribute.getValue();
          break;
        }
      }
    }

    for (int i = 0; i < phoneCodes.getPhoneCodes().size(); i++) {
      PhoneCode.PhoneCode_ countryCode = phoneCodes.getPhoneCodes().get(i);
      if (String.valueOf(countryCode.getDiallingCode()).equals(contactCountryCode)) {
        phoneCodeItem = i;
        tvPhoneCode.setText("+" + countryCode.getDiallingCode());
      }
    }
  }

  @OnClick(R.id.tv_save)
  void onClickEditProfile() {

    if (!(isValid())) {
      if (isMale == true) {
        customer = new UpdateUserRequest.Customer(userDetail.getId(),
            userDetail.getWebsiteId(), stilFirstName.getText(), stilLastName.getText(),
            userDetail.getEmail(), userDetail.getDob(), 1, createUpdateUserObject());

      } else {
        customer = new UpdateUserRequest.Customer(userDetail.getId(),
            userDetail.getWebsiteId(), stilFirstName.getText(), stilLastName.getText(),
            userDetail.getEmail(), userDetail.getDob(), 2, createUpdateUserObject());

      }


      UpdateUserRequest request = new UpdateUserRequest(customer);
      getPresenter().updateUserDetails(request);
    }
  }


  private List<CustomAttribute> createUpdateUserObject() {
    CustomAttribute customAttribute_Nationality =
        new CustomAttribute("nationality",
            String.valueOf(selectedNationality.getId()));
    CustomAttribute customAttribute_CountryCode =
        new CustomAttribute("contact_country_code",
            tvPhoneCode.getText().toString().substring(1));
    CustomAttribute address_Country =
            new CustomAttribute("address_country", String.valueOf(selectedCountryCode.getId()));
    CustomAttribute customAttribute_Facebook =
        new CustomAttribute("facebook", stilFacebook.getText().trim());
    CustomAttribute customAttribute_Instagram =
        new CustomAttribute("instagram", stilInstagram.getText().trim());
    CustomAttribute customAttribute_Telegram =
        new CustomAttribute("telegram", stilTelegram.getText().trim());
    CustomAttribute customAttribute_freshworksCrmContactId =
        new CustomAttribute("freshworks_crm_contact_id", freshworksCrmContactId);


    List<CustomAttribute> customAttributeList = new ArrayList<>();
    customAttributeList.add(customAttribute_Nationality);
    customAttributeList.add(customAttribute_CountryCode);
    customAttributeList.add(address_Country);
    customAttributeList.add(customAttribute_Facebook);
    customAttributeList.add(customAttribute_Instagram);
    customAttributeList.add(customAttribute_Telegram);
    customAttributeList.add(customAttribute_freshworksCrmContactId);

    for (CustomAttribute customAttribute : userDetail.getCustomAttributes()) {
      if (customAttribute.getAttributeCode().equals("language")) {
        customAttributeList.add(
            new CustomAttribute("language", customAttribute.getValue()));
      } else if (customAttribute.getAttributeCode().equals("currency")) {
        customAttributeList.add(
            new CustomAttribute("currency", customAttribute.getValue()));
      } else if (customAttribute.getAttributeCode().equals("member_number")) {
        customAttributeList.add(
            new CustomAttribute("member_number", customAttribute.getValue()));
      } else if (customAttribute.getAttributeCode().equalsIgnoreCase("contract_status")) {
        customAttributeList.add(
            new CustomAttribute("contract_status", customAttribute.getValue()));
      } else if (customAttribute.getAttributeCode().equalsIgnoreCase("salutation")) {
        customAttributeList.add(
            new CustomAttribute("salutation", SalutationUtils.getSalutationCode(stilSalutation.getText())));
      }
      else if (customAttribute.getAttributeCode().equalsIgnoreCase("uid")) {
        customAttributeList.add(
            new CustomAttribute("uid", customAttribute.getValue()));
      } else if (customAttribute.getAttributeCode().equalsIgnoreCase("sales_sv_code")) {
        customAttributeList.add(
            new CustomAttribute("sales_sv_code", customAttribute.getValue()));
      } else if (customAttribute.getAttributeCode().equalsIgnoreCase("sales_agency_code")) {
        customAttributeList.add(
            new CustomAttribute("sales_agency_code", customAttribute.getValue()));
      } else if (customAttribute.getAttributeCode().equalsIgnoreCase("sales_agent_code")) {
        customAttributeList.add(
            new CustomAttribute("sales_agent_code", customAttribute.getValue()));
      } else if (customAttribute.getAttributeCode().equalsIgnoreCase("registration_country_number")) {
        customAttributeList.add(
            new CustomAttribute("registration_country_number", customAttribute.getValue()));
      } else if (customAttribute.getAttributeCode().equalsIgnoreCase("registration_phone_number")) {
        customAttributeList.add(
            new CustomAttribute("registration_phone_number",  stilMobileNumber.getText().trim()));
      } else if (customAttribute.getAttributeCode().equalsIgnoreCase("registration_country")) {
        customAttributeList.add(
            new CustomAttribute("registration_country", customAttribute.getValue()));
      } else if (customAttribute.getAttributeCode().equalsIgnoreCase("registration_state")) {
        customAttributeList.add(
            new CustomAttribute("registration_state", customAttribute.getValue()));
      } else if (customAttribute.getAttributeCode().equalsIgnoreCase("registration_postal_code")) {
        customAttributeList.add(
            new CustomAttribute("registration_postal_code", customAttribute.getValue()));
      } else if (customAttribute.getAttributeCode().equalsIgnoreCase("registration_city")) {
        customAttributeList.add(
            new CustomAttribute("registration_city", customAttribute.getValue()));
      } else if (customAttribute.getAttributeCode().equalsIgnoreCase("registration_address1")) {
        customAttributeList.add(
            new CustomAttribute("registration_address1", customAttribute.getValue()));
      } else if (customAttribute.getAttributeCode().equalsIgnoreCase("membership_temporary_offer_date")) {
        customAttributeList.add(
            new CustomAttribute("membership_temporary_offer_date", customAttribute.getValue()));
      }
    }

    return customAttributeList;
  }

  private void showSalutationDialog() {

    new AlertDialog.Builder(this).setTitle("Choose salutation")
            .setSingleChoiceItems(Constants.SALUTATIONS_NEW, salutationItem, (dialog, item) -> {
              stilSalutation.setText(Constants.SALUTATIONS_NEW[item]);
              salutationItem = item;
              dialog.dismiss();
            })
            .show();
  }





  private void clickMale() {
    ivMale.setImageDrawable(getResources().getDrawable(R.drawable.ic_check_circle_accent));
    ivFemale.setImageDrawable(
            getResources().getDrawable(R.drawable.ic_radio_button_unchecked_accent));
  }

  private void clickFemale() {
    ivFemale.setImageDrawable(getResources().getDrawable(R.drawable.ic_check_circle_accent));
    ivMale.setImageDrawable(
            getResources().getDrawable(R.drawable.ic_radio_button_unchecked_accent));
  }

  @OnClick(R.id.vSalutation)
  void onClickSalutation() {
    showSalutationDialog();
  }

  @OnClick(R.id.iv_toolbar_back)
  void onClickMenu() {
    finish();
  }

  @OnClick(R.id.tvChangePassword)
  void onClickChangePassword() {
    UpdatePasswordActivity.startMe(this);
  }



  @OnClick(R.id.vNationality)
  void onClickNationality() {
    showNationalitiesDialog();
  }

  @OnClick(R.id.vCountry)
  void onClickCountry() {
    showCountryCodesDialog();
  }

  @OnClick(R.id.llPhoneCode)
  void onClickPhoneCodes() {
    showPhoneCodesDialog();
  }

  private void showCountryCodesDialog() {

    String[] countries = new String[countryCodes.size()];
    for (int i = 0; i < countryCodes.size(); i++) {
      countries[i] = StringUtil.toTitleCase(countryCodes.get(i).getName().split(" "));
    }

    final String[] countryArr = countries;
    new AlertDialog.Builder(this).setTitle("Choose country")
            .setSingleChoiceItems(countryArr, countryItem, (dialog, item) -> {
              stilCountry.setText(countryArr[item]);
              selectedCountryCode =
                      new CountryCode(countryCodes.get(item).getId(), countryCodes.get(item).getName());
              Timber.e(String.valueOf(selectedCountryCode));
              countryItem = item;
              dialog.dismiss();
            })
            .setPositiveButton("Cancel", null)
            .show();
  }

  private void showPhoneCodesDialog() {
    List<PhoneCode.PhoneCode_> phoneCode_s = new ArrayList<>();

    for (int j = 0; j < phoneCodes.getPhoneCodes().size(); j++) {
      phoneCode_s.add(phoneCodes.getPhoneCodes().get(j));
    }

    final String[] phones = new String[phoneCode_s.size()];
    String[] codes = new String[phoneCode_s.size()];

    for (int i = 0; i < phoneCode_s.size(); i++) {
      phones[i] = phoneCode_s.get(i).getCountryName();
      codes[i] = phoneCode_s.get(i).getDiallingCode();
    }

    final String[] phoneArr = phones;
    final String[] codeArr = codes;
    //.setTitle("Choose Country Codes")
    new AlertDialog.Builder(this).setTitle("Choose dial code")
            .setSingleChoiceItems(phoneArr, phoneCodeItem, (dialog, item) -> {
              tvPhoneCode.setText("+" + codeArr[item]);
              phoneCodeItem = item;
              dialog.dismiss();
            })
            .setPositiveButton("Cancel", null)
            .show();
  }

  private void showNationalitiesDialog() {
    String[] nationality = new String[nationalities.size()];

    for (int i = 0; i < nationalities.size(); i++) {
      nationality[i] = StringUtil.toTitleCase(nationalities.get(i).getName().split(" "));
    }

    final String[] nationalityArr = nationality;
    new AlertDialog.Builder(this).setTitle("Choose nationality")
            .setSingleChoiceItems(nationalityArr, nationalityItem, (dialog, item) -> {
              stilNationality.setText(nationalityArr[item]);
              selectedNationality =
                      new Nationality(nationalities.get(item).getId(), nationalities.get(item).getName());
              Timber.e(String.valueOf(selectedNationality));
              nationalityItem = item;
              dialog.dismiss();
            })
            .setPositiveButton("Cancel", null)
            .show();
  }

  @Override
  public void returnZoneConfig(List<CountryCode> countryCodes, List<Nationality> nationalities, PhoneCode phoneCodes) {
    this.countryCodes = countryCodes;
    this.nationalities = nationalities;
    this.phoneCodes = phoneCodes;
  }

  @Override
  public void showLoading() {
    if(progressDialog == null){
      progressDialog = new ProgressDialog(this);
      progressDialog.setMessage("Loading...");
    }
    if(!progressDialog.isShowing()){
      progressDialog.show();
    }
  }

  private boolean isValid() {
    boolean IS_NOT_VALID = false;
    if (!Validator.isUsernameValid(stilFirstName.getText())) {
      stilFirstName.showError();
      IS_NOT_VALID = true;
    } else {
      stilFirstName.hideError();
    }
    if (!Validator.isUsernameValid(stilLastName.getText())) {
      stilLastName.showError();
      IS_NOT_VALID = true;
    } else {
      stilLastName.hideError();
    }
    if (!Validator.isPhoneValid(stilMobileNumber.getText())) {
      stilMobileNumber.showError();
      IS_NOT_VALID = true;
    } else {
      stilMobileNumber.hideError();
    }
    if (selectedCountryCode == null || selectedNationality == null) {
      Toast.makeText(this, "Choose Nationality or Country Code", Toast.LENGTH_SHORT).show();
      IS_NOT_VALID = true;
    }
    return IS_NOT_VALID;
  }


  @Override
  public void hideLoading() {
    if(progressDialog != null && progressDialog.isShowing()){
      progressDialog.dismiss();
    }
  }

  @Override
  public void showToast(String msg) {
    super.showToast(msg);
  }

  @OnClick(R.id.iv_toolbar_back)
  void onClickBack(){
    onBackPressed();
  }
}
