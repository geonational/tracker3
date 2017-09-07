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
    $( "#datepicker1" ).datepicker();
    $( "#datepicker2" ).datepicker();
    
    
  });
  </script>



<title>New Contract</title>
</head>
<body>
	<div class="container">
		<form action="${pageContext.request.contextPath}/ContractServlet"
			method="post" role="form" data-toggle="validator">
			<c:if test="${empty action}">
				<c:set var="action" value="add" />
			</c:if>
			<input type="hidden" id="action" name="action" value="${action}">
			<input type="hidden" id="idContract" name="idContract"
				value="${contract.contractId}">
			<h2>Contract</h2>
			<div class="form-group row">

				<label for="userid" class="col-sm-2 form-control-label">UserID:</label>
				<div class="col-sm-6">
					<input type="text" name="userid" id="userid" class="form-control"
						value="${contract.user.userId}" required="true" />
				</div>
			</div>

			<div class="form-group row">

				<label for="startdate" class="col-sm-2 form-control-label">Start
					date</label>
				<div class="col-sm-6">

					<input type="text" name="startdate" id="datepicker2"
						class="form-control"
						value="<fmt:formatDate pattern="MM/dd/yyyy" value="${contract.startDate}" />"
						maxlength="10" required="true" />
				</div>
			</div>


			<div class="form-group row">

				<label for="enddate" class="col-sm-2 form-control-label">End
					date</label>
				<div class="col-sm-6">

					<input type="text" name="enddate" id="datepicker1"
						class="form-control"
						value="<fmt:formatDate pattern="MM/dd/yyyy" value="${contract.endDate}" />"
						maxlength="10" required="true" />
				</div>
			</div>
			<div class="form-group row">
				<label for="rate" class="col-sm-2 form-control-label">Rate:</label>
				<div class="col-sm-6">

					<input type="text" name="rate" id="rate" class="form-control"
						value="${contract.rate}" required="true" />
				</div>
			</div>

			<div class="form-group row">


				<label for="labourcategory" class="col-sm-2 form-control-label">Labour
					Category:</label>
				<div class="col-sm-6">


					<input type="text" name="labourcategory" id="labourcategory"
						class="form-control" value="${contract.labourCategory}"
						required="true" />

				</div>
			</div>

			<div class="form-group row">
				<label for="ponumber" class="col-sm-2 form-control-label">PO
					Number:</label>
				<div class="col-sm-6">

					<input type="text" name="ponumber" id="ponumber"
						class="form-control" value="${contract.poNumber}" required="true" />
				</div>
			</div>

			<div class="form-group row">

				<label for="totalhours" class="col-sm-2 form-control-label">Total
					Hours:</label>
				<div class="col-sm-6">

					<input type="text" name="totalhours" id="totalhours"
						class="form-control" value="${contract.totalHours}"
						placeholder="0" required="true" />
				</div>
			</div>

			<div class="form-group row">


				<label for="totaldollars" class="col-sm-2 form-control-label">Total
					Dollars:</label>
				<div class="col-sm-6">

					<input type="text" name="totaldollars" id="totaldollars"
						class="form-control" value="${contract.totalDollars}"
						placeholder="0" required="true" />
				</div>
			</div>

			<br></br>
			<button type="submit" class="btn btn-primary  btn-md">Accept</button>

		</form>
	</div>
</body>
</html>