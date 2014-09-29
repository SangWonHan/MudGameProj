package Chat.server; 
 

import java.io.IOException;
import java.util.Arrays;
import java.util.Random; 
 

 public class GameProgressThread extends Thread { 
 	 
 	int distance[]; 
 	int appearMonster[]; 
 	 
 	public GameProgressThread() { 
 		distance = new int[100]; 
 		appearMonster = new int[3]; 
 	} 

 	
 	/*
 	public void getInfo() { //���� ��ȸ �޼ҵ�
 		Player[] p = new Player[ChatServer.players.size()];
		for(int i = 0; i < ChatServer.players.size(); i++) {
			p[i] = ChatServer.players.get(i);
			System.out.print(p[i].energy);
		}
 	}
 	*/
 	@Override 
 	public void run() { 
 		 
 		writeStory(); 
 		int timeCount = 0; 
 		int i = 0; 
 		/*
 		String message = null;
 		
 		while(true) {
			try {
				message = ChatServer.fightQueues.take();
				System.out.println("Ŭ���̾�Ʈ�κ��� ���� �޽��� : " + message);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
 		}
 		*/
 		
 //		monsterMeetPoint(); 
 		appearMonster[0] = 4;
 		appearMonster[1] = 19;
 		appearMonster[2] = 49;
 		
 		ChatServer.sendMessageToAll("�Ϲ� ������ ���۵Ǿ����ϴ�.");
 		ChatServer.stage = 1;
 		
 		while (timeCount < 50) {			 
 			 
 			try { 
 				Thread.sleep(500); 
 				System.out.println(timeCount); 
 				ChatServer.sendMessageToAll(timeCount + "��ŭ �̵��߽��ϴ�."); 
 				ChatServer.sendMessageToAll("�п� ���� �޼��� : " + timeCount);
 				
 				if (i < 3) { 
 					if ( timeCount == appearMonster[i]){ 
 						
 						Unit monster = null;
 						
 						int rec = 0;
 						if (i == 0) {
 							monster = new Unit("BanPW", 200, 30, 50);
 							rec = 60;
 						} else if (i == 1) {
 							monster = new Unit("LimJH", 300, 40, 60);
 							rec = 100;
 						} else if (i == 2) {
 							monster = new Unit("ChoHJ(BOSS)", 400, 50, 80);
 							rec = 140;
 						}
 						
 						ChatServer.sendMessageToAll("��Ʈ �п��� ���� " + monster.name + "�� �����ߴ�.");
 						 						
 						FightThread fT = new FightThread(monster);
 				 		fT.start();
 				 		
 				 		fT.join();
 						
 						System.out.println("i �� : " + i); 
 						
 						i++; 
 						
 						for (int j = 0; j < ChatServer.clients.size(); j++) {
 							//�÷��̾ ����
 	 						ClientConnectionThread thread = ChatServer.clients.get(j);
 	 						Player p = thread.p;
 	 						
 	 						p.recervery(rec);
 	 						
 	 			
 						}
 						
 						ChatServer.sendMessageToAll("ü���� " + rec + "�� ȸ���Ǿ����ϴ�.");
 						
 						for (int j = 0; j < ChatServer.clients.size(); j++) {
 				 			//�÷��̾ ����
 							ClientConnectionThread thread = ChatServer.clients.get(j);
 							Player p = thread.p;
 							thread.sendMessage(p.name + "�� HP : " + p.energy);
 				 		}
 					}
 				}
 				
 				timeCount++; 
 				
 			} catch (InterruptedException e) {  
 				 
 			} finally { 
 				 
 			}
 			 			
 		} 
 		
 		if (ChatServer.clients.size() == 1) {
 			//�÷��̾ ����
			ClientConnectionThread thread = ChatServer.clients.get(0);
			Player p = thread.p;
			ChatServer.sendMessageToAll(p.name + "���� ���� �¸��Ͽ����ϴ�. \n"
					+ "������ �����մϴ�.");
			
			ChatServer.clients.remove(0);
			try {
				thread.socket.close();
			} catch (IOException e) {
				//e.printStackTrace();
			}
			
 			return;
 		} else if (ChatServer.clients.size() == 0) {
 			ChatServer.sendMessageToAll("�÷��̾ ��� �����Դϴ�. ������ �����մϴ�.");
 			return;
 		}

 		ChatServer.sendMessageToAll("��� ������ �������ϴ�.");
 		
 		ChatServer.sendMessageToAll("PK�� ���۵Ǿ����ϴ�. �����մϴ�.");
 		
 		ChatServer.stage = 2;
 		PKThread pkT1 = new PKThread();
 		if(ChatServer.clients.size() > 2){
	 		int scoreArray[] = new int[ChatServer.clients.size()+1]; 
	 		for(int j = 0 ; j < ChatServer.clients.size() ; j++){
	 			
	 			//�÷��̾ ����
				ClientConnectionThread thread = ChatServer.clients.get(j);
				Player scoreP = thread.p;
	 			
	 			scoreArray[j] = scoreP.scoreCalculator();
	 			ChatServer.sendMessageToAll(scoreP.name+"���� ���� : "+ Integer.toString(scoreP.score));
	 			}	
	 			
 		}
/* 		
 		for (int j = 0; j < ChatServer.clients.size() / 2; j++) {
 			//�÷��̾ ����
			ClientConnectionThread thread1 = ChatServer.clients.get(j);
			ClientConnectionThread thread2 = ChatServer.clients.get(ChatServer.clients.size() - 1 - j);
			
			ClientConnectionThread obj = null;
			obj = thread1;
			thread1 = thread2;
			thread2 = obj;
 		}
 		
 		if (ChatServer.clients.size() > 2) {
	 		for (int j = ChatServer.clients.size() - 1; j >= 2; j--) {
	 			ChatServer.clients.get(j).sendMessage("����� Ż���Ͽ����ϴ�.");
	 			ChatServer.clients.remove(j);
	 			try {
					ChatServer.clients.get(j).socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	 		}
 		}
 */
 		if (ChatServer.clients.size() > 2) {
 			int delCount = ChatServer.clients.size() - 2;
 			for (int j = 0; j < delCount; j++) {
 				
 				int smallNum = 1000;
 				int smallIdx = 0;
 				
 				for (int k = 0; k < ChatServer.clients.size(); k++) {
 					if (ChatServer.clients.get(k).p.score < smallNum) {
 						smallNum = ChatServer.clients.get(k).p.score;
 						smallIdx = k;
 					}
 				}
 				
 				//�÷��̾ ����
				ClientConnectionThread thread = ChatServer.clients.get(smallIdx);
 				
 				ChatServer.clients.get(smallIdx).sendMessage("����� Ż���Ͽ����ϴ�.");
 				ChatServer.clients.remove(smallIdx);
 				try {
 					thread.socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
 			}
 		}
 		
 		
 		ChatServer.initPKTern();
 		
// 		if(ChatServer.clients.size() == 2){
// 			
 			pkT1.start();
 			try {
 				pkT1.join();
 			} catch (InterruptedException e) {
 				//e.printStackTrace();
 			}
// 		} 	
		
/*
 		
// 		ChatServer.tern = 0;
// 		ChatServer.preTern = 1;
 		
 		PKThread pkT = new PKThread();
 		pkT.start();
 		
 		try {
			pkT.join();
		} catch (InterruptedException e) {
			//e.printStackTrace();
		}
*/ 		
 		ChatServer.sendMessageToAll("��� ������ �������ϴ�. ������ �����մϴ�.");
 		
 		//�÷��̾ ����
		ClientConnectionThread thread = ChatServer.clients.get(0);
		Player p = thread.p;
		ChatServer.sendMessageToAll(p.name + "���� ���� �¸��Ͽ����ϴ�. \n"
				+ "������ �����մϴ�.");
 	}
 	 
 	public void monsterMeetPoint() { 
 	 
 		int ran =0; 
 		boolean cheak; 
 		Random r =new Random(); 
 		 
 		for (int i=0; i<appearMonster.length; i++) { 
 			ran =r.nextInt(100)+1; 
 			 
 			cheak = true; 
 			 
 			for (int j=0; j < i; j++){ 
 				if(appearMonster[j] ==ran){ 
 					i--; 
 					cheak=false; 
 				} 
 			} 
 			if(cheak) appearMonster[i] =ran; 
 		 
 			Arrays.sort(appearMonster); 
	 		for(int i1: appearMonster){ 
	 			System.out.println(i1+ " "); 
	 		} 
 		}
 		
 		for (int i = 0; i < appearMonster.length; i++) {
 			System.out.print(appearMonster[i] + ", ");
 		}
 		System.out.println();
 	}
 	 
 	public void writeStory() { 
 		ChatServer.sendMessageToAll("���� �Ѻ���."); 
 		ChatServer.sendMessageToAll("ȥ���� �ó��� �հ� �Ӽӵ��� ������ ���⿡ ���̰� �ִ�..."); 
 		ChatServer.sendMessageToAll("������ �ȳ���� ���� ���� ǰ�� �ڵ��̶�� �� ������ �� �� ���� ���� ���ڴ� ���� ���ɷȱ� �����̴�."); 
 		ChatServer.sendMessageToAll("������ �йڽ����� ������ �𿩵� ������ ��ٸ��� �־���"); 
 		ChatServer.sendMessageToAll("�׵��� ~����� ����~�� �غ��ϰ� ���ο� ����� �����ߴ�."); 
 		ChatServer.sendMessageToAll("Hello World!"); 
 		ChatServer.sendMessageToAll("���� �������� ���� ���� �ִ� ������ �����ʿ� ���佺�� �����Ͽ���. �׷��� �����ڷ� ���� ���� ����ġ ������...."); 
 	} 
 	/*
  	public void fightMonsterMessage() { 
	 	ChatServer.sendMessageToAll("�� ���� ���ο� �������� �����غ���~~~~~"); 
	 	ChatServer.sendMessageToAll("���� ���İ��� �����Ѵ�..... ���� ���� ���Ҵµ�...."); 
	 	ChatServer.sendMessageToAll("���� �Խ��� ������ ���� ��(?) �ִ�.. ������ ���⼭ ���� �ƴϾ���...!"); 
	 	ChatServer.sendMessageToAll("���� ������Ʈ ��ǰ�� �� �����̴�. ��ǥ�� ���۵ȴ�."); 
	 	ChatServer.sendMessageToAll("������Ʈ ��ǥ�� ��ġ�� ���� ������ ���...."); 
	 	ChatServer.sendMessageToAll("��������� ����. ���ƺ��̴� ���ڿ� å����� �������ϰ� �����ִ�. ȯ����� �ʴ� ������ ���Ⱑ ��� ���ڴ�."); //����1�� 
	 	ChatServer.sendMessageToAll("�������� ������ ���� �� �ִ°�. �������� ���� �� ������ ������ ������ ���� �� ���µ� �ϴ�.");  // 3�� ���ǽ� 
	 	ChatServer.sendMessageToAll("���� ��Ź �ֺ����� 20�� ���� ���� �� �ִ� ���. ó�� ���� ���� �� ���尨�� ��Ƴ��� �� ����.");  // ��Źȸ����, PK�� 
	 	ChatServer.sendMessageToAll("�� ����! Java�� ��Ÿ����. ��ü�� �����Ѵٸ� ����� �ʰ� ����ĥ �� �������̴�."); 
	 	ChatServer.sendMessageToAll("�� ����! �� ���� ������ ���� �ѷ��� Oracle�� ���� ���Ƽ���."); 
	 	ChatServer.sendMessageToAll("�� ����! Network�� ����. Socket���� ���ʷ� ��������."); 
	 	ChatServer.sendMessageToAll("�� ����! JSP�� ��Ÿ����!! �տ� ��Ƽ�� �� �ִ� Script���� ���ʶ߷��� �� �� �ϴ�."); 
	 	ChatServer.sendMessageToAll("�� ����! XML�� ��Ÿ����!  Parsing���� �����Ѵٸ� �ݵ�� �̱� �� �ִ�."); 
	 	ChatServer.sendMessageToAll("�� ����! Ajax�̴�. ��Ȳ���� ����...��Ȳ�ߴ�."); 
	 	ChatServer.sendMessageToAll("�� ����! Struts�� ���ߴ�. ��... �� ����?"); 
	 	ChatServer.sendMessageToAll("�� ����! �츮���Ե� ���� ���°�??? ����Դ� Spring�� ��Ÿ����"); 
	 	ChatServer.sendMessageToAll("�� ����! CHJ...�� ������ �ʴ� ���������� ��Ÿ����! ������� �� �� �����԰� ������ �����غ���."); 
	 	ChatServer.sendMessageToAll("�Ǹ��� �ʺ� �����ڷ� ������ �÷��̾��. ������ ������ 1���� ����ϴ¹�! �������� �쿭�� ��������."); // ��ʸ�Ʈ, PK�� 
	 	ChatServer.sendMessageToAll("�� ����!�ϰͰ���!!!�ƴϰŵ�?�ƴϰŵ�?���ƴϰŵ�???"); 
 	} 
  	*/
 } 
