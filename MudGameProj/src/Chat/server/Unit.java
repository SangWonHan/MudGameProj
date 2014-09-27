package Chat.server;

public class Unit {
	int energy;
	int hp;
	int minDamage;
	int maxDamage;
	String name;
	int damage; 
	
	public Unit() {
		
	}
	
	public void setName(String name) {
		this.name = name;		
	}
	
	public Unit(String name, int hp, int minDamage, int maxDamage) {
		this.name = name;
		this.hp = hp;
		this.minDamage = minDamage;
		this.maxDamage = maxDamage;		
	}
	
	public void attack(Unit unit) {
		unit.hp -= damage;
	}
}
