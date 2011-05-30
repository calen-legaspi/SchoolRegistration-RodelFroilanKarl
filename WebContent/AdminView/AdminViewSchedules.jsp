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
	if(request.getAttribute("allSchedules") == null){
		response.sendRedirect("main");
	}
	ArrayList<Schedule> allSchedules = (ArrayList<Schedule>)request.getAttribute("allSchedules");	
%>

<Table border = "1">
	<tr>
		<th>day</th>
		<th>start time</th>
		<th>end time</th>
	</tr>
	<% for(Schedule schedule : allSchedules){ %>
  
  	<tr>
  		<td><%= schedule.getClassDay() %></td>
  		<td><%= schedule.getClassStartTime() %></td>
  		<td><%= schedule.getClassEndTime() %></td>
  		
  	</tr>
<% } %>
</Table>
</body>
</html>