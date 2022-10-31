<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">

<title>Save Book</title>
</head>

<body>

	<div class="container">

		<h3>Book Directory</h3>
		<hr>

		<p class="h4 mb-4">Enter Book</p>

		<form action="/books/save" method="POST">

			<!-- Add hidden form field to handle update -->
			<input type="hidden" name="bookid" value="${Book.bookid}" />

			<div class="form-inline">
				<input type="text" name="name" value="${Book.name}"
					class="form-control mb-4 col-4" placeholder="Name">



			</div>

			<div class="form-inline">

				<input type="text" name="category" value="${Book.category}"
					class="form-control mb-4 col-4" placeholder="Category">



			</div>

			<div class="form-inline">

				<input type="text" name="author" value="${Book.author}"
					class="form-control mb-4 col-4" placeholder="Author">



			</div>
	<input name="${_csrf.parameterName }"
	 type="hidden" value="${_csrf.token}"/>
			<button type="submit" class="btn btn-info col-2">Save</button>

		</form>

		<hr>
		<a href="/books/list">Back to Books List</a>

	</div>
</body>
</html>