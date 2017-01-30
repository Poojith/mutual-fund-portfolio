<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="employee-template-top.jsp" />
	<div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header"><font size="5" face="verdana" color="#7E9DBB">&nbsp;View Customer Account</font></h1>
                </div>
            </div>
            <!-- /.row -->
            <c:forEach var="error" items="${errors}">
				<h5 style="color: red">${error}</h5>
			</c:forEach>

			<form action="view-customer-account.do" method="POST">
              <div class="row">
                <div class="col-lg-4">
                    <label>Username of the customer</label>
                    <input type="text" class="form-control"
					name="userName">
                  <p class="help-block"></p>
                </div>
                </div>
                
                
              <%-- <div class="row">
                <div class="col-lg-4">
                    <label>Customer</label>
                    <select class="form-control" name="userName">
                        <c:forEach var="c" items="${customer}">
							<option>${c.username}</option>
							
						</c:forEach>
                    </select>
                  <p class="help-block"></p>
                </div>
              </div> --%>


             <input type="submit" name="action" value="View account" class="btn btn-outline btn-primary"/>
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
    
