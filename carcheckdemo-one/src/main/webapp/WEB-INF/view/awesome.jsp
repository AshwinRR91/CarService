<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<form:form action= "processForm" modelAttribute = "Request" method="Get">
	<table>
		<tr>
			<td><label>Request</label></td>
			<td><form:input path = "request"></form:input></td>
		</tr>
	</table>
</form:form>
<body>
</body>
</html>