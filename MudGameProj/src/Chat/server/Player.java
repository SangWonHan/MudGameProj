package Chat.server;

public class Player extends Unit {
	int bombCount = 3;
	int potionCount = 3;
	public Player(){
		this.hp = 300;
		this.maxDamage = 40;
		this.minDamage = 30;
		
	}
	public void useItem(){
		//포션 사용
		for(int i = 0; i < ChatServer.players.size(); i++){
			Player p = ChatServer.players.get(i);
			p.hp += 100;
			p.potionCount = potionCount - 1;
		}
	}
	public void useSkill(){
		//스킬 사용
	}
	public void attack(){
		//일반 공격
		//플레이어가 30~40 사이의 랜덤 데미지를 받는 코드
		/*	for(int i = 0; i < ChatServer.players.size(); i++){ 
		Player p = ChatServer.players.get(i);
		
		//0~10 사이의 1개의 정수인 난수 발생
		Random r = new Random(System.currentTimeMillis());
		  for (int i = 0; i < 2; i++) {
		       // nextInt 리턴값은 0 <= val < 11 로 나옵니다.
		       System.out.println(r.nextInt(11)+1); 
		  }
		  
		p.hp -= 30 + r ;
		}*/
	}
	public void setName(String name){
		this.name = name;
	}
}
