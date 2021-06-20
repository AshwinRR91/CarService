<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>${carServicer.getCarServicerName()}</h1>
<c:if test="${carServicer.getServicesOffered().size()>0}">
	<c:forEach var = "Requests" items = "${carServicer.getServicesOffered()}">
				<p>${Requests}</p>	
	</c:forEach>

</c:if>
<c:if test="${carServicer.getServicesOffered().size()<1}">
Create new Request
</c:if>
</body>
</html>