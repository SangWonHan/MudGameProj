package Test;

import java.util.Random;

public class TestCode {

	public static int RandomDamage() {
		
		int damage = 0;
		int minDamage = 26;
		int maxDamage = 40;
		
		//�������� ���Ѵ�.
		Random generator = new Random();
		
		int diff = maxDamage - minDamage;
		damage = generator.nextInt(diff + 1) + minDamage;
		
		return damage;
	}
	
	public static void main(String[] args) {
		int a = RandomDamage();
		System.out.println(a);
	}
	
}
