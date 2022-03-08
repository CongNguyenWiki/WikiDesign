package com.skypremiuminternational.app.app.utils;


import java.util.Calendar;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 *  Created by WIKI Toan Tran 20211111
 */

public  class AESHexCryptUtil {

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/ECB/PKCS5Padding";
    private static final String DEFUALT_ENCODING = "UTF8";

    private String encKey;
    private String signKey;

    public AESHexCryptUtil(String encKey, String signKey){
        if(encKey != null && !"".equals(encKey) && signKey != null && !"".equals(signKey)){
            this.encKey = encKey;
            this.signKey = signKey;
        }
    }

    /**
     *  20200428 - WIKI Toan Tran - Encrypt funtion
     * @param input
     * @param key
     * @return
     */
    public static String encrypt(String input, String key){

        byte[] crypted = null;
        try{
            SecretKeySpec skey = new SecretKeySpec(key.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, skey);
            crypted = cipher.doFinal(input.getBytes());
        }catch(Exception e){
            System.out.println(e.toString());
        }
        return parseByte2HexStr(crypted);

    }

    /**
     * 20200428 - WIKI Toan Tran - Covert to String
     * @param buf
     * @return
     */
    private static String parseByte2HexStr(byte buf[]) {

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();

    }

    /**
     * 20200428 WIKI Toan Tran - covert to byte[]
     * @param hexStr
     * @return
     */
    private static byte[] parseHexStr2Byte(String hexStr) {

        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length()/2];
        for (int i = 0;i< hexStr.length()/2; i++) {
            int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
            int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;

    }

    /**
     * 20200428 - WIKI Toan Tran - decrypt funtion
     * @param input
     * @param key
     * @return
     */
    public static String decrypt(String input, String key){

        byte[] output = null;

        try{
            SecretKeySpec skey = new SecretKeySpec(key.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, skey);
            output = cipher.doFinal(parseHexStr2Byte(input));

        }catch(Exception e){
            System.out.println(e.toString());
        }
        return new String(output);
    }


    public static String getSk(){
        Calendar now = Calendar.getInstance();
        String timestamp = now.getTimeInMillis()+"";
        String sk = "";
        if(timestamp.length() > 16){
            sk = timestamp.substring(0,15);
        } else if(timestamp.length() < 16) {
            for(int i = 0 ; i < 16 - timestamp.length(); i++){
                sk = sk + "0";
            }
            sk = sk + "" + timestamp;
        } else {
            sk = timestamp;
        }
        return sk;
    }
}