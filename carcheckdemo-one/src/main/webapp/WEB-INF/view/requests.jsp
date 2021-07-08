<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form:form action= "${pageContext.request.contextPath}/servicer/check" modelAttribute="carservicer">
	<c:forEach items = "${carservicer.placedRequest}" var = "request" varStatus = "i" >
		<form:hidden path="placedRequest[${i.index}].id"/>
		<form:hidden path="placedRequest[${i.index}].requestId"/>
		<form:hidden path="placedRequest[${i.index}].request"/>
		<form:hidden path="placedRequest[${i.index}].customerEmail"/>
		<form:checkbox path="placedRequest[${i.index}].status" value ="ACCEPTED"/>
		<label>${request.request}</label>
		<label>Transaction Amount</label>
		<form:input path="placedRequest[${i.index}].transactionAmount"/>
		<input type = "submit" name ="accept" value= "accept">
		<input type = "submit" name ="decline" value= "decline">
		
		<br><br>																																															
	</c:forEach>	

</form:form>
</body>
</html>