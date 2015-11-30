package com.hanains.network.test;

import java.net.*;
import java.util.Scanner;

public class Nslookup {
	public static void main(String[] args) throws Exception{
		Scanner sc = new Scanner(System.in);
		
		while(true){
			System.out.println(">");
			String host = sc.nextLine();
			//			if(host=="exit"){
			//				flag = false;
			//				System.out.println("Nslookup 종료");
			//			}내가 첨에 생각한거 근데안됫음..
			if("exit".equals(host)){
				System.out.println("끝");
				break;
			}//강사님꺼 

			InetAddress[] ns = InetAddress.getAllByName(host);
			
			for (int i = 0; i < ns.length; i++) {
				System.out.println(ns[i].toString());
			}
			
		}
	}

}
