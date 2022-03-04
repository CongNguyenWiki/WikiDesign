package com.skypremiuminternational.app.app.features.navigation_manager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.skypremiuminternational.app.app.features.signin.SignInActivity;
import com.skypremiuminternational.app.app.features.splash.SplashActivity;
import com.skypremiuminternational.app.domain.interactor.auth.GetAppVersion;

import javax.inject.Inject;

public class NavigationManager {
  GetAppVersion getAppVersion;


  @Inject
  public NavigationManager(GetAppVersion getAppVersion) {
    this.getAppVersion = getAppVersion;
  }


  public void getAppVersion(NavAction navAction){
    getAppVersion.asObservable().subscribe(appVersion -> {
      if(navAction != null){
        navAction.move();
      }
    },error ->{
      if(navAction != null){
        navAction.showAlert();
      }
    });
  }


  public void navWithPermission(NavAction navAction){
    navAction.move();
  }




  public interface NavAction {

    public void move();

    public void showAlert();
  }



}
