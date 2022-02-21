package com.example.wikidesign.filter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wikidesign.R;

import java.util.List;

public class FilterbyTransactionAdapter extends RecyclerView.Adapter<FilterbyTransactionAdapter.TransactionViewHolder> {

    List<FilterModel> data;


    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.item_filter_transaction, parent, false);
        TransactionViewHolder holder = new TransactionViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
        holder.bind(data.get(holder.getAdapterPosition()));
    }


    public void setData(List<FilterModel> data){
        this.data = data;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class TransactionViewHolder extends RecyclerView.ViewHolder{
        TextView tvType;
        RecyclerView recyclerCheckBox;
        FilterbyTypeAdapter adapter;

        public TransactionViewHolder(@NonNull View itemView) {
            super(itemView);
            tvType = itemView.findViewById(R.id.tvTyple);
            recyclerCheckBox = itemView.findViewById(R.id.recyclerCheckBox);
        }

        public void bind(FilterModel filterModel) {
            tvType.setText(filterModel.getGroup());

            recyclerCheckBox.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
            recyclerCheckBox.setHasFixedSize(true);
            adapter = new FilterbyTypeAdapter();
            adapter.setData(filterModel.getLstGroup());
            recyclerCheckBox.setAdapter(adapter);
        }
    }


}
