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

			<div class="container-fluid">
				<div class="row">
					<div class="col-md-3 offset-md-1">
						<div class="card mt-5 mb-5">
							<h5 class="card-header bg-secondary text-white">${posting.name}</h5>
							<div class="card-body">

								<div class="ml-4">
									<h5 class="card-title">Datum postavljanja: ${posting.date}</h5>
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
							</div>
						</div>

					</div>
					<div class="col-md-7">

						<div class="card mt-5">
							<div class="card-body">
								<div class="row">
									<div class="col-md-12">
										<h4>Detalji aplikacije sa ID: ${application.id}</h4>
										<hr>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<form>
											<div class="form-group row">
												<label for="firstName" class="col-4 col-form-label">Ime</label>
												<div class="col-8">
													<input id="firstName" name="firstName"
														class="form-control here" type="text"
														disabled value="${application.candidate.firstName}">
												</div>
											</div>
											<div class="form-group row">
												<label for="lastName" class="col-4 col-form-label">Prezime</label>
												<div class="col-8">
													<input id="lastName" name="lastName"
														class="form-control here" type="text"
														disabled value="${application.candidate.lastName}">
												</div>
											</div>
											<div class="form-group row">
												<label for="email" class="col-4 col-form-label">Email</label>
												<div class="col-8">
													<input id="email" name="email" class="form-control here"
														type="text"
														disabled value="${application.candidate.email}">
												</div>
											</div>
											<div class="form-group row">
												<label for="gender" class="col-4 col-form-label">Pol</label>
												<div class="col-8">
													<input id="gender" name="gender" class="form-control here"
														type="text"
														disabled value="${application.candidate.gender}">
												</div>
											</div>
											<div class="form-group row">
												<label for="idNumber" class="col-4 col-form-label">Broj
													licne karte</label>
												<div class="col-8">
													<input id="idNumber" name="idNumber"
														class="form-control here" type="text"
														disabled value="${application.candidate.idNumber}">
												</div>
											</div>
											<div class="form-group row">
												<label for="ssn" class="col-4 col-form-label">JMBG</label>
												<div class="col-8">
													<input id="ssn" name="ssn" class="form-control here"
														type="text"
														disabled value="${application.candidate.ssn}">
												</div>
											</div>
											<div class="form-group row">
												<label for="citizenship" class="col-4 col-form-label">Drzavljanstvo</label>
												<div class="col-8">
													<input id="citizenship" name="citizenship"
														class="form-control here" type="text"
														disabled value="${application.candidate.citizenship.citizenship}">
												</div>
											</div>
											<div class="form-group row">
												<label for="educationLevel" class="col-4 col-form-label">Nivo
													obrazovanja</label>
												<div class="col-8">
													<input id="educationLevel" name="educationLevel"
														class="form-control here" type="text"
														disabled value="${application.candidate.educationLevel}">
												</div>
											</div>
											<div class="form-group row">
												<label for="education" class="col-4 col-form-label">Skole</label>
												<div class="col-8">
													<ul class="list-group">
														<c:forEach items="${application.candidate.education}" var="e" varStatus="status">
															<li class="list-group-item">${e.schoolName}, Napomena: ${e.note}</li>
														</c:forEach>
													</ul>
												</div>
											</div>
											<div class="form-group row">
												<label for="language" class="col-4 col-form-label">Jezici</label>
												<div class="col-8">
													<ul class="list-group">
														<c:forEach items="${languages}" var="l" varStatus="status">
															<li class="list-group-item">${l.language}, Nivo: ${l.level}</li>
														</c:forEach>
													</ul>
												</div>
											</div>
											<div class="form-group row">
												<label for="certificates" class="col-4 col-form-label">Strucni
													radovi</label>
												<div class="col-8">
													<ul class="list-group">
														<c:forEach items="${application.certifications}" var="c" varStatus="status">
															<li class="list-group-item">${c.certificate}, Napomena: ${c.note}</li>
														</c:forEach>
													</ul>
												</div>
											</div>
											<hr>
											<div class="form-group row">
												<p class="col-4 col-form-label">Napomena</p>
												<div class="col-8">
													<p id="candidateNote">${application.note}</p>
												</div>
											</div>
											<hr>

											<div class="clearfix">
												<div class="float-left">
													<button type="button" class="btn btn-success mr-3">Preuzmi CV</button>
													<button type="button" class="btn btn-success mr-3">Preuzmi MP</button>
													<button type="button" class="btn btn-success">Preuzmi PP</button>
												</div>
												<div class="float-right">
													<button type="button" class="btn btn-info mr-3">Kontaktiraj</button>
													<button type="button" class="btn btn-danger">Obrisi</button>
												</div>
											</div>

										</form>
									</div>
								</div>

							</div>
						</div>


					</div>

				</div>
			</div>



			<div class="row">
				<%@ include file="parts/footer.jsp"%>
			</div>




		</div>
	</div>
</body>

</html>