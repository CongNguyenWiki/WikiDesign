package com.skypremiuminternational.app.app.features.landing;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.skypremiuminternational.app.BuildConfig;
import com.skypremiuminternational.app.R;
import com.skypremiuminternational.app.app.features.landing.home.MainBannerSliderAdapter;
import com.skypremiuminternational.app.app.features.navigation.NavigationDialogFragment;
import com.skypremiuminternational.app.app.features.search.SearchActivity;
import com.skypremiuminternational.app.app.internal.mvp.BaseActivity;
import com.skypremiuminternational.app.app.utils.WebViewURL;
import com.skypremiuminternational.app.domain.models.gift_redeemtion.GiftPopUpResponse;
import com.skypremiuminternational.app.domain.models.gift_redeemtion.Result;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;

public class LandingActivity extends BaseActivity<LandingPresenter> implements LandingView<LandingPresenter> {

  public static final String PAGE_KEY = "PAGE";

  public static final String TOKEN_KEY = "customer_token";
  public static String URL_ECM =  WebViewURL.DEFAULT;


  @BindView(R.id.webView)
  WebView webView;
  @BindView(R.id.webProgressBar)
  ProgressBar webProgressBar;
  @BindView(R.id.tvTitle_toolbar)
  TextView tvTitle_toolbar;
  @BindView(R.id.swiperefresh)
  SwipeRefreshLayout swiperefresh;
  @BindView(R.id.ahbn)
  AHBottomNavigation ahbn;
  @BindView(R.id.ivSearch_toolbar)
  ImageView ivSearchToolbar;
  @BindView(R.id.ly_cart_count)
  FrameLayout lyCartCount;
  @BindView(R.id.tv_cart_count)
  TextView tvCartCount;
  @BindView(R.id.clOnboardingBtn)
  ImageButton clOnBoarding;
  @BindView(R.id.clMarketingBtn)
  ImageButton clMarketing;

  int position = -1;
  boolean isBack = false;
  String keyword="",urlMarketing="",urlOnboardingOrRenewal="";



  public static void startMe(Context context) {
    Intent intent = new Intent(context, LandingActivity.class);
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
    context.startActivity(intent);
  }

  public static void startMe(Context context, String page) {
    Intent intent = new Intent(context, LandingActivity.class);
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
    intent.putExtra(PAGE_KEY,page);
    context.startActivity(intent);
  }

  public static void startMe(Context context, String page,int position,boolean isBack) {
    Intent intent = new Intent(context, LandingActivity.class);
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
    intent.putExtra(PAGE_KEY,page);
    intent.putExtra("position",position);
    intent.putExtra("isBack",isBack);
    context.startActivity(intent);
  }

  public static void startMe(Context context, String page,String keyword,int position,boolean isBack) {
    Intent intent = new Intent(context, LandingActivity.class);
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
    intent.putExtra(PAGE_KEY,page);
    intent.putExtra("keyword",keyword);
    intent.putExtra("position",position);
    intent.putExtra("isBack",isBack);
    context.startActivity(intent);
  }




  @Inject
  @Override
  public void injectPresenter(LandingPresenter presenter) {
    super.injectPresenter(presenter);
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    AndroidInjection.inject(this);
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_landing_webview);
    ButterKnife.bind(this);

    //get params
    position = getIntent().getIntExtra("position",2);
    isBack = getIntent().getBooleanExtra("isBack",false);
    keyword = getIntent().getStringExtra("keyword");

    if(getIntent() != null && !TextUtils.isEmpty(getIntent().getStringExtra(PAGE_KEY)) && isBack == true){
      URL_ECM = getIntent().getStringExtra(PAGE_KEY);
    }
    else {
      URL_ECM = WebViewURL.DEFAULT;
    }


    presenter.getRatingOption();
    presenter.showPopUp(getPresenter().internalStorageManager.getUserDetail().getId(),getPresenter().internalStorageManager.getUserDetail().getStoreId());


