package com.im.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.jasper.tagplugins.jstl.core.Catch;
import org.jivesoftware.smack.*;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smackx.OfflineMessageManager;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

public class ChatHelper {
	//�����������Ӳ���������
	static AccountManager accountManager;
	final static ConnectionConfiguration configuration = new ConnectionConfiguration("127.0.0.1",Integer.parseInt("5222"));
	
	public static void goChat(String username,String password,String toSomeone,String myMessage) {
		// �����Զ�����
		configuration.setReconnectionAllowed(true);
		//���߷������Լ���״̬
		configuration.setSendPresence(true);
		Connection connection = new XMPPConnection(configuration);
//		OfflineMessageManager offlineMessageManager = null;
		try {
			// ��������
			connection.connect();
			// ��ȡ�˻�������
			accountManager = connection.getAccountManager();

			// ��¼
			connection.login(username, password);
			System.out.println("��ǰ��ҳ�е�¼���û���" + connection.getUser());
//			offlineMessageManager = new OfflineMessageManager(connection);
//			
//			Iterator<Message> iterator = offlineMessageManager.getMessages();
//			
//			System.out.println(offlineMessageManager.supportsFlexibleRetrieval());
//			System.out.println("������Ϣ������" + offlineMessageManager.getMessageCount());
//			
//			Map<String, ArrayList<Message>> offlineMsgs = new HashMap<String,ArrayList<Message>>();
//			while (iterator.hasNext()) {
//				Message message = iterator.next();
//				System.out.println("�յ�������Ϣ, Received from ��" + message.getFrom() + "�� message: " + message.getBody());
//				String fromUser = message.getFrom().split("/")[0];
//				
//				if (offlineMsgs.containsKey(fromUser)) {
//					offlineMsgs.get(fromUser).add(message);
//				} else {
//					ArrayList<Message> temp = new ArrayList<Message>();
//					temp.add(message);
//					offlineMsgs.put(fromUser, temp);
//				}
//			}
//			
//			//����������Ϣ����
//			Set<String> keys = offlineMsgs.keySet();
//			Iterator<String> offIt = keys.iterator();
//			while(offIt.hasNext()) {
//				String key = offIt.next();
//				ArrayList<Message> ms = offlineMsgs.get(key);
//				
//				for (int i=0;i < ms.size();i++) {
//					System.out.println("-->" + ms.get(i));
//				}
//				
//				offlineMessageManager.deleteMessages();
//				Presence presence = new Presence(Presence.Type.available);
//				connection.sendPacket(presence);
//				connection.disconnect();
//			}
			// ��������ϢListener
			MessageListener messageListener = new MessageListener() {
				@Override
				public void processMessage(Chat chat, Message message) {
					String messageBody = message.getBody();
					System.out.println("�յ���Ϣ��" + messageBody + " " + message.getFrom());
				}
			};
			
			//������Ϣ
			ChatManager chatManager = connection.getChatManager();
			Chat myChat = chatManager.createChat(toSomeone, messageListener);
			myChat.sendMessage(myMessage);
			
			//�ر�����
			//connection.disconnect();
		} catch (XMPPException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public static void receiveMessage(String username,String password) {
		configuration.setReconnectionAllowed(true);
		XMPPConnection connection = new XMPPConnection(configuration);	//�õ�����xmppЭ������Ӷ���
		try {
			connection.connect();		//���ӷ�����
			connection.login(username, password);		//�����û����������¼	
			ChatManager cm = connection.getChatManager(); 	//ȡ�����������			
				
				/*
				 * ��Ӽ�����
				 */
			cm.addChatListener(new ChatManagerListener() {
					
				@Override
				public void chatCreated(Chat chat, boolean create) {
					chat.addMessageListener(new MessageListener() {
							
						@Override
						public void processMessage(Chat chat, Message msg) {
							if (msg.getBody() != null) {
								ArrayList<String> myMessage = MyMessage.getMyMessage();
								myMessage.add(chat.getParticipant() + ":" + msg.getBody());
							}
						}
					});
				}
			});
			//while(true); 			//��ѭ����ά�ָ����Ӳ��ж�
		} catch (XMPPException e) {
			e.printStackTrace();
		}
	}
	
	public static String receiveOfflineMessage(String username,String password) {
		String receiveMessge = null;
		
		AccountManager accountManager;
		final ConnectionConfiguration connectionConfig = new ConnectionConfiguration(
				"127.0.0.1", Integer.parseInt("5222"));

		// �����Զ�����
		connectionConfig.setReconnectionAllowed(true);
		connectionConfig.setSendPresence(false);//��Ҫ���߷������Լ���״̬
		Connection connection = new XMPPConnection(connectionConfig);
		try {
			connection.connect();// ��������
			accountManager = connection.getAccountManager();// ��ȡ�˻�������
		} catch (XMPPException e) {
			throw new IllegalStateException(e);
		} 
		try {
			connection.login("123", "123456");
		} catch (XMPPException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		OfflineMessageManager offlineManager = new OfflineMessageManager(
				connection);
		try {
			Iterator<org.jivesoftware.smack.packet.Message> it = offlineManager
					.getMessages();

			System.out.println(offlineManager.supportsFlexibleRetrieval());
			System.out.println("������Ϣ����: " + offlineManager.getMessageCount());

			Map<String, ArrayList<Message>> offlineMsgs = new HashMap<String, ArrayList<Message>>();

			while (it.hasNext()) {
				org.jivesoftware.smack.packet.Message message = it.next();
				System.out
						.println("�յ�������Ϣ, Received from ��" + message.getFrom()
								+ "�� message: " + message.getBody());
				String fromUser = message.getFrom().split("/")[0];
				receiveMessge = message.getBody();
				if (offlineMsgs.containsKey(fromUser)) {
					offlineMsgs.get(fromUser).add(message);
				} else {
					ArrayList<Message> temp = new ArrayList<Message>();
					temp.add(message);
					offlineMsgs.put(fromUser, temp);
				}
			}

			// ��������д���������Ϣ����......
			Set<String> keys = offlineMsgs.keySet();
			Iterator<String> offIt = keys.iterator();
			while (offIt.hasNext()) {
				String key = offIt.next();
				ArrayList<Message> ms = offlineMsgs.get(key);

				for (int i = 0; i < ms.size(); i++) {
					System.out.println("-->" + ms.get(i));
				}
			}

			offlineManager.deleteMessages();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			offlineManager.deleteMessages();
		} catch (XMPPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//ɾ������������Ϣ
		Presence presence = new Presence(Presence.Type.available);
        		connection.sendPacket(presence);//������
        		connection.disconnect();//�ر�����
		return receiveMessge;
		
	}
}
