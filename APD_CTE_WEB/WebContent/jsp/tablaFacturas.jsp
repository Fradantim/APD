<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<table id="tablaFacturas" class="table table-striped table-responsive table-hover">
	<thead>
		<tr>
			<th>ID</th>
			<th>Fecha Generacion</th>
			<th>Bonificacion</th>
			<th>Estado</th>
			<th>Importe</th>
			<th>Total a Abonar (solo efectivo)</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${facturasInpagas}" var="f"> 
			<tr>
		   		<th>${f.id}</th>
				<th>${f.fecha}</th>
				<th>${f.bonificacion}</th>
				<th>${f.estado}</th>
				<th>${f.importe}</th>
				<th>${f.importe * ( 1 - f.bonificacion/100 )}</th>
			</tr>
		</c:forEach>
		<c:if test="${fn:length(facturasInpagas) == 0}">
			<tr>
		   		<th colspan="6" class="success">Ud no posee facturas pendientes de abonar</th>
			</tr>
		</c:if>
	</tbody>
</table>
