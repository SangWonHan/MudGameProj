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
 					
 					System.out.println("클라이언트로부터 FightThread로 받은 메시지 : " + message);
 					
 					int num = Integer.parseInt(message);
 					
 					switch(num) {
 					case ChatServer.BOOKATTACK:
 					{
 						ChatServer.sendMessageToAll("과제를 회피합니다.");
 						
 						//플레이어를 얻어옴
 						ClientConnectionThread thread = ChatServer.clients.get(ChatServer.tern);
 						Player p = thread.p;
 						
 						ChatServer.sendMessageToAll(p.name +
 								" : " + unit.name + " : 과제따위!!!!!!!"); 						
 						p.attack(unit);
 						ChatServer.sendMessageToAll(p.name +
 								" : " + unit.name + " 에게 과제회피 공격을 가합니다."); 	
 						sleep(500);
 						unit.attack(p);
 						ChatServer.sendMessageToAll(unit.name + "님이 너 탈락이라고 외칩니다.");
 						
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
