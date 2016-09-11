package com.im.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.im.model.User;
import com.im.service.UsersService;
import com.sun.crypto.provider.RSACipher;

@WebServlet("/LoginClServlet")
public class LoginClServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginClServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("LoginClServlet已成功启用");
		response.setContentType("text/html;charset = utf-8");
		request.setCharacterEncoding("utf-8");
		//接收用户提交的用户名和密码及其他操作标识
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		System.out.println("接收的用户名为"+username);
		System.out.println("接收的用户密码为"+password);
		
//		String keepInfo = request.getParameter("keepInfo");
//		String noKeepInfo = request.getParameter("noKeepInfo");
//		String checkCode = request.getParameter("checkCode");
		
//		//判断用户输入是否有空
//		if (password == null || id == null || checkCode == null) {
//			request.setAttribute("err", "登录信息填写不完整");
//			request.getRequestDispatcher("/LoginServlet").forward(request, response);
//			return;
//		}
		
//		//判断验证码是否正确
//		if (checkCode != null && request.getSession().getAttribute("checkCode") != null) {
//			if (!(checkCode.equals(request.getSession().getAttribute("checkCode")))) {
//				request.setAttribute("err", "验证码不正确");
//				request.getRequestDispatcher("/LoginServlet").forward(request, response);
//				return;
//			}
//		}
//		
//		System.out.println("keepInfo?" + keepInfo);
//		System.out.println("noKeepInfo?" + noKeepInfo);
//		//创建cookie
//		if ("keepInfo".equals(keepInfo)) {
//			Cookie cookieId = new Cookie("id", id);
//			cookieId.setMaxAge(3600);
//			response.addCookie(cookieId);
//			
//			Cookie cookiePassword = new Cookie("password", password);
//			cookiePassword.setMaxAge(3600);
//			response.addCookie(cookiePassword);
//			System.out.println("cookie 创建成功");
//		}
//		//消除cookie
//		if ("noKeepInfo".equals(noKeepInfo)) {
//			Cookie cookieId = new Cookie("id", id);
//			cookieId.setMaxAge(0);
//			response.addCookie(cookieId);
//			
//			Cookie cookiePassword = new Cookie("password", password);
//			cookiePassword.setMaxAge(0);
//			response.addCookie(cookiePassword);
//			System.out.println("cookie 消除成功");
//		}
		
		//创建UsersService对象
		UsersService usersService = new UsersService();
		boolean result = false;
		User user = new User();
		if (username != null) {
			user.setUsername(username);
		}
		if (password != null) {
			user.setPassword(password);
		}
		result = usersService.checkUser(user);
		
//		if (result) {
//			//获取该页面被访问多少次
//			ServletContext servletContext = this.getServletContext();
//			Integer nums = (Integer) servletContext.getAttribute("nums");
//			System.out.println("LoginClServlet中的nums：" + nums);
//			if (nums != null) {
//				servletContext.setAttribute("nums", (nums+1));
//			} else {
//				servletContext.setAttribute("nums", 1);
//			}
//		}
		
		if (result) {
			//把user对象保存到session
			HttpSession session = request.getSession();
			session.setAttribute("loginInfo", user);
			System.out.println("session属性loginInfo已设置");
			//response.sendRedirect("/IM/home.html");
			request.getRequestDispatcher("/WEB-INF/home2.0.jsp").forward(request, response);
		} else {
			request.setAttribute("err", "用户名和密码不匹配！！");
			request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
