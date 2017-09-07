<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<!--  <link rel="stylesheet" href="/resources/demos/style.css"> -->





<title>New User</title>
</head>
<body>
	<div class="container">
		<form action="${pageContext.request.contextPath}/UserServlet"
			method="post" role="form" data-toggle="validator">
			<c:if test="${empty action}">
				<c:set var="action" value="add" />
			</c:if>
			<input type="hidden" id="action" name="action" value="${action}">
			<input type="hidden" id="idUser" name="idUser" value="${user.userId}">
			<h2>User</h2>





			<div class="form-group row">
				<label for="username" class="col-sm-2 form-control-label">Username:</label>
				<div class="col-xs-2">

					<input type="text" name="username" id="username"
						class="form-control" value="${user.username}" required="true" />
				</div>
			</div>

			<div class="form-group row">


				<label for="street" class="col-sm-2 form-control-label">Street:</label>
				<div class="col-sm-6">


					<input type="text" name="street" id="street" class="form-control"
						value="${user.street}" required="true" />

				</div>
			</div>

			<div class="form-group row">
				<label for="city" class="col-sm-2 form-control-label">City:</label>
				<div class="col-xs-2">

					<input type="text" name="city" id="city" class="form-control"
						value="${user.city}" required="true" />
				</div>
			</div>

			<div class="form-group row">

				<label for="zip" class="col-sm-2 form-control-label">Zip:</label>
				<div class="col-xs-2">

					<input type="text" name="zip" id="zip" class="form-control"
						value="${user.zip}" required="true" />
				</div>
			</div>

			<div class="form-group row">


				<label for="companyname" class="col-sm-2 form-control-label">Company
					Name:</label>
				<div class="col-sm-6">

					<input type="text" name="companyname" id="companyname"
						class="form-control" value="${user.companyName}" required="true" />
				</div>
			</div>

			<br></br>
			<button type="submit" class="btn btn-primary  btn-md">Accept</button>

		</form>
	</div>
</body>
</html>