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
	
	public void addID(Player p, String id){
		ChatServer.players.add(p);
		p.setName(id);
		sendMessage(id + "ĳ���Ͱ� �����Ǿ����ϴ�.");
		ChatServer.sendMessageToAll(id   + " ���� ���� �Ͽ����ϴ�." );
	}
	
	/*
	public void attack(Unit unit) {
		ChatServer.sendMessageToAll(p.name + "���� �����Ͽ����ϴ�.");
		unit.hp -= p.damage;
	}
	
	public void useBomb(Unit unit) {
		//��ź ���
		ChatServer.sendMessageToAll(p.name + "���� ��ź�� ����Ͽ����ϴ�.");
	}
	*/
	
	// �� �����尡 �� ��
	public void run() {
		String line = null;
		try {
			// ù �޽����� ���̵�� ó��
			if(!start) {
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
								addID(p, id);
								idCheck = true;
								break;
							}
					}else{
						addID(p, id);
						idCheck = true;
						break;
					}
				}
			}	
			// ��� Ŭ���̾�Ʈ���� ���� �޽��� �۽�
			//ChatServer.sendMessageToAll(id + "���� �����Ͽ����ϴ�.");
			// Ŭ���̾�Ʈ�κ��� �޽����� ����
			while ((line = reader.readLine()) != null) {
				// ���� ���� ó��
				if (line.equals("/exit")) {
					break;
				}
			
				else if (line.equals("/start")) {
					if (ChatServer.start) {
						ChatServer.sendMessageToAll(id   + " ���� ������ ���� �Ͽ����ϴ�." );
						ChatServer.start = false;
						GameProgressThread gameThread = new GameProgressThread();
						gameThread.start();
						/*
						FightThread fT = new FightThread();
						fT.start();
						PKThread pT = new PKThread();
						pT.start();
						*/
					}
					else {
						System.out.println("�̹� �����Ͽ����ϴ�.");
						ChatServer.sendMessageToAll("�̹� ������ �����Ͽ����ϴ�.");
					}
				}
				
				else if (line.equals("/gage")) {
					for (int i = 0; i < ChatServer.clients.size(); i++) {
			 			//�÷��̾ ����
						ClientConnectionThread thread = ChatServer.clients.get(i);
						Player p = thread.p;
						ChatServer.sendMessageToAll(p.name + "�� HP : " + p.energy);
			 		}
				}
				
				else if (line.startsWith("/c ")) {
					String[] wMessage = line.split(" ", 2); //"/c 1"
					//{"c", "1"}
					
					if ((ChatServer.clients.get(ChatServer.tern)).equals(this)) { 
						System.out.println(wMessage[1]);
						
						//�޽����� syntax�� �������� üũ
						if (wMessage.length == 2) {
							if (wMessage[1].equals("1") || wMessage[1].equals("2") || wMessage[1].equals("3")
		 							|| wMessage[1].equals("4") || wMessage[1].equals("5")) {
								if (ChatServer.stage == 1) {
									ChatServer.fightQueues.add(wMessage[1]);
								}
								else if (ChatServer.stage == 2) {
									ChatServer.pkQueues.add(wMessage[1]);
								}
							} else {
								System.out.println("�ٽ� �Է��� �ּ���");
								sendMessage("�ٽ� �Է��� �ּ���");
							}
						}
					}
					else {
						System.out.println(p.name + " �� ���ʰ� �ƴմϴ�. ���� �����ʽÿ�.");
						sendMessage(p.name + "�� ���ʰ� �ƴմϴ�. ���� �����ʽÿ�.");
					}
				}
				// ä�� �޽��� ó��
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