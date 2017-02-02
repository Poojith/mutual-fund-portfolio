<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="employee-template-top.jsp" />

<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header"><font size="5" face="verdana" color="#7E9DBB">&nbsp;Deposit Check</font></h1>
		</div>
	</div>
	<!-- /.row -->
	
	<div class="row">
	  <c:forEach var="error" items="${errors}">
			<h5 style="color: red">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${error}</h5>
		</c:forEach>
	</div>
	
	<!-- /.row -->
	<div class="panel-body">
		<form action="employee-deposit-check.do" method="POST">

	<div class="row">
		<div class="col-lg-4">
			<label>Username of the Customer</label> <input type="text"
				class="form-control" name="userName" required="true"> <br>
		</div>
	</div>

	<div class="row">
		<div class="col-lg-4">
			<label>Enter the Amount to deposit</label> <input type="text"
				name="buyAmount" class="form-control" required="true">
			<p class="help-block"></p>
		</div>
	</div>


	<input type="submit" value="Deposit"
		name="action" class="btn btn-outline btn-primary" />
	</form>
</div>
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
