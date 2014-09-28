package Chat.server;

import java.io.IOException;

public class PKThread extends Thread {

	@Override
	public void run() {
		
		String message = null;
 		
 		while(true) {
 			
 			ChatServer.pkShowMenu();
 			
			try {
				message = ChatServer.pkQueues.take();
				System.out.println("Ŭ���̾�Ʈ�κ��� PKThread�� ���� �޽��� : " + message);
				
				int num = Integer.parseInt(message);
				
				//�÷��̾ ����
				ClientConnectionThread thread1 = ChatServer.clients.get(ChatServer.tern);
				Player p1 = thread1.p;
				
				ClientConnectionThread thread2 = ChatServer.clients.get(ChatServer.preTern);
				Player p2 = thread2.p;
				
				switch (num) {
				case 1:	
				case 2:
				case 3:
					p1.attack(p2);
					
					ChatServer.sendMessageToAll(p1.name + "���� " + p2.name + "���� �����Ͽ����ϴ�.");
					ChatServer.sendMessageToAll(p1.name + "���� HP : " + p1.energy);
					ChatServer.sendMessageToAll(p2.name + "���� HP : " + p2.energy);
					
					break;

				case 4:
					p1.useBombPlayer(p2);
					
					ChatServer.sendMessageToAll(p1.name + "���� " + p2.name + "���� ��ź �����Ͽ����ϴ�.");
					
					thread1.sendMessage(p1.name + "�� ��ź ���� : " + p1.bombCount);
					
					break;
					
				case 5:
					p1.usePotion();
					
					thread1.sendMessage(p1.name + "�� ���� ���� : " + p1.potionCount);
					continue;
					
				default:
					break;
				}
				
				
				ChatServer.ternChange();
				
				for (int i = 0; i < ChatServer.clients.size(); i++) {
		 			//�÷��̾ ����
					ClientConnectionThread allThread = ChatServer.clients.get(i);
					Player p = allThread.p;
					allThread.sendMessage(p.name + "�� HP : " + p.energy);
		 		}
				
				if(!isGameOverPlayer()) {
					break;
				}
				
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
 		}
	}

	public boolean isGameOverPlayer() {
		
		boolean gaming = true;
		
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
				
				gaming = false;
			}
		}
		
		return gaming;
	}
	
}
