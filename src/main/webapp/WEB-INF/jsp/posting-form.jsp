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

			<div class="container col-md-6 offset-md-3">
				<div class="clearfix mt-5">
					<h2 class="float-left mt-4 mb-4">Novi konkurs:</h2>
				</div>

				<form:form modelAttribute="postingDto" method="POST"
					action="${pageContext.request.contextPath}/admin/postings/new">
					<div class="form-group">
						<label for="name">Naziv konkursa:</label>
						<form:input path="name" cssClass="form-control"
							placeholder="Unesite naziv konkursa ..." autofocus="autofocus"
							required="required" value="${postingDto.name}" />
						<form:errors path="name" cssClass="text-danger" />
					</div>

					<div class="form-group">
						<ul class="list-group">
							<li class="list-group-item list-group-item-secondary">Zaduzenja:
							<button type="button" class="btn btn-sm btn-primary float-right"
										data-toggle="modal" data-target="#exampleModal">
										Dodaj</button>
							</li>

							<c:forEach items="${postingDto.responsibilities}" var="res"
								varStatus="status">
								<li class="list-group-item">${res}</li>
							</c:forEach>
						</ul>
					</div>
				</form:form>

				<!-- Modal -->
				<div class="modal fade" id="exampleModal" tabindex="-1"
					role="dialog" aria-labelledby="exampleModalLabel"
					aria-hidden="true">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<form action="${pageContext.request.contextPath}/admin/postings/new/responsibility" method="post">
							<div class="modal-body">
								
								<input type="text" name="responsibility">
								
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary"
									data-dismiss="modal">Zatvori</button>
								<button type="submit" class="btn btn-primary">Dodaj</button>
							</div>
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