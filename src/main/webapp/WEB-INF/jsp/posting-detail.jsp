<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

			<div class="container">
				<div class="card mt-5 shadow">
					<h5 class="card-header bg-secondary text-white">${posting.name}</h5>
					<div class="card-body">

						<div class="ml-4">
							<h5 class="card-title">Datum postavljanja: ${posting.date }</h5>
							<h3 class="mt-4">Zaduzenja:</h3>
							<ul>
								<c:forEach items="${posting.responsibilities}" var="res"
									varStatus="status">
									<li>${res.responsibility}</li>
								</c:forEach>
							</ul>

							<h3 class="mt-4">Uslovi:</h3>
							<ul>
								<c:forEach items="${posting.requirements}" var="req"
									varStatus="status">
									<li>${req.requirement}</li>
								</c:forEach>
							</ul>

							<h3 class="mt-4">Nudimo:</h3>
							<ul>
								<c:forEach items="${posting.offering}" var="off"
									varStatus="status">
									<li>${off.offering}</li>
								</c:forEach>
							</ul>
						</div>

						<div class="text-center">
							<form action="${pageContext.request.contextPath}/postings/${posting.id}/apply">
								<input type="submit" class="btn btn-success mr-3"
									value="Prijavi se" />
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>


		<%@ include file="parts/footer.jsp"%>
	</div>



</body>

</html>