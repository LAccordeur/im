package com.im.util;

import java.util.ArrayList;
/*
 * �洢������Ϣ
 * */
public class MyMessage {
	private static ArrayList<String> myMessage = new ArrayList<String>();

	public static ArrayList<String> getMyMessage() {
		return myMessage;
	}

	public static void setMyMessage(ArrayList<String> myMessage) {
		MyMessage.myMessage = myMessage;
	}
	
}
