<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<nav class="navbar navbar-expand-lg navbar-dark bg-secondary">
	<div class="container">
		<a class="navbar-brand" href="${pageContext.request.contextPath}/">Konkurs</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarsExample07" aria-controls="navbarsExample07"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarsExample07">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item"><a class="nav-link"
					href="${pageContext.request.contextPath}/postings">Oglasi</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Link</a></li>
			</ul>

			<security:authorize access="!isAuthenticated()">
				<form:form action="${pageContext.request.contextPath}/login"
					method="get">
					<input type="submit" class="btn btn-light" value="Prijavi se">
				</form:form>
			</security:authorize>

			<security:authorize access="isAuthenticated()">
				<form:form action="${pageContext.request.contextPath}/logout"
					method="post">
					<input type="submit" class="btn btn-light" value="Odjavi se">
				</form:form>
			</security:authorize>
		</div>
	</div>
</nav>
