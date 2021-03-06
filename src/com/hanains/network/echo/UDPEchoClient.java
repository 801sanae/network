package com.hanains.network.echo;

/*
 * UDP 네트워킹
 * 비연결 지향적 프로토콜
 * 데이터 송수신간 연결 절차 없이 빠른 전송 가능
 * 데이터 전달 신뢰성은 떨어짐
 */

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.Scanner;

public class UDPEchoClient {

	private static final String HOST = "127.0.0.1"; //loop back ip 로컬호스트 나한테 보내는 주소. 
	private static final int PORT = 5000;
	private static final int BUFFER_SIZE=1024;

	
	
	public static void main(String[] args) {
		DatagramSocket datagramSocket = null;
		Scanner scanner = new Scanner(System.in);
		try{
			//UDP 소켓생성
			datagramSocket = new DatagramSocket();

			//전송 패킷 생성
			//			String data = "Hello world";
			//			byte[] sendData = data.getBytes("UTF-8");
			while(true){
				System.out.print(">>");
				String data = scanner.nextLine();
				byte[] sendData = data.getBytes("UTF-8");

				DatagramPacket sendPacket
				= new DatagramPacket(sendData, sendData.length, new InetSocketAddress(HOST, PORT));

				//데이터 전송
				datagramSocket.send(sendPacket);

				//데이터 수신

				DatagramPacket receivePacket = new DatagramPacket(new byte[BUFFER_SIZE], BUFFER_SIZE);
				datagramSocket.receive(receivePacket);

				data = new String(receivePacket.getData(), 0, receivePacket.getLength(), "UTF-8");
				System.out.println("<<"+data);
				
			}



		}catch(Exception e){
			log("error:"+e);
		}finally{
			if(datagramSocket!=null){
				datagramSocket.close();
			}
		}
	}

	public static void log(String msg){
		System.out.println("[UDP Echo Client]" + msg);
	}
}
