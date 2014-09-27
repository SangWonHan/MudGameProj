package Chat.server;

import java.util.Random;

public class GameProgressThread extends Thread {
	
	int distance[];
	int monster[];
	
	public GameProgressThread() {
		distance = new int[100];
		monster = new int[3];
	}

	@Override
	public void run() {
		
		writeStory();
		int timeCount = 0;
		
		while (timeCount < 100) {			
			
			try {
				Thread.sleep(500);
				System.out.println(timeCount);
				ChatServer.sendMessageToAll("��11111���ϴ�.");
				timeCount++;
			} catch (InterruptedException e) { 
				
			} finally {
				
			}			
		}
		
	}
	
	public void monsterMeetPoint() {
	
		int ran =0;
		boolean cheak;
		Random r =new Random();
		
		for (int i=0; i<monster.length; i++){
			ran =r.nextInt(100)+1;
			
			cheak = true;
			
			for (int j=0; j < i; j++){
				if(monster[j] ==ran){
					i--;
					cheak=false;
				}
			}
			if(cheak) monster[i] =ran;				
		}
		
		for(int i=0; i<monster.length; i++);
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
