package Chat.server;

public class Player extends Unit {
	int bombCount;
	int potionCount;
	int bombDamage;
	int potionRecovery;
	
	public Player() {
		bombCount = 3;
		potionCount = 3;
		
		hp = 300;
		energy = hp;
		maxDamage = 40;
		minDamage = 30;
		bombDamage = 100;
		potionRecovery = 100;
	}
	
	public Player(String name, int hp, int minDamage, int maxDamage) {
		super(name, hp, minDamage, maxDamage);
		
		bombCount = 3;
		potionCount = 3;
		bombDamage = 100;
		potionRecovery = 100;
		energy = hp;
	}

	public void useBomb(Unit unit) {
		//��ź ���
		ChatServer.sendMessageToAll(name + "���� " + unit.name + "���� ��ź�� ����Ͽ����ϴ�.");
		unit.energy -= damage;
		if (energy >= 0) {
			System.out.println(unit + " �� �׾����ϴ�.");
		}
	}
	
	public void usePotion() {
		//���� ���
		ChatServer.sendMessageToAll(name + "���� ������ ����Ͽ����ϴ�.");
		energy += potionRecovery;
		if (energy >= hp) {
			energy = hp;
		}
	}
}
