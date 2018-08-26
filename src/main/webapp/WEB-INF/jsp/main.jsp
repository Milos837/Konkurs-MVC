<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
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
			<div class="container text-center text-secondary naslov-main">
				<h4 class="display-4">Dobrodosli u Web aplikaciju Konkurs!</h4>
				<hr>
				<h4 class="lead">Kliknite na dugme ispod za pregled oglasa.</h4>
			</div>

			<div class="text-center">
				<a href="${pageContext.request.contextPath}/postings">
					<button class="btn btn-secondary btn-lg mt-3">Oglasi</button>
				</a>
			</div>
		</div>


		<%@ include file="parts/footer.jsp"%>
	</div>



</body>

</html>