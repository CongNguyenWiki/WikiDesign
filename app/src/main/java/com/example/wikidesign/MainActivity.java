package com.example.wikidesign;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.wikidesign.categoryPlaceholder.CategoryPlaceHolderActivity;
import com.example.wikidesign.filter.AllFilterActivity;
import com.example.wikidesign.interestingSelected.SelectedCategoryActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.tvLanding)
    void OnClickLanding(){
        LandingActivity.starMe(this);
    }

    @OnClick(R.id.tvAllFilter)
    void OnClickAllFilter(){
        AllFilterActivity.startMe(this);
    }

    @OnClick(R.id.tvLogin)
    void OnClickLogin(){
        LoginActivity.startMe(this);
    }

    @OnClick(R.id.tvProfile)
    void OnClickProfile(){
        ProfileActivity.startMe(this);
    }

    @OnClick(R.id.tvRegistration)
    void OnClickRegistrationCompleted(){
        RegistrationCompletedActivity.startMe(this);
    }

    @OnClick(R.id.tvSignIn)
    void OnClickSignIn(){
        SignInAndRegisterActivity.startMe(this);
    }

    @OnClick(R.id.tvSplashScreen)
    void OnClickSplash(){
        SplashScreenActivity.startMe(this);
    }

    @OnClick(R.id.tvWelcome)
    void OnClickWelcome(){
        WelcomeActivity.startMe(this);
    }

    @OnClick(R.id.tvCategoryActivity)
    void OnClickCategory(){
        SelectedCategoryActivity.startMe(this);
    }

    @OnClick(R.id.tvPlaceholderActivity)
    void OnClickPlaceholder(){
        CategoryPlaceHolderActivity.startMe(this);
    }
}