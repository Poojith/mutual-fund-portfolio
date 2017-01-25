<jsp:include page="customer-template-top.jsp" />

<div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Research Fund</h1>
                </div>
            </div>

              <div class="row">
                <div class="col-lg-4">
                    <label>Select Fund</label>
                    <select class="form-control" name="fund">
                    
                    <c:forEach var="fund" items="${fundlist}">
                    <option>${fundlist.name}</option>
         			</c:forEach>
                   
                    </select>
                    <p class="help-block"></p>
                </div>
              </div>
             
                        
              

            </div>
            <!--/#wrapper  -->
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