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
