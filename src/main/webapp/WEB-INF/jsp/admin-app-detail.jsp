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
						<div class="card mt-5 mb-5 shadow">
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

						<div class="card mt-5 mb-5 shadow">
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
														class="form-control here" type="text" disabled
														value="${application.candidate.firstName}">
												</div>
											</div>
											<div class="form-group row">
												<label for="lastName" class="col-4 col-form-label">Prezime</label>
												<div class="col-8">
													<input id="lastName" name="lastName"
														class="form-control here" type="text" disabled
														value="${application.candidate.lastName}">
												</div>
											</div>
											<div class="form-group row">
												<label for="email" class="col-4 col-form-label">Email</label>
												<div class="col-8">
													<input id="email" name="email" class="form-control here"
														type="text" disabled
														value="${application.candidate.email}">
												</div>
											</div>
											<div class="form-group row">
												<label for="gender" class="col-4 col-form-label">Pol</label>
												<div class="col-8">
													<input id="gender" name="gender" class="form-control here"
														type="text" disabled
														value="${application.candidate.gender}">
												</div>
											</div>
											<div class="form-group row">
												<label for="idNumber" class="col-4 col-form-label">Broj
													licne karte</label>
												<div class="col-8">
													<input id="idNumber" name="idNumber"
														class="form-control here" type="text" disabled
														value="${application.candidate.idNumber}">
												</div>
											</div>
											<div class="form-group row">
												<label for="ssn" class="col-4 col-form-label">JMBG</label>
												<div class="col-8">
													<input id="ssn" name="ssn" class="form-control here"
														type="text" disabled value="${application.candidate.ssn}">
												</div>
											</div>
											<div class="form-group row">
												<label for="citizenship" class="col-4 col-form-label">Drzavljanstvo</label>
												<div class="col-8">
													<input id="citizenship" name="citizenship"
														class="form-control here" type="text" disabled
														value="${application.candidate.citizenship.citizenship}">
												</div>
											</div>
											<div class="form-group row">
												<label for="educationLevel" class="col-4 col-form-label">Nivo
													obrazovanja</label>
												<div class="col-8">
													<input id="educationLevel" name="educationLevel"
														class="form-control here" type="text" disabled
														value="${application.candidate.educationLevel}">
												</div>
											</div>
											<div class="form-group row">
												<label for="education" class="col-4 col-form-label">Skole</label>
												<div class="col-8">
													<ul class="list-group">
														<c:forEach items="${application.candidate.education}"
															var="e" varStatus="status">
															<li class="list-group-item">${e.schoolName},
																Napomena: ${e.note}</li>
														</c:forEach>
														<c:if
															test="${application.candidate.education.size() == 0}">
															<li class="list-group-item">Kandidat nije uneo ovaj
																podatak.</li>
														</c:if>
													</ul>

												</div>
											</div>
											<div class="form-group row">
												<label for="language" class="col-4 col-form-label">Jezici</label>
												<div class="col-8">
													<ul class="list-group">
														<c:forEach items="${languages}" var="l" varStatus="status">
															<li class="list-group-item">${l.language},Nivo:
																${l.level}</li>
														</c:forEach>
														<c:if test="${languages.size() == 0}">
															<li class="list-group-item">Kandidat nije uneo ovaj
																podatak.</li>
														</c:if>
													</ul>
												</div>
											</div>
											<div class="form-group row">
												<label for="certificates" class="col-4 col-form-label">Strucni
													radovi</label>
												<div class="col-8">
													<ul class="list-group">
														<c:forEach items="${application.certifications}" var="c"
															varStatus="status">
															<li class="list-group-item">${c.certificate},
																Napomena: ${c.note}</li>
														</c:forEach>
														<c:if test="${application.certifications.size() == 0}">
															<li class="list-group-item">Kandidat nije uneo ovaj
																podatak.</li>
														</c:if>
													</ul>
												</div>
											</div>
											<hr>
											<div class="form-group row">
												<p class="col-4 col-form-label">Napomena</p>
												<div class="col-8">
													<p id="candidateNote">${application.candidate.note}</p>
													<c:if test="${application.candidate.note == ''}">
														<li class="list-group-item">Kandidat nije uneo ovaj
															podatak.</li>
													</c:if>
												</div>
											</div>
											<hr>

											<div class="clearfix">
												<div class="float-left">
													<a
														href="${pageContext.request.contextPath}/admin/postings/${posting.id }/applications/${application.id}/downloadCV/">
														<button type="button" class="btn btn-success mr-3">Preuzmi
															CV</button>
													</a>
													<c:if test="${application.hasMotivationalLetter}">
														<a
															href="${pageContext.request.contextPath}/admin/postings/${posting.id }/applications/${application.id}/downloadML/">
															<button type="button" class="btn btn-success mr-3">Preuzmi
																ML</button>
														</a>
													</c:if>
													<c:if test="${application.hasCoverLetter}">
														<a
															href="${pageContext.request.contextPath}/admin/postings/${posting.id }/applications/${application.id}/downloadCL/">
															<button type="button" class="btn btn-success mr-3">Preuzmi
																CL</button>
														</a>
													</c:if>

												</div>
												<div class="float-right">
													<button type="button" class="btn btn-info mr-3"
														data-toggle="modal" data-target="#exampleModal">Kontaktiraj</button>
													<a
														href="${pageContext.request.contextPath}/admin/postings/${posting.id }/applications/${application.id}/delete"
														onclick="if (!(confirm('Da li sigurno zelite da uklonite aplikaciju?'))) return false">
														<button type="button" class="btn btn-danger">Obrisi</button>
													</a>
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

			<!-- Email modal -->
			<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
				aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel">Posalji email</h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<form:form modelAttribute="email" method="post"
								action="${pageContext.request.contextPath}/admin/postings/${posting.id }/applications/${application.id}/sendEmail">
								<form:input path="to" cssClass="form-control mb-2"
									placeholder="Unesite adresu ..." autofocus="autofocus"
									value="${emai.to}" />
								<form:input path="subject" cssClass="form-control mb-2"
									value="${email.subject}" placeholder="Unesite subject ..."
									required="required" />
								<form:textarea path="text" rows="4" cssClass="w-100 rounded"
									placeholder=" Unesite poruku ..." value="${email.text}"
									required="required" />
								<hr>
								<button type="button" class="btn btn-secondary"
									data-dismiss="modal">Odustani</button>
								<input type="submit" class="btn btn-primary" value="Posalji">
							</form:form>
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