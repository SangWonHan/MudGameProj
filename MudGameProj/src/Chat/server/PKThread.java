package Chat.server;

public class PKThread extends Thread {

	@Override
	public void run() {
		
		String message = null;
 		
 		while(true) {
			try {
				message = ChatServer.pkQueues.take();
				System.out.println("Ŭ���̾�Ʈ�κ��� PKThread�� ���� �޽��� : " + message);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
 		}
	}

}
