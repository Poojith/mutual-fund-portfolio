<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="customer-template-top.jsp" />

<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header"><font size="5" face="verdana" color="#7E9DBB">&nbsp;Research Fund</font></h1>
		</div>
	</div>
	<div class="row">
	  <c:forEach var="error" items="${errors}">
			<h5 style="color: red">${error}</h5>
		</c:forEach>
	</div>
	
	
	<div class="panel panel-default">
		<div class="panel-heading"></div>
		<!-- /.panel-heading -->
		<div class="panel-body">
			<form action="researchfund.do" method="POST">
			
			
			<div class="row">
				<div class="col-lg-3">
					<label>Select Fund </label> <select class="form-control" name="fundId">
						<c:if test="${empty fundpricehistory[0].fundId }">
							<c:forEach var="fund" items="${fundlist}">
								<option value="${fund.fundId}">${fund.symbol}</option>
							</c:forEach>
						</c:if>
						
						<c:if test="${not empty fundpricehistory[0].fundId }">
							<c:forEach var="fund" items="${fundlist}">
								<c:if test="${fundpricehistory[0].fundId == fund.fundId }">
									<option value="${fund.fundId }" selected="selected">${fund.symbol}</option>
								</c:if>
								<c:if test="${fundpricehistory[0].fundId != fund.fundId }">
									<option value="${fund.fundId}">${fund.symbol}</option>
								</c:if>
							</c:forEach>
						</c:if>
						

					</select>
					</div>
					<div class="col-lg-3" style="margin-top:25px;">
					
					
					<input type="submit" name="researchfund" value="Select fund"class="btn btn-outline btn-primary" />
					
					</div>
					<p class="help-block"></p>
					
				
				
			</div>
			
		
			
			<br>
			
			<div class="row">
				<div class="panel panel-default">
                        <div class="panel-heading">
                            <i class="fa fa-bar-chart-o fa-fw"></i> Fund Price History Chart
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div id="morris-area-chart"></div>
                        </div>
                        <!-- /.panel-body -->
                    </div>
			</div>
			
			<div class="row">
					<div>
						<div class="table-responsive">
							<table class="table table-bordered table-hover table-striped">
								<thead>
									<tr>
										<th>Date of Price</th>
										<th>Price</th>
									</tr>
								</thead>
								<tbody>					
								<c:forEach var ="p" items="${fundpricehistory}">			
									<tr>
										<td>${p.priceDate}</td>
										<td>${p.price}</td>
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
			</form>
		</div>
		<!-- /.panel-body -->
	</div>
	
</div> 
<!--/#wrapper  -->
<!-- jQuery -->
<script src="Front-end/vendor/jquery/jquery.min.js"></script>
<!-- Bootstrap Core JavaScript -->
<script src="Front-end/vendor/bootstrap/js/bootstrap.min.js"></script>
<!-- Metis Menu Plugin JavaScript -->
<script src="../vendor/metisMenu/metisMenu.min.js"></script>
<!-- Morris Charts JavaScript -->
<script src="Front-end/vendor/raphael/raphael.min.js"></script>
<script src="Front-end/vendor/morrisjs/morris.min.js"></script>
<!-- <script src="Front-end/data/morris-data.js">
 -->	
</script>
<!-- Custom Theme JavaScript -->
<script src="Front-end/dist/js/sb-admin-2.js"></script>
<script type="text/javascript">
new Morris.Line({
    element: 'morris-area-chart',
    data: [
    	<c:forEach var="p" items="${fundpricehistory}">
    		{
    			date: '${p.priceDate}',
    			price: '${p.price}',
    		},
    	</c:forEach>
    	],
    xkey: 'date',
    ykeys: ['price'],
    labels: ['StockPrice'],
    hideHover: 'auto',
    resize: true
});
</script>



<jsp:include page="template-bottom.jsp" />