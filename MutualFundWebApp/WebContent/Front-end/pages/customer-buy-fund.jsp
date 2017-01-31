<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="customer-template-top.jsp" />
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h3 class="page-header"> <font size="5" face="verdana" color="#7E9DBB">&nbsp;Buy Fund </font> </h3>
		</div>
	</div>
	
	<div class="row">
		<c:forEach var="error" items="${errors}">
			<h5 style="color: red">${error}</h5>
		</c:forEach>
	</div>
	
	
	<div class="panel panel-default">
		<div class="panel-heading"></div>
	<div class="panel-body">
		<form action="buyfund.do" method="POST">
			<div class="row">
				<div class="col-lg-4">
					<label>Your Cash Balance: <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${user.cash}" /></label>
					<p class="help-block"></p>

				</div>
			</div>

			<div class="row">
				<div class="col-lg-4">
					<label>Select Fund</label><select class="form-control" name="fundName">
					<c:forEach var="fund" items="${buyfundlist}">
                    <option value="${fund.symbol}">${fund.name}</option>
         			</c:forEach>
						

					</select>
					<p class="help-block"></p>
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col-lg-4">
					<label>Amount to be Buy</label> <input type="text" 
						class="form-control" required="true" name="buyAmount">
					<p class="help-block"></p>
				</div>
			</div>
			<input type="submit" name="buyFund" value="Buy fund"
				class="btn btn-outline btn-primary" />
				
		</form>
		
	</div>
	
	</div>
	
	<!-- /#page-wrapper -->
</div>
<!-- /#wrapper -->
<!-- jQuery -->
<script src="Front-end/vendor/jquery/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="Front-end/vendor/bootstrap/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="Front-end/vendor/metisMenu/metisMenu.min.js"></script>

<!-- Morris Charts JavaScript -->
<script src="Front-end/vendor/raphael/raphael.min.js"></script>
<script src="Front-end/vendor/morrisjs/morris.min.js"></script>

<!-- Custom Theme JavaScript -->
<script src="Front-end/dist/js/sb-admin-2.js"></script>
<jsp:include page="template-bottom.jsp" />



