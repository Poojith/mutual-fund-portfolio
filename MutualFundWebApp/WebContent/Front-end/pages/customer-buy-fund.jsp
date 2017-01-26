<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="customer-template-top.jsp" />
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Buy Fund</h1>
		</div>
	</div>
	
	<div class="row">
		<c:forEach var="error" items="${errors}">
			<h3 style="color: red">${error}</h3>
		</c:forEach>
	</div>

	<div class="panel-body">
		<form action="buyfund.do" method="POST">
			<div class="row">
				<div class="col-lg-4">
					<label>Cash Balance: ${user.cash}</label>
					<p class="help-block"></p>

				</div>
			</div>

			<div class="row">
				<div class="col-lg-4">
					<label>Select Fund</label> <select class="form-control" name="fundName" onchange="this.form.submit()">
					<c:forEach var="fund" items="${buyfundlist}">
                    <option value="${fund.symbol}">${fund.name}</option>
                    <label>${fund.fundId}</label>
         			</c:forEach>
						

					</select>
					<p class="help-block"></p>
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col-lg-4">
					<label>Amount to be Buy</label> <input type="text"
						class="form-control" name="buyAmount">
					<p class="help-block"></p>
				</div>
			</div>
			<input type="submit" name="buyFund" value="Buy fund"
				class="btn btn-outline btn-primary" />
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



