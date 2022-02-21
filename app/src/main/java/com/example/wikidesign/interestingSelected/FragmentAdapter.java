package com.example.wikidesign.interestingSelected;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FragmentAdapter extends FragmentStateAdapter {

    public FragmentAdapter(FragmentActivity fragmentManager) {
        super(fragmentManager);
    }

//    public FragmentAdapter(FragmentManager fragmentManager, Lifecycle lifecycle) {
//        super(fragmentManager, lifecycle);
//    }


    @Override
    public Fragment createFragment(int position) {
       if (position == 1){
           return new FragmentCategoryChild();
       }
       return new FragmentCategoryParent();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
