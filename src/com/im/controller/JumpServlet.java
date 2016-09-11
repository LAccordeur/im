package com.im.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/JumpServlet")
public class JumpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public JumpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String key = request.getParameter("key");
		
		if ("goHome".equals(key)) {
			request.getRequestDispatcher("/WEB-INF/home2.0.jsp").forward(request, response);
		} else if ("goLogin".equals(key)) {
			request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
		} else if ("goCalender".equals(key)) {
			request.getRequestDispatcher("/WEB-INF/calender.html").forward(request, response);
		} else if ("goProject".equals(key)) {
			request.getRequestDispatcher("/WEB-INF/project.html").forward(request, response);
		} else if ("goOffice".equals(key)) {
			request.getRequestDispatcher("/WEB-INF/office.html").forward(request, response);
		} else if ("goText".equals(key)) {
			request.getRequestDispatcher("/WEB-INF/text.html").forward(request, response);
		} else if ("goFinance".equals(key)) {
			request.getRequestDispatcher("/WEB-INF/fiance.html").forward(request, response);
		} else if ("addFunction".equals(key)) {
			request.getRequestDispatcher("/WEB-INF/addfunction.html").forward(request, response);
		} else {
			request.getRequestDispatcher("/WEB-INF/home2.0.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
