package com.gpzuestc.framework.chinese;

import java.io.File;
import java.text.Collator;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

/**
 * @author gpzuestc
 * @mail: guopengzhang@sohu-inc.com
 * @date: 2012-12-25
 * @todo:
 * 
 */
public class PinyinTest {
	@Test
	public void test_sort_pinyin2() {
		String[] arr = { "张三", "李四", "王五", "赵六", "JAVA", "123", "$%$#", "哈哈A",
				"1哈哈A", "1啊哈哈b", "1哈哈a", "哈哈", "哈", "怡情", "ha1", "和好", "伙计" };
		List<String> list = Arrays.asList(arr);
		System.out.println(list);
		Arrays.sort(arr, Collator.getInstance(java.util.Locale.CHINA));
		System.out.println(list);
		Arrays.sort(arr, new PinyinComparator());
		System.out.println(list);
	}

	@Test
	public void pinyin() {
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		format.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);

		String[] pinyinArray = null;
		try{
			pinyinArray = PinyinHelper.toHanyuPinyinStringArray('仇', format);
		}catch (BadHanyuPinyinOutputFormatCombination e){
			e.printStackTrace();
		}

		for (int i = 0; i < pinyinArray.length; ++i)
		{
			System.out.println(pinyinArray[i]);
		}
	}
	
	@Test
	public void pinyinStr() throws Exception{
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		format.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);
		
		List<String> lines = FileUtils.readLines(new File("/Users/gpzuestc/Documents/data"));
		for(String hanyu : lines){
//			String hanyu = "海淀区丹棱街甲1号欧美汇购物中心5楼05号";
			StringBuilder sb = new StringBuilder();
			char[] charArr = hanyu.toCharArray();
			for(int i = 0; i < charArr.length; i++){
				String[] pinyinArray = null;
				try{
					pinyinArray = PinyinHelper.toHanyuPinyinStringArray(charArr[i], format);
				}catch (BadHanyuPinyinOutputFormatCombination e){
					e.printStackTrace();
				}
				if(pinyinArray != null && pinyinArray.length > 0){
					sb.append(pinyinArray[0]);
				}else{
					sb.append(charArr[i]);
				}
				
			}
			System.out.println(sb.toString());
		}
		
		for(int i = 0 ; i < lines.size(); i++){
			UUID uuid = UUID.randomUUID();
			String str = uuid.toString().replaceAll("-", "");
			System.out.println(str);
		}
	}
}
