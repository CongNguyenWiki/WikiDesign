package com.skypremiuminternational.app.app.utils;



public class SalutationUtils {

  public static String getSalutationCode(String value){
    for (int i = 0 ; i < Constants.SALUTATIONS_NEW.length ; i++){
      if(Constants.SALUTATIONS_NEW[i].equalsIgnoreCase(value)){
        return Constants.SALUTATIONS_CODE_NEW[i];
      }
    }

    return value;
  }
   public static String getSalutationValue(String code){
      for (int i = 0 ; i < Constants.SALUTATIONS_CODE_NEW.length ; i++){
        if(Constants.SALUTATIONS_CODE_NEW[i].equalsIgnoreCase(code)){
          return Constants.SALUTATIONS_NEW[i];
        }
      }

      return code;
    }

}
