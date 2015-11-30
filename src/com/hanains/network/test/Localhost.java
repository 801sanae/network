package com.hanains.network.test;

import java.net.*;

public class Localhost {
	//why main is static method? ==>
	//static method vs instance method
	public static void main(String[] args) {


		//Exception --> 가독성 ↓ , memory에 저장하기 떄문에 메모리의 
		//Exception의 고찰.. 실 사용시 안좋은 단점--> 스프링에서 runtimeException으로 넘김?
		//
		try {
			InetAddress inet = InetAddress.getLocalHost();
			System.out.println("Host Name : " + inet.getHostName());
			System.out.println("Host IPADRESS : " + inet.getHostAddress());
			
			byte[] addresses  =inet.getAddress();
//			for (int i = 0; i < addresses.length; i++) {
//				System.out.println(addresses[i]);
//			}
// 			결과값  마이너스 나오는 이유 -->
			for (int i = 0; i < addresses.length; i++) {
				System.out.print(addresses[i]&0xff);
				if(i+1 < addresses.length){
					System.out.print(".");
				}
			}
			
			System.out.println(inet.getLocalHost());
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}



	}
}
