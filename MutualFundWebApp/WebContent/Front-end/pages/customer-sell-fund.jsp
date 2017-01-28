<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="customer-template-top.jsp" />

<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Sell Fund</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<div class="row">
		<c:forEach var="error" items="${errors}">
			<h3 style="color: red">${error}</h3>
		</c:forEach>
	</div>
	
	<div class="panel panel-default">
		<div class="panel-heading">Funds Information</div>
		<!-- /.panel-heading -->
		<div class="panel-body">
			<form action="sellfund.do" method="POST">
				<div class="row">
					<div>
						<div class="table-responsive">
							<table class="table table-bordered table-hover table-striped">
								<thead>
									<tr>
										<th>Fund Name</th>
										<th>Number of Shares</th>
										<th>Total Value</th>
									</tr>
								</thead>
								<tbody>					
								<c:forEach var ="p" items="${portfolio}">			
									<tr>
										<td>${p.fundName}</td>
										<td>${p.shares}</td>
										<td>${p.totalValue}</td>
										<!--adding total values to the bean -->
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
		<!-- /.panel-body -->
	</div>

	<!-- /.row -->
	<!-- /.row -->


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
					<label>Cash Balance: ${user.cash}</label>
					<p class="help-block"></p>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-4">
					<label>Select Fund</label> 
						<select class="form-control" name="fund">
                    
                    <c:forEach var="fund" items="${sellfundlist}">
                    <option value="${fund.symbol}">${fund.name}</option>
         			</c:forEach>
                   
                    </select>
					<p class="help-block"></p>
				</div>
			</div>
			<br>

			<div class="row">
				<div class="col-lg-4">
					<label>Number of Shares to sell</label> <input type="text" 	class="form-control" 
					name="numShares">
					<p class="help-block"></p>
				</div>
			</div>

			<input type="submit" name="sellFund" value="Sell fund"class="btn btn-outline btn-primary" />
		</form>
			<!-- /.row -->
		</div>
		<!-- /.panel-body -->
	</div>
	<!-- /.panel -->
	<!-- /.panel -->

	<!-- /.col-lg-8 -->
	<!-- /.col-lg-4 -->
</div>
<!-- /.row -->
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

</body>

</html>
