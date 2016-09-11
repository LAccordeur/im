package com.im.controller;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.im.model.User;
import com.im.util.ChatHelper;
import com.im.util.MyMessage;

@WebServlet("/ReceiveMsg")
public class ReceiveMsg extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReceiveMsg() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset = utf-8");
		response.setCharacterEncoding("utf-8");
		Writer out = response.getWriter();
		StringBuffer chatMessage = new StringBuffer();
		
		HttpSession session2 = request.getSession();
        User user1 = (User)session2.getAttribute("loginInfo");
        String username = null;
        String password = null;
        if (user1 != null) {
        	username = user1.getUsername();
        	password = user1.getPassword();
        }
        if (username != null && password != null) {
        	ChatHelper.receiveMessage(username, password);
        	
        	ArrayList<String> myMessage = MyMessage.getMyMessage();
        	for (String string : myMessage) {
        		System.out.println("*******************");
        		System.out.println("ÏûÏ¢£º" + string);
        		System.out.println("*******************\n");
        		chatMessage.append(string + "<br>");
        	}
        } 
        out.write(chatMessage.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
