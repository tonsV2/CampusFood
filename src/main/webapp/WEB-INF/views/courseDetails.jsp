<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div>
	<h3>${course.name}</h3>
	<p>${course.dateOfServing}</p>
</div>
<hr />
<a href="<c:url value="/canteens" />">List canteens</a>
