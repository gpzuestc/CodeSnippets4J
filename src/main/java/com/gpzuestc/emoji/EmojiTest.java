package com.gpzuestc.emoji;

import java.nio.charset.Charset;

import org.junit.Test;

import com.google.common.primitives.Bytes;

/**
 * @author gpzuestc
 * @mail: guopengzhang@sohu-inc.com
 * @date: 2013-3-11
 * @todo: 
 * 
 */
public class EmojiTest {
	
	@Test
	public void test(){
		String a = "🐯😣💖💕💞❓";
		byte[] b = a.getBytes();
		System.out.println(byte2hex("a".getBytes()));
		System.out.println(stringToHexString("a"));
		System.out.println(b.length);
		for(int i = 0; i < b.length; i++){
			System.out.println(Integer.toHexString(new Integer(b[i])));
		}
	}
	
	@Test
	public void test1(){
		String a = "\ue050";
		System.out.println(a);
//		String b="\U0001f42f";
//		System.out.println(b);
		String c = "U+2600";
		System.out.println(c);
	}
	
	/**
     * 字节数组转换成16进制字符串
     * @param buf
     * @return
     */
    public  static  String byte2hex(byte buf[]) {
        StringBuffer strbuf = new StringBuffer(buf.length * 2);
        int i;

        for (i = 0; i < buf.length; i++) {
            if (((int) buf[i] & 0xff) < 0x10)
                strbuf.append("0");

            strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
        }

        return strbuf.toString();
    }
    
    public static String stringToHexString(String strPart) {
        String hexString = "";
        for (int i = 0; i < strPart.length(); i++) {
            int ch = (int) strPart.charAt(i);
            String strHex = Integer.toHexString(ch);
            hexString = hexString + strHex;
        }
        return hexString;
    }
}
