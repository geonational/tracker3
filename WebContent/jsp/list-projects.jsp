<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<link rel="stylesheet" href="../css/bootstrap.min.css" />
<script src="../js/bootstrap.min.js"></script>
<!--  need this for the header -->
<script src="//code.jquery.com/jquery-1.10.2.js"></script>

</head>

<body>
	<!-- header -->
	<div id="header"></div>
	<br />


	<div class="container">

		<script> 
 			 $("#header").load("header.html"); 
  			</script>
		<h2>Projects</h2>
		<!--Search Form -->
		<form action="${pageContext.request.contextPath}/ProjectServlet"
			method="get" id="seachProjectForm" role="form">
			<input type="hidden" id="searchAction" name="searchAction"
				value="searchByName">
			<div class="form-group col-xs-5">
				<input type="text" name="projectName" id="projectName"
					class="form-control" required="true"
					placeholder="Name of Project to search % is wildcard" />
			</div>
			<button type="submit" class="btn btn-info">
				<span class="glyphicon glyphicon-search"></span> Search
			</button>
			<br></br> <br></br>
		</form>

		<c:if test="${not empty message}">
			<div class="alert alert-success">${message}</div>
		</c:if>



		<!--Project List-->
		<form action="${pageContext.request.contextPath}/ProjectServlet"
			method="post" id="ProjectForm" role="form">
			<input type="hidden" id="idProject" name="idProject"> <input
				type="hidden" id="action" name="action">
			<c:choose>
				<c:when test="${not empty projectList}">
					<table class="table table-striped">
						<thead>
							<tr>
								<td>#</td>
								<td>Project Name</td>
								<td>Total Hours</td>
								<td>Total Dollars</td>
							</tr>
						</thead>
						<c:forEach var="project" items="${projectList}">
							<c:set var="classSucess" value="" />
							<c:if test="${idProject == project.projectId}">
								<c:set var="classSucess" value="info" />
							</c:if>
							<tr class="${classSucess}">
								<td><a
									href="${pageContext.request.contextPath}/ProjectServlet?projectId=${project.projectId}&searchAction=searchById">${project.projectId}</a></td>
								<td>${project.projectName}</td>
								<td>${project.totalHours}</td>
								<td>${project.totalDollars}</td>
								<td><a href="#" id="remove"
									onclick="document.getElementById('action').value = 'remove';document.getElementById('idProject').value = '${project.projectId}';
  document.getElementById('ProjectForm').submit();">
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
		<form action="jsp/new-project.jsp">
			<br></br>
			<button type="submit" class="btn btn-primary  btn-md">New
				Project</button>
		</form>
	</div>
</body>
</html>