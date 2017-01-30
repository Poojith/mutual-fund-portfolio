<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="employee-template-top.jsp" />
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header"><font size="5" face="verdana" color="#7E9DBB">&nbsp;Transition Day</font></h1>
		</div>
	</div>
	<div class="row">
	  <c:forEach var="error" items="${errors}">
			<h5 style="color: red">&nbsp;&nbsp;&nbsp;&nbsp;${error}</h5>
		</c:forEach>
	</div>
	<form action="employee-transition-day.do" method="POST">
		<div class="row">
	        <div class='col-sm-6'>
	            <label>Date of Trading Day: </label> <br> 
	            <input type="text" name="date" />
	        </div>
	    </div>
		<br>
		
		<div class="row">
			<div class="col-lg-4">
				<label>Update Fund Prices</label> <br>
			</div>
		</div>
		
		<c:forEach var="fund" items="${fundArray}">
			<div class="row">
				<div class="col-lg-4">
					<label><i>Fund ${fund.name}</i></label> 
					<input type="text" class="form-control" name="fund${fund.fundId}">
					<p class="help-block"></p>
				</div>
			</div>
		</c:forEach>
		<input type="submit" name="action" value="Update prices"
		class="btn btn-outline btn-primary" />
	</form>

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