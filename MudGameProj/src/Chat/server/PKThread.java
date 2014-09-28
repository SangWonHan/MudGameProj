package Chat.server;

import java.io.IOException;

public class PKThread extends Thread {

	@Override
	public void run() {
		
		String message = null;
 		
 		while(true) {
 			
 			ChatServer.showMenu();
 			
			try {
				message = ChatServer.pkQueues.take();
				System.out.println("클라이언트로부터 PKThread로 받은 메시지 : " + message);
				
				//플레이어를 얻어옴
				ClientConnectionThread thread1 = ChatServer.clients.get(ChatServer.tern);
				Player p1 = thread1.p;
				
				ClientConnectionThread thread2 = ChatServer.clients.get(ChatServer.preTern);
				Player p2 = thread2.p;
				
				p1.attack(p2);
				
				ChatServer.ternChange();
				
				for (int i = 0; i < ChatServer.clients.size(); i++) {
		 			//플레이어를 얻어옴
					ClientConnectionThread allThread = ChatServer.clients.get(i);
					Player p = allThread.p;
					allThread.sendMessage(p.name + "의 HP : " + p.energy);
		 		}
				
				isGameOverPlayer();
				
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
 		}
	}

	public void isGameOverPlayer() {
		for (int i = 0; i < ChatServer.clients.size(); i++) {
			//플레이어를 얻어옴
				ClientConnectionThread thread = ChatServer.clients.get(ChatServer.tern);
				Player p = thread.p;
			if (p.alive == false) {
				ChatServer.sendMessageToAll(p.name + "님이 GameOver 되었습니다.");
				ChatServer.removeClient(thread);
				try {
					thread.socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
