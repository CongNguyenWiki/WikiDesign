package com.example.wikidesign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.wikidesign.drawerNavigation.DrawerNavigationDialogFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
    }

    public static void startMe(Context context){
        context.startActivity(new Intent(context, ProfileActivity.class));
    }

    @OnClick(R.id.ivNavigation_toolbar)
    void openDrawerNavigationMenu(){
        DrawerNavigationDialogFragment.newInstance().show(getSupportFragmentManager(), "Navigation");
    }
}