<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="pageTitle" value="Home Page"/>
<%@include file="header.jsp" %>


<<<<<<< HEAD
	<img id="detailImage" src = "img/parks/${park.parkCode}.jpg"/>
	<h2 id = "parkName">${park.parkName}</h2>
	<div id = "state">State: ${park.state}</div>
	<div id = "acreage"><strong>Acreage:</strong> ${park.acreage} acres</div>
	<div id = "elevation"><strong>Elevation:</strong> ${park.elevationInFeet} feet</div>
	<div id = "milesOfTrail"><strong>Miles of Trail:</strong> ${park.milesOfTrail} miles</div>
	<div id = "numberOfCampsites"><strong>Number of Campsites:</strong> ${park.numberOfCampsites}</div>
	<div id = "climate"><strong>Climate:</strong> ${park.climate}</div>
	<div id = "yearFounded"><strong>Year Founded:</strong> ${park.yearFounded}</div>
	<div id = "annualVisitorCount"><strong>Annual Visitor Count:</strong> ${park.annualVisitorCount}</div>
	<div id = "quote"><strong>Quote:</strong> ${park.inspirationalQuote}</div>
	<div id = "quoteSource"><strong>Quote Source:</strong> ${park.inspirationalQuoteSource}</div>
	<div id = "description"><strong>Description:</strong> ${park.parkDescription}</div>
	<div id = "entryFee"><strong>Entry Fee:</strong> $${park.entryFee}.00</div>
	<div id = "numberOfAnimalSpecies"><strong>Number of Animal Species:</strong> ${park.numberOfAnimalSpecies}</div>
	<br>
	<br>
	
	<h4>Today</h4>
=======
<main>
<div class="topSpace"></div>
	<div class="detailInfo">
		<img id="detailImage" src = "img/parks/${park.parkCode}.jpg"/>
			<div id="detailFlexPage">
				<h2 id = "parkName">${park.parkName}</h2>
				<div id = "state"><strong>State:</strong> ${park.state}</div>
				<div id = "acreage">Acreage: ${park.acreage} acres</div>
				<div id = "elevation">Elevation: ${park.elevationInFeet} feet</div>
				<div id = "milesOfTrail">Miles of Trail: ${park.milesOfTrail} miles</div>
				<div id = "numberOfCampsites">Number of Campsites: ${park.numberOfCampsites}</div>
				<div id = "climate">Climate: ${park.climate}</div>
				<div id = "yearFounded">Year Founded: ${park.yearFounded}</div>
				<div id = "annualVisitorCount">Annual Visitor Count: ${park.annualVisitorCount}</div>
				<div id = "quote">Quote: ${park.inspirationalQuote}</div>
				<div id = "quoteSource">Quote Source: ${park.inspirationalQuoteSource}</div>
				<div id = "description">Description: ${park.parkDescription}</div>
				<div id = "entryFee">Entry Fee: $${park.entryFee}.00</div>
				<div id = "numberOfAnimalSpecies">Number of Animal Species: ${park.numberOfAnimalSpecies}</div>
			</div>
	</div>
<!-- 	<div class="topSpace"></div>
 -->	<h4>Today</h4>
 <div class="weatherInfo">
>>>>>>> 236e71d411a0a7ffdd38c428707e920ae0be92c3
	<c:forEach var ="weather" items="${weather}">
		<div class="weatherLayout">
		<img src = "img/weather/${weather.forecast}.png" class="weatherImage"/>
			<div class = "tempHigh">High: ${weather.high} ${indicator}</div>
			<div class = "tempLow">Low: ${weather.low} ${indicator}</div>
			<br>
			<div class = "advisoryMessage">${weather.advisoryMessage}</div>
		</div>
		<br>	
	</c:forEach>
	</div>
	
	<form method="POST" action="detail?parkCode=${park.parkCode}" id="temp">
		<input type = "radio" id = "f" name = "fOrC" value="f" checked>
		<label id="fahr" for = "f">Fahrenheit</label>
		<input type = "radio" id ="c" name = "fOrC" value = "c">
		<label id="cels" for = "c">Celcius</label>
		<br>
		<input type ="submit" value = "Change Preference" id = "submitButton">
	</form>

</main>

<%@include file="footer.jsp" %>