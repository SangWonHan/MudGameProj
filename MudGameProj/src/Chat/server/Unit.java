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
	
	public void attackFunction() {

		//플레이어가 공격할 때의 데미지
		//minDamage ~ maxDamage 사이의 1개의 정수인 난수 발생
		Random damage = new Random(System.currentTimeMillis());
		  for (int i1 = minDamage; i1 < 2; i1++) {
	       // nextInt 리턴값은 minDamage <= value < maxDamage 로 나옵니다.
	       System.out.println("데미지: " + damage.nextInt(maxDamage)+1); 
		}
	
	}
	
	
	public void attack(Unit unit){
		//일반 공격
		
	}
	
	public Unit(String name, int hp, int minDamage, int maxDamage) {
		this.name = name;
		this.hp = hp;
		this.minDamage = minDamage;
		this.maxDamage = maxDamage;
		
	}
}
