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

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
	$(document)
			.ready(
					function() {

						var counterRes = 0;
						var counterReq = 0;
						var counterOff = 0;

						$('#dodajRes')
								.click(
										function() {
											var toAdd = $(
													'input[name=ListItemRes]')
													.val();
											if (toAdd.length !== 0) {
												$('#listaRes')
														.append(
																'<input type="text" class="list-group-item" name="responsibilities['
																	+ counterRes + ']" value ="'
																	+ toAdd + '">');
												counterRes++;
											}

										});
						$('#dodajReq')
						.click(
								function() {
									var toAdd = $(
											'input[name=ListItemReq]')
											.val();
									if (toAdd.length !== 0) {
										$('#listaReq')
												.append(
														'<input type="text" class="list-group-item" name="requirements['
															+ counterReq + ']" value ="'
															+ toAdd + '">');
										counterReq++;
									}

								});
						$('#dodajOff')
						.click(
								function() {
									var toAdd = $(
											'input[name=ListItemOff]')
											.val();
									if (toAdd.length !== 0) {
										$('#listaOff')
												.append(
														'<input type="text" class="list-group-item" name="offering['
															+ counterOff + ']" value ="'
															+ toAdd + '">');
										counterOff++;
									}

								});
					});
</script>

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
						<ul class="list-group" id="listaRes">
							<li class="list-group-item list-group-item-secondary">Zaduzenja:
								<button type="button" class="btn btn-sm btn-primary float-right"
									data-toggle="modal" data-target="#exampleModal1">
									Dodaj</button>
							</li>
						</ul>
					</div>
					
					<div class="form-group">
						<ul class="list-group" id="listaReq">
							<li class="list-group-item list-group-item-secondary">Uslovi:
								<button type="button" class="btn btn-sm btn-primary float-right"
									data-toggle="modal" data-target="#exampleModal2">
									Dodaj</button>
							</li>
						</ul>
					</div>
					
					<div class="form-group">
						<ul class="list-group" id="listaOff">
							<li class="list-group-item list-group-item-secondary">Nudimo:
								<button type="button" class="btn btn-sm btn-primary float-right"
									data-toggle="modal" data-target="#exampleModal3">
									Dodaj</button>
							</li>
						</ul>
					</div>

					<div class="text-center">
						<input type="submit" class="btn btn-success mb-3" value="Posalji">
					</div>


				</form:form>

				<!-- Modal1 -->
				<div class="modal fade" id="exampleModal1" tabindex="-1"
					role="dialog" aria-labelledby="exampleModalLabel"
					aria-hidden="true">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="exampleModalLabel">Dodaj zaduzenje</h5>
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>

							<div class="modal-body">

								<input type="text" name="ListItemRes" class="form-control"
									placeholder="Unesite novo zaduzenje ...">

							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary"
									data-dismiss="modal">Zatvori</button>
								<button type="button" class="btn btn-primary" id="dodajRes">Dodaj</button>
							</div>

						</div>
					</div>
				</div>
				
				<!-- Modal2 -->
				<div class="modal fade" id="exampleModal2" tabindex="-1"
					role="dialog" aria-labelledby="exampleModalLabel"
					aria-hidden="true">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="exampleModalLabel">Dodaj uslov</h5>
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>

							<div class="modal-body">

								<input type="text" name="ListItemReq" class="form-control"
									placeholder="Unesite novi uslov ...">

							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary"
									data-dismiss="modal">Zatvori</button>
								<button type="button" class="btn btn-primary" id="dodajReq">Dodaj</button>
							</div>

						</div>
					</div>
				</div>
				
				<!-- Modal3 -->
				<div class="modal fade" id="exampleModal3" tabindex="-1"
					role="dialog" aria-labelledby="exampleModalLabel"
					aria-hidden="true">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="exampleModalLabel">Dodaj sta nudimo</h5>
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>

							<div class="modal-body">

								<input type="text" name="ListItemOff" class="form-control"
									placeholder="Unesite sta nudimo ...">

							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary"
									data-dismiss="modal">Zatvori</button>
								<button type="button" class="btn btn-primary" id="dodajOff">Dodaj</button>
							</div>

						</div>
					</div>
				</div>


			</div>
		</div>

		<%@ include file="parts/footer.jsp"%>
	</div>



</body>

</html>