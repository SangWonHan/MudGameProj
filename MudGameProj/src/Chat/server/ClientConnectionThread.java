package Chat.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import Chat.server.ChatServer;

public class ClientConnectionThread extends Thread {
	
	// ���� (�� Ŭ���̾�Ʈ���� �޽��� ����� ����ϴ�)
	Socket socket;
	// �� ������ ���� �޽����� ���� �� �ִ� Writer
	PrintWriter writer;
	// �� ������ ���� �޽����� ���� �� �ִ� Reader
	BufferedReader reader;
	// �� Ŭ���̾�Ʈ�� ���̵�
	String id;
	Player p = null;
	boolean start = false;
	// ������
	public ClientConnectionThread(Socket socket) throws IOException {
		this.socket = socket;
		p = new Player();
		// writer & reader �ʱ�ȭ
		writer = new PrintWriter(socket.getOutputStream());
		reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}
	
	// �� Ŭ���̾�Ʈ���� �޽����� ������ �޼���
	public void sendMessage(String message) {
		writer.write(message + "\n");
		writer.flush();
	}
	
	// �� �����尡 �� ��
	public void run() {
		String line = null;
		try {
			// ù �޽����� ���̵�� ó��
			
			if(!start){
				boolean idCheck = false;
				while(!idCheck){
					id = reader.readLine();
					if(ChatServer.clients.size() > 0 && ChatServer.players.size() > 0){
						int check = 1;
						for(int i = 0 ; i< ChatServer.players.size();i++){
						String checkId = ChatServer.players.get(i).name;
						if(id.equals(checkId)){
							check = 2;
							sendMessage(checkId);
							sendMessage("���̵� �ߺ��Ǿ����ϴ�. �ٽ� �Է����ּ���.");
							sendMessage("���̵� �Է� : ");
							break;
						}
					}if(check !=2){
						ChatServer.players.add(p);
						p.setName(id);
						sendMessage(id + "ĳ���Ͱ� �����Ǿ����ϴ�.");
						ChatServer.sendMessageToAll(id   + " ���� ���� �Ͽ����ϴ�." );
						idCheck = true;
						break;
					}
				}else{
					ChatServer.players.add(p);
					p.setName(id);
					sendMessage(id + "ĳ���Ͱ� �����Ǿ����ϴ�.");
					ChatServer.sendMessageToAll(id   + " ���� ���� �Ͽ����ϴ�." );
					idCheck = true;
					break;
				}
				}
			}	
			// ��� Ŭ���̾�Ʈ���� ���� �޽��� �۽�
			ChatServer.sendMessageToAll(id + "���� �����Ͽ����ϴ�.");
			// Ŭ���̾�Ʈ�κ��� �޽����� ����
			while ((line = reader.readLine()) != null) {
				// ���� ���� ó��
				if (line.equals("/exit")) {
					break;
				}
			
				else if (line.equals("/start")) {
					if (ChatServer.start) {
						ChatServer.start = false;
						GameProgressThread gameThread = new GameProgressThread();
						gameThread.start();
					}
					else {
						System.out.println("�̹� �����Ͽ����ϴ�.");
						ChatServer.sendMessageToAll("�̹� ������ �����Ͽ����ϴ�.");
					}
				}
				// �Ϲ� �޽��� ó��
				else {
					ChatServer.sendMessageToAll(id + "���� �޽���: " + line);
				}
			}
			
		} catch (IOException e) {
//			e.printStackTrace();
			System.out.println("Ŭ���̾�Ʈ���� ������ ����Ǿ����ϴ�.");
		} finally {
			try {
				socket.close();
			} catch (IOException e) {}
			// ������ ���� �ִ� Ŭ���̾�Ʈ ������κ��� �� Ŭ���̾�Ʈ ����
			ChatServer.removeClient(this);
			// ���� ���� �ȳ� �޽����� ��� Ŭ���̾�Ʈ���� ����
			ChatServer.sendMessageToAll(id + "���� ������ �����Ͽ����ϴ�.");
		}
	}
	
}

/*
else if (line.startsWith("/to ")) { //"/to"�� �����ϴ� �޽���
	String[] wMessage = line.split(" ", 3); //"/to java hello, java~"
											//{"/to", "java", "hello, java~"
	//�޽����� syntax�� �������� üũ
	if (wMessage.length == 3) {
		//��� Ŭ���̾�Ʈ
		ClientConnectionThread targetClient = null;
		
		//��� ���̵�
		String targetId = wMessage[1];
		
		//��� ���̵� �����ϴ��� üũ
		synchronized (ChatServer.clients) {
			for (int i = 0; i < ChatServer.clients.size(); i++) {
				//ChatServer�� Ŭ���̾�Ʈ ����� i��° Ŭ���̾�Ʈ
				ClientConnectionThread client = ChatServer.clients.get(i);
				//i��° Ŭ���̾�Ʈ�� id �Ӽ����� targetid ���� ������
				if (client.id.equals(targetId)); {
					//�޽����� ���� ��� Ŭ���̾�Ʈ�� �ӽ� ����
					targetClient = client;
					// for �ݺ����� ��������
					break;
				}
			}
		}
								
		//���� �޽���
		String message = wMessage[2];
		//�޽��� ����
		//��� ���̵� ���� Ŭ���̾�Ʈ�� �ִٸ�
		if(targetClient != null) {
			//��� Ŭ���̾�Ʈ�� ���� �޽����� ����
			targetClient.sendMessage(id + "���� �ӼӸ� : " + message);
		}
	}
}
*/