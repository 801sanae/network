package com.hanains.network.echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
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
			os = sc.getOutputStream(); is = sc.getInputStream();
			
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(os));
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			String data = null;
			
			while((data = br.readLine())!=null){
				System.out.println("클라이언트로 부터 전송 받은 문자열:"+data);
				pw.println(data);
				pw.flush();
			}
			 
			pw.close(); br.close();
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
