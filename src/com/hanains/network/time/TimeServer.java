package com.hanains.network.time;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeServer {
	private static final int PORT = 5000;
	private static final int BUFFER_SIZE = 1024;
	
	public static void main(String[] args) {
		DatagramSocket ds=null;
		
		try{
			ds = new DatagramSocket(PORT);
			
			System.out.println("수신기다리는중..");
			
			//데이터 수신
			DatagramPacket receiveDP=new DatagramPacket(new byte[BUFFER_SIZE], BUFFER_SIZE);
			ds.receive(receiveDP);
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a");
			String data = format.format(new Date());
			byte[] sendData = data.getBytes();

			//데이터 전송
			DatagramPacket sendDP
			= new DatagramPacket(sendData, sendData.length, new InetSocketAddress(receiveDP.getAddress(), receiveDP.getPort()));
			
			ds.send(sendDP);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
