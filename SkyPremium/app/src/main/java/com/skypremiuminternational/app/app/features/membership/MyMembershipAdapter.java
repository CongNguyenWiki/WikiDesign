package com.skypremiuminternational.app.app.features.membership;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.skypremiuminternational.app.R;
import com.skypremiuminternational.app.domain.models.mymembership_statement.MyMembershipStatementItem;

import java.util.ArrayList;
import java.util.List;


public class MyMembershipAdapter extends RecyclerView.Adapter<MyMembershipViewHolder> {

    private List<MyMembershipStatementItem> dataList = new ArrayList<>();
    private boolean isLatest,isRenew,isUpGrade,isDownGrade,isCancel;



    public MyMembershipAdapter(boolean isLatest, boolean isRenew, boolean isUpGrade,boolean isDownGrade, boolean isCancel) {
        this.isLatest = isLatest;
        this.isRenew = isRenew;
        this.isUpGrade = isUpGrade;
        this.isDownGrade = isDownGrade;
        this.isCancel = isCancel;

    }



    public void setDataList(List<MyMembershipStatementItem> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public MyMembershipViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_membership_statement, parent, false);
        final MyMembershipViewHolder viewHolder = new MyMembershipViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyMembershipViewHolder holder, int position) {
        holder.bind(dataList.get(position),"1",isCancel,isUpGrade,isDownGrade,isRenew);
    }

    @Override
    public int getItemCount() {
        return dataList != null ? dataList.size() : 0;
    }
}
