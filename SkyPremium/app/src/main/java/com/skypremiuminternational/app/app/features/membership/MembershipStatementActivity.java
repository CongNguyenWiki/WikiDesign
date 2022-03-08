package com.skypremiuminternational.app.app.features.membership;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.j256.ormlite.stmt.query.In;
import com.skypremiuminternational.app.R;
import com.skypremiuminternational.app.app.internal.mvp.BaseActivity;
import com.skypremiuminternational.app.app.utils.Constants;
import com.skypremiuminternational.app.app.utils.DecimalUtil;
import com.skypremiuminternational.app.domain.models.mymembership_statement.InforMembershipResponse;
import com.skypremiuminternational.app.domain.models.mymembership_statement.ListMemberShipResponse;
import com.skypremiuminternational.app.domain.models.mymembership_statement.MyMembershipStatementItem;
import com.skypremiuminternational.app.domain.models.user.UserDetailResponse;
import com.skypremiuminternational.app.domain.utils.ProductUtil;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;

public class MembershipStatementActivity extends BaseActivity<MembershipStatementPresenter>
    implements MembershipStatementView<MembershipStatementPresenter> {


  @BindView(R.id.rvMyMembershipStatement)
  RecyclerView rvMyMembershipStatement;
  @BindView(R.id.tvMembership)
  TextView tvMembership;
  @BindView(R.id.tvLoyaltyExpireDate)
  TextView tvLoyaltyExpireDate;
  @BindView(R.id.tvMemberNumber)
  TextView tvMemberNumber;
  @BindView(R.id.img_avatar)
  ImageView img_avatar;
  @BindView(R.id.tvName)
  TextView tvName;
  @BindView(R.id.txt_amtloyalty)
  TextView txtAmtLoyalty;
  @BindView(R.id.tvTitle_toolbar)
  TextView tvTitle_toolbar;
  @BindView(R.id.tvSort_filter)
  TextView tvSortFilter;

  private int selectedCategory = 0;
  private int selectedSorting = 0;
  MyMembershipAdapter adapter;
  String numberMember;
  String salutation;

  public static void startMe(Context context){
    Intent intent = new Intent(context, MembershipStatementActivity.class);
    context.startActivity(intent);

  }



  @Inject
  @Override
  public void injectPresenter(MembershipStatementPresenter presenter) {
    super.injectPresenter(presenter);
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    AndroidInjection.inject(this);
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_membership_statement);
    ButterKnife.bind(this);

    tvTitle_toolbar.setText("My Membership");

    tvSortFilter.setText(String.format("Sort By: %s", Constants.sortArrMembershipStatement[selectedSorting]));



    setupMembershipRecyclerView();
    presenter.getUserDetails();
    fetchMembershipHistory();

  }

  private void setupMembershipRecyclerView() {
    adapter = new MyMembershipAdapter(true,true,true,true,true);
    rvMyMembershipStatement.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

    rvMyMembershipStatement.setAdapter(adapter);
  }


  @Override
  public void renderMembershipStatemnet(List<MyMembershipStatementItem> responseList) {
    adapter.setDataList(responseList);
  }

  @Override
  public void renderUserDetail(UserDetailResponse response) {

    for(int i=0 ; i <response.getCustomAttributes().size();i++){
      if (response.getCustomAttributes().get(i).getAttributeCode().equals("member_number")) {
        numberMember = response.getCustomAttributes().get(i).getValue();
      }
      if (response.getCustomAttributes().get(i).getAttributeCode().equals("salutation")) {
        salutation = response.getCustomAttributes().get(i).getValue();
      }
    }

    if (response.getAvatar() != null) {
      RequestOptions requestOptions = new RequestOptions();
      requestOptions.placeholder(R.drawable.ic_person_white_24);
      requestOptions.dontAnimate();
      requestOptions.centerCrop();
      requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
      requestOptions.error(R.drawable.ic_person_white_24);
      Glide.with(getApplicationContext())
          .asBitmap()
          .load(response.getAvatar())
          .apply(requestOptions)
          .into(new BitmapImageViewTarget(img_avatar) {
            @Override
            protected void setResource(Bitmap resource) {
              RoundedBitmapDrawable circularBitmapDrawable =
                  RoundedBitmapDrawableFactory.create(getApplicationContext().getResources(),
                      resource);
              circularBitmapDrawable.setCircular(true);
              img_avatar.setImageDrawable(circularBitmapDrawable);
            }
          });
    }


    tvMemberNumber.setText(numberMember);

    String name = "";
    name += salutation;

    if (response.getFirstname() != null) {
      name += " " + response.getFirstname();
    }

    if (response.getLastname() != null) {
      name += " " + response.getLastname();
    }
    tvName.setText(name);

    if (response.getLoyaltyPoints() != null) {
      txtAmtLoyalty.setText(
          DecimalUtil.roundTowDecimalWithoutBrace(response.getLoyaltyPoints()));
    } else {
      txtAmtLoyalty.setText(DecimalUtil.roundTwoDecimals(0.0));
    }

    if (ProductUtil.isValid(response.getLoyaltyPointExpiryDate())) {
      String dateFormat = "yyyy-MM-dd";

      DateFormat formatter = new SimpleDateFormat(dateFormat);
      Date date = null;
      try {
        date = (Date) formatter.parse(response.getLoyaltyPointExpiryDate());
      } catch (ParseException e) {
        e.printStackTrace();
      }

      SimpleDateFormat newFormat = new SimpleDateFormat("dd MMM yyyy");
      String finalString = newFormat.format(date);

      tvLoyaltyExpireDate.setText(""+getString(R.string.sky_dollar_exp_on)+" "+finalString+"");
    } else {
      tvLoyaltyExpireDate.setText("");
    }

    presenter.getInfoMembership();
  }

  @OnClick(R.id.tvSort_filter)
  public void onClickSort() {
    new AlertDialog.Builder(this).setTitle("SORT BY:")
        .setItems(Constants.sortArrMembershipStatement, (dialog, item) -> {
          selectedSorting = item;
          tvSortFilter.setText(String.format("Sort By: %s", Constants.sortArrMembershipStatement[item]));
          fetchMembershipHistory();

        })
        .setNegativeButton("Cancel", null)
        .show();
  }

  private void fetchMembershipHistory() {
    getPresenter().setRequest(Constants.sortArrMembershipStatement[selectedSorting],
        Constants.refineArrMembershipStatement[selectedCategory],numberMember,"","");
    getPresenter().getMembershipStatement();
  }


  @Override
  public void renderInfoMembership(List<InforMembershipResponse> inforMembershipResponses) {

    if (inforMembershipResponses.get(0).getAccountStatus().equalsIgnoreCase("Activated") &&
        ((inforMembershipResponses.get(0).getContractStatus().equalsIgnoreCase("Valid")) ||
            inforMembershipResponses.get(0).getContractStatus().equalsIgnoreCase("Withdrawing"))){
//            tvMembership.setText(new StringBuilder("Your ").append(inforMembershipResponses.get(0).getMembershipTier()).append(" is active and is valid till ").append(parseDateformat(inforMembershipResponses.get(0).getExpiryDate())));
      String title = getColoredSpanned("Your membership is active and will expire on","#4A4949");
      String expireDate = getColoredSpanned(parseDateformat(inforMembershipResponses.get(0).getExpiryDate()),"#BAA9AD");
      tvMembership.setText(Html.fromHtml(title + "<br>" + " " +expireDate));

    }
    if (inforMembershipResponses.get(0).getAccountStatus().equalsIgnoreCase("Limited") &&
        ((inforMembershipResponses.get(0).getContractStatus().equalsIgnoreCase("Blacklisted")) ||
            inforMembershipResponses.get(0).getContractStatus().equalsIgnoreCase("Cancelled"))){
      tvMembership.setText(new StringBuilder("Your ").append(inforMembershipResponses.get(0).getMembershipTier()).append(" is inactive since ").append(parseDateformat(inforMembershipResponses.get(0).getExpiryDate())));
    }

    if (inforMembershipResponses.get(0).getAccountStatus().equalsIgnoreCase("Activated") &&
        inforMembershipResponses.get(0).getContractStatus().equalsIgnoreCase("Valid")){
//            tv_cancel_membership.setVisibility(View.VISIBLE);
    }


    if (inforMembershipResponses.get(0).getPaymentPlan().equalsIgnoreCase("OT")){
//            btn_renew_membership.setText("RENEW MEMBERSHIP");
    }
    else {
      if (inforMembershipResponses.get(0).getAccountStatus().equalsIgnoreCase("Activated") &&
          inforMembershipResponses.get(0).getMembershipTier().toLowerCase().equalsIgnoreCase("Platinum membership")){
//                btn_renew_membership.setText("DOWNGRADE MEMBERSHIP");
      }
      if (inforMembershipResponses.get(0).getAccountStatus().equalsIgnoreCase("Activated") &&
          inforMembershipResponses.get(0).getMembershipTier().toLowerCase().equalsIgnoreCase("Gold membership")){
//                btn_renew_membership.setText("UPGRADE MEMBERSHIP");
      }
      if (inforMembershipResponses.get(0).getAccountStatus().equalsIgnoreCase("Limited")){
//                btn_renew_membership.setText("RENEW MEMBERSHIP");
      }
    }
  }

  @OnClick(R.id.ivNavigation_toolbar)
  void onClickBack(){
    onBackPressed();
  }

  private String parseDateformat(String time){
    String inputPattern = "yyyy-MM-dd HH:mm:ss";
    SimpleDateFormat format = new SimpleDateFormat("d");
    SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);

    Date date = null;
    String str = null;

    try {
      date = inputFormat.parse(time);
      str = format.format(date);
    } catch (ParseException e) {
      e.printStackTrace();
    }


    if(str.endsWith("1") && !str.endsWith("11"))
      format = new SimpleDateFormat("d'st' MMM yyyy", Locale.US);
    else if(str.endsWith("2") && !str.endsWith("12"))
      format = new SimpleDateFormat("d'nd' MMM yyyy",Locale.US);
    else if(str.endsWith("3") && !str.endsWith("13"))
      format = new SimpleDateFormat("d'rd' MMM yyyy",Locale.US);
    else
      format = new SimpleDateFormat("d'th' MMM yyyy",Locale.US);

    String yourDate = format.format(date);

    return yourDate;
  }

  private String getColoredSpanned(String text, String color) {
    String input = "<font color=" + color + ">" + text + "</font>";
    return input;
  }
}
