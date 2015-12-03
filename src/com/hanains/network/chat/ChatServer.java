package com.hanains.network.chat;

import java.io.Writer;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

//input <-> output//
public class ChatServer {
	
	private static final int PORT = 5000;
	
	public static void main(String[] args) throws Exception{
		ServerSocket sSc = new ServerSocket();
		List<Writer> listWriters = new ArrayList<Writer>();	

		String host = InetAddress.getLocalHost().getHostAddress();
		sSc.bind(new InetSocketAddress(host, PORT));
		
		ChatServer.log("[서버] 바인딩: " + host + ":" +PORT);
		
		
		while(true){
			Socket sc = sSc.accept();
			
			InetSocketAddress inetSocketAddress = (
					InetSocketAddress)sc.getRemoteSocketAddress();
			
			String remoteHostAddress = inetSocketAddress.getAddress().getHostAddress();
			
			int remoteHostPort = inetSocketAddress.getPort();
			
			System.out.println("[서버]연결된 from " + remoteHostAddress + ":"+ remoteHostPort);
			
			new ChatServerThread(sc, listWriters).start();
		}
	}
	public static void log(String msg){
		System.out.println(msg);
	}
}
