<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="database.*, services.*, domain.*,database.impl.*,java.util.ArrayList" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="StyleSheet" href="css/mainPage.css" type="text/css" title="Main CSS">
<title>Insert title here</title>
</head>
<body>

<jsp:useBean id="allSubjects" class="java.util.ArrayList" scope="request"></jsp:useBean>

<div class="headerwrapper"> 
<%@include file="../include/header-content.html"%>
</div>

<div class="menuwrapper"> 

<%@include file="../include/menu-student.html"%>

</div>

<div class="contentwrapper"> 

<div align="center" class="entry">

<Table border = "1">
    <tr>
       <th colspan="3">List Of All Subjects</th>
    </tr>

	<tr>
		<th>Code</th>
		<th>Level</th>
		<th>Name</th>
	</tr>
	
	<c:forEach items="${allSubjects}" var="subjectBean">
	
	<tr>
  		<td><c:out value="${subjectBean.code}"></c:out></td>
  		<td><c:out value="${subjectBean.level}"></c:out></td>
  		<td><c:out value="${subjectBean.name}"></c:out></td>	
  	</tr>
  	
	</c:forEach>
</Table>

</div>

<br>
<br>
<div align="center" class="entry">
hello
</div>

</div>



	

</body>
</html>