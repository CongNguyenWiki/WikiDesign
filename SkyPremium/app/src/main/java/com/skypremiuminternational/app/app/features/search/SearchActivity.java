package com.skypremiuminternational.app.app.features.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.skypremiuminternational.app.R;
import com.skypremiuminternational.app.app.features.landing.LandingActivity;
import com.skypremiuminternational.app.app.utils.WebViewURL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.edtSearch)
    EditText edtSearch;
    @BindView(R.id.llLast)
    LinearLayout llLast;

    public static void startMe(Context context){
        Intent intent = new Intent(context,SearchActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        edtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    LandingActivity.startMe(SearchActivity.this, WebViewURL.SEARCH,edtSearch.getText().toString().trim(),-1,true);
                    return true;
                }
                return false;
            }
        });


    }

    @OnClick(R.id.ivClear)
    void onClickClear() {
        edtSearch.setText("");
    }

    @OnClick(R.id.tvCancel)
    void onClickCancel() {
        finish();
    }

    @OnClick(R.id.tvLastClear)
    void onClickLastClear() {
        llLast.setVisibility(View.GONE);
    }
}
