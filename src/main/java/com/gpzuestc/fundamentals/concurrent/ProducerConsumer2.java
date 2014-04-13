package com.gpzuestc.fundamentals.concurrent;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gpzuestc
 * @mail: guopengzhang@sohu-inc.com
 * @date: 2013-4-9
 * @todo: 
 * 
 */
public class ProducerConsumer2 {
	private static int capacity = 10; 
    private static List<String> basket = new ArrayList<String>(capacity); 
 
    public static void main(String[] args) { 
        // 多生产者与多消费者 
        int producerSize = 2; 
        Thread[] ps = new Thread[producerSize]; 
        for (int i = 0, step = 500; i < producerSize; i ++) { 
            ps[i] = new Thread(new Producer((i) * step, (i+1) * step), "生产-->线程--" + (i+1)); 
            ps[i].start(); 
        } 
 
        int customerSize = 10; 
        Thread[] cs = new Thread[customerSize]; 
        for (int i = 0; i < customerSize; i ++) { 
            cs[i] = new Thread(new Customer(), "消费线程--" + (i+1)); 
            cs[i].start(); 
        } 
 
        // 等待生产线程结束并中断消费线程 
        for (int i = 0; i < producerSize; i ++) { 
            try {
                ps[i].join(); 
            } catch (InterruptedException e) { 
                e.printStackTrace(); 
            } 
        } 
        for (int i = 0; i < customerSize; i ++) { 
            cs[i].interrupt(); 
        } 
    } 
    static class Producer implements Runnable { 
        private int start; 
        private int end; 
 
        Producer(int start, int end) { 
            this.start = start; 
            this.end = end; 
        } 
 
        @Override 
        public void run() { 
            for (int i = start; i < end; i ++) { 
                synchronized (basket) { 
                    try { 
                        while (basket.size() == capacity) { 
                            basket.wait(); 
                        } 
                        String p = " PRO" + i; 
                        System.out.println(Thread.currentThread().getName() + p); 
                        basket.add(p); 
                        basket.notifyAll(); 
                        Thread.yield(); // 让出当前线程的执行权,有利于看出交替线程运行的效果 
                    } catch (InterruptedException e) { 
                        e.printStackTrace(); 
                        break; 
                    } 
                } 
            } 
        } 
    } 
    static class Customer implements Runnable { 
        @Override 
        public void run() { 
            while (true) { 
                synchronized (basket) { 
                    try{ 
                        while (basket.size() == 0) { 
                            basket.wait(); 
                        } 
                        System.out.println(Thread.currentThread().getName() + basket.remove(0)); 
                        basket.notifyAll(); 
                    } catch (InterruptedException e) { 
                        System.out.println(Thread.currentThread().getName() + "退出"); 
                        break; 
                    } 
                } 
            } 
        } 
    } 
}
