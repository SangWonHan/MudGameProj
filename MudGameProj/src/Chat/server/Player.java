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
		//폭탄 사용
		ChatServer.sendMessageToAll(name + "님이 폭탄을 사용하였습니다.");
	}
	
	public void useSkill(int skill) {
		//스킬 사용
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
		//일반 공격
		if (unit instanceof Player) {
			unit.hp -= damage;
		}
	}
	public void setName(String name) {
		this.name = name;
		
	}
}
