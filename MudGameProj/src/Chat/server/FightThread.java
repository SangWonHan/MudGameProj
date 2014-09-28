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
 								"이 " + unit.name + " 에게 과제회피 공격을 가합니다."); 	
 						sleep(500);
 						unit.attack(p);
 						ChatServer.sendMessageToAll(unit.name + "님이 너 탈락이라고 외칩니다.");
 						
 						ChatServer.energyPrint(unit);
 						isGameOverPlayer();
 					}
 						break;
 					case ChatServer.QUESTIONATTACK:
 					{
 						ChatServer.sendMessageToAll("어려운 질문을 던졌습니다.");
 						
 						//플레이어를 얻어옴
 						ClientConnectionThread thread = ChatServer.clients.get(ChatServer.tern);
 						Player p = thread.p;
 						
 						ChatServer.sendMessageToAll(p.name +
 								" : " + unit.name + " : 일베 얘기는 언제까지 하실건가요?"); 						
 						p.attack(unit);
 						ChatServer.sendMessageToAll(p.name +
 								"님이 질문 공격을 " + unit.name + " 에게 가합니다."); 	
 						sleep(500);
 						unit.attack(p);
 						ChatServer.sendMessageToAll(unit.name + "님이 당황하였습니다. -_-; 님 잠깐 나 좀 봅시다.");
 						
 						ChatServer.energyPrint(unit);
 						isGameOverPlayer();
 					}
 						break;
 					case ChatServer.LAZYATTACK:
 					{
 						ChatServer.sendMessageToAll("플레이어가 농땡이를 부리기 시작합니다.");
 						
 						//플레이어를 얻어옴
 						ClientConnectionThread thread = ChatServer.clients.get(ChatServer.tern);
 						Player p = thread.p;
 						
 						ChatServer.sendMessageToAll(p.name +
 								" : " + unit.name + " : 아 하루 정도는 쉬어도 되겠지?"); 						
 						p.attack(unit);
 						ChatServer.sendMessageToAll(p.name +
 								"님이 " + unit.name + " 에게 못하겠다고 합니다."); 	
 						sleep(500);
 						unit.attack(p);
 						ChatServer.sendMessageToAll(unit.name + "님이 그래가지고 남보다 앞설 수 있겠어? 라고 외칩니다.");
 						
 						ChatServer.energyPrint(unit);
 						isGameOverPlayer();
 					}
 						break;
 					case ChatServer.USEBOMB:
 					{
 						//플레이어를 얻어옴
 						ClientConnectionThread thread = ChatServer.clients.get(ChatServer.tern);
 						
 						Player p = thread.p;
 						p.useBombMonster(unit);
 						thread.sendMessage(p.name + "님 폭탄 개수 : " + p.bombCount);
 					}
 						break;
 					case ChatServer.USEPOTION:
 					{
 						//플레이어를 얻어옴
 						ClientConnectionThread thread = ChatServer.clients.get(ChatServer.tern);
 						Player p = thread.p;
 						p.usePotion();
 						thread.sendMessage(p.name + "님 포션 개수 : " + p.potionCount);
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
