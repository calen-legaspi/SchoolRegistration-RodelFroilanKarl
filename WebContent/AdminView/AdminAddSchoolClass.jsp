<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="database.*,services.*,domain.*,database.impl.*,java.util.Collection,java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	admin add class
	<%
	ArrayList<Subject> listOfSubject = (ArrayList<Subject>) request
			.getAttribute("ListOfSubject");
	ArrayList<Teacher> listOfTeacher = (ArrayList<Teacher>) request
			.getAttribute("ListOfTeacher");
	ArrayList<Schedule> listOfSchedule = (ArrayList<Schedule>) request
			.getAttribute("ListOfSchedule");
%>


	<form method="get" action="admin-add-class-impl">
		<select style="font-size: 20px;" name="subjectCode" size="5">
			<%
				if (listOfSubject.isEmpty()) {
			%>
			<option value="">NO SUBJECTS IN DB</option>
			<%
				} else {
					for (Subject subject : listOfSubject) {
			%>
			<option value="<%=subject.getCourseCode()%>"><%=subject.getCourseName()%></option>
			<%
				}
				}
			%>
		</select> <select style="font-size: 20px;" name="scheduleData" size="5">
			<%
				if (listOfSchedule.isEmpty()) {
			%>
			<option value="">NO SCHEDULES IN DB</option>
			<%
				} else {
					for (Schedule schedule : listOfSchedule) {
			%>
			<option value="<%=schedule.getAllData() %>"><%=schedule.getClassStartTime()%>-<%=schedule.getClassEndTime()%></option>
			<%
				}
				}
			%>
		</select> <select style="font-size: 20px;" name="teacherId" size="5">
			<%
				if (listOfTeacher.isEmpty()) {
			%>
			<option value="">NO TEACHERS IN DB</option>
			<%
				} else {
					for (Teacher teacher : listOfTeacher) {
			%>
			<option value="<%=teacher.getTeacherIdNo()%>"><%=teacher.getTeacherLastName() + ", "
							+ teacher.getTeacherFirstName()%></option>
			<%
				}
				}
			%>
		</select>
		<input type="submit" value="create class">
	</form>
	<br>
<% ArrayList<SchoolClass> allSchoolClass =  (ArrayList<SchoolClass>)request.getAttribute("allSchoolClass"); %>
<Table border = "1">
	<tr>
		<th>subjectCode</th>
		<th>schedule</th>
		<th>teacher</th>
	</tr>
	<% for(SchoolClass schoolClass : allSchoolClass){ %>
  
  	<tr>
  		<td><%= schoolClass.getClassSubjectName() %></td>
  		<td><%= schoolClass.getClassScheduleStartTime() %> - <%=schoolClass.getClassScheduleEndTime() %></td>
  		<td><%= schoolClass.getClassTeacherName() %></td>
  		
  	</tr>
<% } %>
</Table>
</body>
</html>