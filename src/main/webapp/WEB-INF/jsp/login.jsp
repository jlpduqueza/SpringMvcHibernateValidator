<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Spring MVC form submission</title>
</head>

<body>
	<h2>Login</h2>

	<form:form method="POST" action="login" modelAttribute="login">
		<table>
			<tr>
				<td>Username:</td>
				<td><form:input path="username" /></td>
				<td><form:errors path="username" cssStyle="color: #ff0000;"/></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><form:password path="password" /></td>
				<td><form:errors path="password" cssStyle="color: #ff0000;" /></td>
			</tr>
			<tr>
				<td><input type="submit" name="submit" value="Submit"></td>
			</tr>
			<tr>
		</table>
	</form:form>

</body>
</html>