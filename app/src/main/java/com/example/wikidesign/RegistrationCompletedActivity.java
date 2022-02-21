package com.example.wikidesign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.j256.ormlite.stmt.query.In;

import butterknife.OnClick;

public class RegistrationCompletedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_completed);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(RegistrationCompletedActivity.this, LandingActivity.class));
            }
        }, 3000);
    }

    public static void startMe(Context context){
        context.startActivity(new Intent(context, RegistrationCompletedActivity.class));
    }


}