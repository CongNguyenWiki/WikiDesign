package com.example.wikidesign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.wikidesign.interestingSelected.SelectedCategoryActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
    }

    public static void startMe(Context context){
        context.startActivity(new Intent(context, WelcomeActivity.class));
    }

    @OnClick(R.id.btnNextInteresting)
    void OnClickIntentInteresting(){
        SelectedCategoryActivity.startMe(this);
    }
}