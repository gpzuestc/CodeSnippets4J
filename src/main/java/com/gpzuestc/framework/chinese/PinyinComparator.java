package com.gpzuestc.framework.chinese;

import java.util.Comparator;

import net.sourceforge.pinyin4j.PinyinHelper;

/**
 * @author gpzuestc
 * @mail: guopengzhang@sohu-inc.com
 * @date: 2012-12-25
 * @todo: 
 * 
 */
public class PinyinComparator implements Comparator<Object> {
//    public int compare(Object o1, Object o2) {
//        String str1 = (String) o1;
//        String str2 = (String) o2;
//        return transHanZi(str1).compareTo(transHanZi(str2));
//    }
//    private String transHanZi(String hanzi){
//        StringBuilder builder = new StringBuilder();
//        int i = 0;
//        while (i < hanzi.length()) {
//            char ch = hanzi.charAt(i);
//            if (ch < 128) {
//                builder.append(ch);
//            } else {
//                String[] pinyin = PinyinHelper.toHanyuPinyinStringArray(ch);
//                if (pinyin != null && pinyin.length > 0) {
//                    builder.append('哈');
//                    builder.append(pinyin[0].substring(0, pinyin[0].length() - 1));
//                }
//            }
//            i += 1;
//        }
//        return builder.toString();
//    }
	
	private String concatPinyinStringArray(String[] pinyinArray) {  
        StringBuffer pinyinStrBuf = new StringBuffer();  
  
        if ((null != pinyinArray) && (pinyinArray.length > 0)) {  
            for (int i = 0; i < pinyinArray.length; i++) {  
                pinyinStrBuf.append(pinyinArray[i]);  
            }  
        }  
        return pinyinStrBuf.toString();  
    }  
  
    public int compare(Object o1, Object o2) {  
  
        char c1 = ((String) o1).charAt(0);  
        char c2 = ((String) o2).charAt(0);  
        return concatPinyinStringArray(PinyinHelper.toHanyuPinyinStringArray(c1)).compareTo(  
                concatPinyinStringArray(PinyinHelper.toHanyuPinyinStringArray(c2)));  
    }  
}
