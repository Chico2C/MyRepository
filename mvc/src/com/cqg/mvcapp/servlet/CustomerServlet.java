package com.cqg.mvcapp.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cqg.mvcapp.dao.CriteriaCustomer;
import com.cqg.mvcapp.dao.CustomerDAO;
import com.cqg.mvcapp.dao.Factory.impl.CustomerDAOFactory;
import com.cqg.mvcapp.dao.impl.CustomerDAOJdbcImpl;
import com.cqg.mvcapp.dao.impl.CustomerDAOXMLImpl;
import com.cqg.mvcapp.domain.Customer;

/**
 * Servlet implementation class CustomerServlet
 */
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
   
	
	private CustomerDAO customerDAO = CustomerDAOFactory.getInstance().getType();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	// protected void doPost(HttpServletRequest request, HttpServletResponse
	// response) throws ServletException, IOException {
	// String method=request.getParameter("method");
	//
	// switch(method){
	// case "add": add(request,response);break;
	// case "query": query(request,response);break;
	// case "delete": delete(request,response);break;
	// case "update": update(request,response);break;
	// }
	// }
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String servletPath = req.getServletPath();
		String methodName = servletPath.substring(1, servletPath.length() - 3);

		try {
			Method method = getClass().getDeclaredMethod(methodName,
					HttpServletRequest.class, HttpServletResponse.class);
			method.invoke(this, req, resp);
		} catch (Exception e) {
			resp.sendRedirect("error.jsp");
		}

	}

	private void edit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idStr = request.getParameter("id");
		int id = -1;

		String pathForward = "/error.jsp";
		try {
			Customer customer = customerDAO.get(Integer.parseInt(idStr));
			if (customer != null) {
				pathForward = "/updateCustomer.jsp";
				request.setAttribute("customer", customer);
			}
		} catch (NumberFormatException e) {
		}
		request.getRequestDispatcher(pathForward).forward(request, response);
	}

	private void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String oldName = request.getParameter("oldName");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
	

		if (!oldName.equalsIgnoreCase(name)) {
			long count = customerDAO.getCountWithName(name);
			if (count > 0) {
				request.setAttribute("message", "用户" + name + "已被占用，请重新选择");
				request.getRequestDispatcher("/updateCustomer.jsp").forward(
						request, response);
				return;
			}

		}
		
		Customer customer = new Customer(Integer.parseInt(id), name,
				address, phone);
		customerDAO.update(customer);
		response.sendRedirect("query.do");

	}

	private void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String getId = request.getParameter("id");
		Integer id = 0;
		try {
			Integer.parseInt(getId);
			customerDAO.delete(id);
		} catch (NumberFormatException e) {
			response.sendRedirect("query.do");
		}

	}

	private void query(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");

		CriteriaCustomer cc = new CriteriaCustomer(name, address, phone);

		List<Customer> customers = customerDAO.getForCriteriaCustomer(cc);
		request.setAttribute("customers", customers);
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	private void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		request.setAttribute("message", "用户名已被占用请重新选择");

		long count = customerDAO.getCountWithName(name);
		if (count > 0) {
			request.getRequestDispatcher("/newCustomer.jsp").forward(request,
					response);
			return;
		}
		Customer customer = new Customer(id, name, address, phone);
		customerDAO.save(customer);
		response.sendRedirect("success.jsp");

	}

}
