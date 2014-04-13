package com.gpzuestc.fundamentals.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author gpzuestc
 * @date: 2013-10-5
 * @description:
 * 
 */
public class BarrierTest {

	public static void main(String[] args) {
		int playerCount = 2;
		CyclicBarrier cyclicBarrier = new CyclicBarrier(playerCount, new Runnable() {

			@Override
			public void run() {
				System.out.println("所有玩家进入下一关！");
			}
		});

		for (int i = 0; i < playerCount; i++) {
			new Thread(new Player(i, cyclicBarrier)).start();
		}
	}
}

class Player implements Runnable {

	private CyclicBarrier cyclicBarrier;
	private int id;

	public Player(int id, CyclicBarrier cyclicBarrier) {
		this.cyclicBarrier = cyclicBarrier;
		this.id = id;
	}

	@Override
	public void run() {
		try {
			System.out.println("玩家" + id + "正在第一关...");
			cyclicBarrier.await();
			System.out.println("玩家" + id + "进入第二关...");
			cyclicBarrier.await();
			System.out.println("玩家" + id + "进入第三关...");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
	}
}
