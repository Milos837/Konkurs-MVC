<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8" />
<title>Welcome</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/style.css" />
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css"
	integrity="sha384-Smlep5jCw/wG7hdkwQ/Z5nLIefveQRIY9nfy6xoR1uRYBtpZgI6339F5dgvm/e9B"
	crossorigin="anonymous">
</head>
<body class="bg bg-light">

	<div class="text-center naslov">
		<h1>
			<a href="/postings" class="text-muted">Konkurs</a>
		</h1>
	</div>

	<div class="container">

		<form:form modelAttribute="applicationDTO" method="POST"
			action="/postings/${posting.id}/apply" enctype="multipart/form-data">
			<div class="form-group">
				<div class="row">
					<div class="col-md">
						<label for="firstName">Ime:</label>
						<form:input path="firstName" cssClass="form-control"
							placeholder="Unesite ime ..." autofocus="autofocus"
							required="required" />
					</div>
					<div class="col-md">
						<label for="lastName">Prezime:</label>
						<form:input path="lastName" cssClass="form-control"
							placeholder="Unesite prezime ..." required="required"/>
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
					placeholder="Unesite email adresu ..." required="required"/>
			</div>

			<div class="form-group">
				<div class="row">
					<div class="col-md">
						<label for="idNumber">Broj licne karte:</label>
						<form:input path="idNumber" cssClass="form-control"
							placeholder="Unesite broj licne karte ..." required="required"/>
					</div>
					<div class="col-md">
						<label for="ssn">JMBG:</label>
						<form:input path="ssn" cssClass="form-control"
							placeholder="Unesite jmbg ..." required="required"/>
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

			<div class="form-group">
				<label for="candidateNote">Napomena:</label>
				<form:textarea path="candidateNote" rows="3" cssClass="w-100"
					placeholder="Unesite napomenu ..." />
			</div>

			<div class="form-group">
				<label for="file">Okacite CV (u PDF formatu):</label> <input
					type=file name="file" accept="application/pdf" required="required">
			</div>

			<div class="text-center">
				<input type="submit" class="btn btn-success" value="Posalji">
			</div>

		</form:form>

	</div>

</body>

</html>