package com.cqg.mvcapp.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.cqg.mvcapp.dao.Factory.impl.CustomerDAOFactory;

/**
 * Servlet implementation class InitServlet
 */
public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		
		InputStream in=getServletContext().getResourceAsStream("/WEB-INF/classes/swith.properties");
	  CustomerDAOFactory.getInstance().setType("jdbc");
		Properties properties=new Properties();
		try {
			properties.load(in);
			String type=properties.getProperty("type");
			CustomerDAOFactory.getInstance().setType(type);
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		
	}

}
