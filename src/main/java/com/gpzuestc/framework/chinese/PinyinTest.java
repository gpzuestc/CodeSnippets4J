package com.gpzuestc.framework.chinese;

import java.text.Collator;
import java.util.Arrays;
import java.util.List;

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
        String[] arr = {"张三", "李四", "王五", "赵六", "JAVA", "123", "$%$#", "哈哈A",
                "1哈哈A", "1啊哈哈b", "1哈哈a", "哈哈", "哈", "怡情", "ha1", "和好", "伙计"};
        List<String> list = Arrays.asList(arr);
        System.out.println(list);
        Arrays.sort(arr, Collator.getInstance(java.util.Locale.CHINA));
        System.out.println(list);
        Arrays.sort(arr, new PinyinComparator());
        System.out.println(list);
    }
}
