<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>

<head>
	<title>Title</title>
</head>

<body>
	<security:authorize access="hasRole('EMPLOYEE')">
	
	<%
	response.sendRedirect("/home");
	%>
		
	</security:authorize>	
	
	
	<security:authorize access="hasRole('SERVICER')">  
	
	<% 
	response.sendRedirect("/servicer/home");
	%>
	
	</security:authorize>
	
	<hr>	
</body>

</html>









