<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div>
	<h3>${meal.name}</h3>
	<p>date: ${meal.dateOfServing}</p>
	<p>canteen: ${meal.canteen.name}</p>
</div>
<hr />
<a href="<c:url value="/canteens" />">List canteens</a>
