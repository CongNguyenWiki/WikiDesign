package com.example.wikidesign.interestingSelected;


import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wikidesign.R;

import java.util.ArrayList;
import java.util.List;

public class InterestingSelectedAdapter extends RecyclerView.Adapter<InterestingSelectedAdapter.ViewHolder> {

    private List<Interesting> categoryList;
    private InterestingMultipleListener listener;

    public InterestingSelectedAdapter(List<Interesting> categoryList, InterestingMultipleListener listener) {
        this.categoryList = categoryList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public InterestingSelectedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_selected_category, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    public List<Interesting> getSelectedCategory(){
        List<Interesting> selectedList = new ArrayList<>();
        for (Interesting category : categoryList){
            if (category.isSelected == true){
                selectedList.add(category);

            }
        }
        return selectedList;
    }

    public void setData(List<Interesting> categoryList){
        this.categoryList = categoryList;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull InterestingSelectedAdapter.ViewHolder holder, int position) {
        holder.tvCategory.setText(categoryList.get(position).getName());
        holder.imgCategory.setImageResource(categoryList.get(position).getImage());
        holder.bind(categoryList.get(position));
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ConstraintLayout layoutCategory;
        ImageView imgCategory;
        TextView tvCategory;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutCategory = itemView.findViewById(R.id.layoutConstrain);
            imgCategory = itemView.findViewById(R.id.imgCategory);
            tvCategory = itemView.findViewById(R.id.tvCategory);

        }

        void bind( Interesting category) {

            if (category.isSelected) {
                imgCategory.setColorFilter(Color.parseColor("#9F703A"));
                tvCategory.setTextColor(itemView.getResources().getColor(R.color.goldSelected));
            } else {
                imgCategory.setColorFilter(Color.parseColor("#C3C3C3"));
                tvCategory.setTextColor(itemView.getResources().getColor(R.color.greyC3));

            }

            layoutCategory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (category.isSelected) {
                        imgCategory.setColorFilter(Color.parseColor("#C3C3C3"));
                        tvCategory.setTextColor(itemView.getResources().getColor(R.color.greyC3));
                        category.isSelected = false;
                        if (getSelectedCategory().size() < 5) {
                            listener.onInterestingMultipleSelected(false);

                        }else {
                            listener.onInterestingMultipleSelected(true);
                        }
                    } else {
                        imgCategory.setColorFilter(Color.parseColor("#9F703A"));
                        tvCategory.setTextColor(itemView.getResources().getColor(R.color.goldSelected));
                        category.isSelected = true;
                        if (getSelectedCategory().size() >= 5){
                            listener.onInterestingMultipleSelected(true);
                        }else {
                            listener.onInterestingMultipleSelected(false);
                        }


                    }
                }
            });
        }
    }
}
