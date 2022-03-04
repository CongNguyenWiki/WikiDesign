package com.skypremiuminternational.app.app.features.navigation.expandable;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.skypremiuminternational.app.R;
import com.skypremiuminternational.app.app.features.navigation.expandable.listener.IActionCLickListener;
import com.skypremiuminternational.app.domain.models.category.ChildData;

import java.util.List;


public class CategoryAdapter extends BaseExpandableListAdapter{

    private Context context;
    private List<ChildData> dataList;
    IActionCLickListener itemActionListener;

    public CategoryAdapter(Context context, List<ChildData> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public Object getChild(int arg0, int arg1) {
        return arg1;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        CustomExpandableListView secondLevelELV = new CustomExpandableListView(context);
        SubCategoryAdapter adapter = new SubCategoryAdapter(context, dataList.get(groupPosition).getChildrenData(),itemActionListener, dataList.get(groupPosition).getName());
        secondLevelELV.setAdapter(adapter);
        secondLevelELV.setGroupIndicator(null);
        secondLevelELV.setDivider(secondLevelELV.getResources().getDrawable(R.color.transparent));
        secondLevelELV.setChildDivider(secondLevelELV.getResources().getDrawable(R.color.transparent));
        return secondLevelELV;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {

        return this.dataList.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return dataList.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_category_expanble_list, null);

            LinearLayout llCategory = (LinearLayout) convertView.findViewById(R.id.llCategory);
            TextView tvCategory = (TextView) convertView.findViewById(R.id.tvCategory);
            ImageView img = (ImageView) convertView.findViewById(R.id.img);

            tvCategory.setText(dataList.get(groupPosition).getName());

            if (dataList.get(groupPosition).getChildrenData().isEmpty()) {
                img.setVisibility(View.INVISIBLE);
            }else {
                img.setVisibility(View.VISIBLE);
                if (isExpanded) {
                    img.setImageResource(R.drawable.expandable_group_up);
                } else {
                    img.setImageResource(R.drawable.expandable_group_down);
                }

            }

            if (dataList.get(groupPosition).getChildrenData().isEmpty()){
                llCategory.setOnClickListener(view -> itemActionListener.onClickedItem(dataList.get(groupPosition)));

            }


        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    public void setItemActionListener(IActionCLickListener itemActionListener) {
        this.itemActionListener = itemActionListener;
    }
}
