package com.example.wikidesign.categoryPlaceholder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wikidesign.R;

import org.w3c.dom.Text;

import java.util.List;

public class CategoryPlaceholderAdapter extends RecyclerView.Adapter<CategoryPlaceholderAdapter.ViewHolder> {
    private Context context;
    private List<Placeholder> data;

    public CategoryPlaceholderAdapter(Context context, List<Placeholder> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_checkbox, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvRecommended.setText(data.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvRecommended;
        CheckBox cbRecommended;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRecommended = itemView.findViewById(R.id.tvRecommended);
            cbRecommended = itemView.findViewById(R.id.cbRecommended);
        }
    }
}
