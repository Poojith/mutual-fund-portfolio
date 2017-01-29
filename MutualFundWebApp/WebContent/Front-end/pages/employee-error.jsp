<jsp:include page="employee-template-top.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header"><font size="5" face="verdana" color="#7E9DBB">&nbsp;Error Page</h1>
		</div>
	</div>

	<!-- /.row -->
	<!-- /.row -->
	<div class="panel-body">
		<div class="row">
			<c:forEach var="error" items="${errors}">
				<font color="color: #d83b27">&nbsp;&nbsp;${error}</font>
			</c:forEach>
		</div>
	</div>
	</div>


<jsp:include page="template-bottom.jsp" />
