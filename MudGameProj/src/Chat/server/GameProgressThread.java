package Chat.server; 
 

 import java.io.BufferedReader; 
import java.util.Arrays;
import java.util.Random; 
 



import Chat.client.ChatClient; 
 

 public class GameProgressThread extends Thread { 
 	 
 	int distance[]; 
 	int appearMonster[]; 
 	 
 	private static final String MONSTER_KILLED = "���͸� óġ�Ͽ����ϴ�."; 
 	private static final String PLAYER_DIED = "���� ���� ���ϼ̽��ϴ�."; 
 	 
 	public GameProgressThread() { 
 		distance = new int[100]; 
 		appearMonster = new int[3]; 
 	} 
 

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
 		
 		monsterMeetPoint(); 
 		
 		ChatServer.sendMessageToAll("�Ϲ� ������ ���۵Ǿ����ϴ�.");
 		ChatServer.stage = 1;
 		 
 		while (timeCount < 100) {			 
 			 
 			try { 
 				Thread.sleep(500); 
 				System.out.println(timeCount); 
 				ChatServer.sendMessageToAll(timeCount + "��ŭ �̵��߽��ϴ�."); 
 				timeCount++; 
 				if (i < 3) { 
 					if ( timeCount == appearMonster[i]){ 
 						
 						ChatServer.sendMessageToAll(ChatServer.FIGHTSTART); 
 	//					ChatServer.sendMessageToAll(ChatServer.ATCMENU); 
 						System.out.println("i �� : " + i); 
 						i++; 
 					} 
 				} 
 			} catch (InterruptedException e) {  
 				 
 			} finally { 
 				 
 			}
 			
 			
 		} 
 		
 		ChatServer.sendMessageToAll("�Ϲ� ������ �������ϴ�. BOSS�� ������ ���ô�.");
 		
 		ChatServer.sendMessageToAll("PK�� ���۵Ǿ����ϴ�. �����մϴ�.");
 		
 		ChatServer.stage = 2;
 		
 		PKThread pkT = new PKThread();
 		pkT.start();
 	} 
 	 
 	public void monsterMeetPoint() { 
 	 
 		int ran =0; 
 		boolean cheak; 
 		Random r =new Random(); 
 		 
 		for (int i=0; i<appearMonster.length; i++){ 
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
 	/**	public void fightMonsterMessage() { 
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
 	 
 	 
 	public void fightMonster() { 
 		 
 	} 
 	 
 	public void recurvery() { 
 		for(int i = 0; i < ChatServer.players.size(); i++){ 
 			Player p = ChatServer.players.get(i); 
 			p.hp += 100; 
 			p.potionCount += 3; 
 		} 
 	} 
 } 
