package com.gpzuestc.fundamentals.concurrent;


/**
 * @author gpzuestc
 * @mail: guopengzhang@sohu-inc.com
 * @date: 2013-4-9
 * @todo: 
 * 
 */
public class NotifyTest implements Runnable{
	public static Integer number = 0;
	private int flag;
	public NotifyTest(int flag){
		this.flag = flag;
	}
	public synchronized void add() throws InterruptedException{
		while(number == 10){
			this.wait();
		}
		number++;
		System.out.println("add:" + number);
		this.notifyAll();
	}
	
	public synchronized void minus() throws InterruptedException{
		while(number == 0){
			this.wait();
		}
		number--;
		System.out.println("minus:" + number);
		this.notifyAll();
	}

	@Override
	public void run() {
		try {
			if(flag == 0){
				while(true){
					add();
				}
			}else{
				while(true){
					minus();
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new Thread(new NotifyTest(0)).start();
		new Thread(new NotifyTest(1)).start();
//		new Thread(new NotifyTest(0)).start();
//		new Thread(new NotifyTest(1)).start();
	}
}


