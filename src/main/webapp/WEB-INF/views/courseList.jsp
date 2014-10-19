<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
	<head>
		<title>Home</title>
	</head>
	<body>
		<c:forEach var="course" items="${courses}" varStatus="loop">
			<div>
				<h3><c:out value="${course.name}" /></h3>
				<a href="<c:url value="/course/${course.id}" />">Details</a>
			</div>
			<c:if test="${!loop.last}">
				<hr />
			</c:if>
		</c:forEach>
	</body>
</html>
