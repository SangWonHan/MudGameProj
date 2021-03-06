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
 	public void getInfo() { //상태 조회 메소드
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
				System.out.println("클라이언트로부터 받은 메시지 : " + message);
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
 		
 		ChatServer.sendMessageToAll("일반 전투가 시작되었습니다.");
 		ChatServer.stage = 1;
 		
 		while (timeCount < 50) {			 
 			 
 			try { 
 				Thread.sleep(500); 
 				System.out.println(timeCount); 
 				ChatServer.sendMessageToAll(timeCount + "만큼 이동했습니다."); 
 				ChatServer.sendMessageToAll("학원 수료 달성도 : " + timeCount);
 				
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
 						
 						ChatServer.sendMessageToAll("비트 학원의 몬스터 " + monster.name + "이 등장했다.");
 						 						
 						FightThread fT = new FightThread(monster);
 				 		fT.start();
 				 		
 				 		fT.join();
 						
 						System.out.println("i 값 : " + i); 
 						
 						i++; 
 						
 						for (int j = 0; j < ChatServer.clients.size(); j++) {
 							//플레이어를 얻어옴
 	 						ClientConnectionThread thread = ChatServer.clients.get(j);
 	 						Player p = thread.p;
 	 						
 	 						p.recervery(rec);
 	 						
 	 			
 						}
 						
 						ChatServer.sendMessageToAll("체력이 " + rec + "씩 회복되었습니다.");
 						
 						for (int j = 0; j < ChatServer.clients.size(); j++) {
 				 			//플레이어를 얻어옴
 							ClientConnectionThread thread = ChatServer.clients.get(j);
 							Player p = thread.p;
 							thread.sendMessage(p.name + "의 HP : " + p.energy);
 				 		}
 					}
 				}
 				
 				timeCount++; 
 				
 			} catch (InterruptedException e) {  
 				 
 			} finally { 
 				 
 			}
 			 			
 		} 
 		
 		if (ChatServer.clients.size() == 1) {
 			//플레이어를 얻어옴
			ClientConnectionThread thread = ChatServer.clients.get(0);
			Player p = thread.p;
			ChatServer.sendMessageToAll(p.name + "님이 최종 승리하였습니다. \n"
					+ "게임을 종료합니다.");
			
			ChatServer.clients.remove(0);
			try {
				thread.socket.close();
			} catch (IOException e) {
				//e.printStackTrace();
			}
			
 			return;
 		} else if (ChatServer.clients.size() == 0) {
 			ChatServer.sendMessageToAll("플레이어가 모두 전멸입니다. 게임을 종료합니다.");
 			return;
 		}

 		ChatServer.sendMessageToAll("모든 전투가 끝났습니다.");
 		
 		ChatServer.sendMessageToAll("PK가 시작되었습니다. 입장합니다.");
 		
 		ChatServer.stage = 2;
 		PKThread pkT1 = new PKThread();
 		if(ChatServer.clients.size() > 2){
	 		int scoreArray[] = new int[ChatServer.clients.size()+1]; 
	 		for(int j = 0 ; j < ChatServer.clients.size() ; j++){
	 			
	 			//플레이어를 얻어옴
				ClientConnectionThread thread = ChatServer.clients.get(j);
				Player scoreP = thread.p;
	 			
	 			scoreArray[j] = scoreP.scoreCalculator();
	 			ChatServer.sendMessageToAll(scoreP.name+"님의 점수 : "+ Integer.toString(scoreP.score));
	 			}	
	 			
 		}
