<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<table id="tablaPedidos" class="table table-striped table-responsive table-hover">
	<thead>
		<tr>
			<th>ID</th>
			<th>Fecha Generacion</th>
			<th>Estado</th>
			<th>Importe</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pedidosPendientes}" var="p"> 
			<tr>
		   		<th>${p.id}</th>
				<th>${p.fechaGeneracion}</th>
				<th>${p.estado}</th>
				<th>${p.total}</th>
			</tr>
		</c:forEach>
		<c:if test="${fn:length(pedidosPendientes) == 0}">
			<tr>
		   		<th colspan="4">Ud no posee pedidos pendientes</th>
			</tr>
		</c:if>
	</tbody>
</table>
