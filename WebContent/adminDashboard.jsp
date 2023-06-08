<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Dashboard</title>
</head>
<body>

	<div>Hello User Welcome To The Admin Dashboard</div>

    <!--   User::::::::::::::: ${listStudent} -->

	<div align="center">
		<table border="1" cellpadding="5">
			<caption>
				<h2>List of Student</h2>
			</caption>
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Email</th>
				<th>Password</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>
			<c:forEach var="user" items="${list}">
				<tr>
					<td><c:out value="${user.studentId}" /></td>
					<td><c:out value="${user.firstName}" /></td>
					<td><c:out value="${user.email}" /></td>
					<td><c:out value="${user.password}" /></td>
					<td><a href="www.google.com"><c:out value="Edit User ${user.firstName}" /></a></td>
					<td><a href="www.google.com"><c:out value="Delete User ${user.firstName}" /></a></td>
				</tr>
			</c:forEach>
		</table>
	</div>












	<!--<script type="text/javascript">
	
	function preventBack(){window.history.forward();
			setTimeout("preventBack()", 0);}
		  window.onunload = function() {null};
	</script>-->



</body>
</html>