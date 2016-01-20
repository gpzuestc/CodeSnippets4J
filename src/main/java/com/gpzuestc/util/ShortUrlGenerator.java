package com.gpzuestc.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ShortUrlGenerator {
	 
	public static void main(String[] args) {
        String sLongUrl = "http://blog.csdn.net/sunset716/article/details/9229491" ;
        String[] results = generateShortUrl(sLongUrl);
        for(String result: results){
            System.out.println("生成的短链接地址：http://goo.gl/" + result);
        }
//运行结果如下所示
//生成的短链接地址：http://goo.gl/NV3yYb
//生成的短链接地址：http://goo.gl/Bb6zAj
//生成的短链接地址：http://goo.gl/RjqIvm
//生成的短链接地址：http://goo.gl/MrUvqa
    }
 
    private static String[] generateShortUrl(String url) {
        String sMD5EncryptResult = getMd5(key + url);
        String hex = sMD5EncryptResult; 
        String[] resUrl = new String[4];
 
        for ( int i = 0; i < 4; i++) {
            String sTempSubString = hex.substring(i * 8, i * 8 + 8);
            long lHexLong = 0x3FFFFFFF & Long.parseLong (sTempSubString, 16);
            String outChars = "" ;
            for ( int j = 0; j < 6; j++) {
               long index = 0x0000003D & lHexLong;
               outChars += chars[( int ) index];
               lHexLong = lHexLong >> 5;
            }
            resUrl[i] = outChars;
        }
        return resUrl; 
    }
     
    public static String getMd5(String s) {
        byte[] b = s.getBytes();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(b);
            byte[] b2 = md.digest();
            char str[] = new char[b2.length << 1];
            int len = 0;
            for (int i = 0; i < b2.length; i++) {
                byte val = b2[i];
                str[len++] = hexChar[(val >>> 4) & 0xf];
                str[len++] = hexChar[val & 0xf];
            }
            return new String(str);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
 
    static String key = "wuguowei";
    static char hexChar[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8' , '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    static String[] chars = new String[] { "a" , "b" , "c" , "d" , "e" , "f" , "g" , "h" ,
        "i" , "j" , "k" , "l" , "m" , "n" , "o" , "p" , "q" , "r" , "s" , "t" ,
        "u" , "v" , "w" , "x" , "y" , "z" , "0" , "1" , "2" , "3" , "4" , "5" ,
        "6" , "7" , "8" , "9" , "A" , "B" , "C" , "D" , "E" , "F" , "G" , "H" ,
        "I" , "J" , "K" , "L" , "M" , "N" , "O" , "P" , "Q" , "R" , "S" , "T" ,
        "U" , "V" , "W" , "X" , "Y" , "Z"}; 
}
