package com.gpzuestc;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Description:  
 * @author: guopengzhang@sohu-inc.com 
 * @date: Apr 25, 2012
 * 
 */
public class MD5
{

    public MD5()
    {
    }

    public static String crypt(String str)
    {
        if(str == null || str.length() == 0)
            throw new IllegalArgumentException("String to encript cannot be null or zero length");
        StringBuffer hexString = new StringBuffer();
        try
        {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte hash[] = md.digest();
            for(int i = 0; i < hash.length; i++)
                if((0xff & hash[i]) < 16)
                    hexString.append((new StringBuilder("0")).append(Integer.toHexString(0xff & hash[i])).toString());
                else
                    hexString.append(Integer.toHexString(0xff & hash[i]));

        }
        catch(NoSuchAlgorithmException e)
        {
            return "";
        }
        return hexString.toString();
    }
    
    public static void main(String[] args) {
		System.out.println(crypt("1"));
	}
}