    // init webView
    setupWebView(URL_ECM);
    //
    setupBottomMenu();
    // init swipe down
    swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override
      public void onRefresh() {
        webView.reload();
      }
    });


  }

  @Override
  protected void onResume() {
    super.onResume();
    presenter.getCartCount();

    for(Fragment fragment : getSupportFragmentManager ().getFragments()){
      if(fragment.getTag().equalsIgnoreCase("Navigation")){
        NavigationDialogFragment navigationDialogFragment = (NavigationDialogFragment) fragment;
        navigationDialogFragment.refreshUserDetails();
      }
    }

  }


  private void setupBottomMenu() {




    ahbn.setTitleTextSizeInSp(9, 9);
    ahbn.setAccentColor(ContextCompat.getColor(this, R.color.colorAccent));
    ahbn.setDefaultBackgroundColor(Color.parseColor("#FFFFFF"));
    ahbn.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
    ahbn.setInactiveColor(Color.parseColor("#C0B8B6"));

    AHBottomNavigationItem item1 =
            new AHBottomNavigationItem(R.string.travel_all_caps, R.drawable.ic_travel_tab,
                    R.color.colorAccent);
    AHBottomNavigationItem item2 =
            new AHBottomNavigationItem(R.string.wine_all_caps, R.drawable.ic_wine_tab,
                    R.color.colorAccent);
    AHBottomNavigationItem item3 =
            new AHBottomNavigationItem(R.string.home_all_caps, R.drawable.ic_logo_tab,
                    R.color.colorAccent);
    AHBottomNavigationItem item4 =
            new AHBottomNavigationItem(R.string.shopping_all_caps, R.drawable.ic_shopping_tab,
                    R.color.colorAccent);
    AHBottomNavigationItem item5 =
            new AHBottomNavigationItem(R.string.wellness_all_caps, R.drawable.ic_wellness_tab,
                    R.color.colorAccent);

    ahbn.addItem(item1);
    ahbn.addItem(item2);
    ahbn.addItem(item3);
    ahbn.addItem(item4);
    ahbn.addItem(item5);



    ahbn.setOnTabSelectedListener((position, wasSelected) -> {

      switch (position) {
        case 0 : {
          if (isBack == false){
            webView.loadUrl(WebViewURL.TRAVEL);
          }
          else {
            isBack = false;
          }
          return true;
        }
        case 1 : {
          if (isBack == false){
            webView.loadUrl(WebViewURL.WINE_DINE);
          }
          else {
            isBack = false;
          }
          return true;
        }
        case 2 : {
          if (isBack == false){
            webView.loadUrl(WebViewURL.DEFAULT);
          }
          else {
            isBack = false;
          }
          return true;
        }
        case 3 : {
          if (isBack == false){
            webView.loadUrl(WebViewURL.SHOPPING);
          }
          else {
            isBack = false;
          }
          return true;
        }
        case 4 : {
          if (isBack == false){
            webView.loadUrl(WebViewURL.WELLNESS);
          }
          else {
            isBack = false;
          }
          return true;
        }


      }

      return false;
    });




    if (position != -1 && isBack == true){

      ahbn.setCurrentItem(position,true);
    }
    else if (position == -1 && isBack == true){

      isBack =false;
      if (URL_ECM.contentEquals(WebViewURL.SHOPPING_CART)){
        webView.loadUrl(URL_ECM);
      }
      else if (URL_ECM.contentEquals(WebViewURL.ESTORE)){
        webView.loadUrl(URL_ECM+"estore/listing");
      }
      else if (URL_ECM.startsWith(WebViewURL.SEARCH)){
        webView.loadUrl(URL_ECM+"?query="+keyword);
      }
      ahbn.setCurrentItem(-1,false);
    }
    else {
      ahbn.setCurrentItem(2,true);
    }

  }

  private void setupWebView(String urlEcm){
    webProgressBar.setMax(100);

    WebChromeClient webChromeClient =  new WebChromeClient(){
      @Override
      public void onProgressChanged(WebView view, int newProgress) {
        super.onProgressChanged(view, newProgress);
        webProgressBar.setProgress(newProgress);
        if(webProgressBar.getProgress() >= 100){
          webProgressBar.setVisibility(View.GONE);
        } else {
          webProgressBar.setVisibility(View.VISIBLE);
        }
      }
    };


    webView.setWebViewClient(new WebViewClient(){
      @Override
      public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        return super.shouldOverrideUrlLoading(view, request);
      }

      @Override
      public boolean shouldOverrideUrlLoading(WebView view, String url) {
        return super.shouldOverrideUrlLoading(view, url);
      }

      @Override
      public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        long ttl = 2592000;
        long timeStored = Calendar.getInstance().getTimeInMillis()*1000;

        String js =
                "(function(){\n" +
                        "        " +
                        "         let tk = window.localStorage.getItem('M2_VENIA_BROWSER_PERSISTENCE__signin_token');\n" +
                        "         const data = {\"value\":\"\\\""+presenter.getCustomerToken()+"\\\"\",\"timeStored\":"+timeStored+",\"ttl\":"+ttl+"};\n" +
                        "         if((new Date()).getTime() > "+(timeStored + ttl * 1000)+"){\n" +
                        "           return;\n" +
                        "         }\n" +
                        "         console.log(tk);" +
                        "         console.log(JSON.stringify(data));" +
                        "         if(!tk || (tk && tk != JSON.stringify(data))){\n" +
                        "         window.localStorage.clear();\n" +
                        "         window.localStorage.setItem('M2_VENIA_BROWSER_PERSISTENCE__signin_token',JSON.stringify(data));\n" +
//                        "         window.localStorage.setItem('is_mobile', true);" +
//                        "         window.location.reload();\n" +
                        "         }\n" +
                        "         window.localStorage.setItem('is_mobile', true);" +
                        "         })();";
        Log.d("WEB-VIEW","js: " + js);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
          webView.evaluateJavascript(js, new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String value) {
              Log.d("WEB-VIEW","" + value);
            }
          });
        } else {
          webView.loadUrl("javascript:" + js);
        }
      }

      @Override
      public void onPageFinished(WebView view, String url) {
        String cookies = CookieManager.getInstance().getCookie(view.getUrl());
        Log.d("cookie",cookies);
        super.onPageFinished(view, url);
      }


      @Nullable
      @Override
      public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
        String url = request.getUrl().toString();
        if (url.contains(BuildConfig.GRAPHQL_URL+ "/member/graphql")){
          presenter.getCartCount();
        }
        return super.shouldInterceptRequest(view, request);
      }

      @Override
      public void onLoadResource(WebView view, String url) {
        tvTitle_toolbar.setText(view.getTitle());
        super.onLoadResource(view, url);

      }


      @Override
      public void onPageCommitVisible(WebView view, String url) {
        super.onPageCommitVisible(view, url);
        if(swiperefresh.isRefreshing()){
          swiperefresh.setRefreshing(false);
        }
      }
    });
    webView.setWebChromeClient(webChromeClient);
    webView.getSettings().setJavaScriptEnabled(true);
    webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
    WebSettings settings = webView.getSettings();
    settings.setDomStorageEnabled(true);
    settings.setUseWideViewPort(true);
    webView.getSettings().setJavaScriptEnabled(true);
    webView.getSettings().setLoadWithOverviewMode(true);
    webView.getSettings().setUseWideViewPort(true);

    webView.getSettings().setSupportZoom(true);
    webView.getSettings().setBuiltInZoomControls(true);
    webView.getSettings().setDisplayZoomControls(false);

    webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
    webView.setScrollbarFadingEnabled(false);
    enableHTML5AppCache();
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        String cookieString = "is_mobile=true";

        cookieManager.removeAllCookies(new ValueCallback<Boolean>() {
          @Override
          public void onReceiveValue(Boolean value) {

          }
        });
     cookieManager.setCookie(BuildConfig.BASE_URL, cookieString);
     cookieManager.setAcceptThirdPartyCookies(webView,true);




    webView.loadUrl(urlEcm);

  }

  private void enableHTML5AppCache() {
    webView.getSettings().setDomStorageEnabled(true);
    webView.getSettings().setAppCachePath("/data/data/" + getPackageName() + "/cache");
    webView.getSettings().setAppCacheEnabled(true);
    webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
  }


