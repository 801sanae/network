package com.hanains.network.thread;

public class MultiThreadEx02 {
	
	public static void main(String[] args) {
		Thread t1 = new CharThread();
		Thread t2 = new LowerCaseAlphaThread();
		
		t1.start(); t2.start();
	}
	
	
}
