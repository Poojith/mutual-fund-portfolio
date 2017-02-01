<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>CFS - Employee home</title>
    <!-- Bootstrap Core CSS -->
    <link href="Front-end/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="Front-end/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="Front-end/dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Morris Charts CSS -->
    <link href="Front-end/vendor/morrisjs/morris.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="Front-end/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<title>Insert title here</title>
</head>
<body>

    <div id="wrapper">

        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div>
             <a href="employee-home.do"><img src="Front-end/images/CFS-cover.png"></a>
            <!-- /.navbar-header -->

            
            <!-- /.navbar-top-links -->

            <div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
                    <ul class="nav" id="side-menu">
                        <li>
                            <a href="create-account.do"><i class="fa fa-plus fa-fw"></i>Create account</a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-wrench fa-fw"></i> Manage Customer<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="reset-customer-password.do"><i class="fa fa-repeat fa-fw"></i>Reset Customer Password</a>
                                </li>
                                <li>
                                    <a href="view-customer-account.do"><i class="fa fa-eye fa-fw"></i>View Customer Account</a>
                                </li>
                                <li>
                                    <a href="employee-view-transaction-history.do"><i class="fa fa-history fa-fw"></i>Customer Transaction History</a>
                                </li>
                            </ul>
                        </li>
                        
                        <li>
                            <a href="employee-deposit-check.do"><i class="fa fa-credit-card fa-fw"></i> Deposit check</a>
                        </li>
                        <li>
                            <a href="create-fund.do"><i class="fa fa-money fa-fw"></i> Create fund</a>
                        </li>
                        <li>
                            <a href="employee-transition-day.do"><i class="fa fa-line-chart fa-fw"></i> Transition day</a>
                        </li>
                        
                        <li><a href="logout.do"><i class="fa fa-sign-out fa-fw"></i>
							Logout</a></li>

                    </ul>
                </div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
        </nav>
       
      </div>