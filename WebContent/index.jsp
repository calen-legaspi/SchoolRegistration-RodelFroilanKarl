<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="database.*,java.sql.SQLException, services.*, domain.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="StyleSheet" href="css/mainPage.css" type="text/css" title="Main CSS">

</head>
<body>

<div class="headerwrapper"> 
<h1>[ School Registration System ]</h1>
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
			<form method="post">
			<select style="font-size: 20px;" name="User" size="5">
			<% StudentService myService = new StudentService(new StudentDaoJDBC());
			if(myService.checkStudentListIsEmpty()){%>
			  <option value="">User 1             r </option>
			<%} else {
			   for( Student student: myService.returnAllStudents() ){%>
			  <option value="<%= student.getMyIdNo() %>"><%= student %></option> 
			<%}
			  } %>
			</select>
			<br/><input style="font-size: 25px;" type="submit" value="Log In As Student"/>
			<input type="hidden" value="normal">
			</form>
		</td>
		<td width="100">
		</td>
		<td>
			<form action="">
			<br/><br/><br/><br/><br/><input style="font-size: 25px;" type="submit" value="Log In as Admin"/>
			<input type="hidden" value="admin">
			</form>
		</td>
	</tr>
</table>
</div>



</div>





</body>
</html>