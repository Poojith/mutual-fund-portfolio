<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="customer-template-top.jsp" />

<div id="page-wrapper">

	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">
				<font size="5" face="verdana" color="#7E9DBB">&nbsp;Change
					customer password</font>
			</h1>
		</div>
	</div>
	<!-- /.row -->


	<div class="panel-body">
		<div class="row">
			<c:forEach var="error" items="${errors}">
				<h5 style="color: red">&nbsp;${error}</h5>
			</c:forEach>
		</div>
		<form action="changepassword.do" method="POST">

			<div class="row">
				<div class="col-lg-4">
					<label>New password</label> <input type="password"
						name="newPassword" required="true" class="form-control">
					<p class="help-block"></p>
				</div>
			</div>

			<div class="row">
				<div class="col-lg-4">
					<label>Confirm password</label> <input type="password"
						name="confirmPassword" required="true" class="form-control">
					<p class="help-block"></p>
				</div>
			</div>

			<input type="submit" value="Change password" name="action"
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
