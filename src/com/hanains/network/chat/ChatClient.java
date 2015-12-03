package com.hanains.network.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.plaf.basic.BasicPasswordFieldUI;

public class ChatClient {
	private static final String HOST="192.168.1.6";
	private static final int PORT=5000;

	public static void main(String[] args) {
		Socket sc = null;
		InputStream is = null;
		OutputStream os = null;
		BufferedReader br = null;
		PrintWriter pw =null;
		List<Writer> listWriters =new ArrayList<Writer>();
		//키보드 연결
		Scanner scanner =new Scanner(System.in);
		//소켓 생성
		sc = new Socket();

		try {
		//연결
			sc.connect(new InetSocketAddress(HOST, PORT));
			System.out.println("--연결성공--");
		//reader/writer 생성	
			br = new BufferedReader(new InputStreamReader(sc.getInputStream()));
			pw = new PrintWriter(new OutputStreamWriter(sc.getOutputStream()));
		//join 프로토콜
			System.out.print("닉네임>>");
			String nick = scanner.nextLine();
			pw.println("join:"+nick+"\r");
			pw.flush();
		//ChatClientReceiveThread시작
//			new ChatClientReceiveThread(sc, listWriters);
			new ChatClientReceiveThread(br).start();
		//키보드 입력처리
			while(true){
				System.out.print(">>");
				String input = scanner.nextLine();
				
				
		//quit 프로토콜 처리
				if ("quit".equals(input) ==true) {
					pw.println("quit:"+nick);
				}else{
		//메세지 처리
					//강사님구현소스
					pw.println("message:"+input);
					pw.flush();
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
		//자원정리
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
