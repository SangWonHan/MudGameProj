package Chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import Chat.server.ClientConnectionThread;
import Chat.server.Player;

public class ChatServer {
	
	static ArrayList<ClientConnectionThread> clients 
	= new ArrayList<ClientConnectionThread>(); //플레이어 정보를 담는 리스트
	
	static ArrayList<Player> players = new ArrayList<Player>();
	static int stage = 0;
	static BlockingQueue<String> fightQueues = new ArrayBlockingQueue<String>(50);
	static BlockingQueue<String> pkQueues = new ArrayBlockingQueue<String>(50);
	static int tern = 0;
	static int preTern = 0;
	public static boolean start = true;
	
	final static int BOOKATTACK = 1;
	final static int QUESTIONATTACK = 2;
	final static int LAZYATTACK = 3;
	//final static int AVOID = 4;
	final static int USEBOMB = 4;
	final static int USEPOTION = 5;
	final static String FIGHTSTART = "길을 가는 중 몬스터를 만났습니다." + ""
			+ "싸움이 시작되었습니다.\n";
	final static String ATCMENU = "공격 메뉴를 선택해 주세요. ex) 1번 사용 /c 1 \n" 
			+"1.과제회피 공격  2. 질문공세 공격  3. 농땡이 공격  4. 폭탄사용  5. 포션사용";

	//플레이어의 턴을 바꿔준다.
	public static void ternChange() {
		
		preTern = tern;
		
		System.out.println(tern + "차례 입니다.");
		
		tern++;
		
		if (tern >= clients.size()) {
			tern = 0;
		}
		
		System.out.println(tern + "차례 입니다.(턴바뀜)");
		
	}
	
	//모든 클라이언트에게 메시지를 보내는 메서드
	public static void sendMessageToAll(String message) {
		synchronized (clients) {
			for (int i = 0; i < clients.size(); i++) {
				clients.get(i).sendMessage(message);
			}
		}
	}
	
	//클라이언트 목록으로부터 클라이언트 삭제
	public static void removeClient(ClientConnectionThread client) {
		synchronized (clients) {
			clients.remove(client);
		}
	}
 	
 	public static void showMenu() {
 		sendMessageToAll((clients.get(ChatServer.tern)).p.name + 
				" 차례입니다. 공격 메뉴를 선택해 주세요. "
				+ "ex) 1번 사용 /c 1 \n" 
				+ "1.과제회피 공격  2. 질문공세 공격  3. 농땡이 공격  4. 폭탄사용  5. 포션사용");
 	}
 	
 	public static void pkShowMenu() {
 		sendMessageToAll((clients.get(ChatServer.tern)).p.name + 
				" 차례입니다. 공격 메뉴를 선택해 주세요. "
				+ "ex) 1번 사용 /c 1 \n" 
				+ "1 or 2 or 3.일반 공격  4. 폭탄사용  5. 포션사용");
 	}
 	
 	public static void energyPrint(Unit u) {
 		
 		sendMessageToAll(u.name + "의 HP : " + u.energy);
 		
 		for (int i = 0; i < clients.size(); i++) {
 			//플레이어를 얻어옴
			ClientConnectionThread thread = clients.get(i);
			Player p = thread.p;
			thread.sendMessage(p.name + "의 HP : " + p.energy);
 		}
 	}
	
	public static void main(String[] args) throws IOException {
		
		// 서버 소켓 생성
		ServerSocket server = new ServerSocket(10001);
		Socket sock = null;
		System.out.println("서버 소켓 생성 완료");
		
		while (true) {
			System.out.println("클라이언트 연결 대기중...");
			// 서버 소켓으로 들어오는 연결 요청을 받아들여,
			// 클라이언트와의 연결을 담당하는 소켓을 얻어냄
			if (start) {
				sock = server.accept();
			}
			else {
				System.out.println("접속차단함");
			}
			
			System.out.println(sock.getInetAddress().getHostName() +
					"와(과) 연결 완료");
			
			// 클라이언트와 연결된 소켓으로부터 메시지를 입력 받고,
			// 입력받은 메시지를 현재 연결된 모든 클라이언트에게 전달하는
			// 쓰레드를 생성하고 시작
			ClientConnectionThread connection = new ClientConnectionThread(sock);
			connection.start();
			
			synchronized (clients) {
				// 연결된 클라이언트 정보를 저장
				ChatServer.clients.add(connection);
			}
		}
	}
	
}
