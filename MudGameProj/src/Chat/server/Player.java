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
	
	public Player(int hp, int maxDamage, int minDamage) {
		this.hp = hp;
		this.maxDamage = maxDamage;
		this.minDamage = minDamage;
	}
	
	public void useItem(){
		//���� ���
	}
	public void useSkill(){
		//��ų ���
	}
	public void attack(Unit unit){
		//�Ϲ� ����
	}
	public void setName(String name){
		this.name = name;
	}
}
