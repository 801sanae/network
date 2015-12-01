package com.hanains.network.thread;

public class MultiThreadEx01 {
	public static void main(String[] args) throws Exception {
		Thread charThread = new CharThread();
		charThread.start();
		
		for (int i = 0; i < 10; i++) {
			System.out.print(i);
			Thread.sleep(100);
		}
		
	}
}
