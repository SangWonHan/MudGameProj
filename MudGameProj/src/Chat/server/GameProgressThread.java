package Chat.server;

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
				ChatServer.sendMessageToAll("이11111습니다.");
				timeCount++;
			} catch (InterruptedException e) { 
				
			} finally {
				
			}			
		}
		
	}
	
	public void monsterMeetPoint() {
		System.out.println("");
	}
	
	public void writeStory() {
		
	}
	
	public void fightMonster() {
		
	}
	
	public void recurvery() {
		
	}
}
