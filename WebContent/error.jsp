<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*" %>
<%@ page isErrorPage="true" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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


<h1>Oops! An Error was Encountered!</h1><br>

<%-- Exception Handler --%>
<font color="red">
<%= exception.toString() %><br>
</font>

<input type="button" value="Go Back To Where You Were" Onclick ="javascript:history.back();"/>

<%
out.println("<!--");
StringWriter sw = new StringWriter();
PrintWriter pw = new PrintWriter(sw);
exception.printStackTrace(pw);
out.print(sw);
sw.close();
pw.close();
out.println("-->");
%>

</div>

</div>




</body>
</html>