package com.im.util;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.im.model.User;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter(filterName = "/LoginFilter",urlPatterns = {"/*"},initParams = {@WebInitParam(name = "noFilter",value = "GoLogin,LoginClServlet")})
public class LoginFilter implements Filter {
	
	private FilterConfig config;
    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request2 = (HttpServletRequest) request;
		HttpServletResponse response2 = (HttpServletResponse) response;
		
		//������Щ���뱻���˵�ҳ��
		//��ȡ��ʼ����
		String uri = request2.getRequestURI();
		System.out.println("���������õ���uri:"+uri);
		String string = config.getInitParameter("noFilter");
		if (string != null) {
			//System.out.println("������Ϣ��"+string);
			String[] strings = string.split(",");
			for (String string2 : strings) {
				if (string2==null || string2.equals("")){continue;}
				//System.out.println(string2);
				//uri������Щ����Ҫ���˵�ҳ��ؼ��־ͷ���
				if (uri.indexOf(string2) != -1) {
					chain.doFilter(request, response);
					return;
				}
			}
		}
		
		//���˷Ƿ�����
		HttpSession session = request2.getSession();
		User user = (User) session.getAttribute("loginInfo");
		if (user != null) {
			System.out.println(user.getId());
		} else {
			System.out.println("filter�е�userΪ��");
		}
		
		if (user != null) {
			chain.doFilter(request, response);
		} else {
			request2.setAttribute("err", "���ȵ�¼");
			request2.getRequestDispatcher("/GoLogin").forward(request2, response2);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		config = fConfig;
	}

}
