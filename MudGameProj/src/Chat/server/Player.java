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
	
	public void useItem() {
		
	}
	
	public void useBomb(Unit unit) {
		//��ź ���
		ChatServer.sendMessageToAll(name + "���� ��ź�� ����Ͽ����ϴ�.");
	}
	
	public void useSkill(int skill) {
		//��ų ���
		switch (skill) {
		case 1:
			
			break;
			
		case 2:
			
			break;
			
		case 3:
			
			break;
		case 4:
			
			break;
			
		case 5:
			
			break;
		}
	}
	public void attack(Unit unit) {
		//�Ϲ� ����
		if (unit instanceof Player) {
			unit.hp -= damage;
		}
	}
	public void setName(String name) {
		this.name = name;
		
	}
}
