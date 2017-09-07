<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<html>
<head>
<link rel="stylesheet" href="../css/bootstrap.min.css" />
<script src="../js/bootstrap.min.js"></script>
<!--  need this for the header -->
<script type="text/javascript" src="//code.jquery.com/jquery-1.10.2.js"></script>

</head>

<body>
	<!-- header -->
	<div id="header"></div>
	<br />


	<div class="container">

		<script> 
 			 $("#header").load("header.html"); 
  			</script>
		<h2>Timesheets</h2>


		<c:if test="${not empty message}">
			<div class="alert alert-success">${message}</div>
		</c:if>

		<form action="${pageContext.request.contextPath}/TimesheetServlet">
			<input type="hidden" name="searchAction" id="searchAction"
				value="searchForAdd" /> <br></br>
			<button type="submit" class="btn btn-primary  btn-md">New
				timesheet</button>
		</form>


		<!--timesheet List-->
		<form action="${pageContext.request.contextPath}/TimesheetServlet"
			method="post" id="timesheetForm" role="form">
			<input type="hidden" id="idTimesheet" name="idTimesheet"> <input
				type="hidden" id="action" name="action">
			<c:choose>
				<c:when test="${not empty timesheetList}">
					<table class="table table-striped">
						<thead>
							<tr>
								<td>#</td>
								<td>User</td>
								<td>Project</td>
								<td>Start Week</td>
								<td>Monday</td>
								<td>Tuesday</td>
								<td>Wednesday</td>
								<td>Thursday</td>
								<td>Friday</td>
								<td>Saturday</td>
								<td>Sunday</td>
							</tr>
						</thead>
						<c:forEach var="timesheet" items="${timesheetList}">
							<c:set var="classSucess" value="" />
							<c:if test="${idTimesheet == timesheet.timesheetId}">
								<c:set var="classSucess" value="info" />
							</c:if>
							<tr class="${classSucess}">
								<td><a
									href="${pageContext.request.contextPath}/TimesheetServlet?timesheetId=${timesheet.timesheetId}&searchAction=searchById">${timesheet.timesheetId}</a></td>
								<td>${timesheet.user.username}</td>
								<td>${timesheet.project.projectName}</td>
								<td><fmt:formatDate pattern="MMM dd yyyy"
									value="${timesheet.weekStart}" /></td>
								<td>${timesheet.monday}</td>
								<td>${timesheet.tuesday}</td>
								<td>${timesheet.wednesday}</td>
								<td>${timesheet.thursday}</td>
								<td>${timesheet.friday}</td>
								<td>${timesheet.saturday}</td>
								<td>${timesheet.sunday}</td>
								<td><a href="#" id="remove"
									onclick="document.getElementById('action').value = 'remove';document.getElementById('idTimesheet').value = '${timesheet.timesheetId}';
  document.getElementById('timesheetForm').submit();">
										<span class="glyphicon glyphicon-trash" />
								</a></td>
							</tr>
						</c:forEach>
					</table>
				</c:when>
				<c:otherwise>
					<br>
					<div class="alert alert-info">No people found matching your
						search criteria</div>
				</c:otherwise>
			</c:choose>
		</form>
		<form action="${pageContext.request.contextPath}/TimesheetServlet">
			<input type="hidden" name="searchAction" id="searchAction"
				value="searchForAdd" /> <br></br>
			<button type="submit" class="btn btn-primary  btn-md">New
				timesheet</button>
		</form>
	</div>
</body>
</html>