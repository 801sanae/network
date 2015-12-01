package com.hanains.network.thread;

public class CharRunnableImpl implements Runnable {

	@Override
	public void run() {
		for (char c = 'a'; c <= 'Z'; c++) {
			System.out.print(c);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}		
	}

}
