package com.hanains.network.http;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;

public class RequestHandler extends Thread {
	
	private Socket socket;
	
	public RequestHandler( Socket socket ) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		
		BufferedReader bufferedReader = null;
		OutputStream outputStream = null;
		
		try {
			// get IOStream
			bufferedReader = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );
			outputStream = socket.getOutputStream();

			// logging Remote Host IP Address & Port
			InetSocketAddress inetSocketAddress = ( InetSocketAddress )socket.getRemoteSocketAddress();
			SimpleHttpServer.consolLog( "connected from " + inetSocketAddress.getHostName() + ":" + inetSocketAddress.getPort() );

			// 예제 응답입니다.
			// 서버 시작과 테스트를 마친 후, 주석 처리 합니다.
//			outputStream.write( "HTTP/1.1 200 OK\r\n".getBytes( "UTF-8" ) );
//			outputStream.write( "Content-Type:text/html; charset =UTF-8\r\n".getBytes( "UTF-8" ) );
//			outputStream.write( "\r\n".getBytes() );
//			outputStream.write( "<h1>이 페이지가 잘 보이면 실습과제 SimpleHttpServer를 시작할 준비가 된 것입니다.</h1>".getBytes( "UTF-8" ) );

			/*
			 * 구현
			 */
//			PrintWriter pw = new PrintWriter(socket.getOutputStream());
			bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			
			String req="";
			SimpleHttpServer.consolLog("========req information===========");
			while(true){
				String line = bufferedReader.readLine(); //header read
				if(line==null || "".equals(line)) {System.out.println("끝");break;}
				if("".equals(req)) req=line;
				
				SimpleHttpServer.consolLog(line);
			}
			
			String[] tkn = req.split(" ");
			if("GET".equals(tkn[0])){
				System.out.println("dd");
				
				SimpleHttpServer.consolLog("1."+tkn[1]);
				SimpleHttpServer.consolLog("2."+tkn[2]);
				respStaticResource(outputStream, tkn[1], tkn[2]);
			}else{
				response400Error(outputStream, tkn[2]);
			}
			
		} catch( Exception ex ) {
			SimpleHttpServer.consolLog( "error:" + ex );
		} finally {
			// clean-up
			try{
				if( bufferedReader != null ) {
					bufferedReader.close();
				}
				
				if( outputStream != null ) {
					outputStream.close();
				}
				
				if( socket != null && socket.isClosed() == false ) {
					socket.close();
				}
				
			} catch( IOException ex ) {
				SimpleHttpServer.consolLog( "error:" + ex );
			}
		}
	
		
	}
	private void respStaticResource(OutputStream os, String url, String protocol)throws IOException{
		
		if(url.equals("/")){
			url = url+"index.html";
		}
		
		//default html 처리
		
		File file = new File("./webapp"+url);
		//file 존재 여부 체크

		if(file.exists()==false){
			response404Error(os, protocol);
		}
		
		Path path = file.toPath();
		
		String mimeType = Files.probeContentType(path);
				
		byte[] body = Files.readAllBytes(path);
		
//		outputStream.write( "Content-Type:text/html; charset =UTF-8\r\n".getBytes( "UTF-8" ) );
//		os.write(("Content-Type:text/html;"+mimeType).getBytes());
		os.write(body);
		
	}
	
	private void response404Error(OutputStream os, String protocol)throws IOException{
		File file = new File("./webapp/error/404.html");
		byte[] body = Files.readAllBytes(file.toPath());
		os.write(body);
	}
	
	private void response400Error(OutputStream os, String protocol)throws IOException{
		File file = new File("./webapp/error/400.html");
		byte[] body = Files.readAllBytes(file.toPath());
		os.write(body);
	}
}
