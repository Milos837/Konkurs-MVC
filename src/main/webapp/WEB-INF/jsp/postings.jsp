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
				<div class="clearfix mt-5">
					<h2 class="float-left mt-4 mb-4">Aktivni konkursi:</h2>
				</div>

				<table class="table table-striped">
					<thead>
						<tr>
							<th scope="col">ID</th>
							<th scope="col">Naziv</th>
							<th scope="col">Datum postavljanja</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${postings}" var="posting" varStatus="status">
							<tr>
								<td scope="row"><a
									href="${pageContext.request.contextPath}/postings/${posting.id }">${posting.id}</a></td>
								<td><a
									href="${pageContext.request.contextPath}/postings/${posting.id }">${posting.name}</a></td>
								<td><a
									href="${pageContext.request.contextPath}/postings/${posting.id }">${posting.date}</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

			</div>
		</div>


		<%@ include file="parts/footer.jsp"%>
	</div>



</body>

</html>