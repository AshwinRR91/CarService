<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
<!DOCTYPE html>
<html>
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
          <a class="nav-link active text-white" aria-current="page" href="${pageContext.request.contextPath}/home">Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link text-white" href="${pageContext.request.contextPath}/yourRequest">Requests</a>
        </li>
      </ul>
      <form:form class="d-flex" action="${pageContext.request.contextPath}/logout">
      	<input class="btn btn-outline-success" type = "submit" value = "Logout">
      </form:form>
    </div>
  </div>
</nav>
<c:if test="${not empty requests}">
	<!--not empty checks if requests is empty or not if its not empty then proceeds further--> 
	<table class = "table">
			<thead>
				<tr>
					<th scope = "col">Requests</th>
					<th scope = "col">Status</th>
					<th scope = "col">Price</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requests}" var = "requests">
					<tr>
						<td>${requests.request}</td>
						<c:if test="${requests.status.equals('UNKNOWN')}">
							<td>Request has not been accepted yet</td>
						</c:if>
						<c:if test="${requests.status.equals('ACCEPTED')}">
							<td>Request has been accepted</td>
						</c:if>
						<!-- check the decline is in single inverted comma and not double since the test parameter is in double inverted comma -->
						<c:if test="${requests.status.equals('DECLINED')}">
							<td>Request has been declined</td>
						</c:if>
						<td>${requests.estimateAmount}</td>
					</tr>
				</c:forEach>
		</tbody>
	</table>
</c:if>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

</body>
</html>