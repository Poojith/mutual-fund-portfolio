<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="customer-template-top.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div id="page-wrapper">



	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header"><font size="5" face="verdana" color="#7E9DBB">&nbsp;Your account</font></h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>


	<div class="row">
		<c:forEach var="error" items="${errors}">
			<h3 style="color: red">${error}</h3>
		</c:forEach>
	</div>

	<div class="panel-body">
		<form action="viewaccount.do" method="GET">
			<div class="row">
				<div class="col-lg-12"></div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->
			<!-- /.row -->
			<div class="row">
				<!-- /.row -->
				<div class="row">
					<div class="col-lg-3 col-md-6">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-12 text-center">
										<div>Name</div>
									</div>
								</div>
							</div>
							<div class="panel-footer" align="center">${user.firstName}
								&nbsp; ${user.lastName}</div>

						</div>
					</div>
					<div class="col-lg-3 col-md-6">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-12 text-center">
										<div>Address</div>
									</div>
								</div>
							</div>
							<div class="panel-footer" align="center">
								${user.addrLine1} &nbsp; ${user.addrLine2}</div>
						</div>
					</div>
					<div class="col-lg-3 col-md-6">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-12 text-center">
										<div>Cash Balance</div>
									</div>
								</div>
							</div>

							<div class="panel-footer" align="center">${user.cash}</div>

						</div>

					</div>
					<div class="col-lg-3 col-md-6">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-12 text-center">
										<div>Date of Last transaction</div>
									</div>
								</div>
							</div>
							<div class="panel-footer" align="center">${lasttransactiondate}</div>
						</div>
					</div>
				</div>
				<!-- /.row -->
				<div class="row">
					<div class="col-lg-12">
						<!-- /.panel -->
						<div class="panel panel-default">
							<div class="panel-heading"><b>
								Funds Information
								</b>
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
							</div>
							<!-- /.panel-heading -->
							<div class="panel-body">
								<div class="row">
									<div>
										<div class="table-responsive">
											<table class="table table-bordered table-hover table-striped">
												<thead>
													<tr>
														<th>&nbsp;&nbsp;Fund Name</th>
														<th>Number of Shares</th>
														<th>Total Value</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="p" items="${portfolio}">
														<tr>

															<td>${p.fundName}</td>
															<td>${p.shares}</td>
															<td>${p.totalValue}</td>

														</tr>
													</c:forEach>


												</tbody>
											</table>
										</div>
										<!-- /.table-responsive -->
									</div>
									<!-- /.col-lg-4 (nested) -->
									<div class="col-lg-8">
										<div id="morris-bar-chart"></div>
									</div>
									<!-- /.col-lg-8 (nested) -->
								</div>
								<!-- /.row -->
							</div>

						</div>
						<!-- /.panel-body -->
					</div>
					<!-- /.panel -->
					<!-- /.panel -->
				</div>
				<!-- /.col-lg-8 -->
				<!-- /.col-lg-4 -->
			</div>
			<!-- /.row -->
		</form>
	</div>
	<!-- /#page-wrapper -->

</div>
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