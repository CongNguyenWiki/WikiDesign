package com.example.wikidesign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignInAndRegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);

    }

    public static void startMe(Context context){
        context.startActivity(new Intent(context, SignInAndRegisterActivity.class));
    }



    @OnClick(R.id.btnSignIn)
    void OnClick(){
        LoginActivity.startMe(this);
    }



}