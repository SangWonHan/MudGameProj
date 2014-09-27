package Chat.server;

import java.io.BufferedReader;
import java.util.Random;

import Chat.client.ChatClient;

public class GameProgressThread extends Thread {
	
	int distance[];
	int appearMonster[];
	
	private static final String MONSTER_KILLED = "몬스터를 처치하였습니다.";
	private static final String PLAYER_DIED = "에게 죽임 당하셨습니다.";
	
	public GameProgressThread() {
		distance = new int[100];
		appearMonster = new int[3];
	}

	@Override
	public void run() {
		
		writeStory();
		int timeCount = 0;
		int i = 0;

		monsterMeetPoint();
		
		Unit JSP = new Unit(100, 40, 60, "JSP");
		Unit Servlet = new Unit(100, 40, 60, "Servlet");
		Unit Java = new Unit(100, 40, 60, "Java");
		Player user = new Player();
		
		while (timeCount < 100) {			
			
			try {
				Thread.sleep(500);
				System.out.println(timeCount);
				ChatServer.sendMessageToAll(timeCount + "만큼 이동했습니다.");
				timeCount++;
				if (i < 3) {
					if ( timeCount == appearMonster[i]){
						
						ChatServer.sendMessageToAll(ChatServer.FIGHTSTART);
	//					ChatServer.sendMessageToAll(ChatServer.ATCMENU);
						System.out.println("i 값 : " + i);
						i++;
						
						int select = 0;
						
	/*					switch (select) {
						
						case 1 :
							//BOOKATTACK 사용
							user.attack(JSP);
							ChatServer.sendMessageToAll(
							break;
							
						case 2 :
							//QUESTIONATTACK 사용
							break;
							
						case 3 :
							//LAZYATTACK 사용
							
							break;
						
						case 4 :
							//USEBOMB 사용
							break;
							
						case 5 :
							//POTION 사용
							
							break;
							
						default :
							System.out.println("잘못 입력 되었습니다. 기본 공격을 진행합니다.");
							//case 1과 같이 진행
							break;
						
						}*/
					}
				}
			} catch (InterruptedException e) { 
				
			} finally {
				
			}			
		}
		
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
		}
		
		for(int i=0; i<appearMonster.length; i++);
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
	/**	public void fightMonsterMessage() {
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
