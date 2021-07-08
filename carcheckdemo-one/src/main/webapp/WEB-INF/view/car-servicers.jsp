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
<nav class="navbar navbar-expand-lg navbar-light bg-dark " >
  <div class="container-fluid">
    <a class="navbar-brand text-white" href="#">CarService</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active text-white" aria-current="page" href="#">Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link text-white" href="#">Your requests</a>
        </li>
      </ul>
      <form:form class="d-flex" action="${pageContext.request.contextPath}/logout" method="POST">
      	<input class="btn btn-outline-success" type = "submit" value = "Logout">
      </form:form>
    </div>
  </div>
</nav>

<c:forEach var = "carservicer" items="${carservicers}">
	<br>
	<h4>${carservicer.carServicerName}'s service</h4>
					<form:form action="/placeRequest">
						<c:forEach  varStatus = "i"  var = "service" items ="${carservicer.servicesOffered}">	
						<input type = "checkbox" name = "customerRequest[]" value="${service.request}">
						<label>${service.request}</label>
						</c:forEach>
						<input type = "hidden" name="carServicerId" value="${carservicer.id}">
						<input type = "hidden" name="servicerEmail" value="${carservicer.emailId}">
						<input type = "hidden" name = "status" value = "UNKNOWN">
						<button type ="submit">Submit</button>
					</form:form>
			<br>
</c:forEach>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>