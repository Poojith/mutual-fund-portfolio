<jsp:include page="employee-template-top.jsp" />
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Transition Day</h1>
		</div>
	</div>

	<div class="row">
        <div class='col-sm-6'>
            <div class="form-group">
                <div class='input-group date' id='datetimepicker1'>
                    <input type='text' class="form-control" />
                    <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </span>
                </div>
            </div>
        </div>
        <script type="text/javascript">
            $(function () {
                $('#datetimepicker1').datetimepicker();
            });
        </script>
    </div>



	<div class="row">
		<div class="col-lg-4">
			<label>Update Fund Prices</label> <br> <label><i>Fund
					1</i></label> <input type="text" class="form-control" name="fund1">
			<p class="help-block"></p>
		</div>
	</div>

	<div class="row">
		<div class="col-lg-4">
			<label><i>Fund 2</i></label> <input type="text" class="form-control"
				name="fund2">
			<p class="help-block"></p>
		</div>
	</div>

	<div class="row">
		<div class="col-lg-4">
			<label><i>Fund 3</i></label> <input type="text" class="form-control"
				name="fund3">
			<p class="help-block"></p>
		</div>
	</div>


	<input type="submit" value="Update Prices"
		class="btn btn-outline btn-primary" />

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

