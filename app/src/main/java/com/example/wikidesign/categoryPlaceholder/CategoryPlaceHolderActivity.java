package com.example.wikidesign.categoryPlaceholder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.wikidesign.R;

import java.util.ArrayList;
import java.util.List;

public class CategoryPlaceHolderActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    CategoryPlaceholderAdapter adapter;
    List<Placeholder> placeholderList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_place_holder);
        recyclerView = findViewById(R.id.recyclerPlaceholderCategory);
        setupRecyclerView();
    }

    public static void startMe(Context context){
        context.startActivity(new Intent(context, CategoryPlaceHolderActivity.class));
    }

    private void setupRecyclerView(){
        placeholderList = new ArrayList<>();
        placeholderList.add(new Placeholder("Category Placeholder 1"));
        placeholderList.add(new Placeholder("Category Placeholder 2"));
        placeholderList.add(new Placeholder("Category Placeholder 3"));
        placeholderList.add(new Placeholder("Category Placeholder 4"));
        placeholderList.add(new Placeholder("Category Placeholder 5"));
        placeholderList.add(new Placeholder("Category Placeholder 6"));

        adapter = new CategoryPlaceholderAdapter(this, placeholderList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }
}