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

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
	$(document)
			.ready(
					function() {

						var counterLang = 0;
						var counterEdu = 0;
						var counterCert = 0;

						$('#dodajLang')
								.click(
										function() {
											var toAdd = $('#sel1').val();
											var text = $(
													'#sel1 option:selected')
													.text();
											if (toAdd.length !== 0) {
												$('#listaLang')
														.append(
																'<input type="hidden" class="list-group-item" name="language['
																	+ counterLang + '].languageId" value ="'
																	+ toAdd + '">'
																		+ '<li class="list-group-item">'
																		+ text
																		+ '</li>');

												counterLang++;
											}

										});
						$('#dodajEdu')
								.click(
										function() {
											var schoolName = $(
													'input[name=schoolName]')
													.val();
											var schoolNote = $(
													'input[name=schoolNote]')
													.val();
											if (schoolName.length !== 0) {
												$('#listaEdu')
														.append(
																'<input type="hidden" class="list-group-item" name="education['
															+ counterEdu + '].schoolName" value ="'
															+ schoolName + '">' +
															'<input type="hidden" class="list-group-item" name="education['
															+ counterEdu + '].note" value ="'
															+ schoolNote + '">' +
															'<li class="list-group-item">' + schoolName + ', Napomena: ' + schoolNote + '</li>');
												counterEdu++;
											}

										});
						$('#dodajCert')
								.click(
										function() {
											var certificateName = $(
													'input[name=certificateName]')
													.val();
											var certificateNote = $(
													'input[name=certificateNote]')
													.val();
											if (certificateName.length !== 0) {
												$('#listaCert')
														.append(
																'<input type="hidden" class="list-group-item" name="certifications['
															+ counterCert + '].certificate" value ="'
															+ certificateName + '">' +
															'<input type="hidden" class="list-group-item" name="certifications['
															+ counterCert + '].note" value ="'
															+ certificateNote + '">' +
															'<li class="list-group-item">' + certificateName + ', Napomena: ' + certificateNote + '</li>');
												counterCert++;
											}
		
										});
					});
</script>

