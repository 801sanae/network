package com.hanains.network.echo;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class UDPEchoClient {

	private static final String HOST = "127.0.0.1"; //loop back ip 로컬호스트 나한테 보내는 주소. 
	private static final int PORT = 5000;
	private static final int BUFFER_SIZE=1024;
	public static void main(String[] args) {
		DatagramSocket datagramSocket = null;

		try{
			//UDP 소켓생성
			datagramSocket = new DatagramSocket();

			//전송 패킷 생성
			String data = "Hello world";
			byte[] sendData = data.getBytes("UTF-8");

			DatagramPacket sendPacket
			= new DatagramPacket(sendData, sendData.length, new InetSocketAddress(HOST, PORT));

			//데이터 전송
			datagramSocket.send(sendPacket);

			//데이터 수신
			
			DatagramPacket receivePacket = new DatagramPacket(new byte[BUFFER_SIZE], BUFFER_SIZE);
			datagramSocket.receive(receivePacket);
			
			data = new String(receivePacket.getData(), 0, receivePacket.getLength(), "UTF-8");
			log("[Client]데이터 수신:" + data);
			
			
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
