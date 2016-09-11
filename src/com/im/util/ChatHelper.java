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
	//进行聊天连接参数的配置
	static AccountManager accountManager;
	final static ConnectionConfiguration configuration = new ConnectionConfiguration("127.0.0.1",Integer.parseInt("5222"));
	
	public static void goChat(String username,String password,String toSomeone,String myMessage) {
		// 允许自动连接
		configuration.setReconnectionAllowed(true);
		//告诉服务器自己的状态
		configuration.setSendPresence(true);
		Connection connection = new XMPPConnection(configuration);
//		OfflineMessageManager offlineMessageManager = null;
		try {
			// 开启连接
			connection.connect();
			// 获取账户管理类
			accountManager = connection.getAccountManager();

			// 登录
			connection.login(username, password);
			System.out.println("当前网页中登录的用户：" + connection.getUser());
//			offlineMessageManager = new OfflineMessageManager(connection);
//			
//			Iterator<Message> iterator = offlineMessageManager.getMessages();
//			
//			System.out.println(offlineMessageManager.supportsFlexibleRetrieval());
//			System.out.println("离线消息数量：" + offlineMessageManager.getMessageCount());
//			
//			Map<String, ArrayList<Message>> offlineMsgs = new HashMap<String,ArrayList<Message>>();
//			while (iterator.hasNext()) {
//				Message message = iterator.next();
//				System.out.println("收到离线消息, Received from 【" + message.getFrom() + "】 message: " + message.getBody());
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
//			//处理离线消息集合
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
			// 创建的消息Listener
			MessageListener messageListener = new MessageListener() {
				@Override
				public void processMessage(Chat chat, Message message) {
					String messageBody = message.getBody();
					System.out.println("收到信息：" + messageBody + " " + message.getFrom());
				}
			};
			
			//创建消息
			ChatManager chatManager = connection.getChatManager();
			Chat myChat = chatManager.createChat(toSomeone, messageListener);
			myChat.sendMessage(myMessage);
			
			//关闭连接
			//connection.disconnect();
		} catch (XMPPException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public static void receiveMessage(String username,String password) {
		configuration.setReconnectionAllowed(true);
		XMPPConnection connection = new XMPPConnection(configuration);	//得到基于xmpp协议的连接对象
		try {
			connection.connect();		//连接服务器
			connection.login(username, password);		//利用用户名和密码登录	
			ChatManager cm = connection.getChatManager(); 	//取得聊天管理器			
				
				/*
				 * 添加监听器
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
			//while(true); 			//死循环，维持该连接不中断
		} catch (XMPPException e) {
			e.printStackTrace();
		}
	}
	
	public static String receiveOfflineMessage(String username,String password) {
		String receiveMessge = null;
		
		AccountManager accountManager;
		final ConnectionConfiguration connectionConfig = new ConnectionConfiguration(
				"127.0.0.1", Integer.parseInt("5222"));

		// 允许自动连接
		connectionConfig.setReconnectionAllowed(true);
		connectionConfig.setSendPresence(false);//不要告诉服务器自己的状态
		Connection connection = new XMPPConnection(connectionConfig);
		try {
			connection.connect();// 开启连接
			accountManager = connection.getAccountManager();// 获取账户管理类
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
			System.out.println("离线消息数量: " + offlineManager.getMessageCount());

			Map<String, ArrayList<Message>> offlineMsgs = new HashMap<String, ArrayList<Message>>();

			while (it.hasNext()) {
				org.jivesoftware.smack.packet.Message message = it.next();
				System.out
						.println("收到离线消息, Received from 【" + message.getFrom()
								+ "】 message: " + message.getBody());
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

			// 在这里进行处理离线消息集合......
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
		}//删除所有离线消息
		Presence presence = new Presence(Presence.Type.available);
        		connection.sendPacket(presence);//上线了
        		connection.disconnect();//关闭连接
		return receiveMessge;
		
	}
}
