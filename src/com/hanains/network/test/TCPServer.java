package com.hanains.network.test;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
	//%%
	//단일 싱글 스레드라 다중처리가 힘들다..
	//%%
	private static final int PORT = 5000;
	//final 상수..
	//상수는 값을 세팅할 수 없다.
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		try{
			//1.서버 소켓 생성
			serverSocket = new ServerSocket();

			InetAddress inet= InetAddress.getLocalHost();
			String local = inet.getHostAddress();

			//2.바인딩
			serverSocket.bind(new InetSocketAddress(local, PORT));

			System.out.println("[서버]바인딩"+local+":"+PORT);

			//3.연결 요청 대기(accept)
			Socket socket =serverSocket.accept();

			//4.연결 성공 후 연결 정보
			InetSocketAddress inetSocketAddress = (
					InetSocketAddress)socket.getRemoteSocketAddress();

			String remoteHostAddress = inetSocketAddress.getAddress().getHostAddress();

			int remoteHostPort = inetSocketAddress.getPort();

			System.out.println("[서버]연결된 from " + remoteHostAddress + ":"+ remoteHostPort);

			//ioStram받아오기
			InputStream inputStream = socket.getInputStream();
			OutputStream outputStream = socket.getOutputStream();

			
			//7. 데이터 읽기
			try{

				byte[] buffer = new byte[256];
				while(true){
					int readByteCnt = inputStream.read(buffer);
					if(readByteCnt<0){
						System.out.println("[서버]클라이언트로 부터 연결 끝남-1일꺼다");
						break;
					}
					
					//데이터 보내기
					String data = "hello ";
					outputStream.write(data.getBytes("UTF-8"));
					outputStream.flush();
					
					data = new String(buffer, 0, readByteCnt);
					System.out.println("[서버]수신 데이터:"+data);
				}
			}catch(IOException e){System.out.println("[서버] 에러 : "+e);}			
			//자원정리
			inputStream.close(); outputStream.close();
			if(socket.isClosed()==false){
				socket.close();
			}

		} catch(IOException e){} finally {
			//8.서버 소켓 닫기
			if (serverSocket !=null && serverSocket.isClosed()==false) {
				try {
					serverSocket.close();
				} catch (Exception e2) {
					System.out.println("[서버] 에러 : "+e2);
					
				}
			}
		}
	}
}
