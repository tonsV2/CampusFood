<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
	<head>
		<title>Home</title>
	</head>
	<body>
		<c:if test="${empty meals}">
			<p>No meals found!</p>
		</c:if>
		<c:forEach var="meal" items="${meals}" varStatus="loop">
			<div>
				<h3><c:out value="${meal.name}" /></h3>
				<a href="<c:url value="/meal/${meal.id}" />">Details</a>
			</div>
			<c:if test="${!loop.last}">
				<hr />
			</c:if>
		</c:forEach>
	</body>
</html>
