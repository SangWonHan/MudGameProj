package Chat.server;

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
 								" : " + unit.name + " ���� ����ȸ�� ������ ���մϴ�."); 	
 						sleep(500);
 						unit.attack(p);
 						ChatServer.sendMessageToAll(unit.name + "���� �� Ż���̶�� ��Ĩ�ϴ�.");
 						
 						ChatServer.energyPrint(unit);
 					}
 						break;
 					case ChatServer.QUESTIONATTACK:
 					{
 						
 					}
 						break;
 					case ChatServer.LAZYATTACK:
 					{
 						
 					}
 						break;
 					case ChatServer.USEBOMB:
 					{
 						
 					}
 						break;
 					case ChatServer.USEPOTION:
 					{
 						
 					}
 						break;
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
}
