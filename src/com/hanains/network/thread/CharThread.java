package com.hanains.network.thread;

public class CharThread extends Thread {
	@Override
	public void run() {
		for (char c = 65; c <= 'Z'; c++) {
			System.out.print(c+c+c);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}		
	}
}
