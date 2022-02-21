package com.example.wikidesign.help;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.wikidesign.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class HelpSheetDialogFragment extends BottomSheetDialogFragment {

    TextView tvCall, tvEmail, tvAddress;


    private static final String KEY_HELP = "help_project";
    private Help mHelp;
    public static HelpSheetDialogFragment newInstance(Help help){
        HelpSheetDialogFragment helpSheetDialogFragment = new HelpSheetDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_HELP, help);

        helpSheetDialogFragment.setArguments(bundle);

        return helpSheetDialogFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null){
            mHelp = (Help) bundle.get(KEY_HELP);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        dialog = new BottomSheetDialog(getActivity(), R.style.AppBottomSheetDialogTheme);
        View viewDialog = LayoutInflater.from(getContext()).inflate(R.layout.sheet_bottom_help_fragment, null);
        dialog.setContentView(viewDialog);
        initView(viewDialog);

        //Setup events click button, textview here
        return  dialog;
    }

    private void initView(View view){
        tvCall = view.findViewById(R.id.tvPhone);
        tvEmail = view.findViewById(R.id.tvEmailAddress);
        tvAddress = view.findViewById(R.id.tvAddress);
    }

    private void setData(){
        if (mHelp == null){
            return;
        }
        tvCall.setText(mHelp.getPhone());
        tvEmail.setText(mHelp.getEmail());
        tvAddress.setText(mHelp.getAddress());
    }
}
