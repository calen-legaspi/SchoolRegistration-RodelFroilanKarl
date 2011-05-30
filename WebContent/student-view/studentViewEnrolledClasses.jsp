<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="domain.*, java.util.ArrayList, beans.EnrolledClassBean" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ page errorPage="error.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="StyleSheet" href="css/mainPage.css" type="text/css" title="Main CSS">
</head>
<body>

<jsp:useBean id="enrolledClasses" class="java.util.ArrayList" scope="request"></jsp:useBean>

<div class="headerwrapper"> 
<%@include file="../include/header-content.html"%>
</div>

<div class="menuwrapper"> 

<%@include file="../include/menu-student.html"%>

</div>

<div class="contentwrapper"> 

<div align="center" class="entry">

<table class="classDisplay">
	<tr>
		<th colspan="7">Current List of Enrolled Classes</th>
	</tr>
	<tr>
		<th>CODE</th>
		<th>NAME</th>
		<th>LEVEL</th>
		<th>DAY</th>
		<th>START</th>
		<th>END</th>
		<th>TEACHER</th>
	</tr>
	
    <c:forEach items="${enrolledClasses}" var="bean">
	<tr>
		<td><c:out value="${bean.code}"/></td>
		<td><c:out value="${bean.name}"/></td>
		<td><c:out value="${bean.level}"/></td>
		<td><c:out value="${bean.day}"/></td>
		<td><c:out value="${bean.start}"/></td>
		<td><c:out value="${bean.end}"/></td>
		<td><c:out value="${bean.teacher}"/></td>
	</tr>
	</c:forEach>
	
</table>

</div>

</div>

</body>
</html>