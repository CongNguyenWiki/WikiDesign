package com.skypremiuminternational.app.app.features.navigation.expandable;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.skypremiuminternational.app.R;
import com.skypremiuminternational.app.app.features.navigation.expandable.listener.IActionCLickListener;
import com.skypremiuminternational.app.domain.models.category.ChildData_;

import java.util.List;


public class SubCategoryAdapter extends BaseExpandableListAdapter {

    private Context context;
    List<ChildData_> childrenData;
    IActionCLickListener iActionCLickListener;
    String name;

    public SubCategoryAdapter(Context context, List<ChildData_> childrenData, IActionCLickListener iActionCLickListener, String name) {
        this.context = context;
        this.childrenData = childrenData;
        this.iActionCLickListener = iActionCLickListener;
        this.name = name;

    }

    public void setiActionCLickListener(IActionCLickListener iActionCLickListener) {
        this.iActionCLickListener = iActionCLickListener;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupPosition;
    }

    @Override
    public int getGroupCount() {
        return childrenData.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.item_sub_category_expanble_list, null);

        LinearLayout llSubCategory = (LinearLayout) convertView.findViewById(R.id.llSubCategory);
        TextView text = (TextView) convertView.findViewById(R.id.tvSubCategory);
        ImageView img = (ImageView) convertView.findViewById(R.id.img);

        text.setText(childrenData.get(groupPosition).getName());

        if (childrenData.get(groupPosition).getChildrenData().isEmpty()) {
            img.setVisibility(View.INVISIBLE);
        }else if (childrenData.get(groupPosition).getName().equalsIgnoreCase("Search by featured")){
            img.setVisibility(View.VISIBLE);
            if (isExpanded) {
                img.setImageResource(R.drawable.expandable_group_up);
            } else {
                img.setImageResource(R.drawable.expandable_group_down);
            }

        }else {
            img.setVisibility(View.INVISIBLE);
        }

        if (childrenData.get(groupPosition).getChildrenData().isEmpty()){
            llSubCategory.setOnClickListener(view -> iActionCLickListener.onClickedSubItem(name,childrenData.get(groupPosition)));
        }

        return convertView;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.item_sub_category_expandable_list1, null);

        LinearLayout llSubCategory = (LinearLayout) convertView.findViewById(R.id.llSubCategory);
        TextView tvSubCategory = (TextView) convertView.findViewById(R.id.tvSubCategory);
        tvSubCategory.setText(childrenData.get(groupPosition).getChildrenData().get(childPosition).getName());

        if (iActionCLickListener != null){
            llSubCategory.setOnClickListener(view -> iActionCLickListener.onClickedSubItem_(name, childrenData.get(groupPosition).getChildrenData().get(childPosition)));
        }
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (childrenData.get(groupPosition).getName().equalsIgnoreCase("Search by featured")){
            return childrenData.get(groupPosition).getChildrenData().size();
        }else {
            return 0;
        }

    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


}
