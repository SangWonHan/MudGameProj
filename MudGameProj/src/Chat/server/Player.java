package Chat.server;

public class Player extends Unit {
	int bombCount;
	int potionCount;
	
	public Player(){
		bombCount = 3;
		potionCount = 3;
		
		this.hp = 300;
		this.maxDamage = 40;
		this.minDamage = 30;
	}
	
	public Player(String name, int hp, int minDamage, int maxDamage) {
		super(name, hp, minDamage, maxDamage);
	}
	
	public void useItem(){
		//포션 사용
		
	}
	public void useSkill(){
		//스킬 사용
	}
	public void attack(Unit unit){
		//일반 공격
	}
	public void setName(String name){
		this.name = name;
	}
}
