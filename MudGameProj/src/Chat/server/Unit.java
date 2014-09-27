package Chat.server;

public class Unit {
	int hp;
	int minDamage;
	int maxDamage;
	String name;
	
	public Unit() {
		
	}
	
	public void attack(Unit unit){
		//일반 공격
	}
	
	public Unit(int hp, int minDamage, int maxDamage, String name) {
		this.hp = hp;
		this.minDamage = minDamage;
		this.maxDamage = maxDamage;
		this.name = name;
	}
}
