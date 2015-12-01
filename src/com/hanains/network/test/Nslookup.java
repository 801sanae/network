package com.hanains.network.test;

import java.net.*;
import java.util.Scanner;

public class Nslookup {
	public static void main(String[] args) {
		while(true){
			Scanner sc = new Scanner(System.in);
			
			System.out.println(">");
			String host = sc.nextLine();

			if("exit".equals(host) && host!=null){
				System.out.println("끝");
				break;
			}

			InetAddress[] ns;
			
			try {
				ns = InetAddress.getAllByName(host);
				
				for (int i = 0; i < ns.length; i++) {
					System.out.println(ns[i].toString());
				}
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		}
	}

}
