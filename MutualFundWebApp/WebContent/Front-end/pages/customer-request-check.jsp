<jsp:include page="customer-template-top.jsp" />

<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Request Check</h1>
		</div>
	</div>

	<div class="row">
		<c:forEach var="error" items="${errors}">
			<h3 style="color: red">${error}</h3>
		</c:forEach>
	</div>
	
	
	<div class="panel panel-default">
		<div class="panel-heading"></div>
		<!-- /.panel-heading -->
		<div class="panel-body">
			<form action="requestcheck.do" method="POST">
			



	<div class="row">

		<div class="col-lg-4">
			<label>Cash Balance: ${user.cash}</label>

			<p class="help-block"></p>
		</div>
	</div>

	<div class="row">
		<div class="col-lg-4">
			<label>Amount to be withdrawn:</label> <input type="text"
				class="form-control" name="amtWithdrawn">
			<p class="help-block"></p>
		</div>
	</div>

	<div class="row">
		<div class="col-lg-4">
			<label>Confirm Amount:</label> <input type="text"
				class="form-control" name="amtConfirm">
			<p class="help-block"></p>
		</div>
	</div>

	<input type="submit" name="action" value="Request check"
		class="btn btn-outline btn-primary" />
		
		</form>

</div>
</div>
</div>
<
<!-- /#page-wrapper -->


<!-- /#wrapper -->

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

