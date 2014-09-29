package Chat.server;

public class Player extends Unit {
	int bombCount;
	int potionCount;
	int bombDamage;
	int potionRecovery;
	int score;
	int damageScore;
	public Player() {
		super("", 300, 30, 40);
		
		bombCount = 2;
		potionCount = 2;
				
//		hp = 300;
//		energy = hp;
//		maxDamage = 40;
//		minDamage = 30;
		bombDamage = 100;
		potionRecovery = 100;
		damageScore = 0;
	}
	
	public Player(String name, int hp, int minDamage, int maxDamage) {
		super(name, hp, minDamage, maxDamage);
		
		bombCount = 2;
		potionCount = 2;
		bombDamage = 100;
		potionRecovery = 100;
		damageScore = 0;
	}
	public int scoreCalculator(){
			Player p = this;
			p.score = this.bombCount * 100 + this.energy + this.potionCount * 130 + this.damageScore;
			return p.score;
	}
	public void useBombPlayer(Player p) {
		if (bombCount <= 0) {
			ChatServer.sendMessageToAll(potionCount + "´Ô!!! ÆøÅº ¾ø¾î¿ä");
			return;
		}
		
		//ÆøÅº »ç¿ë
		ChatServer.sendMessageToAll(name + "´ÔÀÌ " + p.name + "¿¡°Ô ÆøÅºÀ» »ç¿ëÇÏ¿´½À´Ï´Ù.");
		
		bombCount--;
		
		p.energy -= 100;
		energy += 50;
		
		if (energy >= hp) {
			energy = hp;
		}
	}

	public void useBombMonster(Unit u) {
		if (bombCount <= 0) {
			ChatServer.sendMessageToAll(potionCount + "´Ô!!! ÆøÅº ¾ø¾î¿ä");
			return;
		}
		
		//ÆøÅº »ç¿ë
		ChatServer.sendMessageToAll(name + "´ÔÀÌ ÆøÅºÀ» »ç¿ëÇÏ¿´½À´Ï´Ù.");
		
		bombCount--;
		u.energy -= 200;
		energy += 100;
		
		if (energy >= hp) {
			energy = hp;
		}
	}
	
	public void usePotion() {
		if (potionCount <= 0) {
			ChatServer.sendMessageToAll(name + "´Ô!!! Æ÷¼Ç ¾ø¾î¿ä");
			return;
		}
		//Æ÷¼Ç »ç¿ë
		ChatServer.sendMessageToAll(name + "´ÔÀÌ Æ÷¼ÇÀ» »ç¿ëÇÏ¿´½À´Ï´Ù.");
		potionCount--;
		energy += 130;
		if (energy >= hp) {
			energy = hp;
		}
	}
	
	public void recervery(int rec) {
		energy += rec;
		if (energy >= hp) {
			energy = hp;
		}
	}

	@Override
	public void attack(Unit unit) {
		super.attack(unit);
		
		damageScore += damage;
	}
}
