package com.hanains.network.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class TCPClient {
	private static final String SERVER_IP="192.168.56.1";
	private static final int SERVER_PORT=5000;

	public static void main(String[] args) {
		Socket socket = null;
		InputStream inputStream = null;
		OutputStream outputStream = null;

		socket = new Socket();

		try {
			socket.connect(new InetSocketAddress(SERVER_IP, SERVER_PORT));
			
			System.out.println("[client]연결 성공");
			
			inputStream = socket.getInputStream();
			outputStream  = socket.getOutputStream();
			
			String data = "Hellow workd";
			outputStream.write(data.getBytes("UTF-8"));
			
			byte[] buffer= new byte[256];
			int readByteCnt = inputStream.read(buffer);
			data =  new String(buffer, 0, readByteCnt);
			System.out.println(">>" + data);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(inputStream!=null){
				try {
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(outputStream!=null){
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if(socket!=null && socket.isClosed()==false){
				try {
					socket.close();
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			}
		}
	}
}
