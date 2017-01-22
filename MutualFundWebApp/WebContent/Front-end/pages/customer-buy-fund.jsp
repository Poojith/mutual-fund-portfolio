<jsp:include page="customer-template-top.jsp" />
<div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Buy Fund</h1>
                </div>
            </div>

              <div class="row">
              	<c:forEach var="error" items="${errors}">
					<h3 style="color: red">${error}</h3>
				</c:forEach>
                <div class="col-lg-4">
                    <label>Select Fund</label>
                    <select class="form-control" id="fundoption" name="fundoption">
                    <option value="FSCSX" name="FSCSX">Fidelity® Select Software & IT Svcs Port</option>
                    <option value="ROGSX" name="ROGSX">Red Oak Technology Select Fund</option>
                    <option value="VITAX" name="VITAX">Vanguard Information Technology Index Fd</option>
                     <option value="MTCAX" name="MTCAX">MFS® Technology Fund</option>            
                    
                    </select>
                    <p class="help-block"></p>
                </div>
              </div>
              <br>

              <div class="row">
                <div class="col-lg-4">
                    <label>Cash Balance: </label>
                    <span class="cash-balance">
                    <a href></a>
                    </span>
                    
                    <p class="help-block"></p>
                </div>
              </div>
              
               <div class="row">
                <div class="col-lg-4">
                    <label>Amount to be Buy:</label>
                    <input type="text" class="form-control" name="buyamt">
                    <p class="help-block"></p>
                </div>
              </div>

              <input type="submit" name="button" value="Buy Fund" class="btn btn-outline btn-primary"/>

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
