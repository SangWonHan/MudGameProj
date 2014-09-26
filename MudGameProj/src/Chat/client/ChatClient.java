package Chat.client;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ChatClient {
	
	// 메시지를 읽어서 화면에 출력하는 쓰레드 클래스
	public static class ClientChatThread extends Thread {
		
		private Scanner scanner;
		
		public ClientChatThread(Scanner scanner) {
			this.scanner = scanner;
		}
		
		// 이 쓰레드가 별도로 동작하면서 할 일
		public void run() {
			while (true) {
				System.out.println(scanner.nextLine());
			}
		}
		
	}
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		// 서버에 연결하는 소켓 생성
		Socket socket = new Socket("127.0.0.1", 10001);
		/*
		String[] clientInfo = new String[2];
		
		String ip = null;
		String sPort = null;
		int port = 0;
		if (args.length != 2){
			ip = args[0];
			sPort = args[1];
			port = Integer.parseInt(sPort);
		}
		
		Socket socket = new Socket(ip, port);
		*/
		// 위 소켓으로부터 메시지를 쓰고 읽을 수 있는 Writer 및 Reader 객체 생성
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//		BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		Scanner reader = new Scanner(socket.getInputStream());
		
		// 키보드 입력 받을 수 있는 Scanner 객체 생성
		Scanner scanner = new Scanner(System.in);
		
		// 아이디 입력
		System.out.print("아이디 입력: ");
		String id = scanner.nextLine();
		writer.write(id + "\n");
		writer.flush();
		
		// 메시지 읽는 쓰레드 생성 및 시작
		ClientChatThread t = new ClientChatThread(reader);
		t.start();
		
		while (true) {
			// 키보드로부터 서버에 보낼 메시지 입력 받음
//			System.out.print("보낼 메시지: ");
			String message = scanner.nextLine();
			
			// 반복문 종료 조건
			if (message.equals("/exit")) {
				writer.write(message + "\n");
				writer.flush();
				break;
			}
			
			// Writer 객체를 통해서 메시지를 보내고
			writer.write(message + "\n");
			writer.flush();
			// Reader 객체를 통해서 메시지를 받고
//			message = reader.nextLine();
//			System.out.println("받은 메시지: " + message);
		}
		
		// 종료
		socket.close();
	}
	
}




















