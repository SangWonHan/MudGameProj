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
				ChatServer.sendMessageToAll("이11111습니다.");
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
		
	}
	
	public void fightMonster() {
		
	}
	
	public void recurvery() {
		
	}
}
