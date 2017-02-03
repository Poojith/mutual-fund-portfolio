<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="customer-template-top.jsp" />

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
	google.charts.load('current', {
		'packages' : [ 'corechart' ]
	});
	google.charts.setOnLoadCallback(drawChart);

	function drawChart() {

		$
				.getJSON(
						"https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance%20.quotes%20where%20symbol%20in%20(%22FB%22%2C%22AAPL%22%2C%22GOOG%22%2C%22MSFT%22%2C%22AMZN%22)%0A%09%09&format=json&env=http%3A%2F%2Fdatatables.org%2Falltables.env&callback=",
						function(cdata) {
							var ticker_data = [ [ 'Company', 'Value', {
								role : 'annotation'
							} ] ];

							for (var i = 0; i < cdata["query"]["results"]["quote"].length; i++) {
								ticker_data
										.push([
												cdata["query"]["results"]["quote"][i]["symbol"],
												parseInt(cdata["query"]["results"]["quote"][i]["BookValue"]),
												cdata["query"]["results"]["quote"][i]["BookValue"] ]);
							}

							console.log(ticker_data);
							var data = google.visualization
									.arrayToDataTable(ticker_data);

							var options = {
								'title' : 'Book value',
								'width' : 850,
								'height' : 700
							};

							var chart = new google.visualization.ColumnChart(
									document.getElementById('display3'));
							chart.draw(data, options);
						});
	}
	google.charts.setOnLoadCallback(drawChart2);
	function drawChart2() {
		$
				.getJSON(
						"https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance%20.quotes%20where%20symbol%20in%20(%22FB%22%2C%22AAPL%22%2C%22GOOG%22%2C%22MSFT%22%2C%22AMZN%22)%0A%09%09&format=json&env=http%3A%2F%2Fdatatables.org%2Falltables.env&callback=",
						function(cdata) {
							var ticker_data2 = [ [ 'Company',
									'Change in ratio', {
										role : 'annotation'
									} ] ];

							for (var i = 0; i < cdata["query"]["results"]["quote"].length; i++) {
								ticker_data2
										.push([
												cdata["query"]["results"]["quote"][i]["symbol"],
												parseInt(cdata["query"]["results"]["quote"][i]["PEGRatio"]),
												cdata["query"]["results"]["quote"][i]["PEGRatio"] ]);
							}

							console.log(ticker_data2);
							var data2 = google.visualization
									.arrayToDataTable(ticker_data2);

							var options2 = {
								'title' : 'Price/Earnings To Growth(PEG) ratio',
								'width' : 850,
								'height' : 500
							};

							var chart2 = new google.visualization.ScatterChart(
									document.getElementById('display2'));
							chart2.draw(data2, options2);
						});
	}

	google.charts.setOnLoadCallback(drawChart3);
	function drawChart3() {
		$
				.getJSON(
						"https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance%20.quotes%20where%20symbol%20in%20(%22FB%22%2C%22AAPL%22%2C%22GOOG%22%2C%22MSFT%22%2C%22AMZN%22)%0A%09%09&format=json&env=http%3A%2F%2Fdatatables.org%2Falltables.env&callback=",
						function(cdata) {
							var ticker_data3 = [ [ 'Company',
									'Percentage change', {
										role : 'annotation'
									} ] ];

							for (var i = 0; i < cdata["query"]["results"]["quote"].length; i++) {
								ticker_data3
										.push([
												cdata["query"]["results"]["quote"][i]["Name"],
												parseInt(cdata["query"]["results"]["quote"][i]["PercentChangeFromYearLow"]),
												cdata["query"]["results"]["quote"][i]["PercentChangeFromTwoHundreddayMovingAverage"] ]);
							}
							var data3 = google.visualization
									.arrayToDataTable(ticker_data3);
							var options3 = {
								'title' : 'Percent average from two hundred days',
								'width' : 860,
								'height' : 600
							};

							var chart3 = new google.visualization.BarChart(
									document.getElementById("display"));
							chart3.draw(data3, options3);
						});
	}
</script>

<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">
				<font size="5" face="verdana" color="#7E9DBB">Personalized
					Real-time Market Updates for You</font>
			</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<div id="display"></div>
	<div id="display2"></div>
	<div id="display3"></div>

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