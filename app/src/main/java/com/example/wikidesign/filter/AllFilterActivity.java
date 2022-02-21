package com.example.wikidesign.filter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.wikidesign.R;

import java.util.ArrayList;
import java.util.List;

public class AllFilterActivity extends AppCompatActivity {

    RecyclerView recyclerFilterBy;
    FilterbyTransactionAdapter adapter;
    List<FilterModel> filterModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_filter);
        recyclerFilterBy = findViewById(R.id.recyclerFilterBy);
        setUpRecyclerView();
    }

    public static void startMe(Context context){
        context.startActivity(new Intent(context, AllFilterActivity.class));
    }

    private void setUpRecyclerView() {

        List<String> lstSortBy = new ArrayList<>();
        lstSortBy.add("Recommended");
        lstSortBy.add("Popularity");
        lstSortBy.add("Rating");
        lstSortBy.add("Saving");
        lstSortBy.add("Distance");

        List<String> lstCategory = new ArrayList<>();
        lstCategory.add("Category Placeholder");
        lstCategory.add("Category Placeholder");
        lstCategory.add("Category Placeholder");

        List<String> lstBrand = new ArrayList<>();
        lstBrand.add("Brand Placeholder");
        lstBrand.add("Brand Placeholder");
        lstBrand.add("Brand Placeholder");


        filterModelList = new ArrayList<>();
        filterModelList.add(new FilterModel("Sort By",lstSortBy));
        filterModelList.add(new FilterModel("Category",lstCategory));
        filterModelList.add(new FilterModel("Brand",lstBrand));

        recyclerFilterBy.setNestedScrollingEnabled(false);
        recyclerFilterBy.setHasFixedSize(false);
        recyclerFilterBy.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FilterbyTransactionAdapter();
        adapter.setData(filterModelList);
        recyclerFilterBy.setAdapter(adapter);


    }
}