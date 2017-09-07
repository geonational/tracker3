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

<script>
	$(function() {
		$("#datepicker1").datepicker();

	});
</script>



<title>New Timesheet</title>
</head>
<body>
	<div class="container">
		<form action="${pageContext.request.contextPath}/TimesheetServlet"
			method="post" role="form" data-toggle="validator">
			<c:if test="${empty action}">
				<c:set var="action" value="add" />
			</c:if>
			<input type="hidden" id="action" name="action" value="${action}">
			<input type="hidden" id="idTimesheet" name="idTimesheet"
				value="${timesheet.timesheetId}">
			<h2>New Timesheet</h2>
			<div class="form-group row">



				<label for="userid" class="col-sm-2 form-control-label">UserID:</label>
				<div class="col-xs-2">
					<select class="form-control" id="userid" name="userid">
						<c:forEach items="${userList}" var="user">
							<option value="${user.userId}"
								${user.userId == timesheet.user.userId ? 'selected="selected"' : ''}><c:out
									value="${user.username}" /></option>
						</c:forEach>
					</select>
				</div>
			</div>

			<div class="form-group row">

				<label for="projectid" class="col-sm-2 form-control-label">Project:</label>
				<div class="col-xs-2">
					<select class="form-control" id="projectid" name="projectid">
						<c:forEach items="${projectList}" var="project">
							<option value="${project.projectId}"
								${project.projectId == timesheet.project.projectId ? 'selected="selected"' : ''}><c:out
									value="${project.projectName}" /></option>
						</c:forEach>
					</select>
				</div>
			</div>



			<div class="form-group row">

				<label for="weekstart" class="col-sm-2 form-control-label">Week
					Start</label>
				<div class="col-sm-6">

					<input type="text" name="weekstart" id="datepicker1"
						class="form-control"
						value="<fmt:formatDate pattern="MM/dd/yyyy" value="${timesheet.weekStart}" />"
						maxlength="10" required="true" />
				</div>
			</div>


			<div class="form-group row">
				<label for="monday" class="col-sm-2 form-control-label">Monday:</label>
				<div class="col-sm-6">

					<input type="text" name="monday" id="rate" class="form-control"
						value="${timesheet.monday}" placeholder="0" required="true" />
				</div>
			</div>

			<div class="form-group row">


				<label for="tuesday" class="col-sm-2 form-control-label">Tuesday:</label>
				<div class="col-sm-6">


					<input type="text" name="tuesday" id="tuesday" class="form-control"
						value="${timesheet.tuesday}" placeholder="0" required="true" />

				</div>
			</div>

			<div class="form-group row">
				<label for="wednesday" class="col-sm-2 form-control-label">Wednesday:</label>
				<div class="col-sm-6">

					<input type="text" name="wednesday" id="ponumber"
						class="form-control" value="${timesheet.wednesday}"
						placeholder="0" required="true" />
				</div>
			</div>

			<div class="form-group row">

				<label for="thursday" class="col-sm-2 form-control-label">Thursday:</label>
				<div class="col-sm-6">

					<input type="text" name="thursday" id="totalhours"
						class="form-control" value="${timesheet.thursday}" placeholder="0"
						required="true" />
				</div>
			</div>

			<div class="form-group row">


				<label for="friday" class="col-sm-2 form-control-label">Friday:</label>
				<div class="col-sm-6">

					<input type="text" name="friday" id="friday" class="form-control"
						value="${timesheet.friday}" placeholder="0" required="true" />
				</div>
			</div>

			<div class="form-group row">
				<label for="saturday" class="col-sm-2 form-control-label">Saturday:</label>
				<div class="col-sm-6">

					<input type="text" name="saturday" id="saturday"
						class="form-control" value="${timesheet.saturday}" required="true" />
				</div>
			</div>

			<div class="form-group row">
				<label for="sunday" class="col-sm-2 form-control-label">Sunday:</label>
				<div class="col-sm-6">

					<input type="text" name="sunday" id="sunday" class="form-control"
						value="${timesheet.sunday}" required="true" />
				</div>
			</div>



			<br></br>
			<button type="submit" class="btn btn-primary  btn-md">Accept</button>

		</form>
	</div>
</body>
</html>