package com.hanains.network.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ChatServerThread extends Thread{

	private String nick;
	private Socket sc = null;
	private List<Writer> listWriters =null;

	BufferedReader br=null;
	PrintWriter pw=null;

	public ChatServerThread(Socket sc, List<Writer> listWriters) {
		this.sc = sc;
		this.listWriters = listWriters;
	}

	@Override
	public void run() {

		try {
			//스트림얻기
			br = new BufferedReader(new InputStreamReader(sc.getInputStream(), StandardCharsets.UTF_8));
			pw = new PrintWriter(new OutputStreamWriter(sc.getOutputStream(), StandardCharsets.UTF_8), true);

			while(true){
				String req =br.readLine();
				if(req == null){
					ChatServer.log("클라이언트로부터 연결 끊킴"); break;
				} 
				//
				String[] tkns =req.split(":");
				//TODO
				if ("join".equals(tkns[0])) {
//					ChatServer.log(":::"+tkns[0]);//protocol
//					ChatServer.log(":::"+tkns[1]);//nick
					doJoin(tkns[1], pw);
				}else if("message".equals(tkns[0])){
				//TODO	
					doMessage( tkns[1]);
				}else if("quit".equals(tkns[0])){
					doQuit(pw);
				}else{
					ChatServer.log("에러: 알수 없는 요청("+tkns[0]+")");
				}
			}

		} catch (IOException e1) {
			e1.printStackTrace();
		} finally{
			try{
				if(br==null && pw==null){
					br.close(); pw.close();
				}
				if(sc.isClosed()==false && sc==null){
					sc.close();
				}
			} catch(IOException e2){e2.printStackTrace();}
		}
	}// End of run()

	private void doJoin(String nick, Writer writer){
		this.nick = nick;
		
		String data = nick + " 님이 참여하엿다."+"\r\n";
		broadcast(data);
		
		addWriter(writer);
		
		pw.println("join:ok\r\n");
		pw.flush();
		
	}
	
	private void addWriter(Writer writer){
		synchronized (listWriters) {
			listWriters.add(writer);
		}
	}
	
	private void broadcast(String data){
		synchronized (listWriters) {
			for(Writer writer : listWriters){
				PrintWriter pw =(PrintWriter)writer;
				pw.println(data+"\r\n");
				pw.flush();
			}
		}
	}
	//TODO
	private void doMessage(String msg){
		String data = "message:"+msg;
		broadcast(data);
	}
	
	private void doQuit(Writer writer){
		removeWriter(writer);
		String data = nick + "님이 퇴장하셧습니다.";
		broadcast(data+"\r\n");
	}
	
	private void removeWriter(Writer writer){
		listWriters.remove(writer);
	}
}
