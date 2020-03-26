<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="pageTitle" value="Home Page"/>
<%@include file="header.jsp" %>

<title>NPGeek Favorite Park Survey</title>

<main>
	<c:url value="/survey" var="surveyFormUrl"/>
	<div class=surveyPage">
	<div class="topSpace"></div>
	<form:form action="${surveyFormUrl}" method="POST" modelAttribute="survey">
		<div class="favPark">
 			<form:label path="parkCode" class="label">Favorite National Park</form:label> 
 			<form:select path="parkCode" class="input">
				<option value=""></option>
				<option value="CVNP">Cuyahoga Valley National Park</option>
				<option value="ENP">Everglades National Park</option>
				<option value="GCNP">Grand Canyon National Park</option>
				<option value="GNP">Glacier National Park</option>
				<option value="GSMNP">Great Smoky Mountain National Park</option>
				<option value="GTNP">Grand Teton National Park</option>
				<option value="MRNP">Mount Ranier National Park</option>
				<option value="RMNP">Rocky Mountain National Park</option>
				<option value="YNP">Yellowstone Valley National Park</option>
				<option value="YNP2">Yosemite Valley National Park</option>
			</form:select>
			<form:errors style="color:red;" path="parkCode" cssClass="error"/>
		</div>
		<div class="email">
			<form:label path="emailAddress" class="label">Your email address</form:label>
		    <form:input type="text" path="emailAddress" class="input"/>
		    <form:errors style="color:red;" path="emailAddress" cssClass="error"/>
		</div>
		<div class="state">
			<form:label path="state" for="state" class="label">State of residence</form:label> 
			<form:select path="state" class="input">
				<option></option>
				<option>Alabama</option>
				<option>Alaska</option>
				<option>Arizona</option>
				<option>Arkansas</option>
				<option>California</option>
				<option>Colorado</option>
				<option>Connecticut</option>
				<option>Delaware</option>
				<option>Florida</option>
				<option>Georgia</option>
				<option>Hawaii</option>
				<option>Idaho</option>
				<option>Illinois</option>
				<option>Kansas</option>
				<option>Kentucky</option>
				<option>Louisiana</option>
				<option>Maine</option>
				<option>Maryland</option>
				<option>Massachusetts</option>
				<option>Michigan</option>
				<option>Minnesota</option>
				<option>Mississippi</option>
				<option>Missouri</option>
				<option>Montana</option>
				<option>Nebraska</option>
				<option>Nevada</option>
				<option>New Hampshire</option>
				<option>New Jersey</option>
				<option>New Mexico</option>
				<option>New York</option>
				<option>North Carolina</option>
				<option>North Dakota</option>
				<option>Ohio</option>
				<option>Oklahoma</option>
				<option>Oregon</option>
				<option>Pennsylvania</option>
				<option>Puerto Rico</option>
				<option>Rhode Island</option>
				<option>South Carolina</option>
				<option>South Dakota</option>
				<option>Tennessee</option>
				<option>Texas</option>
				<option>Utah</option>
				<option>Vermont</option>
				<option>Virginia</option>
				<option>Washington</option>
				<option>West Virginia</option>
				<option>Wisconsin</option>
				<option>Wyoming</option>
			</form:select>
			<form:errors style="color:red;" path="state" cssClass="error"/>
		</div>
		<div class="activityLevel">
			<form:label path="activityLevel" for="activityLevel" class="label">Activity level</form:label>
			<form:select path="activityLevel" class="input">
				<option></option>
				<option value="Inactive">Inactive</option>
				<option value="Sedentary">Sedentary</option>
				<option value="Active">Active</option>
				<option value="Extremely Active">Extremely Active</option>
			<%-- <form:label path="activityLevel" for="inactive">Inactive</form:label>
			<form:input type="radio" path="activityLevel" value="Inactive" id="inactive"/>
			<form:label path="activityLevel" for="sedentary">Sedentary</form:label>
			<form:input type="radio" path="activityLevel" value="Sedentary" id="sedentary"/>
			<form:label path="activityLevel" for="active">Active</form:label>
			<form:input type="radio" path="activityLevel" value="Active" id="active"/>
			<form:label path="activityLevel" for="extremely active">Extremely Active</form:label>
			<form:input type="radio" path="activityLevel" value="Extremely Active" id="extremelyActive"/> --%>
			</form:select>
			<form:errors style="color:red;" path="activityLevel" cssClass="error"/>
		</div>
		<div class="submit">
			<button type="submit">Submit</button>
		</div>
	</form:form>
	<div class="topSpace"></div>
	</div>
</main>


<%@include file="footer.jsp"%>