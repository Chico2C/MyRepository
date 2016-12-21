<%@page import="com.cqg.mvcapp.domain.Customer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
		Object msg = request.getAttribute("message");
		if (msg != null) {
	%>
	<font color="red"><%=msg%></font>

	<%
		}
		String id = null;
		String name = null;
		String oldName = null;
		String address = null;
		String phone = null;
		Customer customer = (Customer) request.getAttribute("customer");
		if (customer != null) {
			id = customer.getId() + "";
			name = customer.getName();
			oldName = customer.getName();
			address = customer.getAddress();
			phone = customer.getPhone();
		} else {
			id = request.getParameter("id");
			name = request.getParameter("oldName");
			oldName = request.getParameter("oldName");
			address = request.getParameter("address");
			phone = request.getParameter("phone");
		}
	%>

	<form action="update.do" method="post">
		<input type="hidden" name="oldName" value="<%=oldName%>" />

		<table>
			<tr>
				<td>Id:</td>
				<td><input type="text" name="id" value="<%=id%>" /></td>
			</tr>
			<tr>
				<td>CustomerName:</td>
				<td><input type="text" name="name" value="<%=name%>" /></td>
			</tr>
			<tr>
				<td>Address:</td>
				<td><input type="text" name="address" value="<%=address%>" /></td>
			</tr>
			<tr>
				<td>Phone:</td>
				<td><input type="text" name="phone" value="<%=phone%>" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Submit" /></td>
			</tr>
		</table>
	</form>

</body>
</html>