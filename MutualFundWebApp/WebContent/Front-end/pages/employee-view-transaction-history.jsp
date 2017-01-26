<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="employee-template-top.jsp" />
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">View Customer Transaction</h1>
		</div>
	</div>

	<!-- /.row -->
	<!-- /.row -->
	<div class="panel-body">
		<form action="resetpassword.do" method="POST">
			<div class="row">

				<!-- /.panel -->
				<div class="panel panel-default">

					<div class="pull-right">
						<div class="btn-group">
							<ul class="dropdown-menu pull-right" role="menu">
								<li><a href="#">Action</a></li>
								<li><a href="#">Another action</a></li>
								<li><a href="#">Something else here</a></li>
								<li class="divider"></li>
								<li><a href="#">Separated link</a></li>
							</ul>
						</div>
					</div>

					<!-- /.panel-heading -->
					<div class="panel-body">
						<div class="row">


							<div class="col-lg-4">
								<label>Search customer to view transaction history </label> <input
									type="text" class="form-control" name="userName"> <br>


							</div>

						</div>

						<div class="row">
							<div class="col-lg-4">
								<input type="submit" name="action" value="Search"
									class="btn btn-outline btn-primary" />
								
								<p class="help-block"></p>
							</div>
						</div>


						<br>
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
								<c:forEach var="transaction" items=${transactions}>
								<tr>
									<th></th>
									<th></th>
									<th></th>
									<th></th>
									<th></th>
									<th></th>
									<th></th>
									
								</tr>
								</c:forEach>
									
							</table>
						</div>

						<!-- /.table-responsive -->

						<!-- /.col-lg-4 (nested) -->
						<!-- /.col-lg-8 (nested) -->

					</div>

				</div>
				<!-- /.panel-body -->
			</div>
		</form>
		<!-- /.panel -->
		<!-- /.panel -->

		<!-- /.col-lg-8 -->
		<!-- /.col-lg-4 -->
	</div>
	<!-- /.row -->
</div>
<!-- /#page-wrapper -->


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

