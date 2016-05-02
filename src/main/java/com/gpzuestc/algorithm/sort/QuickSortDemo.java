package com.gpzuestc.algorithm.sort;

public class QuickSortDemo {
	// Integer[] arr = new Integer[]{10,3,1,6,5,8,0,43,12};

	// 划分数组
	int partion(int arrays[], int start, int end) {
		// int x = array[end];
		// int i = start - 1;// 注意这点，把i设成负值，然后作为移动的标志
		// int j;
		// for (j = start; j < end; j++) {
		// if (array[j] <= x) {
		// i++;
		// int temp = array[j];
		// array[j] = array[i];
		// array[i] = temp;
		// }
		// }
		// int temp = array[j];
		// array[j] = array[i + 1];
		// array[i + 1] = temp;
		// return i + 1;// 返回的应该是交换后的哨兵的位置

		int i = start;
		int j = end;
		int key = arrays[i]; //第一个元素
		boolean flag = true; //true从后往前遍历
		while (i != j) {
			if (flag) {
				if (key > arrays[j]) {
					swap(arrays, i, j);
					flag = false;
				} else {
					j--;
				}
			} else {
				if (key < arrays[i]) {
					swap(arrays, i, j);
					flag = true;
				} else {
					i++;
				}
			}
		}
		return i;
	}

	// 递归解决每个划分后的小数组
	void quickSort(int[] array, int p, int r) {
		if (p < r) {
			int q = partion(array, p, r);
			quickSort(array, p, q - 1);
			quickSort(array, q + 1, r);
		}
	}

	public static void main(String[] args) {
		int[] array = { 4, 3, 1, 5, 6, 2 };
		QuickSortDemo sort = new QuickSortDemo();
		sort.quickSort(array, 0, array.length - 1);
		for (int i : array) {
			System.out.print(i + "\t");
		}
	}

	private void swap(int[] arrays, int i, int j) {
		int temp;
		temp = arrays[i];
		arrays[i] = arrays[j];
		arrays[j] = temp;
	}
}
