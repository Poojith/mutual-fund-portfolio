<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>CFS</title>
<!-- Bootstrap Core CSS -->
<link href="Front-end/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- MetisMenu CSS -->
<link href="Front-end/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="Front-end/dist/css/sb-admin-2.css" rel="stylesheet">

<!-- Morris Charts CSS -->
<link href="Front-end/vendor/morrisjs/morris.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="Front-end/vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<title>Carnegie Financial Services</title>
</head>
<body>
	<div id="wrapper">

		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top" role="navigation"
			style="margin-bottom: 0">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
		</div>
		<img src="Front-end/images/CFS-cover.png"> <!-- /.navbar-header --> <!-- jQuery -->
		<div class="container">
			<div class="row">
				<div class="col-md-4 col-md-offset-4">
					<div class="login-panel panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">Please sign in</h3>
						</div>
						<div class="panel-body">
						<div class="row">
	 				 <c:forEach var="error" items="${errors}">
			<h3 style="color: red">${error}</h3>
		</c:forEach>
	</div>
							<form action="login.do" method="POST">
								<fieldset>
									<div class="form-group">
										<input class="form-control" placeholder="Username"
											name="userName" size="20" type="text" autofocus
											value="${user.userName}">
									</div>
									<div class="form-group">
										<input class="form-control" placeholder="Password"
											name="password" type="password">
									</div>
									<!-- Change this to a button or input when using this as a form -->
									<input type="submit" value="Customer login" name="action"
										class="btn btn-outline btn-primary" /> <input type="submit"
										value="Employee login" name="action"
										class="btn btn-outline btn-primary" />
								</fieldset>
							</form></div></div></div></div></div>
		<br><br><br>

		<script src="../vendor/jquery/jquery.min.js"></script> <!-- Bootstrap Core JavaScript -->
		<script src="../vendor/bootstrap/js/bootstrap.min.js"></script> <!-- Metis Menu Plugin JavaScript -->
		<script src="../vendor/metisMenu/metisMenu.min.js"></script> <!-- Morris Charts JavaScript -->
		<script src="../vendor/raphael/raphael.min.js"></script> <script
			src="../vendor/morrisjs/morris.min.js"></script> <script
			src="../data/morris-data.js"></script> <!-- Custom Theme JavaScript -->
		<script src="../dist/js/sb-admin-2.js"></script> <br><br><br> <br><br><br> <br><br>
		<jsp:include page="template-bottom.jsp" />
		