</head>
<body class="bg bg-light">

	<div id="container">
		<%@ include file="parts/header.jsp"%>

		<div id="body">
			<div class="text-center text-secondary">
				<h2 class="mt-5 mb-5">Prijava za konkurs ${posting.name}</h2>
			</div>

			<div class="container">

				<form:form modelAttribute="applicationDto" method="POST"
					action="${pageContext.request.contextPath}/postings/${posting.id}/apply"
					enctype="multipart/form-data">
					<div class="form-group">
						<div class="row">
							<div class="col-md">
								<label for="firstName">Ime:</label>
								<form:input path="firstName" cssClass="form-control"
									placeholder="Unesite ime ..." autofocus="autofocus"
									required="required" value="${applicationDto.firstName}" />
								<form:errors path="firstName" cssClass="text-danger" />
							</div>
							<div class="col-md">
								<label for="lastName">Prezime:</label>
								<form:input path="lastName" cssClass="form-control"
									placeholder="Unesite prezime ..." required="required"
									value="${applicationDto.lastName}" />
								<form:errors path="lastName" cssClass="text-danger" />
							</div>
						</div>
					</div>

					<div class="form-group">
						<span class="h6 mr-4">Pol:</span>
						<div class="form-check form-check-inline">
							<label class="form-check-label" for="radio1"> Muski </label>
							<form:radiobutton path="gender" id="radio1" value="MALE" />
						</div>
						<div class="form-check form-check-inline">
							<label class="form-check-label" for="radio2"> Zenski </label>
							<form:radiobutton path="gender" value="FEMALE" id="radio2" />
						</div>
					</div>

					<div class="form-group">
						<label for="email">Email addresa:</label>
						<form:input path="email" cssClass="form-control"
							placeholder="Unesite email adresu ..." required="required"
							value="${applicationDto.email}" />
						<form:errors path="email" cssClass="text-danger" />
					</div>

					<div class="form-group">
						<div class="row">
							<div class="col-md">
								<label for="idNumber">Broj licne karte:</label>
								<form:input path="idNumber" cssClass="form-control"
									placeholder="Unesite broj licne karte ..." required="required"
									value="${applicationDto.idNumber}" />
								<form:errors path="idNumber" cssClass="text-danger" />
							</div>
							<div class="col-md">
								<label for="ssn">JMBG:</label>
								<form:input path="ssn" cssClass="form-control"
									placeholder="Unesite jmbg ..." required="required"
									value="${applicationDto.ssn}" />
								<form:errors path="ssn" cssClass="text-danger" />
							</div>
						</div>
					</div>

					<div class="form-group">
						<div class="row">
							<div class="col-md">
								<label for="citizenshipId">Drzavljanstvo:</label>
								<form:select cssClass="form-control" path="citizenshipId">
									<form:options itemValue="id" itemLabel="citizenship"
										items="${citizenships}" />
								</form:select>
							</div>
							<div class="col-md">
								<label for="educationLevel">Stepen strucne spreme:</label>
								<form:select cssClass="form-control" path="educationLevel">
									<form:option value="I" label="Prvi" />
									<form:option value="II" label="Drugi" />
									<form:option value="III" label="Treci" />
									<form:option value="IV" label="Cetvrti" />
									<form:option value="V" label="Peti" />
									<form:option value="VI" label="Sesti" />
									<form:option value="VII" label="Sedmi" />
									<form:option value="VIII" label="Osmi" />
								</form:select>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="col-md">
							<div class="form-group">
								<ul class="list-group" id="listaLang">
									<li class="list-group-item list-group-item-secondary">Jezici:
										<button type="button"
											class="btn btn-sm btn-secondary float-right"
											data-toggle="modal" data-target="#exampleModal1">
											Dodaj</button>
									</li>
								</ul>
							</div>
						</div>
						<div class="col-md">
							<div class="form-group">
								<ul class="list-group" id="listaEdu">
									<li class="list-group-item list-group-item-secondary">Skole:
										<button type="button"
											class="btn btn-sm btn-secondary float-right"
											data-toggle="modal" data-target="#exampleModal2">
											Dodaj</button>
									</li>
								</ul>
							</div>
						</div>
						<div class="col-md">
							<div class="form-group">
								<ul class="list-group" id="listaCert">
									<li class="list-group-item list-group-item-secondary">Strucni
										radovi:
										<button type="button"
											class="btn btn-sm btn-secondary float-right"
											data-toggle="modal" data-target="#exampleModal3">
											Dodaj</button>
									</li>
								</ul>
							</div>
						</div>
					</div>

					<div class="form-group">
						<label for="candidateNote">Napomena:</label>
						<form:textarea path="candidateNote" rows="3" cssClass="w-100"
							placeholder="Unesite napomenu ..." />
						<form:errors path="candidateNote" cssClass="text-danger" />
					</div>

					<div class="form-group">
						<label for="file">Okacite CV (u PDF formatu)*:</label><br>
						<input
							type=file name="file" accept="application/pdf"
							required="required">
					</div>
					
					<div class="form-group">
						<label for="mLetter">Okacite motivaciono pismo (u PDF formatu):</label><br>
						<input
							type=file name="mLetter" accept="application/pdf">
					</div>
					
					<div class="form-group">
						<label for="cLetter">Okacite propratno pismo (u PDF formatu):</label><br>
						<input
							type=file name="cLetter" accept="application/pdf">
					</div>

					<div class="text-center">
						<input type="submit" class="btn btn-success mb-3" value="Posalji">
					</div>

				</form:form>

			</div>
		</div>

		<!-- Modal za jezike -->
		<div class="modal fade" id="exampleModal1" tabindex="-1" role="dialog"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Dodaj jezik</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>

					<div class="modal-body">

						<div class="form-group">
							<label for="sel1">Izaberite jezik:</label> <select
								class="form-control" id="sel1">
								<c:forEach items="${languages}" var="l" varStatus="status">
									<option value="${l.id}">${l.language},Nivo: ${l.level}</option>
								</c:forEach>
							</select>
						</div>

					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Zatvori</button>
						<button type="button" class="btn btn-primary" id="dodajLang">Dodaj</button>
					</div>

				</div>
			</div>
		</div>

		<!-- Modal za skole -->
		<div class="modal fade" id="exampleModal2" tabindex="-1" role="dialog"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Dodaj skolu</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>

					<div class="modal-body">

						<div class="form-group">
							<label for="schoolName">Naziv skole:</label> <input type="text"
								class="form-control" placeholder="Unesite naziv skole ..." name="schoolName">
						</div>
						<div class="form-group">
							<label for="schoolNote">Napomena:</label> <input type="text"
								class="form-control" placeholder="Unesite napomenu ..." name="schoolNote">
						</div>

					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Zatvori</button>
						<button type="button" class="btn btn-primary" id="dodajEdu">Dodaj</button>
					</div>

				</div>
			</div>
		</div>
		
		<!-- Modal za strucne radove -->
		<div class="modal fade" id="exampleModal3" tabindex="-1" role="dialog"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Dodaj strucni rad</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>

					<div class="modal-body">

						<div class="form-group">
							<label for="certificateName">Naziv strucnog rada:</label> <input type="text"
								class="form-control" placeholder="Unesite naziv strucnog rada ..." name="certificateName">
						</div>
						<div class="form-group">
							<label for="certificateNote">Napomena:</label> <input type="text"
								class="form-control" placeholder="Unesite napomenu ..." name="certificateNote">
						</div>

					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Zatvori</button>
						<button type="button" class="btn btn-primary" id="dodajCert">Dodaj</button>
					</div>

				</div>
			</div>
		</div>


		<%@ include file="parts/footer.jsp"%>
	</div>



</body>

</html>