package com.hanains.network.test;

import java.net.*;
import java.util.Scanner;

public class Nslookup {
	public static void main(String[] args) throws Exception{
		InetAddress ns =null;
		boolean flag=true;
		
		Scanner sc = new Scanner(System.in);
		while(flag){
			System.out.println(">");
			String host = sc.nextLine();
			if(host=="exit"){
				flag = false;
				System.out.println("Nslookup 종료");
//			}
//			if("exit".equals(host)){
//				break;
//			} 강사님
			
			InetAddress res = ns.getByName(host);
			System.out.println(res.toString());
			}
		}
	}
}
