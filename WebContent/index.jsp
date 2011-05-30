<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="database.impl.* ,java.sql.SQLException, services.*, domain.*"%>
<%@ page errorPage="error.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>School Registration System - MAIN</title>
<link rel="StyleSheet" href="css/mainPage.css" type="text/css" title="Main CSS">

</head>
<body>

<div class="headerwrapper"> 
<%@include file="../include/header-content.html"%>
</div>

<div class="menuwrapper"> 
"There are alot of things we need to do..."
</div>

<div class="contentwrapper"> 

<div align="center" class="entry">
LOG IN AS ONE OF THESE USERS:<br/>
<table class="logintable" cellspacing="50" cellpadding="10">
	<tr>
		<td>
			<form method="post" action="LogInServlet">
			<select style="font-size: 20px;" name="studentNo" size="5">
			<%
				EnrollmentService myService = new EnrollmentServiceImpl(new StudentDaoJDBC(),new SchoolClassDaoJDBC());
							if(myService.checkStudentListIsEmpty()){
			%>
			  <option value=""> NO STUDENTS IN DB </option>
			<%} else {
			   for( Student student: myService.returnAllStudents() ){%>
			  <option value="<%= student.getMyIdNo() %>"><%= student %></option> 
			<%}
			  } %>
			</select>
			<br/><input style="font-size: 25px;" type="submit" value="Log In As Student"/>
			<input type="hidden" name="type" value="student">
			</form>
		</td>
		<td width="100">
		</td>
		<td>
			<form method="post" action="LogInServlet">
			<br/><br/><br/><br/><br/><input style="font-size: 25px;" type="submit" value="Log In as Admin"/>
			<input type="hidden" name="type" value="admin">
			</form>
		</td>
	</tr>
</table>
</div>



</div>





</body>
</html>