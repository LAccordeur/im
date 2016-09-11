package com.im.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.im.model.User;
import com.im.util.ChatHelper;
import com.im.util.MyMessage;

@WebServlet("/ChatServlet")
public class ChatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ChatServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset = utf-8");
		request.setCharacterEncoding("utf-8");
		
		//��ȡ���������������Ϣ
		String JID = request.getParameter("JID");
		String message = request.getParameter("message");
		//����Ϣ����myMessage�Ա������촰����ʾ
		ArrayList<String> myMessage = MyMessage.getMyMessage();
		myMessage.add("��˵:" + message);
		
		HttpSession session= request.getSession();
		session.setAttribute("receiverJID", JID);
		User user = (User) session.getAttribute("loginInfo");
		String username = null;
		String password = null;
		if (user != null) {
			username = user.getUsername();
			password = user.getPassword();
		}
		System.out.println("chat����֮JID:" + JID);
		System.out.println("chat����֮message:" + message);
		//������������ķ���
		ChatHelper.goChat(username, password, JID, message);
		//request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
