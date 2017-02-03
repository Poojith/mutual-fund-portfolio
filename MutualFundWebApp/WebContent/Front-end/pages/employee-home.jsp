<jsp:include page="employee-template-top.jsp" />

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
	google.charts.load('current', {
		'packages' : [ 'corechart' ]
	});

	google.charts.setOnLoadCallback(drawChart3);
	function drawChart3() {
		$
				.getJSON(
						"https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance%20.quotes%20where%20symbol%20in%20(%22FB%22%2C%22AAPL%22%2C%22GOOG%22%2C%22MSFT%22%2C%22AMZN%22)%0A%09%09&format=json&env=http%3A%2F%2Fdatatables.org%2Falltables.env&callback=",
						function(cdata) {
							var ticker_data3 = [ [ 'Company',
									'Percentage change from year low', {
										role : 'annotation'
									} ] ];

							for (var i = 0; i < cdata["query"]["results"]["quote"].length; i++) {
								ticker_data3
										.push([
												cdata["query"]["results"]["quote"][i]["Name"],
												parseInt(cdata["query"]["results"]["quote"][i]["PercentChangeFromYearLow"]),
												cdata["query"]["results"]["quote"][i]["Symbol"] ]);
							}
							var data3 = google.visualization
									.arrayToDataTable(ticker_data3);
							var options3 = {
								'title' : 'Percentage change from year low',
								'width' : 950,
								'height' : 600
							};

							var chart3 = new google.visualization.PieChart(
									document.getElementById('display'));
							chart3.draw(data3, options3);
						});
	}

	google.charts.setOnLoadCallback(drawChart);

	function drawChart() {

		$
				.getJSON(
						"https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance%20.quotes%20where%20symbol%20in%20(%22FB%22%2C%22AAPL%22%2C%22GOOG%22%2C%22MSFT%22%2C%22AMZN%22)%0A%09%09&format=json&env=http%3A%2F%2Fdatatables.org%2Falltables.env&callback=",
						function(cdata) {
							var ticker_data = [ [ 'Company',
									'Market capitalization', {
										role : 'annotation'
									} ] ];

							for (var i = 0; i < cdata["query"]["results"]["quote"].length; i++) {
								ticker_data
										.push([
												cdata["query"]["results"]["quote"][i]["symbol"],
												parseInt(cdata["query"]["results"]["quote"][i]["MarketCapitalization"]),
												cdata["query"]["results"]["quote"][i]["Name"] ]);
							}

							console.log(ticker_data);
							var data = google.visualization
									.arrayToDataTable(ticker_data);

							var options = {
								'title' : 'Capitalization in market',
								'width' : 1000,
								'height' : 700
							};

							var chart = new google.visualization.ColumnChart(
									document.getElementById('display2'));
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
									'Change percentage', {
										role : 'annotation'
									} ] ];

							for (var i = 0; i < cdata["query"]["results"]["quote"].length; i++) {
								ticker_data2
										.push([
												cdata["query"]["results"]["quote"][i]["symbol"],
												parseInt(cdata["query"]["results"]["quote"][i]["EPSEstimateNextYear"]),
												cdata["query"]["results"]["quote"][i]["Name"] ]);
							}

							console.log(ticker_data2);
							var data2 = google.visualization
									.arrayToDataTable(ticker_data2);

							var options2 = {
								'title' : 'Change in percentage over the past month',
								'width' : 900,
								'height' : 500
							};

							var chart2 = new google.visualization.LineChart(
									document.getElementById('display3'));
							chart2.draw(data2, options2);
						});
	}
</script>

<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">
				<font size="5" face="verdana" color="#7E9DBB">Real-time
					market updates</font>
			</h1>
		</div>
	</div>
	<div id="display"></div>
	<div id="display2"></div>
	<div id="display3"></div>
</div>
<jsp:include page="template-bottom.jsp" />
