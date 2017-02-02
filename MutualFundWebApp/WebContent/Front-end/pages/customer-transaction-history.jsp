<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="customer-template-top.jsp" />

<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header"><font size="5" face="verdana" color="#7E9DBB">&nbsp;Transaction History</font></h1>
		</div>
	</div>
	
	<div class="row">
		<c:forEach var="error" items="${errors}">
			<h5 style="color: red">${error}</h5>
		</c:forEach>
	</div>

	<!-- /.panel -->
	<div class="panel panel-default">
	<div class="panel-body">
	<form action="sellfund.do" method="POST">
		<div class="btn-group">
			<ul class="dropdown-menu pull-right" role="menu">
				<li><a href="#">Action</a></li>
				<li><a href="#">Another action</a></li>
				<li><a href="#">Something else here</a></li>
				<li class="divider"></li>
				<li><a href="#">Separated link</a></li>
			</ul>

		</div>

		<!-- /.panel-heading -->
		<div class="panel-body">
			<div class="row">
				<div>
					<div class="table-responsive">
						<table class="table table-bordered table-hover table-striped">
							<thead>
								<tr>
									<th>Transaction Date</th>
									<th>Operation</th>
									<th>Status</th>
									<th>Fund Name</th>
									<th>Number of Shares</th>
									<th>Share Price</th>
									<th>Total Amount</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach var ="transaction" items="${transactionhistory}" >
								<tr>
									<td align="center">${transaction.executeDate }</td>
									<td>${transaction.transactionType }</td>
									<td>${transaction.status }</td>
									<td>${transaction.fundName }</td>
									<td>${transaction.shares }</td>
									<td><fmt:formatNumber currencySymbol="$" type="currency" minFractionDigits="2" maxFractionDigits="2" value="${transaction.sharePrice}" /></td>
									<td><fmt:formatNumber currencySymbol="$" type="currency" minFractionDigits="2" maxFractionDigits="2" value="${transaction.amount}" /></td>
								</tr>
						   </c:forEach>
								</tbody>
						</table>
					</div>
					<!-- /.table-responsive -->
				</div>
				<!-- /.col-lg-4 (nested) -->
				<!-- /.col-lg-8 (nested) -->
			
			</div>
			
			<!-- /.row -->
			
			</div></form>
		</div>
		<!-- /.panel-body -->
	</div>
	<!-- /.panel -->
	<!-- /.panel -->

	<!-- /.col-lg-8 -->
	<!-- /.col-lg-4 -->
</div>



<!-- jQuery -->
<script src="Front-end/vendor/jquery/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="Front-end/vendor/bootstrap/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="Front-end/vendor/metisMenu/metisMenu.min.js"></script>

<!-- Morris Charts JavaScript -->
<script src="Front-end/vendor/raphael/raphael.min.js"></script>
<script src="Front-end/vendor/morrisjs/morris.min.js"></script>

<!-- Custom Theme JavaScript -->
<script src="Front-end/dist/js/sb-admin-2.js"></script>
<jsp:include page="template-bottom.jsp" />
