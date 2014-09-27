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
		//폭탄 사용
		ChatServer.sendMessageToAll(name + "님이 " + unit.name + "에게 폭탄을 사용하였습니다.");
		unit.energy -= damage;
		if (energy >= 0) {
			System.out.println(unit + " 이 죽었습니다.");
		}
	}
	
	public void usePotion() {
		//포션 사용
		ChatServer.sendMessageToAll(name + "님이 포션을 사용하였습니다.");
		energy += potionRecovery;
		if (energy >= hp) {
			energy = hp;
		}
	}
}
