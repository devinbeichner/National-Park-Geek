<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="Favorite Parks"/>
<%@include file="header.jsp" %>

<title>NPGeek Favorite Parks</title>
<main> 
	
	<div class=parkTotals>
	<table border=1>
	<tr>
		<th class="empty"></th>
		<th class="tableHeader">National Park</th>
		<th class="tableHeader">Total Surveys Submitted</th>
	</tr>
	<c:forEach var="park" items="${favoriteParks}">
	<tr>
		<td class="parkImageFav"><img src="img/parks/${park.parkCode}.jpg" id="imageFav"/></td>
		<td class= "parkNameFav" >${park.parkName}</td>
		<td class= "parkSurveyQty" >${park.surveyQty}</td>
	<tr>
	
	

	
</c:forEach> 
</table>
</div>
</main>


<%@include file="footer.jsp"%>