package com.hanains.network.thread;

public class MultiThreadEx03 {
	
	public static void main(String[] args) {

		Thread t = new Thread(new CharRunnableImpl());
		t.start();
		
		for (int i = 0; i < 10; i++) {
			System.out.print(i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
