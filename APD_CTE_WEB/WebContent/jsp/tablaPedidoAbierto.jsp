<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table id="tablaPedidos" class="table table-striped table-responsive table-hover">
	<thead>
		<tr>
			<th>ID</th>
			<th>Estado</th>
			<th>Importe</th>
		</tr>
	</thead>
	<tbody>
		<c:if test="${not empty pedidoAbierto}">
			<tr>
				<th>${pedidoAbierto.id}</th>
				<th>${pedidoAbierto.estado}</th>
				<th>${pedidoAbierto.total}</th>
			</tr>
		</c:if>
		
		<c:if test="${empty pedidoAbierto}">
			<tr>
		   		<th colspan="3">Ud no posee un pedido abierto en este momento.</th>
			</tr>
		</c:if>
	</tbody>
</table>
