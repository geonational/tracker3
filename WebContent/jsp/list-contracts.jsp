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
		<h2>Contracts</h2>
		<!--Search Form -->
		<form action="${pageContext.request.contextPath}/ContractServlet"
			method="get" id="seachContractForm" role="form">
			<input type="hidden" id="searchAction" name="searchAction"
				value="searchByName">
			<div class="form-group col-xs-5">
				<input type="text" name="contractName" id="contractName"
					class="form-control" required="true"
					placeholder="Type the ID of the Contract" />
			</div>
			<button type="submit" class="btn btn-info">
				<span class="glyphicon glyphicon-search"></span> Search
			</button>
			<br></br> <br></br>
		</form>

		<c:if test="${not empty message}">
			<div class="alert alert-success">${message}</div>
		</c:if>



		<!--Contract List-->
		<form action="${pageContext.request.contextPath}/ContractServlet"
			method="post" id="contractForm" role="form">
			<input type="hidden" id="idContract" name="idContract"> <input
				type="hidden" id="action" name="action">
			<c:choose>
				<c:when test="${not empty contractList}">
					<table class="table table-striped">
						<thead>
							<tr>
								<td>#</td>
								<td>User ID</td>
								<td>Total Hours</td>
								<td>Total Dollars</td>
								<td>Labor Category</td>
								<td>PO Number</td>
								<td>Start Date</td>
								<td>End Date</td>
								<td>Rate</td>
							</tr>
						</thead>
						<c:forEach var="contract" items="${contractList}">
							<c:set var="classSucess" value="" />
							<c:if test="${idContract == contract.contractId}">
								<c:set var="classSucess" value="info" />
							</c:if>
							<tr class="${classSucess}">
								<td><a
									href="${pageContext.request.contextPath}/ContractServlet?contractId=${contract.contractId}&searchAction=searchById">${contract.contractId}</a></td>
								<td>${contract.user.username}</td>
								<td>${contract.totalHours}</td>
								<td>${contract.totalDollars}</td>
								<td>${contract.labourCategory}</td>
								<td>${contract.poNumber}</td>
								<td><fmt:formatDate pattern="MMM dd yyyy"
									value="${contract.startDate}" /></td>
								<td><fmt:formatDate pattern="MMM dd yyyy"
									value="${contract.endDate}" /></td>
								<td>${contract.rate}</td>
								<td><a href="#" id="remove"
									onclick="document.getElementById('action').value = 'remove';document.getElementById('idContract').value = '${contract.contractId}';
  document.getElementById('contractForm').submit();">
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
		<form action="jsp/new-contract.jsp">
			<br></br>
			<button type="submit" class="btn btn-primary  btn-md">New
				contract</button>
		</form>
	</div>
</body>
</html>