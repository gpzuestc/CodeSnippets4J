package com.gpzuestc.fundamentals.concurrent;

/**
 * @author gpzuestc
 * @mail: guopengzhang@sohu-inc.com
 * @date: 2013-5-27
 * @todo: 
 * 
 */
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    // 是用ReentrantLock，还是用synchronized
    public static boolean useSynchronized = true;

    public static void main(String[] args) {
        IBuffer buff = null;
        if (useSynchronized) {
            buff = new Buffer();
        } else {
            buff = new BufferInterruptibly();
        }
        final Writer writer = new Writer(buff);
        final Reader reader = new Reader(buff);
        writer.start();
        reader.start();
        new Thread(new Runnable() {
            public void run() {
                long start = System.currentTimeMillis();
                for (;;) {
                    // 等5秒钟去中断读
                    if (System.currentTimeMillis() - start > 5000) {
                        System.out.println("不等了，尝试中断");
                        reader.interrupt();
                        break;
                    }

                }

            }
        }).start();
    }
}

interface IBuffer {
    public void write();

    public void read() throws InterruptedException;
}

class Buffer implements IBuffer {
    private Object lock;

    public Buffer() {
        lock = this;
    }

    public void write() {
        synchronized (lock) {
            long startTime = System.currentTimeMillis();
            System.out.println("开始往这个buff写入数据…");
            for (;;)// 模拟要处理很长时间
            {
                if (System.currentTimeMillis() - startTime > Integer.MAX_VALUE)
                    break;
            }
            System.out.println("终于写完了");
        }
    }

    public void read() {
        synchronized (lock) {
            System.out.println("从这个buff读数据");
        }
    }
}

class BufferInterruptibly implements IBuffer {

    private ReentrantLock lock = new ReentrantLock();

    public void write() {
        lock.lock();
        try {
            long startTime = System.currentTimeMillis();
            System.out.println("开始往这个buff写入数据…");
            for (;;)// 模拟要处理很长时间
            {
                if (System.currentTimeMillis() - startTime > Integer.MAX_VALUE)
                    break;
            }
            System.out.println("终于写完了");
        } finally {
            lock.unlock();
        }
    }

    public void read() throws InterruptedException {
        lock.lockInterruptibly();// 注意这里，可以响应中断
        try {
            System.out.println("从这个buff读数据");
        } finally {
            lock.unlock();
        }
    }

}

class Writer extends Thread {

    private IBuffer buff;

    public Writer(IBuffer buff) {
        this.buff = buff;
    }

    @Override
    public void run() {
        buff.write();
    }

}

class Reader extends Thread {

    private IBuffer buff;

    public Reader(IBuffer buff) {
        this.buff = buff;
    }

    @Override
    public void run() {

        try {
            buff.read();
        } catch (InterruptedException e) {
            System.out.println("我不读了");
        }

        System.out.println("读结束");

    }
}

