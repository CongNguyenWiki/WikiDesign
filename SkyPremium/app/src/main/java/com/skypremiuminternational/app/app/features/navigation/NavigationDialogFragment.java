package com.skypremiuminternational.app.app.features.navigation;

import static android.app.Activity.RESULT_OK;

import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.MimeTypeMap;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.skypremiuminternational.app.BuildConfig;
import com.skypremiuminternational.app.R;
import com.skypremiuminternational.app.app.features.faq.FaqActivity;
import com.skypremiuminternational.app.app.features.info.InfoActivity;
import com.skypremiuminternational.app.app.features.landing.LandingActivity;
import com.skypremiuminternational.app.app.features.my_favourite.MyFavouriteActivity;
import com.skypremiuminternational.app.app.features.navigation.expandable.CategoryAdapter;
import com.skypremiuminternational.app.app.features.navigation.expandable.listener.IActionCLickListener;
import com.skypremiuminternational.app.app.features.profile.my_profile.ProfileActivity;
import com.skypremiuminternational.app.app.features.signin.SignInActivity;
import com.skypremiuminternational.app.app.internal.mvp.BaseDialogFragment;
import com.skypremiuminternational.app.app.utils.DecimalUtil;
import com.skypremiuminternational.app.app.utils.WebViewURL;
import com.skypremiuminternational.app.domain.models.category.ChildData;
import com.skypremiuminternational.app.domain.models.category.ChildData_;
import com.skypremiuminternational.app.domain.models.user.CustomAttribute;
import com.skypremiuminternational.app.domain.models.user.UserDetailResponse;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.AndroidSupportInjection;

public class NavigationDialogFragment extends BaseDialogFragment<NavigationPresenter> implements NavigationView<NavigationPresenter> {


  public static final String LINK_ABOUT = BuildConfig.CWEB_DOMAIN+"/sg/about?source=mobile";
  public static final String LINK_MEMBERSHIP_SERVICE = BuildConfig.BASE_URL+"/sg/member/membershipservice";
  public static final String LINK_LEGAL = BuildConfig.CWEB_DOMAIN+"/sg/legal?source=mobile";
  public static final String LINK_MEMBER_BENEFITS = BuildConfig.CWEB_DOMAIN+"/sg/sky-premium-exclusive-privileges?source=mobile";
  public static final String LINK_HOW_IT_WORKS = BuildConfig.CWEB_DOMAIN+"/sg/how-it-works?source=mobile";



  @BindView(R.id.imgAvatar)
  ImageView imgAvatar;
  @BindView(R.id.tvName)
  TextView tvName;
  @BindView(R.id.tvPoints)
  TextView tvPoints;
  @BindView(R.id.tvVersion)
  TextView tvVersion;
  @BindView(R.id.expandableListView)
  ExpandableListView expandableListView;
  CategoryAdapter categoryAdapter;
  private String  uploadImageUrl, imageName, imageType;


