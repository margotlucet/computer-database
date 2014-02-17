<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="page" required="true" type="java.lang.Integer"%>
<%@ attribute name="resultsPerPage" required="true"
	type="java.lang.Integer"%>
<%@ attribute name="pageCount" required="true" type="java.lang.Integer"%>
<%@ attribute name="numberOfElements" required="true"
	type="java.lang.Integer"%>
<%@ attribute name="totalElements" required="true"
	type="java.lang.Integer"%>
<%@ attribute name="actionPrefix" required="true"
	type="java.lang.String"%>

<ul class="pagination">

	<c:if test="${ page == 1 }">
		<li class="disabled"><a> &laquo; </a></li>
	</c:if>
	<c:if test="${ page > 1 }">
		<li><a
			href="${actionPrefix}page=${page-1}&resultsPerPage=${resultsPerPage}">&laquo;
		</a></li>
	</c:if>
	<c:forEach var="i" begin="1" end="15">

		<c:if test="${page == i }">
			<li class="active"><a href="${actionPrefix}page=${i}&resultsPerPage=${resultsPerPage}&search=${search}"> ${i} </a></li>

		</c:if>
		<c:if test="${page!=i}">
			<li><a href="${actionPrefix}page=${i}&resultsPerPage=${resultsPerPage}&search=${search}"> ${i} </a></li>
		</c:if>
	</c:forEach>
	<c:if test="${ page == pageCount }">
		<li class="disabled"><a>&raquo;</a></li>
	</c:if>
	<c:if test="${ page != pageCount }">
		<li><a
			href="${actionPrefix}page=${page+1}&resultsPerPage=${resultsPerPage}&search=${search}">
				&raquo;</a></li>
	</c:if>

</ul>

<!--  
	<lbl:message
				code="form.pagination.display"
				arguments="${(page-1)*resultsPerPage+1},${(page-1)*resultsPerPage+numberOfElements},${totalElements}" />
				-->