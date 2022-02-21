package com.example.wikidesign.interestingSelected;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wikidesign.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentCategoryParent#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentCategoryParent extends Fragment implements InterestingMultipleListener {



    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    RecyclerView recyclerView;
    RelativeLayout btnApply;
    List<Interesting> categoryList;
    TextView tvSelectedNumber;

    public FragmentCategoryParent() {

    }

    public static FragmentCategoryParent newInstance(String param1, String param2) {
        FragmentCategoryParent fragment = new FragmentCategoryParent();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category_parent, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerSelectCategory);
        btnApply = (RelativeLayout) view.findViewById(R.id.btnApply);
        tvSelectedNumber = (TextView) view.findViewById(R.id.tvSelectedNumber);
        setUpRecyclerView();

        return view;
    }

    public void setUpRecyclerView() {
        categoryList  = new ArrayList<>();
        Interesting category = new Interesting();
        category.image = R.drawable.ic_baseline_card_travel_24;
        category.name = "Travel";
        categoryList.add(category);

        Interesting category2 = new Interesting();
        category2.image = R.drawable.ic_baseline_liquor;
        category2.name = "Luxury goods";
        categoryList.add(category2);

        Interesting category3 = new Interesting();
        category3.image = R.drawable.ic_baseline_dining;
        category3.name = "Dining";
        categoryList.add(category3);

        Interesting category4 = new Interesting();
        category4.image = R.drawable.ic_baseline_relaxation;
        category4.name = "Relaxation";
        categoryList.add(category4);

        Interesting category5 = new Interesting();
        category5.image = R.drawable.ic_baseline_shopping;
        category5.name = "Shopping";
        categoryList.add(category5);

        Interesting category6 = new Interesting();
        category6.image = R.drawable.ic_baseline_room_service;
        category6.name = "Room Service";
        categoryList.add(category6);

        Interesting category7 = new Interesting();
        category7.image = R.drawable.ic_baseline_events;
        category7.name = "Events";
        categoryList.add(category7);

        Interesting category8 = new Interesting();
        category8.image = R.drawable.ic_baseline_recipes;
        category8.name = "Recipes";
        categoryList.add(category8);

        Interesting category9 = new Interesting();
        category9.image = R.drawable.ic_baseline_restaurant;
        category9.name = "Hottest Restaurants";
        categoryList.add(category9);

        InterestingSelectedAdapter categorySelectedAdapter = new InterestingSelectedAdapter(categoryList,  this);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        recyclerView.setHasFixedSize(false);
        categorySelectedAdapter.setData(categoryList);
        recyclerView.setAdapter(categorySelectedAdapter);

        Log.d("List Size:", categoryList.size()+ "");



        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Interesting> list = categorySelectedAdapter.getSelectedCategory();
                StringBuilder name = new StringBuilder();
                for (int i = 0; i < list.size(); i++){
                    if (i == 0){
                        name.append(list.get(i).name);

                    }else {
                        name.append("\n").append(list.get(i).name);
                    }
                }
                Toast.makeText(getContext(), "" + name, Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public void onInterestingMultipleSelected(boolean isSelected) {
        InterestingSelectedAdapter categorySelectedAdapter = new InterestingSelectedAdapter(categoryList, this);

        if (isSelected){
            btnApply.setVisibility(View.VISIBLE);
            tvSelectedNumber.setText(categorySelectedAdapter.getSelectedCategory().size()+" selected");
        }else {
            btnApply.setVisibility(View.GONE);
            tvSelectedNumber.setText(categorySelectedAdapter.getSelectedCategory().size()+" selected");
        }
    }
}