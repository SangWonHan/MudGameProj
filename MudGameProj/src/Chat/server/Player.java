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
		for(int i = 0; i < ChatServer.players.size(); i++){
			Player p = ChatServer.players.get(i);
			p.hp += 100;
			p.potionCount = potionCount - 1;
		}
	}
	public void useSkill(){
		//��ų ���
	}
	public void attack(){
		//�Ϲ� ����
		//�÷��̾ 30~40 ������ ���� �������� �޴� �ڵ�
		/*	for(int i = 0; i < ChatServer.players.size(); i++){ 
		Player p = ChatServer.players.get(i);
		
		//0~10 ������ 1���� ������ ���� �߻�
		Random r = new Random(System.currentTimeMillis());
		  for (int i = 0; i < 2; i++) {
		       // nextInt ���ϰ��� 0 <= val < 11 �� ���ɴϴ�.
		       System.out.println(r.nextInt(11)+1); 
		  }
		  
		p.hp -= 30 + r ;
		}*/
	}
	public void setName(String name){
		this.name = name;
	}
}
