package com.example.wikidesign.categorySelected;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wikidesign.R;
import com.example.wikidesign.filter.FilterModel;
import com.example.wikidesign.filter.FilterbyTransactionAdapter;
import com.example.wikidesign.interestingSelected.InterestingMultipleListener;
import com.google.common.io.Resources;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

public class CategorySelectedAdapter extends RecyclerView.Adapter<CategorySelectedAdapter.ViewHolder> {

    private List<Category> categoryList;
    private CategoryMultipleListener listener;

    public CategorySelectedAdapter(List<Category> categoryList, CategoryMultipleListener listener) {
        this.categoryList = categoryList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.item_selected_category, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvCategory.setText(categoryList.get(position).getName());
        holder.imgCategory.setImageResource(categoryList.get(position).getImage());
        holder.bind(categoryList.get(position));

    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public List<Category> getSelectedCategory(){
        List<Category> selectedList = new ArrayList<>();
        for (Category category : categoryList){
            if (category.isSelected == true){
                selectedList.add(category);

            }
        }
        return selectedList;
    }

    public void setData(List<Category> categoryList){
        this.categoryList = categoryList;
        notifyDataSetChanged();
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

        void bind( Category category) {

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
                            listener.onMultipleSelected(false);

                        }else {
                            listener.onMultipleSelected(true);
                        }
                    } else {
                        imgCategory.setColorFilter(Color.parseColor("#9F703A"));
                        tvCategory.setTextColor(itemView.getResources().getColor(R.color.goldSelected));
                        category.isSelected = true;
                        if (getSelectedCategory().size() >= 5){
                            listener.onMultipleSelected(true);
                        }else {
                            listener.onMultipleSelected(false);
                        }


                    }
                }
            });
        }
        }

}
