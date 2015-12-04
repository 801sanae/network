package com.hanains.network.time;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.Scanner;

public class TimeClient {
	private static final String HOST = "127.0.0.1"; //loop back ip 로컬호스트 나한테 보내는 주소. 
	private static final int PORT = 5000;
	private static final int BUFFER_SIZE=1024;
	
	public static void main(String[] args) {
		DatagramSocket ds = null;
		Scanner scanner = new Scanner(System.in);

		
		try{
			ds = new DatagramSocket();
			
			System.out.print("\"\"을 입력하시오:");
			String data = scanner.nextLine();
			byte[] sendData = data.getBytes("UTF-8");
			while(true){
			DatagramPacket sendPacket
			= new DatagramPacket(sendData, sendData.length, new InetSocketAddress(HOST, PORT));

			//데이터 전송
			ds.send(sendPacket);
			
			//데이터 수신

			DatagramPacket receiveDP = new DatagramPacket(new byte[BUFFER_SIZE], BUFFER_SIZE);
			ds.receive(receiveDP);

			data = new String(receiveDP.getData(), 0, receiveDP.getLength(), "UTF-8");
			System.out.println("<<"+data);
			}
		} catch(Exception e){
			e.printStackTrace();
		}finally{
			if(ds!=null){
				ds.close();
			}
		}
	}
}
