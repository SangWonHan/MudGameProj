package Chat.server;

public class Player extends Unit {
	int bombCount;
	int potionCount;
	int bombDamage;
	int potionRecovery;
	
	public Player() {
		super("", 300, 30, 40);
		
		bombCount = 3;
		potionCount = 3;
				
//		hp = 300;
//		energy = hp;
//		maxDamage = 40;
//		minDamage = 30;
		bombDamage = 100;
		potionRecovery = 100;
	}
	
	public Player(String name, int hp, int minDamage, int maxDamage) {
		super(name, hp, minDamage, maxDamage);
		
		bombCount = 2;
		potionCount = 2;
		bombDamage = 100;
		potionRecovery = 100;
	}
	
	public void useBombPlayer(Player p) {
		if (bombCount <= 0) {
			ChatServer.sendMessageToAll(potionCount + "��!!! ��ź �����");
		}
		
		//��ź ���
		ChatServer.sendMessageToAll(name + "���� " + p.name + "���� ��ź�� ����Ͽ����ϴ�.");
		
		bombCount--;
		
		p.energy -= damage;
		energy += 50;
	}

	public void useBombMonster() {
		if (bombCount <= 0) {
			ChatServer.sendMessageToAll(potionCount + "��!!! ��ź �����");
		}
		
		//��ź ���
		ChatServer.sendMessageToAll(name + "���� ��ź�� ����Ͽ����ϴ�.");
		
		bombCount--;
		energy += 100;
		
		if (energy >= hp) {
			energy = hp;
		}
	}
	
	public void usePotion() {
		if (potionCount <= 0) {
			ChatServer.sendMessageToAll(potionCount + "��!!! ���� �����");
		}
		//���� ���
		ChatServer.sendMessageToAll(name + "���� ������ ����Ͽ����ϴ�.");
		potionCount--;
		energy += 100;
		if (energy >= hp) {
			energy = hp;
		}
	}
}
