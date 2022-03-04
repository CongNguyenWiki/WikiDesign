package com.skypremiuminternational.app.app.features.splash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.skypremiuminternational.app.R;
import com.skypremiuminternational.app.app.features.landing.LandingActivity;
import com.skypremiuminternational.app.app.features.signin.SignInActivity;
import com.skypremiuminternational.app.app.internal.mvp.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;

public class SplashActivity extends BaseActivity<SplashPresenter>
    implements SplashView<SplashPresenter>  {


//  private final static int NUMBER_CONFIG = 3;

  @BindView(R.id.progressBar)
  ProgressBar progressBar;

//  int progress = 0;

  @BindView(R.id.tv_try_again)
  TextView tvTryAgain;


  @Inject
  @Override
  public void injectPresenter(SplashPresenter presenter) {
    super.injectPresenter(presenter);
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    AndroidInjection.inject(this);
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);
    ButterKnife.bind(this);
//    setConfigProgressBar();


//    // get config data
//    getPresenter().getNationalities();
//    getPresenter().getPhoneCodes();
//    getPresenter().getCountryCodes();

//      getPresenter().updateConfig();
      getPresenter().getAllData();
//
//      new Handler().postDelayed(new Runnable() {
//        @Override
//        public void run() {
//          presenter.checkLogin();
//        }
//      },5000) ;
  }

//  private void setConfigProgressBar() {
//    progressBarHorizon.setMax(NUMBER_CONFIG);
//  }


  @Override
  public void gotoLanding() {
    LandingActivity.startMe(SplashActivity.this);
    finish();

  }

  @Override
  public void gotoSignIn() {
    SignInActivity.startMe(SplashActivity.this);
    finish();
  }

  @Override
  public void render(Boolean value) {
    if (value != null && value == true){
      tvTryAgain.setVisibility(View.GONE);
      presenter.checkLogin();
    }
    else {
      tvTryAgain.setVisibility(View.VISIBLE);
    }
  }


//  @Override
//  public synchronized void updateProgress(String progressName) {
//    progress++;
//    progressBarHorizon.setProgress(progress);
//
//    if(progress ==  NUMBER_CONFIG){
//      presenter.checkLogin();
//    }
//  }


  @Override
  public void showLoading() {
    progressBar.setVisibility(View.VISIBLE);
    super.showLoading();
  }

  @Override
  public void hideLoading() {
    progressBar.setVisibility(View.GONE);
    super.hideLoading();
  }

  @OnClick(R.id.tv_try_again)
  public void onCLickTryAgain(){
    presenter.getAllData();
  }

  @Override
  public void showError(String error) {
    Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
  }
}
