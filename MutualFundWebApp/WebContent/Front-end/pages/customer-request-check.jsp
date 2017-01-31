<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="customer-template-top.jsp" />

<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header"><font size="5" face="verdana" color="#7E9DBB">&nbsp;Request Check</font></h1>
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
			<label>Your Cash Balance: ${user.cash}</label>

			<p class="help-block"></p>
		</div>
	</div>

	<div class="row">
		<div class="col-lg-4">
			<label>Amount to be withdrawn:</label> <input type="text"
				class="form-control" required="true" name="amtWithdrawn" >
			<p class="help-block"></p>
		</div>
	</div>

	<div class="row">
		<div class="col-lg-4">
			<label>Confirm Amount:</label> <input type="text"
				class="form-control" required="true" name="amtConfirm" required="true">
			<p class="help-block"></p>
		</div>
	</div>

	<input type="submit" name="requestCheck" value="Request check"
		class="btn btn-outline btn-primary" />
		
		</form>

</div>
</div>
</div>

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

