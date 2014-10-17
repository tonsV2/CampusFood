<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
	<head>
		<title>Home</title>
	</head>
	<body>
		<c:forEach var="canteen" items="${canteens}">
			<div>
				<h3><c:out value="${canteen.name}" /></h3>
				<a href="<c:url value="/canteen/${canteen.id}" />">Details</a>
			</div>
		</c:forEach>
	</body>
</html>