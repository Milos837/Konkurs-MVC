<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8" />
<title>Welcome</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/style.css" />
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css" integrity="sha384-Smlep5jCw/wG7hdkwQ/Z5nLIefveQRIY9nfy6xoR1uRYBtpZgI6339F5dgvm/e9B" crossorigin="anonymous">
</head>
<body>
	
	<div class="text-center">
	<h1>Konkurs</h1>
	</div>
	
	<div class="container">
	
	<table class="table table-striped">
		<thead>
		<tr>
			<th scope="col">ID:</th>
			<th scope="col">Name:</th>
			<th scope="col">Date:</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${postings}" var="posting" varStatus="status">
			<tr>
				<td scope="row"><a href="/postings/${posting.id }">${posting.id}</a></td>
				<td><a href="/postings/${posting.id }">${posting.name}</a></td>
				<td><a href="/postings/${posting.id }">${posting.date}</a></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
	</div>

</body>

</html>