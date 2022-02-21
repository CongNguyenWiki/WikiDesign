package com.example.wikidesign.categorySelected;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wikidesign.MainActivity;
import com.example.wikidesign.R;
import com.example.wikidesign.interestingSelected.InterestingMultipleListener;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewMultipleSelectedActivity extends AppCompatActivity implements CategoryMultipleListener {
    RecyclerView recyclerView;
    RelativeLayout btnApply;
    List<Category> categoryList;
    TextView tvSelectedNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_multiple_selected);
        recyclerView = findViewById(R.id.recyclerSelectCategory);
        btnApply = findViewById(R.id.btnApply);
        tvSelectedNumber = findViewById(R.id.tvSelectedNumber);
        setUpRecyclerView();

    }

    public static void startMe(Context context){
        context.startActivity(new Intent(context, RecyclerViewMultipleSelectedActivity.class));
    }

    public void setUpRecyclerView(){

        categoryList  = new ArrayList<>();
        Category category = new Category();
        category.image = R.drawable.ic_baseline_card_travel_24;
        category.name = "Travel";
        categoryList.add(category);

        Category category2 = new Category();
        category2.image = R.drawable.ic_baseline_liquor;
        category2.name = "Luxury goods";
        categoryList.add(category2);

        Category category3 = new Category();
        category3.image = R.drawable.ic_baseline_dining;
        category3.name = "Dining";
        categoryList.add(category3);

        Category category4 = new Category();
        category4.image = R.drawable.ic_baseline_relaxation;
        category4.name = "Relaxation";
        categoryList.add(category4);

        Category category5 = new Category();
        category5.image = R.drawable.ic_baseline_shopping;
        category5.name = "Shopping";
        categoryList.add(category5);

        Category category6 = new Category();
        category6.image = R.drawable.ic_baseline_room_service;
        category6.name = "Room Service";
        categoryList.add(category6);

        Category category7 = new Category();
        category7.image = R.drawable.ic_baseline_events;
        category7.name = "Events";
        categoryList.add(category7);

        Category category8 = new Category();
        category8.image = R.drawable.ic_baseline_recipes;
        category8.name = "Recipes";
        categoryList.add(category8);

        Category category9 = new Category();
        category9.image = R.drawable.ic_baseline_restaurant;
        category9.name = "Hottest Restaurants";
        categoryList.add(category9);

        CategorySelectedAdapter categorySelectedAdapter = new CategorySelectedAdapter(categoryList,  this);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setHasFixedSize(false);
        categorySelectedAdapter.setData(categoryList);
        recyclerView.setAdapter(categorySelectedAdapter);



        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Category> list = categorySelectedAdapter.getSelectedCategory();
                StringBuilder name = new StringBuilder();
                for (int i = 0; i < list.size(); i++){
                    if (i == 0){
                        name.append(list.get(i).name);

                    }else {
                        name.append("\n").append(list.get(i).name);
                    }
                }
                Toast.makeText(RecyclerViewMultipleSelectedActivity.this, "" + name, Toast.LENGTH_SHORT).show();

            }
        });


    }


    @Override
    public void onMultipleSelected(boolean isSelected) {
        CategorySelectedAdapter categorySelectedAdapter = new CategorySelectedAdapter(categoryList, this);

        if (isSelected){
            btnApply.setVisibility(View.VISIBLE);
            tvSelectedNumber.setText(categorySelectedAdapter.getSelectedCategory().size()+" selected");
        }else {
            btnApply.setVisibility(View.GONE);
            tvSelectedNumber.setText(categorySelectedAdapter.getSelectedCategory().size()+" selected");
        }
    }
}