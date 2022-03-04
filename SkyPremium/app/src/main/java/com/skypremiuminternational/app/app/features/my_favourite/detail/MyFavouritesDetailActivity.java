package com.skypremiuminternational.app.app.features.my_favourite.detail;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.Gson;
import com.skypremiuminternational.app.R;
import com.skypremiuminternational.app.app.features.my_favourite.MyFavouriteActivity;
import com.skypremiuminternational.app.app.internal.mvp.BaseActivity;
import com.skypremiuminternational.app.app.utils.WebViewURL;
import com.skypremiuminternational.app.domain.models.favourite.FavouriteItem;

import java.util.Calendar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;

public class MyFavouritesDetailActivity extends BaseActivity<MyFavouritesPresenter> implements MyFavouritesDetailView<MyFavouritesPresenter> {

  @BindView(R.id.webView)
  WebView webView;
  @BindView(R.id.swiperefresh)
  SwipeRefreshLayout swiperefresh;
  @BindView(R.id.webProgressBar)
  ProgressBar webProgressBar;
  @BindView(R.id.tvTitle_toolbar)
  TextView tvTitleToolbar;


  FavouriteItem item;

  public static void startMe(Context context, FavouriteItem item){
    Intent intent = new Intent(context, MyFavouritesDetailActivity.class);
    intent.putExtra("item", new Gson().toJson(item));
    context.startActivity(intent);
  }

  @Inject
  @Override
  public void injectPresenter(MyFavouritesPresenter presenter) {
    super.injectPresenter(presenter);
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    AndroidInjection.inject(this);
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail_favourites);
    ButterKnife.bind(this);


    item = new Gson().fromJson(getIntent().getStringExtra("item"), FavouriteItem.class);


    if (item != null && item.getProductId() !=null){
      // init webView
      setupWebView(item);
    }
    else {
      finish();
    }




    // init swipe down
    swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override
      public void onRefresh() {
        webView.reload();
      }
    });


  }

  @OnClick(R.id.iv_toolbar_back)
  void onClickBack(){
    if (webView.canGoBack()) {
      webView.goBack();
    }else{
      finish();
    }
  }

  @Override
  public void onBackPressed() {
    if (webView.canGoBack()) {
      webView.goBack();
    }else{
      finish();
    }

  }

  private void setupWebView(FavouriteItem item){
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
        super.onPageFinished(view, url);


      }

      @Override
      public void onLoadResource(WebView view, String url) {
        tvTitleToolbar.setText(view.getTitle());
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
    //    CookieManager cookieManager = CookieManager.getInstance();
    //    cookieManager.setAcceptCookie(true);
    //    String cookieString = TOKEN_KEY+"="+presenter.getCustomerToken();
    //    cookieManager.removeAllCookies(new ValueCallback<Boolean>() {
    //      @Override
    //      public void onReceiveValue(Boolean value) {
    //
    //      }
    //    });
    // cookieManager.setCookie(URL_ECM_BASE, cookieString);

    Log.d("URL",WebViewURL.PRODUCT_DETAIL+item.getProductId());

    webView.loadUrl(WebViewURL.PRODUCT_DETAIL+item.getProductId());

  }

  private void enableHTML5AppCache() {
    webView.getSettings().setDomStorageEnabled(true);
    webView.getSettings().setAppCachePath("/data/data/" + getPackageName() + "/cache");
    webView.getSettings().setAppCacheEnabled(true);
    webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
  }


}