//  @OnClick(R.id.ivBack)
//  void goBack(){
//    webView.goBack();
//  }
//
//  @OnClick(R.id.ivForward)
//  void goForward(){
//    webView.goForward();
//  }
//
//  @OnClick(R.id.ivReload)
//  void goReload() {
//    webView.reload();
//  }




  @OnClick(R.id.ivNavigation_toolbar)
  void openMenu() {
    NavigationDialogFragment.newInstance().show(getSupportFragmentManager(), "Navigation");

  }
  @OnClick(R.id.ivCart_toolbar)
  void openCart() {
    URL_ECM = WebViewURL.SHOPPING_CART;
    ahbn.setCurrentItem(-1,false);
    webView.loadUrl(URL_ECM);
  }

  @OnClick(R.id.btn_estore)
  void onClickEstore(){
    URL_ECM = WebViewURL.ESTORE +"estore/listing";
    ahbn.setCurrentItem(-1,false);
    webView.loadUrl(URL_ECM);
  }


  @OnClick(R.id.clMarketingBtn)
  void onClickMarketing(){
    if (!TextUtils.isEmpty(urlMarketing) && urlMarketing != null){
      ahbn.setCurrentItem(-1,false);
      webView.loadUrl(urlMarketing);
    }
  }

  @OnClick(R.id.clOnboardingBtn)
  void onClickOnboardingOrRenewal(){
    if (!TextUtils.isEmpty(urlOnboardingOrRenewal) && urlOnboardingOrRenewal != null){
      ahbn.setCurrentItem(-1,false);
      webView.loadUrl(urlOnboardingOrRenewal);
    }
  }



  @OnClick(R.id.ivSearch_toolbar)
  void openSearch() {
    SearchActivity.startMe(this);
  }

  long backPressed = 0;
  @Override
  public void onBackPressed() {
    if (webView.canGoBack()) {
      webView.goBack();
    }else{
      //webView.goBack();
//    super.onBackPressed();
      if(isTaskRoot() && backPressed + 2000 > System.currentTimeMillis()){
        finish();
      } else {
        backPressed = System.currentTimeMillis();
        Toast.makeText(getBaseContext(), "Press once again to exit!", Toast.LENGTH_SHORT).show();
      }
    }



  }

  @Override
  public void updateCartCount(String count) {
    if (count == null && TextUtils.isEmpty(count)) {
      lyCartCount.setVisibility(View.INVISIBLE);
      tvCartCount.setVisibility(View.INVISIBLE);
    } else {
      if (!count.equalsIgnoreCase("0")) {
        lyCartCount.setVisibility(View.VISIBLE);
        tvCartCount.setVisibility(View.VISIBLE);
        tvCartCount.setText(count);
      } else {
        lyCartCount.setVisibility(View.INVISIBLE);
        tvCartCount.setVisibility(View.INVISIBLE);
      }
    }

  }

  @Override
  public void renderGiftPopupResponse(List<GiftPopUpResponse> giftPopUpResponse) {
    if (giftPopUpResponse != null) {
      giftPopUp(giftPopUpResponse);
    }
  }

  private void giftPopUp(List<GiftPopUpResponse> giftPopUpResponse) {
    if(!giftPopUpResponse.get(0).getResult().isEmpty()) {
      for (Result i : giftPopUpResponse.get(0).getResult()) {
        if (Integer.parseInt(i.getCampaignId()) == 3) {
          urlMarketing = i.getLink();
          clMarketing.setVisibility(Integer.parseInt(i.getIsRedeemed()) == 0 ? View.VISIBLE : View.GONE);
        } else{
          urlOnboardingOrRenewal = i.getLink();
          clOnBoarding.setVisibility(Integer.parseInt(i.getIsRedeemed()) == 0 ? View.VISIBLE : View.GONE);
        }
      }
    } else {
      clMarketing.setVisibility(View.GONE);
      clOnBoarding.setVisibility(View.GONE);
    }

  }




}
