package com.example.wikidesign;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.example.wikidesign.drawerNavigation.DrawerNavigationDialogFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.j256.ormlite.stmt.query.In;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LandingActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.webProgressBar)
    ProgressBar webProgressBar;
    @BindView(R.id.tvTitle_toolbar)
    TextView tvTitle_toolbar;
    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout swiperefresh;
    @BindView(R.id.ivSearch_toolbar)
    ImageView ivSearchToolbar;
    @BindView(R.id.bottomNavigationMenu)
    BottomNavigationView bottomNavigationView;


    boolean isBack = false;

    public static void starMe(Context context) {
        context.startActivity(new Intent(context, LandingActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        ButterKnife.bind(this);

        setupBottomMenu();

    }



    private void setupBottomMenu() {


    }




    @OnClick(R.id.ivNavigation_toolbar)
    void openDrawerNavigationMenu(){
        DrawerNavigationDialogFragment.newInstance().show(getSupportFragmentManager(), "Navigation");
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.itemDiscover:

        }
        return false;
    }
}