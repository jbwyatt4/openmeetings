package org.xmlcrm.utils.math;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class MD5Calc {
   private MessageDigest md5;
   private byte[] digest;
   
   public MD5Calc(String algorithm) {
      try {
         md5 = MessageDigest.getInstance(algorithm);
      } catch(NoSuchAlgorithmException nsae) {
         nsae.printStackTrace();
      }
   }
         
     public String toHexString(byte b) {
         int value = (b & 0x7F) + (b < 0 ? 128 : 0);
      
          String ret = (value < 16 ? "0" : "");
          ret += Integer.toHexString(value).toLowerCase();
      
      return ret;
     }
    
   public String do_checksum(String data)
   {
      StringBuffer strbuf = new StringBuffer();
      
            md5.update(data.getBytes(), 0, data.length());     
            digest = md5.digest();     
   
            for (int i = 0; i < digest.length; i++) {
               strbuf.append(toHexString(digest[i]));
      }
         
      return strbuf.toString();     
   }
   
   public static void main(String[] args)
     {
        MD5Calc md5 = new MD5Calc("MD5");
       
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int num = 0;
      String input = "";
      
      if(args.length == 1) {
         try {
            num = Integer.parseInt(args[0]);
         } catch(NumberFormatException nfe) {
            nfe.printStackTrace();
         }
         
         try {   
             while(num-- > 0) {
                input = br.readLine();
               
                System.out.println(input+"\t-\t"+md5.do_checksum(input)+"\n");
             }
          }
          catch(IOException ioe) {
             ioe.printStackTrace();
          }   
      }         
   }
} 