<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang = "en">
<head>
	<meta charset="ISO-8859-1">
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- Bootstrap CSS -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
	<title>Hello, world!</title>
	<title>${customer.firstName}'s requests</title>
</head>
<body>
<h1>Home</h1>
<div class = "container">	
	<p>Welcome ${customer.firstName}</p>
</div>
<br>
<c:if test="${not empty requests}">
	// not empty checks if requests is empty or not if its not empty then proceeds further
	<table class = "table">
			<thead>
				<tr>
					<th scope = "col">Requests<th>
					<th scope = "col">Status</th>
				</tr>
			</thead>
		<tbody>
			<c:forEach items="${requests}" var = "requests">
					<tr>
						<td>${requests.request}</td>
						<c:if test="${requests.status.equals('UNKNOWN')}">
						<td>Request has not been accepted yet</td>
						</c:if>
					</tr>
			</c:forEach>
		</tbody>
	</table>
</c:if>
<h5>Check for services in a pincode</h5>
<br>
<form action="${pageContext.request.contextPath}/carServicers">
	<label for="pincodelabel">Search by pincode</label>
	<input type = "text" placeholder = "enter pincode" name = "pincode"></input>
	<button type="submit">Submit</button>
</form>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>