package Chat.server;

import java.util.Random;

public class Unit {
	int hp;
	int minDamage;
	int maxDamage;
	String name;
	int damage; 
	
	public Unit() {
		
	}
	
	public int calcDamage() {
		//�������� ���Ѵ�.
		Random generator = new Random();
		
		int diff = maxDamage - minDamage;
		damage = generator.nextInt(diff + 1) + minDamage;
		
		return damage;
	}
	
	public void attack(Unit unit) {
		//�Ϲ� ����
		if (unit instanceof Unit) {
			unit.hp -= damage;
		}
	}
	
	public Unit(String name, int hp, int minDamage, int maxDamage) {
		this.name = name;
		this.hp = hp;
		this.minDamage = minDamage;
		this.maxDamage = maxDamage;
		
	}
}
