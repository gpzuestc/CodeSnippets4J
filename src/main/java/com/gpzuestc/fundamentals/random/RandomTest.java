package com.gpzuestc.fundamentals.random;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

/**
 * Description  
 * @author guopengzhang@sohu-inc.com @Nov 16, 2011
 *
 */

public class RandomTest {
	
	private static Random random = new Random();
	
	public static Set<Integer> getNoDuplicateRandomInt (int max, int size) throws Exception{
		Set<Integer> indexSet = new HashSet<Integer>();
		if(size > 0){
			if (max + 1 > size) {
				Random random = new Random();
				while (indexSet.size() < size) {
					indexSet.add(random.nextInt(max + 1));
				}
			} else if (max + 1 == size) {
				for(int i = 0; i < size; i++){
					indexSet.add(i);
				}
			}else{
				throw new Exception("");
			}
		}
		
		return indexSet;
	}

	
	public static void main(String[] args) {
//		public void testUUID(){
		
		UUID uuid = UUID.randomUUID();
		System.out.println(uuid);
		System.out.println(uuid.toString().replaceAll("-", ""));
//		System.out.println(uuid.toString().replaceAll("-", "").toUpperCase());
	}
	
	@Test
	public void testRandom(){
		Random random = new Random();
		for(int i = 0; i < 10; i++){
			System.out.println(random.nextInt(6));
		}
	}
	
//	public static void main(String[] args) throws Exception {
//		Set<Integer> set = getNoDuplicateRandomInt(9, 10);
//		for(Integer i : set){
//			System.out.println(i);
//		}
//	}
	
	@Test
	public void testRandomStr(){
		String prefix = "sohu_";
		int totalLen = 25;
		String baseStr = "abcdefghigklmnopqrstuvwxyzABCDEFGHIGKLMNOPQRSTUVWXYZ0123456789~!@#$%^&*()<>?:;,.=+";
		Random r = new Random();
		int baseLen = baseStr.length();
		r.nextInt(baseLen);
		String result = prefix;
		for(int i = 0; i < totalLen - prefix.length(); i++){
			result += baseStr.charAt(r.nextInt(baseLen));
		}
		System.out.println(result);
	}
	
	@Test
	public void testRandomMuti(){
		
		for(int i = 0; i < 100; i++){
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					System.out.println(random.nextInt(100));
				}
			}).start();
		}
	}
	
	@Test
	public void testRandomIntZero(){
		System.out.println(new Random().nextInt(0)); // java.lang.IllegalArgumentException: n must be positive

	}
	
	@Test
	public void testGenUUID(){
		int count = 12;
		List<String> uuids = new ArrayList<String>();
		for(int i = 0 ; i < count; i++){
			UUID uuid = UUID.randomUUID();
			String str = uuid.toString().replaceAll("-", "");
			uuids.add(str);
			System.out.println("http://hello.xianlaohu.com/xxxxx/" + str);
		}
		
		for(int i = 0 ; i < uuids.size(); i++){
			System.out.println(uuids.get(i));
		}
	}
	
	@Test
	public void testRandomUtil(){
		System.out.println(RandomUtils.nextInt(0, 2));
		System.out.println(RandomStringUtils.randomAlphabetic(6));
		System.out.println(RandomStringUtils.randomAlphanumeric(10));
		System.out.println(RandomStringUtils.randomAscii(10));
		System.out.println(RandomStringUtils.randomNumeric(10));
		
		System.out.println(RandomUtils.nextLong(1, 3));
		System.out.println(RandomStringUtils.randomAlphabetic(6).toLowerCase());
	}
	
	@Test
	public void testSeed(){
		Long seed = 100l;
		Random random = new Random(seed);
		System.out.println(random.nextInt(50));
	}
	
}


