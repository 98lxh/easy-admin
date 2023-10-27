package com.easy.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StrUtil {
    public static boolean isEmpty(String str){
        return str == null || str.trim().length() == 0;
    }


    public static String md5(String str){
      try{
          MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] md5 = md.digest(str.getBytes(StandardCharsets.UTF_8));

            //将处理后的字节转成16进制，得到最终32个字符
          StringBuffer sb = new StringBuffer();
          for(byte b : md5){
            sb.append(String.format("%02x",b));
          }
          return sb.toString();
      }catch(NoSuchAlgorithmException e){
          e.printStackTrace();
      }
        return "";
    }
}
