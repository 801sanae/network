package com.hanains.network.echo;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
	
	private static final int PORT = 5000;
	
	public static void main(String[] args) throws Exception{
		ServerSocket serverSocket = null;
		
		serverSocket = new ServerSocket();
		
		InetAddress inet = InetAddress.getLocalHost();
		String host = inet.getHostAddress();
		
		serverSocket.bind(new InetSocketAddress(host, PORT));
		
		System.out.println("[서버] 바인딩 :" + host + PORT);
		
		Socket sc = serverSocket.accept();
		
		Thread t = new EchoServerRecevieThread(sc);
		t.start();
		
		serverSocket.close();
	}
}
