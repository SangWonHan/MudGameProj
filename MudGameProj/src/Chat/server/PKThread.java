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
				System.out.println("Ŭ���̾�Ʈ�κ��� PKThread�� ���� �޽��� : " + message);
				
				//�÷��̾ ����
				ClientConnectionThread thread1 = ChatServer.clients.get(ChatServer.tern);
				Player p1 = thread1.p;
				
				ClientConnectionThread thread2 = ChatServer.clients.get(ChatServer.preTern);
				Player p2 = thread2.p;
				
				p1.attack(p2);
				
				ChatServer.ternChange();
				
				for (int i = 0; i < ChatServer.clients.size(); i++) {
		 			//�÷��̾ ����
					ClientConnectionThread allThread = ChatServer.clients.get(i);
					Player p = allThread.p;
					allThread.sendMessage(p.name + "�� HP : " + p.energy);
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
			//�÷��̾ ����
				ClientConnectionThread thread = ChatServer.clients.get(ChatServer.tern);
				Player p = thread.p;
			if (p.alive == false) {
				ChatServer.sendMessageToAll(p.name + "���� GameOver �Ǿ����ϴ�.");
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
