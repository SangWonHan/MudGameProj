package Chat.client;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ChatClient {
	
	// �޽����� �о ȭ�鿡 ����ϴ� ������ Ŭ����
	public static class ClientChatThread extends Thread {
		
		private Scanner scanner;
		
		public ClientChatThread(Scanner scanner) {
			this.scanner = scanner;
		}
		
		// �� �����尡 ������ �����ϸ鼭 �� ��
		public void run() {
			while (true) {
				System.out.println(scanner.nextLine());
			}
		}
		
	}
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		// ������ �����ϴ� ���� ����
//		Socket socket = new Socket("192.168.0.105", 10001);
		Socket socket = new Socket("127.0.0.1", 10001);
		
		// �� �������κ��� �޽����� ���� ���� �� �ִ� Writer �� Reader ��ü ����
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//		BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		Scanner reader = new Scanner(socket.getInputStream());
		
		// Ű���� �Է� ���� �� �ִ� Scanner ��ü ����
		Scanner scanner = new Scanner(System.in);
		
		// ���̵� �Է�
		System.out.print("���̵� �Է�: ");
		String id = scanner.nextLine();
		writer.write(id + "\n");
		writer.flush();
		
		// �޽��� �д� ������ ���� �� ����
		ClientChatThread t = new ClientChatThread(reader);
		t.start();
		
		while (true) {
			// Ű����κ��� ������ ���� �޽��� �Է� ����
//			System.out.print("���� �޽���: ");
			String message = scanner.nextLine();
			
			// �ݺ��� ���� ����
			if (message.equals("/exit")) {
				writer.write(message + "\n");
				writer.flush();
				break;
			}
			
			// Writer ��ü�� ���ؼ� �޽����� ������
			writer.write(message + "\n");
			writer.flush();
			// Reader ��ü�� ���ؼ� �޽����� �ް�
//			message = reader.nextLine();
//			System.out.println("���� �޽���: " + message);
		}
		
		// ����
		socket.close();
	}
	
}




















