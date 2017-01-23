<jsp:include page="customer-template-top.jsp" />

<div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Sell Fund</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <div class="row">
              	<c:forEach var="error" items="${errors}">
					<h3 style="color: red">${error}</h3>
				</c:forEach>
			</div>
            <div class="panel panel-default">
                        <div class="panel-heading">
                            Funds Information
                           <div class="pull-right">
                               <div class="btn-group">
<ul class="dropdown-menu pull-right" role="menu">
                    <li><a href="#">Action</a>
                  </li>
                                        <li><a href="#">Another action</a>
                                        </li>
                                        <li><a href="#">Something else here</a>
                                        </li>
                                        <li class="divider"></li>
                                        <li><a href="#">Separated link</a>
                                        </li>
                                 </ul>
                             </div>
                          </div>
                      </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="row">
                              <div>
                                    <div class="table-responsive">
                                        <table class="table table-bordered table-hover table-striped">
                                            <thead>
                                                <tr>
<th>Fund Name</th>
                                                    <th> Number of Shares</th>
                                                    <th> Total Value</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
<td>FSCSX</td>
                                                    <td>10</td>
                                                    <td>$321.33</td>
                                                </tr>
                                                <tr>
<td>ROGSX</td>
                                                    <td>51</td>
                                                    <td>$234.34</td>
                                                </tr>
                                                <tr>
<td>VITAX</td>
                                                    <td>100.6</td>
                                                    <td>$724.17</td>
                                                </tr>
                                                <tr>
<td>MTCAX</td>
                                                    <td>14</td>
                                                    <td>$23.71</td>
                                                </tr>

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
                        </div>
                        <!-- /.panel-body -->
                  </div>
            
            <!-- /.row -->
            <!-- /.row -->
           

<!-- /.panel -->
                    <div class="panel panel-default">
                          <div class="pull-right">
                               <div class="btn-group">
<ul class="dropdown-menu pull-right" role="menu">
                    <li><a href="#">Action</a>
                  </li>
                                        <li><a href="#">Another action</a>
                                        </li>
                                        <li><a href="#">Something else here</a>
                                        </li>
                                        <li class="divider"></li>
                                        <li><a href="#">Separated link</a>
                                        </li>
                                 </ul>
                             </div>
                          </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                         <div class="row">
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
                    <label>Cash Balance:</label>
                    <span class="cash-balance">
                    <a href></a>
                    </span>
                    
                    <p class="help-block"></p>
                </div>
              </div>
              
               <div class="row">
                <div class="col-lg-4">
                    <label>Number of Shares to sell:</label>
                    <input type="text" class="form-control" name="numShares">
                    <p class="help-block"></p>
                </div>
              </div>

              <input type="submit" name="button" value="Sell Fund" class="btn btn-outline btn-primary"/>
					
                            <!-- /.row -->
                        </div>
                        <!-- /.panel-body -->
                  </div>
                    <!-- /.panel -->                    <!-- /.panel -->

                <!-- /.col-lg-8 -->                <!-- /.col-lg-4 -->
            </div>
            <!-- /.row -->
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
