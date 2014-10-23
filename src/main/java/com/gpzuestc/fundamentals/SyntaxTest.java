package com.gpzuestc.fundamentals;

import org.junit.Test;

public class SyntaxTest {

	@Test
	public void breakTest() {
		outer: for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 4; j++) {
				if (j == 1) {
					break outer;
				}
				System.out.println(i + ":" + j);
			}
		}
		//label 作用跳出两层循环，默认只能跳出一层
		System.out.println("----------");
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 4; j++) {
				if (j == 1) {
					break;
				}
				System.out.println(i + ":" + j);
			}
		}
	}

	@Test
	public void continueTest() {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 4; j++) {
				if (j == 1) {
					continue;
				}
				System.out.println(i + ":" + j);
			}
		}
	}

}
