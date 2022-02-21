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

public class ParentBottomSheetDialog extends BottomSheetDialogFragment {
    TextView tvHelpSign;

    public static ParentBottomSheetDialog newInstance(){
        ParentBottomSheetDialog parentBottomSheetDialog = new ParentBottomSheetDialog();
        return parentBottomSheetDialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Help helpList = new Help("+65 6533 0000", "membersg@25hrs.com.sg", "Mon to Fri: 0900 to 1800\n Eve of PH: 0900 to 1300\n PH, Sat, Sun: Office Closed");

        BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        bottomSheetDialog = new BottomSheetDialog(getActivity(), R.style.AppBottomSheetDialogTheme);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.parent_sheet_bottom_fragment, null);
        bottomSheetDialog.setContentView(view);
        initView(view);
        tvHelpSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HelpSheetDialogFragment helpSheetDialogFragment = HelpSheetDialogFragment.newInstance(helpList);
                helpSheetDialogFragment.show(getFragmentManager(), helpSheetDialogFragment.getTag());
                dismiss();
            }
        });

        return bottomSheetDialog;
    }

    private void initView(View view){
        tvHelpSign = view.findViewById(R.id.tvHelpSignIn);
    }
}
