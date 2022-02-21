package com.example.wikidesign.shopCategoryList;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wikidesign.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShopCategoryListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShopCategoryListFragment extends BottomSheetDialogFragment {




    public static ShopCategoryListFragment newInstance(String param1, String param2) {
        ShopCategoryListFragment fragment = new ShopCategoryListFragment();

        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_shop_category_list, null);
        bottomSheetDialog.setContentView(view);
        return bottomSheetDialog;
    }


}