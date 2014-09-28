package Chat.server;

import java.io.IOException;

public class FightThread extends Thread {

	Unit unit;
	
	public FightThread() {
		// TODO Auto-generated constructor stub
	}
	
	public FightThread(Unit unit) {
		this.unit = unit;
	}
	
	@Override
	public void run() {
		String message = null;
 		
 		while(true) {
// 			synchronized (ChatServer.clients) {
 			
 				ChatServer.showMenu();
 			
 				try { 					
 					message = ChatServer.fightQueues.take();
 					
 					System.out.println("Ŭ���̾�Ʈ�κ��� FightThread�� ���� �޽��� : " + message);
 					
 					int num = Integer.parseInt(message);
 					
 					switch(num) {
 					case ChatServer.BOOKATTACK:
 					{
 						ChatServer.sendMessageToAll("������ ȸ���մϴ�.");
 						
 						//�÷��̾ ����
 						ClientConnectionThread thread = ChatServer.clients.get(ChatServer.tern);
 						Player p = thread.p;
 						
 						ChatServer.sendMessageToAll(p.name +
 								" : " + unit.name + " : ��������!!!!!!!"); 						
 						p.attack(unit);
 						ChatServer.sendMessageToAll(p.name +
 								"�� " + unit.name + " ���� ����ȸ�� ������ ���մϴ�."); 	
 						sleep(500);
 						unit.attack(p);
 						ChatServer.sendMessageToAll(unit.name + "���� �� Ż���̶�� ��Ĩ�ϴ�.");
 						
 						ChatServer.energyPrint(unit);
 						isGameOverPlayer();
 					}
 						break;
 					case ChatServer.QUESTIONATTACK:
 					{
 						ChatServer.sendMessageToAll("����� ������ �������ϴ�.");
 						
 						//�÷��̾ ����
 						ClientConnectionThread thread = ChatServer.clients.get(ChatServer.tern);
 						Player p = thread.p;
 						
 						ChatServer.sendMessageToAll(p.name +
 								" : " + unit.name + " : �Ϻ� ���� �������� �Ͻǰǰ���?"); 						
 						p.attack(unit);
 						ChatServer.sendMessageToAll(p.name +
 								"���� ���� ������ " + unit.name + " ���� ���մϴ�."); 	
 						sleep(500);
 						unit.attack(p);
 						ChatServer.sendMessageToAll(unit.name + "���� ��Ȳ�Ͽ����ϴ�. -_-; �� ��� �� �� ���ô�.");
 						
 						ChatServer.energyPrint(unit);
 						isGameOverPlayer();
 					}
 						break;
 					case ChatServer.LAZYATTACK:
 					{
 						ChatServer.sendMessageToAll("�÷��̾ ���̸� �θ��� �����մϴ�.");
 						
 						//�÷��̾ ����
 						ClientConnectionThread thread = ChatServer.clients.get(ChatServer.tern);
 						Player p = thread.p;
 						
 						ChatServer.sendMessageToAll(p.name +
 								" : " + unit.name + " : �� �Ϸ� ������ ��� �ǰ���?"); 						
 						p.attack(unit);
 						ChatServer.sendMessageToAll(p.name +
 								"���� " + unit.name + " ���� ���ϰڴٰ� �մϴ�."); 	
 						sleep(500);
 						unit.attack(p);
 						ChatServer.sendMessageToAll(unit.name + "���� �׷������� ������ �ռ� �� �ְھ�? ��� ��Ĩ�ϴ�.");
 						
 						ChatServer.energyPrint(unit);
 						isGameOverPlayer();
 					}
 						break;
 					case ChatServer.USEBOMB:
 					{
 						//�÷��̾ ����
 						ClientConnectionThread thread = ChatServer.clients.get(ChatServer.tern);
 						
 						Player p = thread.p;
 						p.useBombMonster(unit);
 						thread.sendMessage(p.name + "�� ��ź ���� : " + p.bombCount);
 					}
 						break;
 					case ChatServer.USEPOTION:
 					{
 						//�÷��̾ ����
 						ClientConnectionThread thread = ChatServer.clients.get(ChatServer.tern);
 						Player p = thread.p;
 						p.usePotion();
 						thread.sendMessage(p.name + "�� ���� ���� : " + p.potionCount);
 					}
 						continue;
 					}
 										
					ChatServer.ternChange();
					
					if (unit.energy <= 0) {
						break;
					}
					
					
 					
 				} catch (InterruptedException e1) {
 					// TODO Auto-generated catch block
 					e1.printStackTrace();
 				}
//			}			
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
