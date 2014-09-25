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
	}
	public void useSkill(){
		//스킬 사용
	}
	public void attack(){
		//일반 공격
	}
	public void setName(String name){
		this.name = name;
	}
}
