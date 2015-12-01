package com.hanains.network.thread;

public class LowerCaseAlphaThread extends Thread {
	@Override
	public void run() {
		for (char c = 65; c <= 'z'; c++) {
			System.out.print(c);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
