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




<title>New Project</title>
</head>
<body>
	<div class="container">
		<form action="${pageContext.request.contextPath}/ProjectServlet"
			method="post" role="form" data-toggle="validator">
			<c:if test="${empty action}">
				<c:set var="action" value="add" />
			</c:if>
			<input type="hidden" id="action" name="action" value="${action}">
			<input type="hidden" id="idProject" name="idProject"
				value="${project.projectId}">
			<h2>Project</h2>
			<div class="form-group row">




				<div class="form-group row">
					<label for="projectname" class="col-sm-2 form-control-label">Project
						Name:</label>
					<div class="col-sm-6">

						<input type="text" name="projectname" id="projectname"
							class="form-control" value="${project.projectName}"
							required="true" />
					</div>
				</div>


				<div class="form-group row">

					<label for="totalhours" class="col-sm-2 form-control-label">Total
						Hours:</label>
					<div class="col-sm-6">

						<input type="text" name="totalhours" id="totalhours"
							class="form-control" value="${project.totalHours}"
							placeholder="0" required="true" />
					</div>
				</div>

				<div class="form-group row">


					<label for="totaldollars" class="col-sm-2 form-control-label">Total
						Dollars:</label>
					<div class="col-sm-6">

						<input type="text" name="totaldollars" id="totaldollars"
							class="form-control" value="${project.totalDollars}"
							placeholder="0" required="true" />
					</div>
				</div>

				<br></br>
				<button type="submit" class="btn btn-primary  btn-md">Accept</button>
		</form>
	</div>
</body>
</html>