package com.hanains.network.echo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class EchoServerRecevieThread extends Thread{
	Socket sc=null;
	
	InputStream is = null;
	OutputStream os = null;
	
	
	public EchoServerRecevieThread(Socket sc) {
		this.sc = sc;
	}
	
	@Override
	public void run(){
		try {
			is = sc.getInputStream(); os = sc.getOutputStream(); 
			byte[] buffer = new byte[256];
			
			while(true){
				int readByteCnt = is.read(buffer);
				if(readByteCnt < 0 ){
					System.out.println("[서버]클라이언트로부터 연결 끊킴");
					break;
				}
				
				String data ="Hello";
				os.write(data.getBytes("UTF-8"));
				os.flush();
				
				data = new String(buffer, 0 , readByteCnt);
				System.out.println("[서버]수신 데이터 : " + data);
			}
			
			is.close(); os.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try{
				if(sc != null && sc.isClosed()==false){
					sc.close();
				}
			}catch(Exception e){
				e.printStackTrace();
				System.out.println("[서버]Thread : Error :" + e );
			}
		}
	}
}
