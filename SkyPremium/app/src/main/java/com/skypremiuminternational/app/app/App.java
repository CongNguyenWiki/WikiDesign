package com.skypremiuminternational.app.app;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;
import androidx.fragment.app.Fragment;

import cat.ereza.customactivityoncrash.CustomActivityOnCrash;
import com.skypremiuminternational.app.BuildConfig;
import com.skypremiuminternational.app.R;
import com.skypremiuminternational.app.app.internal.di.DaggerApplicationComponent;
import com.skypremiuminternational.app.app.utils.DebugTree;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;

import javax.inject.Inject;

import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;
import timber.log.Timber;



public class App extends MultiDexApplication
    implements HasAndroidInjector{

  @Inject
  DispatchingAndroidInjector<Object> dispatchingAndroidInjector;

  private static Context context;
  public static boolean isOpenningApp = false;

  @Override
  public void onCreate() {
    super.onCreate();

    context = getApplicationContext();

    DaggerApplicationComponent.builder().application(this).build().inject(this);

    ViewPump.init(ViewPump.builder()
        .addInterceptor(new CalligraphyInterceptor(
            new CalligraphyConfig.Builder()
                .setDefaultFontPath(getString(R.string.fonts_Lato_Regular))
                .setFontAttrId(R.attr.fontPath)
                .build()))
        .build());
    if (BuildConfig.DEBUG) {
      CustomActivityOnCrash.install(this);
    }
    Timber.plant(new DebugTree());
    MultiDex.install(this);
    registerBroadcastReceiver();
  }

  public static Context getAppContext() {
    return context;
  }




  private void registerBroadcastReceiver() {
  }

  public LocalBroadcastManager getLocalBroadcastManager() {
    return LocalBroadcastManager.getInstance(context);
  }

  BroadcastReceiver restoreIdReceiver = new BroadcastReceiver() {
    @Override
    public void onReceive(Context context, Intent intent) {

    }

  };


  @Override
  public void onTerminate() {
    isOpenningApp =  false;
    super.onTerminate();
    getLocalBroadcastManager().unregisterReceiver(restoreIdReceiver);
  }

  @Override
  public AndroidInjector<Object> androidInjector() {
    return dispatchingAndroidInjector;
  }
}
