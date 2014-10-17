<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div>
	<h3>${canteen.name}</h3>
<%-- 	<c:forEach var="tag" items="${restaurant.tags}"> --%>
<%-- 		<c:out value="${tag}"/>, --%>
<%-- 	</c:forEach> --%>
	<p>${canteen.address}... link to google maps</p>
	<pre>${canteen.openingHours}</pre>
	<pre>${canteen.contact}</pre>
	<a href="${restaurant.homepage}">homePage</a>
</div>
<hr />
<a href="<c:url value="/canteens" />">List canteens</a>