  public static NavigationDialogFragment newInstance() {
    NavigationDialogFragment fragment = new NavigationDialogFragment();
    return fragment;
  }


  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    //return super.onCreateDialog(savedInstanceState);
    Dialog dialog = super.onCreateDialog(savedInstanceState);
    //dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
    return dialog;
  }

  @Override
  public void onStart() {
    super.onStart();
    Dialog dialog = getDialog();
    if (dialog != null) {
      dialog.getWindow()
          .setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
      dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ButterKnife.bind(view);


    String versionName = "v" + BuildConfig.VERSION_NAME + "." + BuildConfig.BUILD_TIME
            + BuildConfig.APP_TYPE;
    tvVersion.setText(versionName);
    presenter.getUserDetailsLocal();
//    expandableListView.setGroupIndicator(null);

    getExpandalbeData(getPresenter().getInternalStore().getCategories().getChildrenData());


  }

  private void getExpandalbeData(List<ChildData> childrenData) {
    List<ChildData> list = new ArrayList<>();
    for(ChildData childData : childrenData){
      if (childData.getName().equalsIgnoreCase("Home")
              || childData.getName().equalsIgnoreCase("Travel")
              || childData.getName().equalsIgnoreCase("Wine & Dine")
              || childData.getName().equalsIgnoreCase("Shopping")
              || childData.getName().equalsIgnoreCase("Wellness")
              || childData.getName().equalsIgnoreCase("eStore")){

        list.add(childData);
      }
    }
    CategoryAdapter categoryAdapter = new CategoryAdapter(getContext(), list);
    expandableListView.setAdapter(categoryAdapter);

    categoryAdapter.setItemActionListener(new IActionCLickListener() {
      @Override
      public void onClickedItem(ChildData item) {
        if (item.getName().equalsIgnoreCase("Home")) {
          LandingActivity.startMe(getActivity());
        } else if (item.getName().equalsIgnoreCase("Travel")) {
          LandingActivity.startMe(getActivity(), WebViewURL.DEFAULT + item.getLink(), 0, true);
        } else if (item.getName().equalsIgnoreCase("Wine & Dine")) {
          LandingActivity.startMe(getActivity(), WebViewURL.DEFAULT + item.getLink(), 1, true);
        } else if (item.getName().equalsIgnoreCase("Shopping")) {
          LandingActivity.startMe(getActivity(), WebViewURL.DEFAULT + item.getLink(), 3, true);
        } else if (item.getName().equalsIgnoreCase("Wellness")) {
          LandingActivity.startMe(getActivity(), WebViewURL.DEFAULT + item.getLink(), 4, true);
        } else if (item.getName().equalsIgnoreCase("eStore")) {
          LandingActivity.startMe(getActivity(), WebViewURL.ESTORE + item.getLink(), -1, true);
        }
      }


      @Override
      public void onClickedSubItem(String name, ChildData_ item) {
        if (name.equalsIgnoreCase("Home")) {
          LandingActivity.startMe(getActivity());
        } else if (name.equalsIgnoreCase("Travel")) {
          LandingActivity.startMe(getActivity(), WebViewURL.DEFAULT + item.getLink(), 0, true);
        } else if (name.equalsIgnoreCase("Wine & Dine")) {
          LandingActivity.startMe(getActivity(), WebViewURL.DEFAULT + item.getLink(), 1, true);
        } else if (name.equalsIgnoreCase("Shopping")) {
          LandingActivity.startMe(getActivity(), WebViewURL.DEFAULT + item.getLink(), 3, true);
        } else if (name.equalsIgnoreCase("Wellness")) {
          LandingActivity.startMe(getActivity(), WebViewURL.DEFAULT + item.getLink(), 4, true);
        } else if (name.equalsIgnoreCase("eStore")) {
          LandingActivity.startMe(getActivity(), WebViewURL.ESTORE + item.getLink(), -1, true);
        }

      }

      @Override
      public void onClickedSubItem_(String name, ChildData item) {
        if (name.equalsIgnoreCase("Home")) {
          LandingActivity.startMe(getActivity());
        } else if (name.equalsIgnoreCase("Travel")) {
          LandingActivity.startMe(getActivity(), WebViewURL.DEFAULT + item.getLink(), 0, true);
        } else if (name.equalsIgnoreCase("Wine & Dine")) {
          LandingActivity.startMe(getActivity(), WebViewURL.DEFAULT + item.getLink(), 1, true);
        } else if (name.equalsIgnoreCase("Shopping")) {
          LandingActivity.startMe(getActivity(), WebViewURL.DEFAULT + item.getLink(), 3, true);
        } else if (name.equalsIgnoreCase("Wellness")) {
          LandingActivity.startMe(getActivity(), WebViewURL.DEFAULT + item.getLink(), 4, true);
        } else if (name.equalsIgnoreCase("eStore")) {
          LandingActivity.startMe(getActivity(), WebViewURL.ESTORE + item.getLink(), -1, true);
        }
      }

    });
  }



  @Override
  public void onDismiss(DialogInterface dialog) {
    super.onDismiss(dialog);
  }

  @OnClick(R.id.imgClose)
  void onClickContinue() {
    dismiss();
  }

  @OnClick(R.id.tvLogOut)
  void onClickLogout() {
    presenter.onLogout();
    dismiss();
    getActivity().finish();
    Intent mIntent = new Intent(getActivity(), SignInActivity.class);
    mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
    startActivity(mIntent);
  }

  @OnClick(R.id.tvProfile)
  void onClickMyProfile() {
    ProfileActivity.startMe(getActivity());
  }


  @OnClick(R.id.tvHome)
  void onClickHome() {
    LandingActivity.startMe(getActivity());
  }

//  @OnClick(R.id.tvEStore)
//  void onClickProduct() {
//   //ProductActivity.startMe(getActivity());
//    LandingActivity.startMe(getActivity(), WebViewURL.ESTORE_LISTING,-1,true);
//  }
//
//  @OnClick(R.id.tvTravel)
//  void onClickTravel(){
//    LandingActivity.startMe(getActivity(), WebViewURL.TRAVEL_LISTING,0,true);
//  }
//
//  @OnClick(R.id.tvWellness)
//  void onClickWellness(){
//    LandingActivity.startMe(getActivity(), WebViewURL.WELLNESS_LISTING,4,true);
//  }
//
//  @OnClick(R.id.tv_wine)
//  void onClickWineDine(){
//    LandingActivity.startMe(getActivity(), WebViewURL.WINE_DINE_LISTING,1,true);
//  }
//
//  @OnClick(R.id.tvShopping)
//  void onClickShopping(){
//    LandingActivity.startMe(getActivity(), WebViewURL.SHOPPING_LISTING,3,true);
//  }

  @OnClick(R.id.tvFavourites)
  void onClickMyFavourite() {
    MyFavouriteActivity.startMe(getActivity());
  }


  @OnClick(R.id.tvFaq)
  void onClickFaq() {
    dismiss();
    if (getActivity().getClass()
            .getSimpleName()
            .equalsIgnoreCase(LandingActivity.class.getSimpleName())) {
        FaqActivity.startMe(getContext());
    }
    else if (!getActivity().getClass()
            .getSimpleName()
            .equalsIgnoreCase(FaqActivity.class.getSimpleName())) {
        getActivity().finish();
        FaqActivity.startMe(getContext());
    }

  }

  @OnClick(R.id.tvAbout)
  void onClickAbout() {
    showInfo(LINK_ABOUT, "About");
  }



  @OnClick(R.id.tvLegal)
  void onClickLegal() {
    showInfo(LINK_LEGAL, "Legal");
  }

  @OnClick(R.id.tvMemberBenefits)
  void onClickMemberBenefits() {
    showInfo(LINK_MEMBER_BENEFITS, "Member Benefits");
  }

  @OnClick(R.id.tvHowItWorks)
  void onClickHowItWorks() {
    showInfo(LINK_HOW_IT_WORKS, "How it works");
  }

  @OnClick(R.id.tvMembership)
  void onClickMembership() {
    showInfo(LINK_MEMBERSHIP_SERVICE, "Membership Service");
  }


  @OnClick(R.id.imgFacebook)
  void onClickFacebook() {
    String url = "https://www.facebook.com/SkyPremiumSG/";
    Intent i = new Intent(Intent.ACTION_VIEW);
    i.setData(Uri.parse(url));
    startActivity(i);
  }

  @OnClick(R.id.imgInstagram)
  void onClickInstagram() {
    String url = "https://www.instagram.com/skypremiumsg/";
    Intent i = new Intent(Intent.ACTION_VIEW);
    i.setData(Uri.parse(url));
    startActivity(i);
  }

  private void showInfo(String link, String title) {

    if (getActivity().getClass()
            .getSimpleName()
            .equalsIgnoreCase(LandingActivity.class.getSimpleName())) {
      InfoActivity.start(getActivity(), link, title);
    } else {
      getActivity().finish();
      InfoActivity.start(getActivity(), link, title);
    }
    dismiss();
  }


  @Override
  protected int getLayoutId() {
    return R.layout.dialog_fragment_navigation_old;
  }

  @Override
  public void onAttach(@NonNull Context context) {
    AndroidSupportInjection.inject(this);
    super.onAttach(context);
  }

  @Inject
  @Override
  public void injectPresenter(NavigationPresenter presenter) {
    super.injectPresenter(presenter);
  }

  @Override
  public void renderUserDetail(UserDetailResponse value) {

    String name = "";

    name += getSalutation(value);

    if (value.getFirstname() != null) {
      name += " " + value.getFirstname();
    }
    if (value.getLastname() != null) {
      name += " " + value.getLastname();
    }
    tvName.setText(name);




    //avatar
    if (value.getAvatar() != null) {
      RequestOptions requestOptions = new RequestOptions();
      requestOptions.placeholder(R.drawable.ic_person_white_24);
      requestOptions.dontAnimate();
      requestOptions.centerCrop();
      requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
      requestOptions.error(R.drawable.ic_person_white_24);
      Glide.with(getActivity())
          .asBitmap()
          .load(value.getAvatar())
          .apply(requestOptions)
          .into(new BitmapImageViewTarget(imgAvatar) {
            @Override
            protected void setResource(Bitmap resource) {
              if (getActivity() != null) {
                RoundedBitmapDrawable circularBitmapDrawable =
                    RoundedBitmapDrawableFactory.create(getActivity().getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                imgAvatar.setImageDrawable(circularBitmapDrawable);
              }
            }
          });
    }

    //loyalty amount
    if (value.getLoyaltyPoints() != null && !TextUtils.isEmpty(String.valueOf(value.getLoyaltyPoints()))) {
      tvPoints.setText(
          DecimalUtil.roundTowDecimalWithoutBrace(value.getLoyaltyPoints()));
    } else {
      tvPoints.setText(DecimalUtil.roundTowDecimalWithoutBrace(0.0));
    }

  }

  @OnClick(R.id.imgAvatar)
  public void onClickAvatar() {
    startCropImage(null);
  }

  private void startCropImage(Uri uri) {
    CropImage.activity(uri)
            //.setMinCropResultSize(500, 500)
            .setAspectRatio(500, 500)
            .setGuidelines(CropImageView.Guidelines.ON)
            .setMultiTouchEnabled(true)
            .start(getContext(), this);
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    // handle result of CropImage
    if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
      CropImage.ActivityResult result = CropImage.getActivityResult(data);
      if (resultCode == RESULT_OK) {
        //image type
        if (result.getOriginalUri().getScheme().equals(ContentResolver.SCHEME_CONTENT)) {
          ContentResolver cr = getActivity().getContentResolver();
          imageType = cr.getType(result.getOriginalUri());
        } else {
          String extension =
                  MimeTypeMap.getFileExtensionFromUrl(result.getOriginalUri().toString());
          if (extension != null) {
            imageType =
                    MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension).toLowerCase();
          }
        }

        //file Name
        imageName = result.getUri().getLastPathSegment();

        final InputStream imageStream;
        try {
          imageStream = getActivity().getContentResolver().openInputStream(result.getUri());
          final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);

          ByteArrayOutputStream baos = new ByteArrayOutputStream();
          selectedImage.compress(Bitmap.CompressFormat.JPEG, 100, baos);
          byte[] b = baos.toByteArray();
          uploadImageUrl = Base64.encodeToString(b, Base64.DEFAULT);

          presenter.uploadImageToServer(uploadImageUrl, imageType, imageName);
        } catch (FileNotFoundException e) {
          e.printStackTrace();
        }
      } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
        Toast.makeText(getActivity(), "Upload Fail", Toast.LENGTH_SHORT).show();
      }
    }
  }


  private String getSalutation(UserDetailResponse response) {
    for (CustomAttribute attributes : response.getCustomAttributes()) {
      if (attributes.getAttributeCode().equals("salutation")) {
        return attributes.getValue();
      }
    }
    return "";
  }

  public void refreshUserDetails(){
    presenter.getUserDetailsLocal();
  }
}
