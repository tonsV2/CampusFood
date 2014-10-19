<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div>
	<h3>${canteen.name}</h3>
	<p>${canteen.address}... link to google maps</p>
	<pre>${canteen.openingHours}</pre>
	<pre>${canteen.contact}</pre>
	<a href="${restaurant.homepage}">homePage</a>
	<br />
	Courses:
	<c:forEach var="course" items="${canteen.courses}">
		<br />
		<c:out value="${course.name}"/>
	</c:forEach>
</div>
<hr />
<a href="<c:url value="/canteens" />">List canteens</a>
