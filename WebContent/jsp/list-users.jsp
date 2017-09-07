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
		<h2>Users</h2>
		<!--Search Form -->
		<form action="${pageContext.request.contextPath}/UserServlet"
			method="get" id="seachUserForm" role="form">
			<input type="hidden" id="searchAction" name="searchAction"
				value="searchByName">
			<div class="form-group col-xs-5">
				<input type="text" name="userName" id="userName"
					class="form-control" required="true"
					placeholder="Enter the name of  User % is wildcard" />
			</div>
			<button type="submit" class="btn btn-info">
				<span class="glyphicon glyphicon-search"></span> Search
			</button>
			<br></br> <br></br>
		</form>

		<c:if test="${not empty message}">
			<div class="alert alert-success">${message}</div>
		</c:if>



		<!--user List-->
		<form action="${pageContext.request.contextPath}/UserServlet"
			method="post" id="userForm" role="form">
			<input type="hidden" id="idUser" name="idUser"> <input
				type="hidden" id="action" name="action">
			<c:choose>
				<c:when test="${not empty userList}">
					<table class="table table-striped">
						<thead>
							<tr>
								<td>#</td>
								<td>User Name</td>
								<td>Street</td>
								<td>City</td>
								<td>Zip</td>
								<td>Company Name</td>
							</tr>
						</thead>
						<c:forEach var="user" items="${userList}">
							<c:set var="classSucess" value="" />
							<c:if test="${idUser == user.userId}">
								<c:set var="classSucess" value="info" />
							</c:if>
							<tr class="${classSucess}">
								<td><a
									href="${pageContext.request.contextPath}/UserServlet?userId=${user.userId}&searchAction=searchById">${user.userId}</a></td>
								<td>${user.username}</td>
								<td>${user.street}</td>
								<td>${user.city}</td>
								<td>${user.zip}</td>
								<td>${user.companyName}</td>
								<td><a href="#" id="remove"
									onclick="document.getElementById('action').value = 'remove';document.getElementById('iduser').value = '${user.userId}';
  document.getElementById('userForm').submit();">
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
		<form action="jsp/new-user.jsp">
			<br></br>
			<button type="submit" class="btn btn-primary  btn-md">New
				user</button>
		</form>
	</div>
</body>
</html>