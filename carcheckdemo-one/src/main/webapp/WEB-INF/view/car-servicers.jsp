<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang = "en">
<head>
	<meta charset="ISO-8859-1">
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- Bootstrap CSS -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
	<title>Insert title here</title>
</head>
<body>
<c:forEach var = "carservicer" items="${carservicers}">
	<br>
	<h4>${carservicer.carServicerName}'s service</h4>
					<form:form action="/placeRequest">
						<c:forEach  varStatus = "i"  var = "service" items ="${carservicer.servicesOffered}">	
						<input type = "checkbox" name = "customerRequest[]" value="${service.request}">
						<label>${service.request}</label>
						</c:forEach>
						<button type ="submit">Submit</button>
					</form:form>
			<br>
</c:forEach>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>