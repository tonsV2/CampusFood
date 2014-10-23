<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
	<head>
		<%-- Used by geolocate --%>
		<script src="//code.jquery.com/jquery-1.10.2.js"></script>
		<%-- Auto complete address --%>
		<script src="https://maps.googleapis.com/maps/api/js?v=3.exp&libraries=places"></script>
		<script src="<c:url value="/resources/javascript/geolocate.js" />"></script>
		<%-- Align form elements --%>
		<link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/align_form.css" />" />

	</head>
	<body>

		<form:form method="post" modelAttribute="canteen">
			<form:label path="name">Name</form:label>
			<form:input path="name" />

			<form:label path="address">Where</form:label>
			<form:input path="address" />

			<form:label path="openingHours">Opening Hours</form:label>
			<form:textarea path="openingHours" rows="5" cols="30" />

			<form:label path="contact">Kontakt</form:label>
			<form:input path="contact" />

			<input type="submit" />
		</form:form>

</body></html>
