<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
	<head>
	<%-- TODO: make geolocation unobtrusive... $(document).ready(function(... --%>
		<script src="https://maps.googleapis.com/maps/api/js?v=3.exp&libraries=places"></script>
		<script src="<c:url value="/resources/javascript/geolocate.js" />"></script>

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
	<body onload="initialize()">

		<form:form method="post" modelAttribute="canteen">
			<form:label path="name">Name</form:label>
			<form:input path="name" />

			<form:label path="address">Where</form:label>
			<form:input path="address" onFocus="geolocate()" onkeypress="enterKeyPressDontSubmit(event);" />

			<form:label path="openingHours">Opening Hours</form:label>
			<form:textarea path="openingHours" rows="5" cols="30" />

			<form:label path="contact">Kontakt</form:label>
			<form:input path="contact" />

			<input type="submit" />
		</form:form>

</body></html>
