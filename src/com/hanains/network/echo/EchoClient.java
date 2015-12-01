package com.hanains.network.echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public class EchoClient {
	private static final String HOST = "192.168.1.30";
	private static final int PORT = 5000;

	public static void main(String[] args) {
		Socket sc = null;
		InputStream is = null;
		OutputStream os =null;

		sc = new Socket();

		try {
			sc.connect(new InetSocketAddress(HOST, PORT));

			System.out.println("[클라이언트]연결 성공");

			is = sc.getInputStream(); os=sc.getOutputStream();

			BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

			os = sc.getOutputStream(); is = sc.getInputStream(); 

			PrintWriter pw = new PrintWriter(new OutputStreamWriter(os));

			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			String data = null;

			while((data = keyboard.readLine())!=null){
				if(data.equals("exit")) break;
				pw.println(data);
				pw.flush();
				String echo = br.readLine();
				System.out.println("echo:"+echo);
			}

			byte[] buffer = new byte[256];


			pw.close(); br.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try{
				if(is!=null) is.close();
			}catch(IOException e1){ e1.printStackTrace();}

			try{
				if(os!=null) os.close();
			}catch(IOException e2){e2.printStackTrace();}

			try{
				if(sc!=null && sc.isClosed()==false) sc.close();
			}catch(IOException e3){e3.printStackTrace();}

		}
	}
}
