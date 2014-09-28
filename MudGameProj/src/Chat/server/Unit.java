package Chat.server;

import java.util.Random;

public class Unit {
	int energy;
	protected int hp;
	int minDamage;
	int maxDamage;
	String name;
	int damage; 
	boolean alive;
	
	public Unit() {
		
	}
	
	public Unit(String name, int hp, int minDamage, int maxDamage) {
		this.name = name;
		this.hp = hp;
		this.minDamage = minDamage;
		this.maxDamage = maxDamage;		
		energy = hp;
	}
	
	protected void calcDamage() {
		//�������� ���Ѵ�.
		Random generator = new Random();
		
		int diff = maxDamage - minDamage;
		
		damage = generator.nextInt(diff + 1) + minDamage;
	}
	
	public void setName(String name) {
		this.name = name;		
	}
	
	public void attack(Unit unit) {		
		
		calcDamage();
		
		System.out.println(name + " �� ���� ������ : " + damage);
		
		unit.energy -= damage;
				
	}
}
