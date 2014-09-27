package Chat.server;

public class FightThread extends Thread {

	@Override
	public void run() {
		String message = null;
 		
 		while(true) {
			try {
				message = ChatServer.fightQueues.take();
				System.out.println("클라이언트로부터 FightThread로 받은 메시지 : " + message);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
 		}
	}
	
}
