package com.example.wikidesign.filter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wikidesign.R;

import java.util.ArrayList;
import java.util.List;

public class FilterbyTypeAdapter extends RecyclerView.Adapter<FilterbyTypeAdapter.ViewHolder> {


    private List<String> data;




    @NonNull
    @Override
    public FilterbyTypeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.item_checkbox, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FilterbyTypeAdapter.ViewHolder holder, int position) {
        holder.tvRecommended.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<String> data){
        this.data = data;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout contrainItemCheckbox;
        TextView tvRecommended;
        CheckBox cbRecommended;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            contrainItemCheckbox = itemView.findViewById(R.id.contrainItemCheckbox);
            tvRecommended = itemView.findViewById(R.id.tvRecommended);
            cbRecommended = itemView.findViewById(R.id.cbRecommended);

        }

//    public void bind(String name, boolean isMargin){
//            if (isMargin == true){
//                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//
//
//            }
//
//        }
    }




}
