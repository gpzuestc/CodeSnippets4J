package com.gpzuestc.jvm;

/**
 * @author gpzuestc
 * @date: 2013-9-20
 * @description:  
 * 
 */
public class VolatileTest {
	
	public static void main(String[] args) throws Exception{
		VarCheck vc = new VarCheck();
		new Thread(vc).start();
		Thread.sleep(1000);
		vc.setStop();
		System.out.println("set stop");
		System.out.println("end!");
	}
}


class VarCheck implements Runnable{
	private volatile boolean stop;
//	private boolean stop;

	@Override
	public void run() {
		int i = 0;
		while(!stop){
			i++;
			System.out.println("I am running; loop=" + stop + "; count=" + i);
//			try {
//				Thread.sleep(100);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
		System.out.println("end; loop=" + stop);
	}
	
	public void setStop(){
		this.stop = true;
	}
}