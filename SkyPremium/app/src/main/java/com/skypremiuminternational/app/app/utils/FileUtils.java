package com.skypremiuminternational.app.app.utils;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.OpenableColumns;

public class FileUtils {



  public static double getFileSizeFromUri(Uri uri, Context context){

    double size = 0;
    Cursor returnCursor = context.getContentResolver().
        query(uri, null, null, null, null);
    int sizeIndex = returnCursor.getColumnIndex(OpenableColumns.SIZE);
    returnCursor.moveToFirst();

    size =  returnCursor.getDouble(sizeIndex);
    returnCursor.close();
    return size;
  }

  public static double convertByteToKB(final double sizeByte){
    return sizeByte/1024;
  }
  public static double convertByteToMB(final double sizeByte){
    return sizeByte/1024/1024;
  }
}
