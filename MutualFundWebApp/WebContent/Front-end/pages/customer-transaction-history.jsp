<jsp:include page="customer-template-top.jsp" />

<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Transaction History</h1>
		</div>
	</div>
	
	<div class="row">
		<c:forEach var="error" items="${errors}">
			<h3 style="color: red">${error}</h3>
		</c:forEach>
	</div>

	<!-- /.panel -->
	<div class="panel panel-default">
	<div class="panel-body">
	<form action="sellfund.do" method="GET">
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
							<c:forEach var ="transaction" >
								<tr>
									<td align="center">01/02/2017</td>
									<td>Buy</td>
									<td>Completed</td>
									<td>Fund 1</td>
									<td>10</td>
									<td>$32.13</td>
									<td>$321.30</td>
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
<script src="../vendor/jquery/jquery.min.js"></script>
<!-- Bootstrap Core JavaScript -->
<script src="../vendor/bootstrap/js/bootstrap.min.js"></script>
<!-- Metis Menu Plugin JavaScript -->
<script src="../vendor/metisMenu/metisMenu.min.js"></script>
<!-- Morris Charts JavaScript -->
<script src="../vendor/raphael/raphael.min.js"></script>
<script src="../vendor/morrisjs/morris.min.js"></script>
<script src="../data/morris-data.js"></script>
<!-- Custom Theme JavaScript -->
<script src="../dist/js/sb-admin-2.js"></script>



<jsp:include page="template-bottom.jsp" />