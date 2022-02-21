package com.example.wikidesign.drawerNavigation;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wikidesign.ProfileActivity;
import com.example.wikidesign.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DrawerNavigationDialogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DrawerNavigationDialogFragment extends DialogFragment {
    @BindView(R.id.imgAvatar)
    ImageView imgAvatar;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvPoints)
    TextView tvPoints;



    public DrawerNavigationDialogFragment() {
        // Required empty public constructor
    }

    public static DrawerNavigationDialogFragment newInstance() {
        DrawerNavigationDialogFragment fragment = new DrawerNavigationDialogFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_drawer_navigation_dialog, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    //Create Dialog
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //return super.onCreateDialog(savedInstanceState);
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        //dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    //set Layout for Dialog full screen
    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow()
                    .setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    @OnClick(R.id.imgClose)
    void onClickContinue() {
        dismiss();
    }

    @OnClick(R.id.tvProfile)
    void openProfile(){
        ProfileActivity.startMe(getActivity());
    }


}