package Chat.server;

public class FightThread extends Thread {

	@Override
	public void run() {
		String message = null;
 		
 		while(true) {
			try {
				message = ChatServer.fightQueues.take();
				System.out.println("Ŭ���̾�Ʈ�κ��� FightThread�� ���� �޽��� : " + message);
				
				//�� �κп� ���Ϳ� ������ �ο�� �κ��� ������ �ֽñ� �ٶ��ϴ�.
				
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
 		}
	}
	
}
