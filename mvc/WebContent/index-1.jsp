<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="scripts/jquery-1.7.2.js"></script>
<script type="text/javascript">
	$(function() {
		$(".delete").click(function() {
			var content = $(this).parent().parent().find("td:eq(1)").text();
			var flag = confirm("确定要删除" + content + "的信息吗？");
			return flag;
		});
	})
</script>
</head>
<body>
	<form action="query.do" method="post">
		<table>
			<tr>
				<td>CustomerName:</td>
				<td><input type="text" name="name" /></td>
			</tr>
			<tr>
				<td>Address:</td>
				<td><input type="text" name="address" /></td>
			</tr>
			<tr>
				<td>Phone:</td>
				<td><input type="text" name="phone" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Query" /></td>
				<td><a href="newCustomer.jsp">Add New Customer</a></td>
			</tr>
		</table>
	</form>
	<c:if test="${!empty requestScope.customer }">
		<hr>
		<br>
		<br>
		<table border="1" cellpadding="10" cellspacing="0">
			<tr>
				<th>ID</th>
				<th>NAME</th>
				<th>ADDRESS</th>
				<th>PHONE</th>
				<th>UPADATE</th>
				<th>DELETE</th>
			</tr>
			<c:forEach items="${requestScope.customer }" var="cust">
				<tr>
					<td>${cust.id }</td>
					<td>${cust.name }</td>
					<td>${cust.address }</td>
					<td>${cust.phone }</td>
					<c:url value="/edit.do" var="editUrl">
						<c:param name="id" value="${cust.id }"></c:param>
					</c:url>
					<td><a href="${editUrl }">UPDATE</a></td>
					<c:url value="/delete.do" var="deleteUrl">
						<c:param name="id" value="${cust.id }"></c:param>
					</c:url>
					<td><a href="${deleteUrl }" class="delete">DELETE</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>