<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="database.*, services.*, domain.*,database.impl.*,java.util.ArrayList" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%	
	if(request.getAttribute("allTeachers") == null){
		response.sendRedirect("main");
	}
	ArrayList<Teacher> allTeachers = (ArrayList<Teacher>)request.getAttribute("allTeachers");	
%>

<Table border = "1">
	<tr>
		<th>teacher id</th>
		<th>name</th>
	</tr>
	<% for(Teacher teacher : allTeachers){ %>
  
  	<tr>
  		<td><%= teacher.getTeacherIdNo() %></td>
  		<td><%= teacher.getTeacherLastName()+", "+teacher.getTeacherFirstName() %></td>
  	</tr>
<% } %>
</Table>
</body>
</html>