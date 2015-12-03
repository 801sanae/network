package com.hanains.network.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class ChatClientReceiveThread extends Thread{
//	private Socket sc=null;
//	private BufferedReader br = null;
//	private PrintWriter pw =null;
//	List<Writer> listWriters=null;
//	Scanner scanner = new Scanner(System.in);
//
//	public ChatClientReceiveThread(Socket sc, List<Writer> listWriters) {
//		this.sc=sc;
//		this.listWriters=listWriters;
//	}
//	@Override
//	public void run() {
//		System.out.print("닉네임:>>");
//		String nick = scanner.nextLine();
//		try {
//			br = new BufferedReader(new InputStreamReader(sc.getInputStream()));
//			pw = new PrintWriter(new OutputStreamWriter(sc.getOutputStream()));
//
//			String data = scanner.nextLine();
//
//			pw.print("join:"+nick+"\r\n");
//			pw.flush();
//			pw.write(data+"\r\n");
//			String text = br.readLine();
//
//			pw.println("message:"+text+"\r");
//
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally{
//			try{
//				if(br!=null) br.close();
//				if(pw!=null) pw.close();
//				if(sc!=null && sc.isClosed()==false) sc.close();
//			}catch(IOException e1){ e1.printStackTrace();}
//		}
//	}
//	private void msg(String nick, String text){
//		System.out.println(nick+":"+text+"\r\n");
//	}
//}
	private BufferedReader br;
	public ChatClientReceiveThread(BufferedReader br) {
		// TODO Auto-generated constructor stub
		this.br = br;
	}
	@Override
	public void run() {
		while(true){
			String d;
			try {
				d = br.readLine();
				if(d==null) break;
				System.out.println(d);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}