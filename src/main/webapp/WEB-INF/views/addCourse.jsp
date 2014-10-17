<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
	<head>
		<link rel="stylesheet" href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
		<script src="//code.jquery.com/jquery-1.10.2.js"></script>
		<script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>

		<script src="<c:url value="/resources/javascript/pickDate.js" />"></script>
	
		<style><!--
label {
	display: inline-block;
	float: left;
	clear: left;
	width: 10%;
	text-align: left;
}

input, textarea {
	display: inline-block;
	float: left;
}

input[type="submit"] {
	clear: left;
}
		--></style>
	</head>
	<body>

		<form:form method="post" modelAttribute="course">
			<form:label path="name">Name</form:label>
			<form:input path="name" />

			<form:label path="dateOfServing">When</form:label>
			<form:input path="dateOfServing" />

			<input type="submit" />
		</form:form>

</body></html>
