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
<script>
	function myFunction() {
		var input, filter, table, tr, td, i;
		input = document.getElementById("myInput");
		filter = input.value.toUpperCase();
		table = document.getElementById("myTable");
		tr = table.getElementsByTagName("tr");
		for (i = 0; i < tr.length; i++) {
			td = tr[i].getElementsByTagName("td")[1];
			if (td) {
				if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
					tr[i].style.display = "";
				} else {
					tr[i].style.display = "none";
				}
			}
		}
	}
</script>
</head>
<body class="bg bg-light">

	<div id="container">

		<%@ include file="parts/header.jsp"%>




		<div id="body">

			<div class="container-fluid">
				<div class="row">
					<div class="col-md-3 offset-md-1">
						<div class="card mt-5">
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
						<div class="clearfix mt-4">
							<h2 class="float-left">Aplikacije:</h2>
							<div class="float-right">
								<div class="form-group">
									<input type="text" class="form-control" name="searchTerm"
										id="myInput" placeholder="Pretraga ..." onkeyup="myFunction()" autofocus>
								</div>
							</div>
						</div>
						<!--  Tabela sa svim aplikantima  -->
						<table class="table table-striped table-hover tabela" id="myTable">
							<thead>
								<tr>
									<th scope="col">ID</th>
									<th scope="col">Ime i prezime</th>
									<th scope="col">Email</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${applications}" var="app" varStatus="status">
									<tr>
										<td>
										<a href="${pageContext.request.contextPath}/admin/postings/${posting.id }/applications/${app.id}">${app.id}</a>
										</td>
										<td>
										<a href="${pageContext.request.contextPath}/admin/postings/${posting.id }/applications/${app.id}">
										${app.candidate.firstName} ${app.candidate.lastName}</a>
										</td>
										<td>
										<a href="${pageContext.request.contextPath}/admin/postings/${posting.id }/applications/${app.id}">
										${app.candidate.email}</a>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
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