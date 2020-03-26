<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="Home Page"/>
<%@include file="header.jsp" %>

<main>
<div class="topSpace"></div> 
<c:forEach var="park" items="${parks}">
	<div class="parkDetails">
	<a href="detail?parkCode=${park.parkCode}">
		<img src="img/parks/${park.parkCode}.jpg" class="parkImage" /></a>
		<div class="detailFlex">
		<h1 class="parkName">${park.parkName}</h1>
		<h2 class="parkLocation">${park.state}</h2>
		<p class="parkDescription">${park.parkDescription}</p>
		</div>

	</div>
</c:forEach> 
</main>


<%@include file="footer.jsp"%>