/* 		
 		for (int j = 0; j < ChatServer.clients.size() / 2; j++) {
 			//플레이어를 얻어옴
			ClientConnectionThread thread1 = ChatServer.clients.get(j);
			ClientConnectionThread thread2 = ChatServer.clients.get(ChatServer.clients.size() - 1 - j);
			
			ClientConnectionThread obj = null;
			obj = thread1;
			thread1 = thread2;
			thread2 = obj;
 		}
 		
 		if (ChatServer.clients.size() > 2) {
	 		for (int j = ChatServer.clients.size() - 1; j >= 2; j--) {
	 			ChatServer.clients.get(j).sendMessage("당신은 탈락하였습니다.");
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
 				
 				//플레이어를 얻어옴
				ClientConnectionThread thread = ChatServer.clients.get(smallIdx);
 				
 				ChatServer.clients.get(smallIdx).sendMessage("당신은 탈락하였습니다.");
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
 		ChatServer.sendMessageToAll("모든 전투가 끝났습니다. 게임을 종료합니다.");
 		
 		//플레이어를 얻어옴
		ClientConnectionThread thread = ChatServer.clients.get(0);
		Player p = thread.p;
		ChatServer.sendMessageToAll(p.name + "님이 최종 승리하였습니다. \n"
				+ "게임을 종료합니다.");
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
 		ChatServer.sendMessageToAll("강남 한복판."); 
 		ChatServer.sendMessageToAll("혼잡한 시내를 뚫고 속속들이 용사들이 여기에 모이고 있다..."); 
 		ChatServer.sendMessageToAll("세계의 안녕★을 위해 뜻을 품은 자들이라면 그 누구라도 모여 그 뜻을 같이 하자는 방이 내걸렸기 때문이다."); 
 		ChatServer.sendMessageToAll("하지만 압박스러운 면접이 모여든 용사들을 기다리고 있었고"); 
 		ChatServer.sendMessageToAll("그들은 ~뜻밖의 만남~을 극복하고 새로운 세계와 조우했다."); 
 		ChatServer.sendMessageToAll("Hello World!"); 
 		ChatServer.sendMessageToAll("이제 개발자의 꿈을 갖고 있는 용사들이 스펙초월 멘토스쿨에 입학하였다. 그러나 개발자로 가는 길은 만만치 않은데...."); 
 	} 
 	/*
  	public void fightMonsterMessage() { 
	 	ChatServer.sendMessageToAll("자 이제 새로운 마음으로 시작해보자~~~~~"); 
	 	ChatServer.sendMessageToAll("슬슬 지쳐가기 시작한다..... 아직 많이 남았는데...."); 
	 	ChatServer.sendMessageToAll("이제 게시판 정도는 만들 수(?) 있다.. 하지만 여기서 끝이 아니었다...!"); 
	 	ChatServer.sendMessageToAll("이제 프로젝트 출품을 할 차례이다. 발표가 시작된다."); 
	 	ChatServer.sendMessageToAll("프로젝트 발표를 마치고 이제 각자의 길로...."); 
	 	ChatServer.sendMessageToAll("어두컴컴한 지하. 낡아보이는 의자와 책상들이 무질서하게 놓여있다. 환기되지 않는 끈적한 공기가 기분 나쁘다."); //지하1층 
	 	ChatServer.sendMessageToAll("집중적인 교육을 받을 수 있는곳. 레벨업이 빠를 것 같지만 누구도 졸음은 피할 수 없는듯 하다.");  // 3층 강의실 
	 	ChatServer.sendMessageToAll("넓은 원탁 주변으로 20명 정도 앉을 수 있는 장소. 처음 왔을 때의 그 긴장감이 살아나는 것 같다.");  // 원탁회의장, PK룸 
	 	ChatServer.sendMessageToAll("적 등장! Java가 나타났다. 객체를 이해한다면 어렵지 않게 물리칠 수 있을것이다."); 
	 	ChatServer.sendMessageToAll("적 등장! 수 많은 쿼리로 몸을 둘러싼 Oracle이 길을 막아섰다."); 
	 	ChatServer.sendMessageToAll("적 등장! Network의 등장. Socket부터 차례로 공략하자."); 
	 	ChatServer.sendMessageToAll("적 등장! JSP가 나타났다!! 앞에 버티고 서 있는 Script부터 무너뜨려야 할 듯 하다."); 
	 	ChatServer.sendMessageToAll("적 등장! XML가 나타났다!  Parsing으로 공략한다면 반드시 이길 수 있다."); 
	 	ChatServer.sendMessageToAll("적 등장! Ajax이다. 당황하지 말고...당황했다."); 
	 	ChatServer.sendMessageToAll("적 등장! Struts가 말했다. 너... 나 알지?"); 
	 	ChatServer.sendMessageToAll("적 등장! 우리에게도 봄은 오는가??? 오긴왔다 Spring이 나타났다"); 
	 	ChatServer.sendMessageToAll("적 등장! CHJ...잘 보이지 않던 최종보스가 나타났다! 여기까지 온 그 강인함과 지혜로 돌파해보자."); 
	 	ChatServer.sendMessageToAll("훌륭한 초보 개발자로 전직한 플레이어들. 하지만 세상은 1위만 기억하는법! 동료들과의 우열을 가려보자."); // 토너먼트, PK룸 
	 	ChatServer.sendMessageToAll("적 등장!일것같냐!!!아니거든?아니거든?적아니거든???"); 
 	} 
  	*/
 } 
