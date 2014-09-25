package Chat.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import Chat.server.ChatServer;

public class ClientConnectionThread extends Thread {
	
	// 소켓 (이 클라이언트와의 메시지 통신을 담당하는)
	Socket socket;
	// 위 소켓을 통해 메시지를 보낼 수 있는 Writer
	PrintWriter writer;
	// 위 소켓을 통해 메시지를 받을 수 있는 Reader
	BufferedReader reader;
	// 이 클라이언트의 아이디
	String id;
	Player p = null;
	boolean start = false;
	// 생성자
	public ClientConnectionThread(Socket socket) throws IOException {
		this.socket = socket;
		p = new Player();
		// writer & reader 초기화
		writer = new PrintWriter(socket.getOutputStream());
		reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}
	
	// 이 클라이언트에게 메시지를 보내는 메서드
	public void sendMessage(String message) {
		writer.write(message + "\n");
		writer.flush();
	}
	
	// 이 쓰레드가 할 일
	public void run() {
		String line = null;
		try {
			// 첫 메시지를 아이디로 처리
			
			if(!start){
				boolean idCheck = false;
				while(!idCheck){
					id = reader.readLine();
					if(ChatServer.clients.size() > 0 && ChatServer.players.size() > 0){
						int check = 1;
						for(int i = 0 ; i< ChatServer.players.size();i++){
						String checkId = ChatServer.players.get(i).name;
						if(id.equals(checkId)){
							check = 2;
							sendMessage(checkId);
							sendMessage("아이디가 중복되었습니다. 다시 입력해주세요.");
							sendMessage("아이디 입력 : ");
							break;
						}
					}if(check !=2){
						ChatServer.players.add(p);
						p.setName(id);
						sendMessage(id + "캐릭터가 생성되었습니다.");
						ChatServer.sendMessageToAll(id   + " 님이 접속 하였습니다." );
						idCheck = true;
						break;
					}
				}else{
					ChatServer.players.add(p);
					p.setName(id);
					sendMessage(id + "캐릭터가 생성되었습니다.");
					ChatServer.sendMessageToAll(id   + " 님이 접속 하였습니다." );
					idCheck = true;
					break;
				}
				}
			}	
			// 모든 클라이언트에게 접속 메시지 송신
			ChatServer.sendMessageToAll(id + "님이 접속하였습니다.");
			// 클라이언트로부터 메시지를 수신
			while ((line = reader.readLine()) != null) {
				// 접속 종료 처리
				if (line.equals("/exit")) {
					break;
				}
			
				else if (line.equals("/start")) {
					if (ChatServer.start) {
						ChatServer.start = false;
						GameProgressThread gameThread = new GameProgressThread();
						gameThread.start();
					}
					else {
						System.out.println("이미 시작하였습니다.");
						ChatServer.sendMessageToAll("이미 게임을 시작하였습니다.");
					}
				}
				// 일반 메시지 처리
				else {
					ChatServer.sendMessageToAll(id + "님의 메시지: " + line);
				}
			}
			
		} catch (IOException e) {
//			e.printStackTrace();
			System.out.println("클라이언트와의 연결이 종료되었습니다.");
		} finally {
			try {
				socket.close();
			} catch (IOException e) {}
			// 서버가 갖고 있는 클라이언트 목록으로부터 이 클라이언트 제거
			ChatServer.removeClient(this);
			// 접속 종료 안내 메시지를 모든 클라이언트에게 전송
			ChatServer.sendMessageToAll(id + "님이 접속을 종료하였습니다.");
		}
	}
	
}

/*
else if (line.startsWith("/to ")) { //"/to"로 시작하는 메시지
	String[] wMessage = line.split(" ", 3); //"/to java hello, java~"
											//{"/to", "java", "hello, java~"
	//메시지가 syntax에 적합한지 체크
	if (wMessage.length == 3) {
		//대상 클라이언트
		ClientConnectionThread targetClient = null;
		
		//대상 아이디
		String targetId = wMessage[1];
		
		//대상 아이디가 존재하는지 체크
		synchronized (ChatServer.clients) {
			for (int i = 0; i < ChatServer.clients.size(); i++) {
				//ChatServer의 클라이언트 목록의 i번째 클라이언트
				ClientConnectionThread client = ChatServer.clients.get(i);
				//i번째 클라이언트의 id 속성값이 targetid 값과 같으면
				if (client.id.equals(targetId)); {
					//메시지를 보낼 대상 클라이언트로 임시 저장
					targetClient = client;
					// for 반복문을 빠져나감
					break;
				}
			}
		}
								
		//보낼 메시지
		String message = wMessage[2];
		//메시지 전송
		//대상 아이디를 갖는 클라이언트가 있다면
		if(targetClient != null) {
			//대상 클라이언트를 통해 메시지를 전송
			targetClient.sendMessage(id + "님의 귓속말 : " + message);
		}
	}
}
*/