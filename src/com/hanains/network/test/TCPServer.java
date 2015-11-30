package com.hanains.network.test;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

	private static final int PORT = 5000;
	//final 상수..
	//상수는 값을 세팅할 수 없다.
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		try{
			//1.서버 소켓 생성
			serverSocket = new ServerSocket();
			
			InetAddress inet= InetAddress.getLocalHost();
			String local = inet.getHostAddress();
			
			//2.바인딩
			serverSocket.bind(new InetSocketAddress(local, PORT));
			
			System.out.println("[서버]바인딩"+local+":"+PORT);
			
			//3.연결 요청 대기(accept)
			Socket socket =serverSocket.accept();
			
			//4.연결 성공 후 연결 정보
			InetSocketAddress inetSocketAddress = (
					InetSocketAddress)socket.getRemoteSocketAddress();
			
			String remoteHostAddress = inetSocketAddress.getAddress().getHostAddress();
			
			int remoteHostPort = inetSocketAddress.getPort();
			
			System.out.println("[서버]연결된 from " + remoteHostAddress + ":"+ remoteHostPort);
			
			//5.소켓 닫기
			if(socket.isClosed()==false){
				socket.close();
			}
			
		} catch(IOException e){

		} finally {
			//서버 소켓 닫기
			if (serverSocket !=null && serverSocket.isClosed()==false) {
				try {
					serverSocket.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
	}
}
