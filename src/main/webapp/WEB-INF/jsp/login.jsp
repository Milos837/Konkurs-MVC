<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Welcome</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/style.css" />
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css"
	integrity="sha384-Smlep5jCw/wG7hdkwQ/Z5nLIefveQRIY9nfy6xoR1uRYBtpZgI6339F5dgvm/e9B"
	crossorigin="anonymous">
</head>
<body class="bg bg-light">

	<div id="container">
		<%@ include file="parts/header.jsp"%>


		<div id="body">
			
			<form:form
				action="${pageContext.request.contextPath}/authenticateLogin"
				method="POST" cssClass="form form-signin" id="form-signin">
				<h2 class="text-center">Prijavljivanje</h2>
				<div class="form-group">
					<label for="username">Korisnicko ime:</label> <input type="text"
						class="form-control" name="username"
						placeholder="Unesite korisnicko ime ..." required autofocus>
				</div>
				<div class="form-group">
					<label for="password">Lozinka:</label> <input type="text"
						class="form-control" name="password"
						placeholder="Unesite lozinku ..." required>
				</div>

				<input type="submit" value="login" class="btn btn-block btn-success">

				<c:if test="${param.error != null}">
					<div class="alert alert-danger mt-4 fade show">Pogresno
						korisnicko ime i/ili lozinka.</div>
				</c:if>

			</form:form>



		</div>


		<%@ include file="parts/footer.jsp"%>
	</div>



</body>

</html>