<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<style>
.t1 {
	color: red;
}
</style>
</head>
<body>
	<div class='container'>
		<h3>Books Directory</h3>
		<hr>

		<!-- Add a search form -->





		<form action="/books/search" class="form-inline">

			<!-- Add a button -->
			<a href="/books/showFormForAdd"
				class="btn btn-primary btn-sm mb-3"> Add Book </a> <input
				type="search" name="name" placeholder="Name"
				class="form-control-sm ml-5 mr-2 mb-3" /> 
				<input type="search"
				name="author" placeholder="Author" class="form-control-sm mr-2 mb-3" />

			<button type="submit" class="btn btn-success btn-sm mb-3">Search</button>
			<a href="/logout"
				class="btn btn-primary btn-sm mb-3 mx-auto"> Logout </a>

		</form>

		<h1>List Of books</h1>
		<table class="table table-bordered table-striped t1">
			<thead class="thead-dark">
				<tr>
					<th>Name</th>
					<th>Category</th>
					<th>Author</th>

					<th>Action</th>

				</tr>
			</thead>

			<tbody>
				<c:forEach items="${Books}" var="tempBook">
					<tr>
						<td><c:out value="${tempBook.name}" /></td>
						<td><c:out value="${tempBook.category}" /></td>
						<td><c:out value="${tempBook.author}" /></td>

						<td>
							<!-- Add "update" button/link --> <a
							href="/books/showFormForUpdate?bookId=${tempBook.bookid}"
							class="btn btn-warning btn-sm"> Update </a> <!-- Add "delete" button/link -->
							<a
							href="/books/delete?bookId=${tempBook.bookid}"
							class="btn btn-danger btn-sm"
							onclick="if (!(confirm('Are you sure you want to delete this employee?'))) return false">
								Delete </a>

						</td>


					</tr>
				</c:forEach>

			</tbody>
		</table>
	</div>
</body>
</html>