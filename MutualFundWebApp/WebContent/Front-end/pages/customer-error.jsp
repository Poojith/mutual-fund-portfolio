<jsp:include page="customer-template-top.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Error Page</h1>
		</div>
	</div>

	<!-- /.row -->
	<!-- /.row -->
	<div class="panel-body">
		<div class="row">
			<c:forEach var="error" items="${errors}">
				<h5 style="color: red">${error}</h5>
			</c:forEach>
		</div>
	</div>
	</div>


<jsp:include page="template-bottom.jsp" />
