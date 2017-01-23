<jsp:include page="customer-template-top.jsp" />

<div id="page-wrapper">
           <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">View Portfolio</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <div class="row">
                <div class="col-lg-12"> </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <!-- /.row -->
            <div class="row">
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-3 col-md-6">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <div class="row">
<div class="col-xs-12 text-center">
                <div>Name</div>
</div>
                          </div>
                        </div>
                             <div class="panel-footer" align="center">
                             John Smith
                            </div>

                    </div>
                </div>
                <div class="col-lg-3 col-md-6">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <div class="row">
<div class="col-xs-12 text-center">
                <div>Address</div>
</div>
                          </div>
                        </div>
                            <div class="panel-footer" align="center">5000 Forbes Ave, Pittsburgh
                            </div>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <div class="row">
<div class="col-xs-12 text-center">
                <div>Cash Balance</div>
</div>
                            </div>
                        </div>

                            <div class="panel-footer" align="center">$ 23,454.60
                            </div>

                    </div>

                </div>
                <div class="col-lg-3 col-md-6">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <div class="row">
<div class="col-xs-12 text-center">
                <div>Date of Last transaction</div>
</div>
                            </div>
                        </div>
                                <div class="panel-footer" align="center">01/22/2017

                            </div>
                    </div>
                </div>
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
<!-- /.panel -->
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
<td> Fund 1</td>
                                                    <td>10</td>
                                                    <td>$321.33</td>
                                                </tr>
                                                <tr>
<td>Fund 2</td>
                                                    <td>51</td>
                                                    <td>$234.34</td>
                                                </tr>
                                                <tr>
<td> Fund 3</td>
                                                    <td>100.6</td>
                                                    <td>$724.17</td>
                                                </tr>
                                                <tr>
<td>Fund 4</td>
                                                    <td>14</td>
                                                    <td>$23.71</td>
                                                </tr>
                                                <tr>
<td>Fund 5</td>
                                                    <td>212</td>
                                                    <td>$8345.23</td>
                                                </tr>
                                                <tr>
<td>Fund 6</td>
                                                    <td>34</td>
                                                    <td>$245.12</td>
                                                </tr>
                                                <tr>
<td>Fund 7</td>
                                                    <td>43.4</td>
                                                    <td>$5663.54</td>
                                                </tr>
                                                <tr>
<td>Fund 8</td>
                                                    <td>24.5</td>
                                                    <td>$943.45</td>
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
                    <!-- /.panel -->                    <!-- /.panel -->
              </div>
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
    <jsp:include page="template-bottom.jsp" />