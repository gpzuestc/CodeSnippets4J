package com.gpzuestc.fundamentals.concurrent;

public class ProducerConsumer {
	// 利用生产者和消费者的举例说明，让你更简易了解wait(),notify(),notifyAll()三个函数.
	// 生产者和消费者共同来操作堆栈,消费者从堆中取产品,生产者放产品!
	// 只有当生产者把产品放到了堆中,消费者才可以取到产品,不然可想而知生产者和消费者就会发生矛盾（即程序出错）!
	// 当你用两个线程来分别控制生产者和消费者时.他们是同时的.
	// 你可以用wait()方法让消费者那个线程停下来等生产者把产品放到堆里,放完之后,消费者才可以取.
	// 当生产者放完了产品之后,就用notify()通知wait(),让消费者获得运行的权利!
	public static void main(String args[]) throws InterruptedException {
		Arraybuffer ab = new Arraybuffer();
		Producer producer = new Producer(ab, "Producer");
		Consumer consumer = new Consumer(ab, "Consumer");
		producer.start();
		consumer.start();
	}
}

class Arraybuffer {
	public int pnum = 0;
	public int[] intArray = new int[10];

	public synchronized void put(int value) throws InterruptedException {
		while (pnum == intArray.length) {
			System.out.println("intArry is full");
			wait();
		}
		intArray[pnum] = value;
		pnum++;
		notifyAll();
	}

	public synchronized int get() throws InterruptedException {
		while (pnum == 0) {
			System.out.println("intArry is empty");
			wait();
		}
		pnum--;
		notifyAll();// 这里使用notifyAll()函数也可以
		return intArray[0];
	}
}

class Producer extends Thread {
	private String name;
	private Arraybuffer c;

	public Producer(Arraybuffer cc, String name) {
		this.c = cc;
		this.name = name;
	}

	public void run() {
		int i;
		for (i = 0; i < 10; i++) {
			try {
				c.put(i);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(this.name + "put:" + i);
		}
		System.out.println("i am not produce");
	}
}

class Consumer extends Thread {
	private String name;
	private Arraybuffer c;

	public Consumer(Arraybuffer cc, String name) {
		this.c = cc;
		this.name = name;
	}

	public void run() {
		int i;
		for (i = 0; i < 10; i++) {
			try {
				c.get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(this.name + "get:" + i);
		}
	}
}