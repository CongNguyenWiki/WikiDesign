package com.skypremiuminternational.app.app.features.my_orders.detail.edit_review.AsyncTask;

import static com.skypremiuminternational.app.app.utils.ImageDataUtils.clearAllBitmapEditZone;
import static com.skypremiuminternational.app.app.utils.ImageDataUtils.saveImageByEditPath;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;


import com.skypremiuminternational.app.app.features.my_orders.detail.edit_review.EditReviewDialogFragment;

import java.io.InputStream;

public class DownloadAsyncTask extends AsyncTask<String,Void, Bitmap> {
    int num  = 0 ;
    EditReviewDialogFragment editReviewDialogFragment;
    ProgressDialog mProgressDialog;
    public DownloadAsyncTask(EditReviewDialogFragment editReviewDialogFragment){
      this.editReviewDialogFragment = editReviewDialogFragment;
    }
    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      mProgressDialog = new ProgressDialog(editReviewDialogFragment.getActivity());
      mProgressDialog.setMessage("Loading...");
      mProgressDialog.setIndeterminate(false);
      mProgressDialog.show();
      clearAllBitmapEditZone(editReviewDialogFragment.getActivity());
    }
    @Override
    protected Bitmap doInBackground(String... URL) {
      Bitmap bitmap = null;
      for(String s : URL){
        try {
          // Download Image from URL
          InputStream input = new java.net.URL(s).openStream();
          // Decode Bitmap
          bitmap = BitmapFactory.decodeStream(input);

          saveImageByEditPath(editReviewDialogFragment.getActivity(),bitmap,"image-edit-"+num);
          num++;
        } catch (Exception e) {
          e.printStackTrace();
        }
      }


      return bitmap;
    }
    @Override
    protected void onPostExecute(Bitmap result) {

      Log.d("HELLO", ""+ num );
      editReviewDialogFragment.renderListImage();
      mProgressDialog.dismiss();
    }
  }

