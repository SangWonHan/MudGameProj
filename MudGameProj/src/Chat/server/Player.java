package Chat.server;

public class Player extends Unit {
	int bombCount = 3;
	int potionCount = 3;
	public Player(){
		this.hp = 300;
		this.maxDamage = 40;
		this.minDamage = 30;
		
	}
	public void useItem(){
		//���� ���
	}
	public void useSkill(){
		//��ų ���
	}
	public void attack(){
		//�Ϲ� ����
	}
	public void setName(String name){
		this.name = name;
	}
}
