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

		//�÷��̾ ������ ���� ������
		//minDamage ~ maxDamage ������ 1���� ������ ���� �߻�
		Random damage = new Random(System.currentTimeMillis());
		  for (int i1 = minDamage; i1 < 2; i1++) {
	       // nextInt ���ϰ��� minDamage <= value < maxDamage �� ���ɴϴ�.
	       System.out.println("������: " + damage.nextInt(maxDamage)+1); 
		}
	
	}
	
	
	public void attack(Unit unit){
		//�Ϲ� ����
		
	}
	
	public Unit(String name, int hp, int minDamage, int maxDamage) {
		this.name = name;
		this.hp = hp;
		this.minDamage = minDamage;
		this.maxDamage = maxDamage;
		
	}
}
