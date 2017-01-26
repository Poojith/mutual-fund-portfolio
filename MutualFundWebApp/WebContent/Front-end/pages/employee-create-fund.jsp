<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="employee-template-top.jsp" />
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Create Fund</h1>
		</div>
	</div>
	<div class="panel-body">
	<div class="row">
	  <c:forEach var="error" items="${errors}">
			<h3 style="color: red">${error}</h3>
		</c:forEach>
	</div>
	<form action="create-fund.do" method="POST">
	

		<div class="row">
			<div class="col-lg-4">
				<label>Fund Name</label> <input type="text" class="form-control"
					name="fundName">
				<p class="help-block"></p>
			</div>
		</div>

		<div class="row">
			<div class="col-lg-4">
				<label>Ticker</label> <input type="text" class="form-control"
					name="ticker">
				<p class="help-block"></p>
			</div>
		</div>

		<input type="submit" name="action" value="Create fund"
			class="btn btn-outline btn-primary" />
			</form>
	</div>
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
