package com.skypremiuminternational.app.app.features.signin;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.skypremiuminternational.app.R;
import com.skypremiuminternational.app.app.features.forgot_password.ForgotPasswordActivity;
import com.skypremiuminternational.app.app.features.landing.LandingActivity;
import com.skypremiuminternational.app.app.features.navigation_manager.NavigationManager;
import com.skypremiuminternational.app.app.features.skytextinputlayout.SkyTextInputLayout;
import com.skypremiuminternational.app.app.internal.mvp.BaseActivity;
import com.skypremiuminternational.app.data.network.request.SignInRequest;
import com.skypremiuminternational.app.domain.models.config.BackgroundLogin;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;

public class SignInActivity extends BaseActivity<SignInPresenter> implements SignInView<SignInPresenter> {


  @BindView(R.id.stilEmail)
  SkyTextInputLayout stilEmail;
  @BindView(R.id.stilPassword)
  SkyTextInputLayout stilPassword;
  @BindView(R.id.img)
  ImageView img;


  ProgressDialog progressDialog;

  @Inject
  NavigationManager navigationManager;

  public static void startMe(Context context){
    Intent intent = new Intent(context,SignInActivity.class);
    context.startActivity(intent);
  }

  @Inject
  @Override
  public void injectPresenter(SignInPresenter presenter) {
    super.injectPresenter(presenter);
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    AndroidInjection.inject(this);
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_signin);
    ButterKnife.bind(this);

    List<BackgroundLogin> urlBg = getPresenter().getBackgroundLoginLocal();
//    getPresenter().getCategoryFromApi();
    if(urlBg != null && urlBg.size() > 0){
      Glide.with(this).load(urlBg.get(0).getBackgroundImage()).into(img);
    }
    getPresenter().getBackgroundLogin();
  }

  @OnClick(R.id.tvSignIn)
  void onClickSignIn() {


    if (TextUtils.isEmpty(stilEmail.getText())){
      stilEmail.showError();
    }
    if (TextUtils.isEmpty(stilPassword.getText())){
      stilPassword.showError();
    }
    else {
      stilEmail.hideError();
      stilPassword.hideError();

      SignInRequest request = new SignInRequest();
      request.setUserName(stilEmail.getText());
      request.setPassword(stilPassword.getText());
      presenter.signIn(request);
    }


//    getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
//        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
//
//    navigationManager.navWithPermission(new NavigationManager.NavAction() {
//      @Override
//      public void move() {
//        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
//        LandingActivity.startMe(SignInActivity.this);
//      }
//
//      @Override
//      public void showAlert() {
//        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
//        new AlertDialog.Builder(SignInActivity.this)
//            .setTitle("Alert")
//            .setMessage("Don't have permission")
//            .create()
//            .show();
//      }
//    });

  }


  @Override
  public void gotoLanding() {
    LandingActivity.startMe(this);
  }

  @Override
  public void showError(Throwable throwable) {
//    Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
    Toast.makeText(this, " " + getString(R.string.invalid_email_password), Toast.LENGTH_SHORT).show();

  }

  @Override
  public void showLoading() {
    if(progressDialog == null ){
      progressDialog =  new ProgressDialog(this);
    }
    progressDialog.setMessage("Loading...");
    progressDialog.show();
  }

  @Override
  public void hideLoading() {
    if(progressDialog !=null  && progressDialog.isShowing()){
      progressDialog.dismiss();
    }
  }

  @OnClick(R.id.tvForgotPassword)
  void onClickForgotPassword() {
    ForgotPasswordActivity.startMe(this);
  }


  @Override
  public void renderBackground(String url) {
    Glide.with(this)
            .load(url)
            .into(img);
  }
}
