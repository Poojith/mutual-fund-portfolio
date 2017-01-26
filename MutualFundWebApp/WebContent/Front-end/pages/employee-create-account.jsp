<jsp:include page="employee-template-top.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Create Account</h1>

		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="panel-body">
		<form action="create-account.do" method="POST">
			<div class="row">
				<c:forEach var="error" items="${errors}">
					<h3 style="color:red"> ${error} </h3>
				</c:forEach>
				<div class="col-lg-6">
					<label>User Name</label> <input type="text" class="form-control"
						name="userName" value="${form.userName}" />
					<p class="help-block"></p>
				</div>

				<div class="col-lg-6">
					<label>First Name</label> <input type="text" class="form-control"
						name="firstName" value="${form.firstName}" />
					<p class="help-block"></p>
				</div>
			</div>


			<div class="row">
				<div class="col-lg-6">
					<label>Last Name</label> <input type="text" class="form-control"
						name="lastName" value="${form.lastName}" />
					<p class="help-block"></p>
				</div>

				<div class="col-lg-6">
					<label>Address Line 1</label> <input type="text"
						class="form-control" name="address1" value="${form.address1}" />
					<p class="help-block"></p>
				</div>
			</div>


			<div class="row">

				<div class="col-lg-6">
					<label>Address Line 2</label> <input type="text"
						class="form-control" name="address2" value="${form.address2}" />
					<p class="help-block"></p>
				</div>


				<div class="col-lg-6">
					<label>City</label> <input type="text" class="form-control"
						name="city" value="${form.city}" />
					<p class="help-block"></p>
				</div>
			</div>

			<div class="row">
				<div class="col-lg-6">
					<label>State</label> <select class="form-control" name="state">
						<option value="">N/A</option>
						<option value="AK">Alaska</option>
						<option value="AL">Alabama</option>
						<option value="AR">Arkansas</option>
						<option value="AZ">Arizona</option>
						<option value="CA">California</option>
						<option value="CO">Colorado</option>
						<option value="CT">Connecticut</option>
						<option value="DC">District of Columbia</option>
						<option value="DE">Delaware</option>
						<option value="FL">Florida</option>
						<option value="GA">Georgia</option>
						<option value="HI">Hawaii</option>
						<option value="IA">Iowa</option>
						<option value="ID">Idaho</option>
						<option value="IL">Illinois</option>
						<option value="IN">Indiana</option>
						<option value="KS">Kansas</option>
						<option value="KY">Kentucky</option>
						<option value="LA">Louisiana</option>
						<option value="MA">Massachusetts</option>
						<option value="MD">Maryland</option>
						<option value="ME">Maine</option>
						<option value="MI">Michigan</option>
						<option value="MN">Minnesota</option>
						<option value="MO">Missouri</option>
						<option value="MS">Mississippi</option>
						<option value="MT">Montana</option>
						<option value="NC">North Carolina</option>
						<option value="ND">North Dakota</option>
						<option value="NE">Nebraska</option>
						<option value="NH">New Hampshire</option>
						<option value="NJ">New Jersey</option>
						<option value="NM">New Mexico</option>
						<option value="NV">Nevada</option>
						<option value="NY">New York</option>
						<option value="OH">Ohio</option>
						<option value="OK">Oklahoma</option>
						<option value="OR">Oregon</option>
						<option value="PA">Pennsylvania</option>
						<option value="PR">Puerto Rico</option>
						<option value="RI">Rhode Island</option>
						<option value="SC">South Carolina</option>
						<option value="SD">South Dakota</option>
						<option value="TN">Tennessee</option>
						<option value="TX">Texas</option>
						<option value="UT">Utah</option>
						<option value="VA">Virginia</option>
						<option value="VT">Vermont</option>
						<option value="WA">Washington</option>
						<option value="WI">Wisconsin</option>
						<option value="WV">West Virginia</option>
						<option value="WY">Wyoming</option>
					</select>
					<p class="help-block"></p>
				</div>



				<div class="col-lg-6">
					<label>Zip Code</label> <input type="text" class="form-control"
						name="zipcode" value="${form.zipcode}" />
					<p class="help-block"></p>
				</div>
			</div>

			<div class="row">
				<div class="col-lg-6">
					<label>Create Password</label> <input type="password"
						class="form-control" name="password" value="">
					<p class="help-block"></p>

				</div>


				<div class="col-lg-6">
					<label>Confirm Password</label> <input type="password"
						class="form-control" name="confirmPassword" value="">
				</div>

			</div>
			<br> <input type="submit" name="action"
				value="Create customer account" class="btn btn-outline btn-primary" />
			<input type="submit" name="action" 
			value="Create employee account" class="btn btn-outline btn-primary" /> <br>
		</form>

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
