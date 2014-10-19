<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div>
	<h3>${course.name}</h3>
	<p>date: ${course.dateOfServing}</p>
	<p>canteen: ${course.canteen.name}</p>
</div>
<hr />
<a href="<c:url value="/canteens" />">List canteens</a>
