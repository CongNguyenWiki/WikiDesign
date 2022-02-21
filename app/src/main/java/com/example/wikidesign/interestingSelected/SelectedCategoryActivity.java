package com.example.wikidesign.interestingSelected;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wikidesign.R;
import com.example.wikidesign.RegistrationCompletedActivity;
import com.example.wikidesign.categorySelected.Category;
import com.example.wikidesign.categorySelected.CategorySelectedAdapter;
import com.example.wikidesign.categorySelected.RecyclerViewMultipleSelectedActivity;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.j256.ormlite.stmt.query.In;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectedCategoryActivity extends AppCompatActivity implements InterestingMultipleListener {


    @BindView(R.id.recyclerSelectCategory)
    RecyclerView recyclerView;
    @BindView(R.id.btnNext)
    RelativeLayout btnNext;
    @BindView(R.id.tvSelectedNumber)
    TextView tvSelectedNumber;

     List<Interesting> interestingList;

//    @BindView(R.id.tabLayout)
//    TabLayout tabLayout;
//    FragmentAdapter adapter;
//    @BindView(R.id.viewPage2)
//     ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_category);
        ButterKnife.bind(this);
        setUpRecyclerView();

//        adapter = new FragmentAdapter(getSupportFragmentManager(), getLifecycle());
//        adapter = new FragmentAdapter(this);
//        viewPager.setAdapter(adapter);

//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                viewPager.setCurrentItem(tab.getPosition());
//
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//            }
//        });
//
//        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
//            @Override
//            public void onPageSelected(int position) {
//                tabLayout.selectTab(tabLayout.getTabAt(position));
//            }
//        });



    }

    public void setUpRecyclerView() {
        interestingList  = new ArrayList<>();
        Interesting interesting = new Interesting();
        interesting.image = R.drawable.ic_baseline_card_travel_24;
        interesting.name = "Travel";
        interestingList.add(interesting);

        Interesting interesting2 = new Interesting();
        interesting2.image = R.drawable.ic_baseline_liquor;
        interesting2.name = "Luxury goods";
        interestingList.add(interesting2);

        Interesting interesting3 = new Interesting();
        interesting3.image = R.drawable.ic_baseline_dining;
        interesting3.name = "Dining";
        interestingList.add(interesting3);

        Interesting interesting4 = new Interesting();
        interesting4.image = R.drawable.ic_baseline_relaxation;
        interesting4.name = "Relaxation";
        interestingList.add(interesting4);

        Interesting interesting5 = new Interesting();
        interesting5.image = R.drawable.ic_baseline_shopping;
        interesting5.name = "Shopping";
        interestingList.add(interesting5);

        Interesting interesting6 = new Interesting();
        interesting6.image = R.drawable.ic_baseline_room_service;
        interesting6.name = "Room Service";
        interestingList.add(interesting6);

        Interesting interesting7 = new Interesting();
        interesting7.image = R.drawable.ic_baseline_events;
        interesting7.name = "Events";
        interestingList.add(interesting7);

        Interesting interesting8 = new Interesting();
        interesting8.image = R.drawable.ic_baseline_recipes;
        interesting8.name = "Recipes";
        interestingList.add(interesting8);

        Interesting interesting9 = new Interesting();
        interesting9.image = R.drawable.ic_baseline_restaurant;
        interesting9.name = "Hottest Restaurants";
        interestingList.add(interesting9);

        InterestingSelectedAdapter interestingSelectedAdapter = new InterestingSelectedAdapter(interestingList,  this);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
        recyclerView.setHasFixedSize(false);
        interestingSelectedAdapter.setData(interestingList);
        recyclerView.setAdapter(interestingSelectedAdapter);

        Log.d("List Size:", interestingList.size()+ "");


    }

    @Override
    public void onInterestingMultipleSelected(boolean isSelected) {
        InterestingSelectedAdapter interestingSelectedAdapter = new InterestingSelectedAdapter(interestingList, this);

        if (isSelected){
            btnNext.setVisibility(View.VISIBLE);
            tvSelectedNumber.setText(interestingSelectedAdapter.getSelectedCategory().size()+" selected");
        }else {
            btnNext.setVisibility(View.GONE);
            tvSelectedNumber.setText(interestingSelectedAdapter.getSelectedCategory().size()+" selected");
        }
    }


    public static void startMe(Context context){
        context.startActivity(new Intent(context, SelectedCategoryActivity.class));
    }

    @OnClick(R.id.btnNext)
    void OnClickIntentCompleted(){
        RegistrationCompletedActivity.startMe(this);
        InterestingSelectedAdapter interestingSelectedAdapter = new InterestingSelectedAdapter(interestingList, this);

        List<Interesting> list = interestingSelectedAdapter.getSelectedCategory();
        StringBuilder name = new StringBuilder();
        for (int i = 0; i < list.size(); i++){
            if (i == 0){
                name.append(list.get(i).name);

            }else {
                name.append("\n").append(list.get(i).name);
            }
        }
        Toast.makeText(SelectedCategoryActivity.this, "" + name, Toast.LENGTH_SHORT).show();
    }

}