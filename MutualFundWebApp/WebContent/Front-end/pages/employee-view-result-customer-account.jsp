<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="employee-template-top.jsp" />
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">View Customer Account</h1>
		</div>
	</div>
	<!-- /.row -->


	<div class="panel-body">
		<c:forEach var="error" items="${errors}">
			<h3 style="color: red">${error}</h3>
		</c:forEach>
		<form action="view-customer-account.do" method="GET">
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
							<div class="panel-footer" align="center">${user.addrLine1}
								&nbsp; ${user.addrLine2}</div>
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

				<!-- /.col-lg-8 -->
				<!-- /.col-lg-4 -->
			</div>
			<!-- /.row -->
		</form>
	</div>




	<input type="submit" value="View account" name="action"
		class="btn btn-outline btn-primary" /> </a>

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

