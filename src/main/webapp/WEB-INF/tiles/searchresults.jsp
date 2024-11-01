<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="pagi"%>


<div class="row">

	<div class="col-md-12 results-noresult">

		<c:if test="${empty page.content}">
    		No results.
    	</c:if>

	</div>

</div>


You searched by hobby: ${s}

<c:url var="searchUrl" value="/search?s=${s}" />
<c:url var="img" value="/img" />

<div class="row">

	<div class="col-md-12">

		<pagi:pagination url="${searchUrl}" page="${page}" size="3" />

	</div>

</div>

<c:forEach var="result" items="${page.content}">

	<c:url var="profilePhoto" value="/profilephoto/${result.userId}" />
	<c:url var="profilelink" value="/profile/${result.userId}" />
	<c:url var="chatviewLink" value="/chatview/${result.userId}" />

	<div class="row person-row">

		<div class="col-md-12">

			<div class="results-photo">
				<a href="${profilelink}"><img id="profilePhotoImage" src="${result.profilePhotoUrl}" /></a>
			</div>

			<div class="results-details">

				<div class="results-name">
					<a href="${profilelink}"><c:out value="${result.firstname}" />
						<c:out value="${result.surname}" /></a>
				</div>

				<div class="results-interests">

					<c:forEach var="interest" items="${result.interests}"
						varStatus="status">

						<c:url var="interestLink" value="/search?s=${interest}" />

						<a href="${interestLink}"><c:out value="${interest}" /></a>

						<c:if test="${!status.last}"> | </c:if>

					</c:forEach>

				</div>
				<div class="results-contact">
					<div class="chat-icon-container">
						<a href="${chatviewLink}"><img id="chat-icon" src="${img}/message.png" /></a>
					</div> 
				</div>

			</div>

		</div>

	</div>
</c:forEach>