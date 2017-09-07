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







</head>
<body>
	<!-- header -->
	<div id="header"></div>
	<br />


	<div class="container">

		<script> 
 			 $("#header").load("header.html"); 
  			</script>





		<form action="${pageContext.request.contextPath}/InvoiceServlet"
			method="post" role="form" data-toggle="validator">
			<c:if test="${empty action}">
				<c:set var="action" value="add" />
			</c:if>
			<input type="hidden" id="action" name="action" value="${action}">
			<input type="hidden" id="idContract" name="idContract"
				value="${contract.contractId}">
			<h2>Generate Invoice</h2>
			<div class="form-group row">

				<label for="contractid" class="col-sm-2 form-control-label">Contract
					Name</label>
				<div class="col-xs-2">
					<select class="form-control" id="reportcontractid"
						name="reportcontractid">
						<c:forEach items="${contractList}" var="contract">
							<option value="${contract.contractId}"><c:out
									value="${contract.poNumber}" /></option>
						</c:forEach>
					</select>
				</div>
			</div>


			<div class="form-group row">

				<label for="month" class="col-sm-2 form-control-label">Select
					Month:</label>
				<div class="col-xs-2">
					<select class="form-control" id="reportmonth" name="reportmonth">
						<option>JAN</option>
						<option>FEB</option>
						<option>MAR</option>
						<option>APR</option>
						<option>MAY</option>
						<option>JUN</option>
						<option>JUL</option>
						<option>AUG</option>
						<option>SEP</option>
						<option>OCT</option>
						<option>NOV</option>
						<option>DEC</option>
					</select>
				</div>
			</div>
			<div class="form-group row">


				<label for="year" class="col-sm-2 form-control-label">Year:</label>
				<div class="col-xs-2">
					<input type="number" size="4" name="reportyear" id="reportyear"
						class="form-control" value="" required="true" />

				</div>
			</div>

			<div class="form-group row">

				<label for="reportnumber" class="col-sm-2 form-control-label">Report
					Number:</label>

				<div class="col-xs-2">
					<input type="text" name="reportnumber" id="reportnumber"
						class="form-control" value="" required="true" />

				</div>
			</div>

			<br></br>
			<button type="submit" class="btn btn-primary  btn-md">Generate</button>

		</form>
	</div>
</body>
</html>