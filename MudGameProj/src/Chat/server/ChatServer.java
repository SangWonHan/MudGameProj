package Chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer {
	
	static ArrayList<ClientConnectionThread> clients
		= new ArrayList<ClientConnectionThread>();
	
	//��� Ŭ���̾�Ʈ���� �޽����� ������ �޼���
	public static void sendMessageToAll(String message) {
		synchronized (clients) {
			for (int i = 0; i < clients.size(); i++) {
				clients.get(i).sendMessage(message);
			}
		}
	}
	
	public static boolean start = true;
	
	//Ŭ���̾�Ʈ ������κ��� Ŭ���̾�Ʈ ����
	public static void removeClient(ClientConnectionThread client) {
		synchronized (clients) {
			clients.remove(client);
		}
	}
	
	public static void start() {
		
	}

	public static void writeStory() {
		
	}
	
	public static void fightMonster() {
		
	}
	
	public static void recurvery() {
		
	}
	
	public static void main(String[] args) throws IOException {
		
		// ���� ���� ����
		ServerSocket server = new ServerSocket(10001);
		Socket sock = null;
		System.out.println("���� ���� ���� �Ϸ�");
		
		while (true) {
			System.out.println("Ŭ���̾�Ʈ ���� �����...");
			// ���� �������� ������ ���� ��û�� �޾Ƶ鿩,
			// Ŭ���̾�Ʈ���� ������ ����ϴ� ������ ��
			if (start) {
				sock = server.accept();
			}
			else {
				System.out.println("����������");
			}
			
			System.out.println(sock.getInetAddress().getHostName() +
					"��(��) ���� �Ϸ�");
			
			// Ŭ���̾�Ʈ�� ����� �������κ��� �޽����� �Է� �ް�,
			// �Է¹��� �޽����� ���� ����� ��� Ŭ���̾�Ʈ���� �����ϴ�
			// �����带 �����ϰ� ����
			ClientConnectionThread connection = new ClientConnectionThread(sock);
			connection.start();
			
			synchronized (clients) {
				// ����� Ŭ���̾�Ʈ ������ ����
				ChatServer.clients.add(connection);
			}
		}
	}
	
}
