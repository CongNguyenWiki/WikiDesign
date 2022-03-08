package com.skypremiuminternational.app.app.utils;

import android.content.Context;

import androidx.appcompat.app.AlertDialog;

public class DialogUtils {

  public static void showAlertDialog(Context context, String msg) {

    AlertDialog.Builder builder = new AlertDialog.Builder(context);
    builder.setMessage(msg)
        .setPositiveButton("OK", (dialogInterface, i) -> {
          // Do Nothing and dismiss
        }).show();

  }
}
