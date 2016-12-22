<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="scripts/jquery-1.7.2.js"></script>
<script type="text/javascript">

	$(function(){
		
		$(":input[name=lastName]").change(function(){
			
			var val=$(this).val();
			val=$.trim(val);
			var $this=$(this);
			
			if(val != ""){
				
				$this.nextAll("font").remove();
				
				var url ="emp-validLastName";
				var args={"lastName":val,"time":new Date()};
				$.post(url,args,function(data){
					if(data == "1"){
						$this.after("<font color='green'>LastName 可用!</font>");
					}else if(data == "0"){
						$this.after("<font color='red'>LastName 不可用!</font>");
						
					}else{
						$this.after("<font color='red'>服务器错误!</font>");
					}
				});
			}else {
				alert("lastName 不能为空");
			}
			
		});
		
	});

</script>
</head>
<body>
		<h4>All New Employee</h4>
		<s:form action="emp-save"> 
			<s:if test="id !=null">
		   <s:textfield name="lastName" label="LastName" disabled="true"></s:textfield>
		   <s:hidden name="id"></s:hidden>
			</s:if>
			<s:else>
		   <s:textfield name="lastName" label="LastName"></s:textfield>
			</s:else>
		   <s:textfield name="email" label="Email"></s:textfield>
		   <s:textfield name="birth" label="Birth"></s:textfield>
		   <s:select list="#request.departments" listKey="id" listValue="departmentName"
		            name="department.id" label="Department"></s:select>		
		   <s:submit></s:submit>
		</s:form>
</body>
</html>