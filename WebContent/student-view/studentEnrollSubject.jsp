<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="domain.*, services.*, database.impl.*, java.util.ArrayList, beans.EnrolledClassBean" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ page errorPage="error.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>School Registration System - Enroll </title>
<link rel="StyleSheet" href="css/mainPage.css" type="text/css" title="Main CSS">
<script type="text/javascript">

function enroll(subj){
	var isConfirmed = confirm('Enrolling in '+subj+'. Continue?');
	if(isConfirmed){
		
		//alert('enroll?subject='+subj+'');
		window.location='enroll?subject='+subj+'';
	} else {
		alert('Cancelled Transaction.');
	}
}

</script>

</head>
<body>

<jsp:useBean id="availableClasses" class="java.util.ArrayList" scope="request"></jsp:useBean>

<div class="headerwrapper"> 
<%@include file="../include/header-content.html"%>
</div>

<div class="menuwrapper"> 

<%@include file="../include/menu-student.html"%>

</div>

<div class="contentwrapper"> 

<div align="center" class="entry">


<form method="post">

<table class="classDisplay">
	<tr>
		<th colspan="8">Current List of Available Classes</th>
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
	
    <c:forEach items="${availableClasses}" var="bean">
	<tr>
		<td><c:out value="${bean.code}"/></td>
		<td><c:out value="${bean.name}"/></td>
		<td><c:out value="${bean.level}"/></td>
		<td><c:out value="${bean.day}"/></td>
		<td><c:out value="${bean.start}"/></td>
		<td><c:out value="${bean.end}"/></td>
		<td><c:out value="${bean.teacher}"/></td>
		<td>
			<input type="button" value="Enroll" onClick="enroll('<c:out value="${bean.classNo}"/>')"/>
		</td>
	</tr>
	</c:forEach>
</table>

</form>

</div>

</div>

</body>
</html>